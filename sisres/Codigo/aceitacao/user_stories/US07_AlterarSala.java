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
 * US7 T�tulo: Alterar sala. Como usu�rio Eu quero alterar dados de salas Para
 * que haja confiabilidade nos dados contidos no sistema.
 * 
 * Cen�rio 1: Existe sala cadastrada e dados novos s�o v�lidos. Dado que a sala
 * est� cadastrada; Quando o usu�rio edita algum campo E todos os dados s�o
 * v�lidos, E solicita altera��o; Ent�o o sistema deve alterar os registros da
 * sala. E informar o sucesso da altera��o.
 * 
 * Cen�rio 2: N�o existe sala cadastrada. Dado que n�o existe o registro da
 * sala; Quando o usu�rio solicita altera��o; Ent�o o sistema informa que n�o h�
 * o registro.
 * 
 * Cen�rio 3: Existe sala cadastrada e dados novos n�o s�o v�lidos. Dado que a
 * sala est� cadastrada; Quando o usu�rio edita algum campo E algum dado n�o �
 * v�lido, E solicita altera��o; Ent�o o sistema deve exibir a seguinte
 * mensagem: �O campo [campo] � inv�lido�, E n�o realizar altera��o.
 */

public class US07_AlterarSala {
    private FrameFixture window;
    private Robot robot;
    private Classroom sala;
    private DialogFixture dialog;
    private int index;

    @Before public void setUp() throws PatrimonyException, SQLException {
        robot = BasicRobot.robotWithNewAwtHierarchy();
        robot.settings().delayBetweenEvents(5);

        window = new FrameFixture(robot, new Main2());
        window.show(new Dimension(900, 500)); // shows the frame to test

        sala = new Classroom("code", "Sala para testes de aceitacao", "123");
        ClassroomDAO.getClassroom().include(sala);

        index = ClassroomDAO.getClassroom().searchAll().size() - 1;

        window.button("Sala").click();
        dialog = window.dialog("SalaView");

    }

    @After public void tearDown() throws SQLException, PatrimonyException {
        if (sala != null)
            ClassroomDAO.getClassroom().delete(sala);
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
        dialog.table("tabelaPatrimonio").selectRows(index);
        dialog.button("Alterar").click();
        DialogFixture cadastro = dialog.dialog("AlterarSala");
        cadastro.button("Cancelar").click();
    }

    @Test public void testCenario1() throws SQLException, PatrimonyException {

        dialog.table("tabelaPatrimonio").selectRows(index);

        dialog.button("Alterar").click();
        DialogFixture cadastro = dialog.dialog("AlterarSala");

        cadastro.textBox("Capacidade").setText("1234");

        cadastro.button("Alterar").click();
        cadastro.optionPane().requireMessage("Sala Alterada com sucesso");
        sleep();
        cadastro.optionPane().okButton().click();

        sala = ClassroomDAO.getClassroom().searchAll().get(index);
    }

    @Test public void testCenario2() throws SQLException, PatrimonyException {

        if (sala != null)
            ClassroomDAO.getClassroom().delete(sala);
        sala = null;
        dialog.button("Alterar").click();
        dialog.optionPane().requireMessage("Selecione uma linha!");
        sleep();

    }

    @Test public void testCenario3CapacidadeInvalida() throws SQLException, PatrimonyException {

        dialog.table("tabelaPatrimonio").selectRows(index);
        dialog.button("Alterar").click();
        DialogFixture cadastro = dialog.dialog("AlterarSala");

        cadastro.textBox("Capacidade").setText("abc");
        cadastro.textBox("Codigo").setText("code");
        cadastro.textBox("Descricao").setText("Sala para testes de aceitacao");

        cadastro.button("Alterar").click();
        cadastro.optionPane().requireMessage("Capacidade Invalida.");
        sleep();
        cadastro.optionPane().okButton().click();
        sala = ClassroomDAO.getClassroom().searchAll().get(index);

    }

    @Test public void testCenario3CapacidadeBranco() throws SQLException, PatrimonyException {

        dialog.table("tabelaPatrimonio").selectRows(index);
        dialog.button("Alterar").click();
        DialogFixture cadastro = dialog.dialog("AlterarSala");

        cadastro.textBox("Capacidade").setText("");
        cadastro.textBox("Codigo").setText("code");
        cadastro.textBox("Descricao").setText("Sala para testes de aceitacao");

        cadastro.button("Alterar").click();
        cadastro.optionPane().requireMessage("Capacidade em Branco.");
        sleep();
        cadastro.optionPane().okButton().click();
        sala = ClassroomDAO.getClassroom().searchAll().get(index);
    }

    @Test public void testCenario3CodigoBranco() throws SQLException, PatrimonyException {

        dialog.table("tabelaPatrimonio").selectRows(index);
        dialog.button("Alterar").click();
        DialogFixture cadastro = dialog.dialog("AlterarSala");

        cadastro.textBox("Capacidade").setText("123");
        cadastro.textBox("Codigo").setText("");
        cadastro.textBox("Descricao").setText("Sala para testes de aceitacao");

        cadastro.button("Alterar").click();
        cadastro.optionPane().requireMessage("Codigo em Branco.");
        sleep();
        cadastro.optionPane().okButton().click();
        sala = ClassroomDAO.getClassroom().searchAll().get(index);
    }

    @Test public void testCenario3DescricaoBranco() throws SQLException, PatrimonyException {

        dialog.table("tabelaPatrimonio").selectRows(index);
        dialog.button("Alterar").click();
        DialogFixture cadastro = dialog.dialog("AlterarSala");

        cadastro.textBox("Capacidade").setText("123");
        cadastro.textBox("Codigo").setText("code");
        cadastro.textBox("Descricao").setText("");

        cadastro.button("Alterar").click();
        cadastro.optionPane().requireMessage("Descricao em Branco.");
        sleep();
        cadastro.optionPane().okButton().click();
        sala = ClassroomDAO.getClassroom().searchAll().get(index);
    }

}
