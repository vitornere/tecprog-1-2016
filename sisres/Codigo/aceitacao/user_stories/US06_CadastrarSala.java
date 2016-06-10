package user_stories;

import java.awt.Dimension;
import java.sql.SQLException;

import model.Classroom;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.ClassroomDAO;
import view.Main2;
import exception.PatrimonyException;

/**
 * US6 T�tulo: Cadastrar sala. Como usu�rio Eu quero cadastrar salas Para que
 * haja possibilidade de reserva quando dispon�vel.
 * 
 * Cen�rio 1: N�o h� cadastro e dados inseridos s�o v�lidos. Dado que n�o h�
 * cadastro da sala, E os dados inseridos todos s�o v�lidos, Quando o usu�rio
 * solicitar o cadastro da sala, Ent�o o sistema deve registrar o novo cadastro,
 * E informar o sucesso da opera��o.
 * 
 * Cen�rio 2: H� cadastro e dados inseridos s�o v�lidos. Dado que h� o cadastro
 * da sala, E os dados do novo cadastro s�o v�lidos, Quando o usu�rio solicitar
 * o cadastro da sala, Ent�o o sistema deve informar que a sala j� est�
 * cadastrada, E n�o deve registrar um novo cadastro.
 * 
 * Cen�rio 3: N�o h� cadastro e dados inseridos s�o inv�lidos. Dado que n�o h� o
 * cadastro da sala, E os dados do novo cadastro s�o inv�lidos, Quando o usu�rio
 * solicitar o cadastro da sala, Ent�o o sistema deve exibir a seguinte
 * mensagem: �O campo [campo] � inv�lido�, E n�o deve registrar um novo
 * cadastro.
 */

public class US06_CadastrarSala {

    private FrameFixture window;
    private Robot robot;
    private Classroom sala;
    private DialogFixture dialog;
    private int index;

    @Before public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();
        robot.settings().delayBetweenEvents(5);

        window = new FrameFixture(robot, new Main2());
        window.show(new Dimension(900, 500)); // shows the frame to test
        window.button("Sala").click();
        dialog = window.dialog("SalaView");
    }

    @After public void tearDown() throws SQLException, PatrimonyException {
        if (sala != null)
<<<<<<< HEAD
            ClassroomDAO.getClassroom().delete(sala);
=======
            ClassroomDAO.getInstance().delete(sala);
>>>>>>> devel
        window.cleanUp();
    }

    public void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test public void testCancelar() {
        dialog.button("Cadastrar").click();
        DialogFixture cadastro = dialog.dialog("CadastroSala");
        cadastro.button("Cancelar").click();
    }

    @Test public void testCenario1() throws SQLException, PatrimonyException {
        dialog.button("Cadastrar").click();
        DialogFixture cadastro = dialog.dialog("CadastroSala");

        cadastro.textBox("Capacidade").enterText("123");
        cadastro.textBox("Codigo").enterText("code");
        cadastro.textBox("Descricao").enterText("Sala para testes de aceitacao");

        cadastro.button("Cadastrar").click();
        cadastro.optionPane().requireMessage("Sala Cadastrada com sucesso");
        sleep();
        cadastro.optionPane().okButton().click();

<<<<<<< HEAD
        index = ClassroomDAO.getClassroom().searchAll().size() - 1;
        sala = ClassroomDAO.getClassroom().searchAll().get(index);
=======
        index = ClassroomDAO.getInstance().searchAll().size() - 1;
        sala = ClassroomDAO.getInstance().searchAll().get(index);
>>>>>>> devel
    }

    @Test public void testCenario2() throws SQLException, PatrimonyException {

        sala = new Classroom("code","Sala para testes de aceitacao","123");
<<<<<<< HEAD
        ClassroomDAO.getClassroom().include(sala);
=======
        ClassroomDAO.getInstance().add(sala);
>>>>>>> devel

        dialog.button("Cadastrar").click();
        DialogFixture cadastro = dialog.dialog("CadastroSala");

        cadastro.textBox("Capacidade").enterText("123");
        cadastro.textBox("Codigo").enterText("code");
        cadastro.textBox("Descricao").enterText("Sala para testes de aceitacao");

        cadastro.button("Cadastrar").click();
        cadastro.optionPane().requireMessage("Sala com o mesmo codigo ja cadastrada.");
        sleep();
        cadastro.optionPane().okButton().click();
    }

    @Test public void testCenario3CapacidadeInvalida() throws SQLException, PatrimonyException {

        dialog.button("Cadastrar").click();
        DialogFixture cadastro = dialog.dialog("CadastroSala");
        
        cadastro.textBox("Capacidade").enterText("abc");
        cadastro.textBox("Codigo").enterText("code");
        cadastro.textBox("Descricao").enterText("Sala para testes de aceitacao");

        cadastro.button("Cadastrar").click();
        cadastro.optionPane().requireMessage("Capacidade Invalida.");
        sleep();
        cadastro.optionPane().okButton().click();

    }
    
    @Test public void testCenario3CapacidadeBranco() throws SQLException, PatrimonyException {

        dialog.button("Cadastrar").click();
        DialogFixture cadastro = dialog.dialog("CadastroSala");

        cadastro.textBox("Capacidade").enterText("");
        cadastro.textBox("Codigo").enterText("code");
        cadastro.textBox("Descricao").enterText("Sala para testes de aceitacao");

        cadastro.button("Cadastrar").click();
        cadastro.optionPane().requireMessage("Capacidade em Branco.");
        sleep();
        cadastro.optionPane().okButton().click();
    }


    @Test public void testCenario3CodigoBranco() throws SQLException, PatrimonyException {

        dialog.button("Cadastrar").click();
        DialogFixture cadastro = dialog.dialog("CadastroSala");

        cadastro.textBox("Capacidade").enterText("123");
        cadastro.textBox("Codigo").enterText("");
        cadastro.textBox("Descricao").enterText("Sala para testes de aceitacao");

        cadastro.button("Cadastrar").click();
        cadastro.optionPane().requireMessage("Codigo em Branco.");
        sleep();
        cadastro.optionPane().okButton().click();
    }

    @Test public void testCenario3DescricaoBranco() throws SQLException, PatrimonyException {

        dialog.button("Cadastrar").click();
        DialogFixture cadastro = dialog.dialog("CadastroSala");

        cadastro.textBox("Capacidade").enterText("123");
        cadastro.textBox("Codigo").enterText("code");
        cadastro.textBox("Descricao").enterText("");

        cadastro.button("Cadastrar").click();
        cadastro.optionPane().requireMessage("Descricao em Branco.");
        sleep();
        cadastro.optionPane().okButton().click();
    }
    
}
