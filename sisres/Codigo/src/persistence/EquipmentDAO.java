package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Equipment;
import exception.PatrimonyException;

public class EquipmentDAO {

    // Mensagens
    private static final String EQUIPAMENTO_JA_EXISTENTE = "Equipamento ja cadastrado.";
    private static final String EQUIPAMENTO_NAO_EXISTENTE = "Equipamento nao cadastrado.";
    private static final String EQUIPAMENTO_NULO = "Equipamento esta nulo.";
    private static final String EQUIPAMENTO_EM_USO = "Equipamento esta sendo utilizado em uma reserva.";
    private static final String CODIGO_JA_EXISTENTE = "Equipamento com o mesmo codigo ja cadastrado.";

    // Singleton
    private static EquipmentDAO instance;

    private EquipmentDAO() {
    }

    public static EquipmentDAO getNewEquipment() {
        if (instance == null)
            instance = new EquipmentDAO();
        return instance;
    }

    //

    public void include(Equipment equipamento) throws SQLException, PatrimonyException {
        if (equipamento == null)
            throw new PatrimonyException(EQUIPAMENTO_NULO);
        else if (this.inDBCodigo(equipamento.getIdEquipment()))
            throw new PatrimonyException(CODIGO_JA_EXISTENTE);
        else if (!this.inDB(equipamento)) {
            this.updateQuery("INSERT INTO " + "equipamento (codigo, descricao) VALUES (" + "\"" + equipamento.getIdEquipment() + "\", "
                    + "\"" + equipamento.getDescriptionEquipment() + "\");");
        }
    }

    public void update(Equipment old_equipamento, Equipment new_equipamento) throws SQLException, PatrimonyException {
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
        else if (!new_equipamento.getIdEquipment().equals(old_equipamento.getIdEquipment()) && this.inDBCodigo(new_equipamento.getIdEquipment()))
            throw new PatrimonyException(CODIGO_JA_EXISTENTE);
        else if (!this.inDB(new_equipamento)) {
            String msg = "UPDATE equipamento SET " + "codigo = \"" + new_equipamento.getIdEquipment() + "\", " + "descricao = \""
                    + new_equipamento.getDescriptionEquipment() + "\"" + " WHERE " + "equipamento.codigo = \"" + old_equipamento.getIdEquipment()
                    + "\" and " + "equipamento.descricao = \"" + old_equipamento.getDescriptionEquipment() + "\";";

            con.setAutoCommit(false);
            pst = con.prepareStatement(msg);
            pst.executeUpdate();
            con.commit();

            pst.close();

        } else
            throw new PatrimonyException(EQUIPAMENTO_JA_EXISTENTE);
        con.close();
    }

    public void delete(Equipment equipamento) throws SQLException, PatrimonyException {
        if (equipamento == null)
            throw new PatrimonyException(EQUIPAMENTO_NULO);
        else if (this.inOtherDB(equipamento))
            throw new PatrimonyException(EQUIPAMENTO_EM_USO);
        if (this.inDB(equipamento)) {
            this.updateQuery("DELETE FROM equipamento WHERE " + "equipamento.codigo = \"" + equipamento.getIdEquipment() + "\" and "
                    + "equipamento.descricao = \"" + equipamento.getDescriptionEquipment() + "\";");
        } else
            throw new PatrimonyException(EQUIPAMENTO_NAO_EXISTENTE);
    }

    public Vector<Equipment> searchAll() throws SQLException, PatrimonyException {
        return this.buscar("SELECT * FROM equipamento;");
    }

    public Vector<Equipment> buscarPorCodigo(String valor) throws SQLException, PatrimonyException {
        return this.buscar("SELECT * FROM equipamento WHERE codigo = " + "\"" + valor + "\";");
    }

    public Vector<Equipment> buscarPorDescricao(String valor) throws SQLException, PatrimonyException {
        return this.buscar("SELECT * FROM equipamento WHERE descricao = " + "\"" + valor + "\";");
    }

    /**
     * Metodos Privados
     * */

    private Vector<Equipment> buscar(String query) throws SQLException, PatrimonyException {
        Vector<Equipment> vet = new Vector<Equipment>();

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

    private boolean inDB(Equipment e) throws SQLException, PatrimonyException {
        return this.inDBGeneric("SELECT * FROM equipamento WHERE " + "equipamento.codigo = \"" + e.getIdEquipment() + "\" and "
                + "equipamento.descricao = \"" + e.getDescriptionEquipment() + "\";");
    }

    private boolean inDBCodigo(String codigo) throws SQLException {
        return this.inDBGeneric("SELECT * FROM equipamento WHERE " + "codigo = \"" + codigo + "\";");
    }

    private boolean inOtherDB(Equipment e) throws SQLException {
        return this.inDBGeneric("SELECT * FROM reserva_equipamento WHERE "
                + "id_equipamento = (SELECT id_equipamento FROM equipamento WHERE " + "equipamento.codigo = \"" + e.getIdEquipment()
                + "\" and " + "equipamento.descricao = \"" + e.getDescriptionEquipment() + "\");");
    }

    private Equipment fetchEquipamento(ResultSet rs) throws PatrimonyException, SQLException {
        return new Equipment(rs.getString("codigo"), rs.getString("descricao"));
    }

    private void updateQuery(String msg) throws SQLException {
        Connection con = FactoryConnection.getInstance().getConnection();
        PreparedStatement pst = con.prepareStatement(msg);
        pst.executeUpdate();
        pst.close();
        con.close();
    }

}
