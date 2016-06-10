package user_stories;

import java.awt.Dimension;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Student;
import model.Professor;
<<<<<<< HEAD
import model.ReserveClassroomForStudent;
import model.ReserveClassroomForProfessor;
=======
import model.ReservaSalaAluno;
import model.ReservaSalaProfessor;
>>>>>>> devel
import model.Classroom;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.StudentDAO;
import persistence.ProfessorDAO;
<<<<<<< HEAD
import persistence.ReserveClassroomForStudentDAO;
import persistence.ReserveClassroomForProfessorDAO;
import persistence.ClassroomDAO;
import view.Main2;
import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;
=======
import persistence.ResSalaAlunoDAO;
import persistence.ReserveClassroomProfessorDAO;
import persistence.ClassroomDAO;
import view.Main2;
import exception.ClienteException;
import exception.PatrimonyException;
import exception.ReservaException;
>>>>>>> devel

public class US01_AlterarReservaSala {
    private FrameFixture window;
    private Robot robot;
    private Classroom sala;
<<<<<<< HEAD
    private ReserveClassroomForProfessor reservaProf;
    private ReserveClassroomForStudent reservaAluno;
    private Student aluno;
=======
    private ReservaSalaProfessor reservaProf;
    private ReservaSalaAluno reservaAluno;
    private Aluno aluno;
>>>>>>> devel
    private Professor prof;
    private DialogFixture dialog;
    private int index;
    private int indexReserva;
    private String data;

    
    private void dataAtual() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        this.data = formatador.format(date);
    }

<<<<<<< HEAD
    @Before public void setUp() throws PatrimonyException, SQLException, ClientException, ReserveException {
=======
    @Before public void setUp() throws PatrimonyException, SQLException, ClienteException, ReservaException {
>>>>>>> devel
        robot = BasicRobot.robotWithNewAwtHierarchy();
        robot.settings().delayBetweenEvents(5);

        window = new FrameFixture(robot, new Main2());
        window.show(new Dimension(900, 500)); // shows the frame to test

        sala = new Classroom("code", "Sala para testes de aceitacao", "123");
<<<<<<< HEAD
        ClassroomDAO.getClassroom().include(sala);
=======
        ClassroomDAO.getInstance().add(sala);
>>>>>>> devel

        prof = new Professor("Professor Teste", "658.535.144-40", "110038096", "9211-2144", "teste incluir repetido");
        ProfessorDAO.getNewProfessor().include(prof);

        aluno = new Student("Aluno Teste", "658.535.144-40", "110038096", "9211-2144", "teste incluir repetido");
        StudentDAO.getNewStudent().include(aluno);

        dataAtual();
                
<<<<<<< HEAD
        index = ClassroomDAO.getClassroom().searchAll().size() - 1;
        indexReserva = ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().searchForDate(data).size() - 1;
=======
        index = ClassroomDAO.getInstance().searchAll().size() - 1;
        indexReserva = ReserveClassroomProfessorDAO.getInstance().buscarPorData(data).size() - 1;
>>>>>>> devel

        StudentDAO.getNewStudent().include(aluno);

        reservaAluno = new ReserveClassroomForStudent(data, "23:59", sala, "abc", "100", aluno);
        ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reservaAluno);

        window.button("Sala").click();
        dialog = window.dialog("SalaView");
    }

<<<<<<< HEAD
    @After public void tearDown() throws SQLException, PatrimonyException, ClientException, ReserveException {
        if (reservaProf != null)
            ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().delete(reservaProf);
=======
    @After public void tearDown() throws SQLException, PatrimonyException, ClienteException, ReservaException {
        if (reservaProf != null)
            ReserveClassroomProfessorDAO.getInstance().delete(reservaProf);
>>>>>>> devel
        if (reservaAluno != null)
            ReserveClassroomForStudentDAO.getReserveClassroomForStudent().delete(reservaAluno);
        if (sala != null)
<<<<<<< HEAD
            ClassroomDAO.getClassroom().delete(sala);
=======
            ClassroomDAO.getInstance().delete(sala);
>>>>>>> devel
        if (aluno != null)
            StudentDAO.getNewStudent().delete(aluno);
        if (prof != null)
            ProfessorDAO.getNewProfessor().delete(prof);
        window.cleanUp();
    }

    public void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
<<<<<<< HEAD
    @Test public void testCenario2AlunoCadeirasIndisponiveis() throws SQLException, ClientException, PatrimonyException, ReserveException {
=======
    @Test public void testCenario2AlunoCadeirasIndisponiveis() throws SQLException, ClienteException, PatrimonyException, ReservaException {
>>>>>>> devel
        
        dialog.table("tabelaPatrimonio").selectRows(index);
        dialog.button("Visualizar Horarios").click();

        DialogFixture diaReservaSala = dialog.dialog("DiaReservaSala");
        diaReservaSala.button("VisualizarButton").click();

        DialogFixture horarioReservaSala = dialog.dialog("HorarioReservaSala");
        horarioReservaSala.button("ReservarButton").click();

        DialogFixture fazerReservaSalaView = dialog.dialog("FazerReservaSalaView");
        fazerReservaSalaView.radioButton("alunoRadioButton").click();
        fazerReservaSalaView.textBox("CPF").enterText("658.535.144-40");
        fazerReservaSalaView.button("BuscarCpfButton").click();
        fazerReservaSalaView.textBox("Finalidade").enterText("aula");
        fazerReservaSalaView.textBox("Hora").enterText("00:00");
        fazerReservaSalaView.button("VerificarCadeirasButton").click();
        fazerReservaSalaView.textBox("Quantidade de Cadeiras Reservadas").enterText("1234");
        fazerReservaSalaView.button("Reservar").click();

        fazerReservaSalaView.optionPane().requireMessage("A sala nao possui este numero de cadeiras para reservar.");
        fazerReservaSalaView.optionPane().okButton().click();

    }


}
