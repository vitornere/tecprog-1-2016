package user_stories;

import java.awt.Dimension;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Student;
import model.Professor;
import model.ReserveClassroomForStudent;
import model.ReserveClassroomForProfessor;
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
import persistence.ReserveClassroomForStudentDAO;
import persistence.ReserveClassroomForProfessorDAO;
import persistence.ClassroomDAO;
import view.Main2;
import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;

public class US01_AlterarReservaSala {
    private FrameFixture window;
    private Robot robot;
    private Classroom sala;
    private ReserveClassroomForProfessor reservaProf;
    private ReserveClassroomForStudent reservaAluno;
    private Student aluno;
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

    @Before public void setUp() throws PatrimonyException, SQLException, ClientException, ReserveException {
        robot = BasicRobot.robotWithNewAwtHierarchy();
        robot.settings().delayBetweenEvents(5);

        window = new FrameFixture(robot, new Main2());
        window.show(new Dimension(900, 500)); // shows the frame to test

        sala = new Classroom("code", "Sala para testes de aceitacao", "123");
        ClassroomDAO.getClassroom().include(sala);

        prof = new Professor("Professor Teste", "658.535.144-40", "110038096", "9211-2144", "teste incluir repetido");
        ProfessorDAO.getNewProfessor().include(prof);

        aluno = new Student("Aluno Teste", "658.535.144-40", "110038096", "9211-2144", "teste incluir repetido");
        StudentDAO.getNewStudent().include(aluno);

        dataAtual();
                
        index = ClassroomDAO.getClassroom().searchAll().size() - 1;
        indexReserva = ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().searchForDate(data).size() - 1;

        StudentDAO.getNewStudent().include(aluno);

        reservaAluno = new ReserveClassroomForStudent(data, "23:59", sala, "abc", "100", aluno);
        ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reservaAluno);

        window.button("Sala").click();
        dialog = window.dialog("SalaView");
    }

    @After public void tearDown() throws SQLException, PatrimonyException, ClientException, ReserveException {
        if (reservaProf != null)
            ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().delete(reservaProf);
        if (reservaAluno != null)
            ReserveClassroomForStudentDAO.getReserveClassroomForStudent().delete(reservaAluno);
        if (sala != null)
            ClassroomDAO.getClassroom().delete(sala);
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
    @Test public void testCenario2AlunoCadeirasIndisponiveis() throws SQLException, ClientException, PatrimonyException, ReserveException {
        
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
