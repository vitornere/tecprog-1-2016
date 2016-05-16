package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Equipamento;
import exception.PatrimonyException;

public class EquipamentoDAO {

    // Mensagens
    private static final String EQUIPAMENTO_JA_EXISTENTE = "Equipamento ja cadastrado.";
    private static final String EQUIPAMENTO_NAO_EXISTENTE = "Equipamento nao cadastrado.";
    private static final String EQUIPAMENTO_NULO = "Equipamento esta nulo.";
    private static final String EQUIPAMENTO_EM_USO = "Equipamento esta sendo utilizado em uma reserva.";
    private static final String CODIGO_JA_EXISTENTE = "Equipamento com o mesmo codigo ja cadastrado.";

    // Singleton
    private static EquipamentoDAO instance;

    private EquipamentoDAO() {
    }

    public static EquipamentoDAO getInstance() {
        if (instance == null)
            instance = new EquipamentoDAO();
        return instance;
    }

    //

    public void incluir(Equipamento equipamento) throws SQLException, PatrimonyException {
        if (equipamento == null)
            throw new PatrimonyException(EQUIPAMENTO_NULO);
        else if (this.inDBCodigo(equipamento.getCode()))
            throw new PatrimonyException(CODIGO_JA_EXISTENTE);
        else if (!this.inDB(equipamento)) {
            this.updateQuery("INSERT INTO " + "equipamento (codigo, descricao) VALUES (" + "\"" + equipamento.getCode() + "\", "
                    + "\"" + equipamento.getDescription() + "\");");
        }
    }

    public void alterar(Equipamento old_equipamento, Equipamento new_equipamento) throws SQLException, PatrimonyException {
        if (old_equipamento == null)
            throw new PatrimonyException(EQUIPAMENTO_NULO);
        if (new_equipamento == null)
            throw new PatrimonyException(EQUIPAMENTO_NULO);

        Connection con = FactoryConnection.getInstance().getConnection();
        PreparedStatement pst;

        if (!this.inDB(old_equipamento))
            throw new PatrimonyException(EQUIPAMENTO_NAO_EXISTENTE);
        else if (this.inOtherDB(old_equipamento))
            throw new PatrimonyException(EQUIPAMENTO_EM_USO);
        else if (!new_equipamento.getCode().equals(old_equipamento.getCode()) && this.inDBCodigo(new_equipamento.getCode()))
            throw new PatrimonyException(CODIGO_JA_EXISTENTE);
        else if (!this.inDB(new_equipamento)) {
            String msg = "UPDATE equipamento SET " + "codigo = \"" + new_equipamento.getCode() + "\", " + "descricao = \""
                    + new_equipamento.getDescription() + "\"" + " WHERE " + "equipamento.codigo = \"" + old_equipamento.getCode()
                    + "\" and " + "equipamento.descricao = \"" + old_equipamento.getDescription() + "\";";

            con.setAutoCommit(false);
            pst = con.prepareStatement(msg);
            pst.executeUpdate();
            con.commit();

            pst.close();

        } else
            throw new PatrimonyException(EQUIPAMENTO_JA_EXISTENTE);
        con.close();
    }

    public void excluir(Equipamento equipamento) throws SQLException, PatrimonyException {
        if (equipamento == null)
            throw new PatrimonyException(EQUIPAMENTO_NULO);
        else if (this.inOtherDB(equipamento))
            throw new PatrimonyException(EQUIPAMENTO_EM_USO);
        if (this.inDB(equipamento)) {
            this.updateQuery("DELETE FROM equipamento WHERE " + "equipamento.codigo = \"" + equipamento.getCode() + "\" and "
                    + "equipamento.descricao = \"" + equipamento.getDescription() + "\";");
        } else
            throw new PatrimonyException(EQUIPAMENTO_NAO_EXISTENTE);
    }

    public Vector<Equipamento> buscarTodos() throws SQLException, PatrimonyException {
        return this.buscar("SELECT * FROM equipamento;");
    }

    public Vector<Equipamento> buscarPorCodigo(String valor) throws SQLException, PatrimonyException {
        return this.buscar("SELECT * FROM equipamento WHERE codigo = " + "\"" + valor + "\";");
    }

    public Vector<Equipamento> buscarPorDescricao(String valor) throws SQLException, PatrimonyException {
        return this.buscar("SELECT * FROM equipamento WHERE descricao = " + "\"" + valor + "\";");
    }

    /**
     * Metodos Privados
     * */

    private Vector<Equipamento> buscar(String query) throws SQLException, PatrimonyException {
        Vector<Equipamento> vet = new Vector<Equipamento>();

        Connection con = FactoryConnection.getInstance().getConnection();

        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        while (rs.next())
            vet.add(this.fetchEquipamento(rs));

        pst.close();
        rs.close();
        con.close();
        return vet;
    }

    private boolean inDBGeneric(String query) throws SQLException {
        Connection con = FactoryConnection.getInstance().getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        if (!rs.next()) {
            rs.close();
            pst.close();
            con.close();
            return false;
        } else {
            rs.close();
            pst.close();
            con.close();
            return true;
        }
    }

    private boolean inDB(Equipamento e) throws SQLException, PatrimonyException {
        return this.inDBGeneric("SELECT * FROM equipamento WHERE " + "equipamento.codigo = \"" + e.getCode() + "\" and "
                + "equipamento.descricao = \"" + e.getDescription() + "\";");
    }

    private boolean inDBCodigo(String codigo) throws SQLException {
        return this.inDBGeneric("SELECT * FROM equipamento WHERE " + "codigo = \"" + codigo + "\";");
    }

    private boolean inOtherDB(Equipamento e) throws SQLException {
        return this.inDBGeneric("SELECT * FROM reserva_equipamento WHERE "
                + "id_equipamento = (SELECT id_equipamento FROM equipamento WHERE " + "equipamento.codigo = \"" + e.getCode()
                + "\" and " + "equipamento.descricao = \"" + e.getDescription() + "\");");
    }

    private Equipamento fetchEquipamento(ResultSet rs) throws PatrimonyException, SQLException {
        return new Equipamento(rs.getString("codigo"), rs.getString("descricao"));
    }

    private void updateQuery(String msg) throws SQLException {
        Connection con = FactoryConnection.getInstance().getConnection();
        PreparedStatement pst = con.prepareStatement(msg);
        pst.executeUpdate();
        pst.close();
        con.close();
    }

}
