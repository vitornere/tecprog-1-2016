package control;

import java.sql.SQLException;
import java.util.Vector;
import java.util.zip.DataFormatException;

import model.Equipamento;
import model.Professor;
import model.ReservaEquipamentoProfessor;
import persistence.EquipamentReservationForTeacherDAO;
import exception.ClienteException;
import exception.PatrimonyException;
import exception.ReservaException;

public class ManterResEquipamentoProfessor {
    private Vector<Object> rev_equipamento_professor_vet = new Vector<Object>();

    // Singleton
    private static ManterResEquipamentoProfessor instance;

    private ManterResEquipamentoProfessor() {
    }

    public static ManterResEquipamentoProfessor getInstance() {
        if (instance == null)
            instance = new ManterResEquipamentoProfessor();
        return instance;
    }

    //

<<<<<<< HEAD:sisres/src/control/ManterResEquipamentoProfessor.java
    public Vector<ReservaEquipamentoProfessor> getReservasHora(String hora) throws SQLException, PatrimonioException,
            ClienteException, ReservaException, DataFormatException {
        return ResEquipamentoProfessorDAO.getInstance().buscarPorHora(hora);

    }

    public Vector<ReservaEquipamentoProfessor> getReservasMes(int mes) throws SQLException, PatrimonioException, ClienteException,
            ReservaException, DataFormatException {
        return ResEquipamentoProfessorDAO.getInstance().buscarPorMes(mes);
    }

    public Vector<Object> getResEquipamentoProfessor_vet() throws SQLException, ClienteException, PatrimonioException,
            ReservaException, DataFormatException {
        this.rev_equipamento_professor_vet = ResEquipamentoProfessorDAO.getInstance().buscarTodos();
=======
    public Vector<ReservaEquipamentoProfessor> getReservasHora(String hora) throws SQLException, PatrimonyException,
            ClienteException, ReservaException {
        return EquipamentReservationForTeacherDAO.getInstance().buscarPorHora(hora);

    }

    public Vector<ReservaEquipamentoProfessor> getReservasMes(int mes) throws SQLException, PatrimonyException, ClienteException,
            ReservaException {
        return EquipamentReservationForTeacherDAO.getInstance().buscarPorMes(mes);
    }

    public Vector<Object> getResEquipamentoProfessor_vet() throws SQLException, ClienteException, PatrimonyException,
            ReservaException {
        this.rev_equipamento_professor_vet = EquipamentReservationForTeacherDAO.getInstance().buscarTodos();
>>>>>>> devel:sisres/src/control/ManterResEquipamentoProfessor.java
        return this.rev_equipamento_professor_vet;
    }

    public void inserir(Equipamento equipamento, Professor prof, String data, String hora) throws SQLException, ReservaException, DataFormatException {
        ReservaEquipamentoProfessor reserva = new ReservaEquipamentoProfessor(data, hora, equipamento, prof);
        EquipamentReservationForTeacherDAO.getInstance().incluir(reserva);
        this.rev_equipamento_professor_vet.add(reserva);
    }

    public void alterar(String finalidade, ReservaEquipamentoProfessor reserva) throws SQLException, ReservaException, DataFormatException {

        ReservaEquipamentoProfessor reserva_old = new ReservaEquipamentoProfessor(reserva.getData(), reserva.getHora(),
                reserva.getEquipamento(), reserva.getProfessor());
        EquipamentReservationForTeacherDAO.getInstance().alterar(reserva_old, reserva);

    }

    public void excluir(ReservaEquipamentoProfessor reserva) throws SQLException, ReservaException {
        EquipamentReservationForTeacherDAO.getInstance().excluir(reserva);
        this.rev_equipamento_professor_vet.remove(reserva);
    }
}
