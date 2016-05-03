/*
 * File: EquipamentDAO.java
 * Description: Class to make transactions with the database to crud equipament
 * */

package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Equipamento;
import exception.PatrimonioException;

public class EquipamentDAO {

	// Mensagens
	private static final String EXISTENTEQUIPAMENT = "Equipamento ja cadastrado.";
	private static final String EQUIPAMENTNOTEXIST = "Equipamento nao cadastrado.";
	private static final String NULLEQUIPAMENT = "Equipamento esta nulo.";
	private static final String EQUIPAMENTINUSE = "Equipamento esta sendo utilizado em uma reserva.";
	private static final String EXISTENTCODE = "Equipamento com o mesmo codigo ja cadastrado.";

	// Singleton
	private static EquipamentDAO instance;

	private EquipamentDAO() {
	}

	public static EquipamentDAO getInstance() {
		if (instance == null) {
			instance = new EquipamentDAO();
		} 
		else {
			// Nothing to do
		}
		return instance;
	}

	//

	public void add(Equipamento equipament) throws SQLException,
			PatrimonioException {
		if (equipament == null) {
			throw new PatrimonioException(NULLEQUIPAMENT);
		}
		else {
			if (this.inDBcode(equipament.getCodigo())) {
				throw new PatrimonioException(EXISTENTCODE);
			} 
			else {
				if (!this.inDB(equipament)) {
					this.updateQuery("INSERT INTO "
							+ "equipamento (codigo, descricao) VALUES (" + "\""
							+ equipament.getCodigo() + "\", " + "\""
							+ equipament.getDescricao() + "\");");
				}
			}
		}
	}

	public void change(Equipamento old_equipamento, Equipamento new_equipamento)
			throws SQLException, PatrimonioException {
		if (old_equipamento == null) {
			throw new PatrimonioException(NULLEQUIPAMENT);
		}
		if (new_equipamento == null) {
			throw new PatrimonioException(NULLEQUIPAMENT);
		}

		Connection con = FactoryConnection.getInstance().getConnection();

		assert con != null;

		PreparedStatement pst;

		if (!this.inDB(old_equipamento)) {
			throw new PatrimonioException(EQUIPAMENTNOTEXIST);
		} 
		else {
			if (this.inOtherDB(old_equipamento)) {
				throw new PatrimonioException(EQUIPAMENTINUSE);
			} 
			else {
				if (!new_equipamento.getCodigo().equals(
						old_equipamento.getCodigo())
						&& this.inDBcode(new_equipamento.getCodigo())) {
					throw new PatrimonioException(EXISTENTCODE);
				} 
				else {
					if (!this.inDB(new_equipamento)) {
						String msg = "UPDATEequipament SET " + "codigo = \""
								+ new_equipamento.getCodigo() + "\", "
								+ "descricao = \""
								+ new_equipamento.getDescricao() + "\""
								+ " WHERE " + "equipamento.codigo = \""
								+ old_equipamento.getCodigo() + "\" and "
								+ "equipamento.descricao = \""
								+ old_equipamento.getDescricao() + "\";";

						con.setAutoCommit(false);
						pst = con.prepareStatement(msg);
						pst.executeUpdate();
						con.commit();

						pst.close();

					} 
					else {
						throw new PatrimonioException(EXISTENTEQUIPAMENT);
					}
				}
			}
		}

		con.close();
	}

	public void delete(Equipamento equipament) throws SQLException,
			PatrimonioException {
		if (equipament == null) {
			throw new PatrimonioException(NULLEQUIPAMENT);
		} 
		else {
			if (this.inOtherDB(equipament)) {
				throw new PatrimonioException(EQUIPAMENTINUSE);
			}
			if (this.inDB(equipament)) {
				this.updateQuery("DELETEFROM equipamento WHERE "
						+ "equipamento.codigo = \"" + equipament.getCodigo()
						+ "\" and " + "equipamento.descricao = \""
						+ equipament.getDescricao() + "\";");
			}
			else {
				throw new PatrimonioException(EQUIPAMENTNOTEXIST);
			}
		}
	}

	public Vector<Equipamento> searchAll() throws SQLException,
			PatrimonioException {
		return this.search("SELECT *FROM equipamento;");
	}

	public Vector<Equipamento> searchByCode(String code) throws SQLException,
			PatrimonioException {
		return this.search("SELECT *FROM equipamento WHERE codigo = " + "\""
				+ code + "\";");
	}

	public Vector<Equipamento> searchByDescription(String description)
			throws SQLException, PatrimonioException {
		return this.search("SELECT *FROM equipamento WHERE descricao = " + "\""
				+ description + "\";");
	}

	/**
	 * Metodos Privados
	 * */

	private Vector<Equipamento> search(String query) throws SQLException,
			PatrimonioException {
		Vector<Equipamento> vet = new Vector<Equipamento>();

		Connection con = FactoryConnection.getInstance().getConnection();

		assert con != null;
		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			vet.add(this.fetchEquipamento(rs));
		}

		pst.close();
		rs.close();
		con.close();
		return vet;
	}

	private boolean inDBGeneric(String query) throws SQLException {
		Connection con = FactoryConnection.getInstance().getConnection();

		assert con != null;

		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();

		if (!rs.next()) {
			rs.close();
			pst.close();
			con.close();
			return false;
		}
		else {
			rs.close();
			pst.close();
			con.close();
			return true;
		}
	}

	private boolean inDB(Equipamento equipament) throws SQLException,
			PatrimonioException {
		return this.inDBGeneric("SELECT *FROM equipamento WHERE "
				+ "equipamento.codigo = \"" + equipament.getCodigo() + "\" and "
				+ "equipamento.descricao = \"" + equipament.getDescricao() + "\";");
	}

	private boolean inDBcode(String code) throws SQLException {
		return this.inDBGeneric("SELECT *FROM equipamento WHERE "
				+ "codigo = \"" + code + "\";");
	}

	private boolean inOtherDB(Equipamento equipament) throws SQLException {
		return this
				.inDBGeneric("SELECT * FROM reserva_equipamento WHERE "
						+ "id_equipamento = (SELECT id_equipamentoFROM equipamento WHERE "
						+ "equipamento.codigo = \"" + equipament.getCodigo() + "\" and "
						+ "equipamento.descricao = \"" + equipament.getDescricao()
						+ "\");");
	}

	private Equipamento fetchEquipamento(ResultSet equipament_data)
			throws PatrimonioException, SQLException {
		return new Equipamento(equipament_data.getString("codigo"),
				equipament_data.getString("descricao"));
	}

	private void updateQuery(String messenge) throws SQLException {
		Connection con = FactoryConnection.getInstance().getConnection();

		assert con != null;
		PreparedStatement pst = con.prepareStatement(messenge);
		pst.executeUpdate();
		pst.close();
		con.close();
	}

}
