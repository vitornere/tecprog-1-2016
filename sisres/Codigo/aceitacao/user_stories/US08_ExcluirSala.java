package user_stories;

import java.awt.Dimension;
import java.sql.SQLException;

import model.Student;
import model.Classroom;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.StudentDAO;
import persistence.SalaDAO;
import view.Main2;
import exception.ClientException;
import exception.PatrimonyException;

/**
 * US8 T�tulo: Excluir sala. Como usu�rio Eu quero excluir uma sala Para que a
 * mesma seja indisponibilizada para reserva.
 * 
 * Cen�rio 1: Existe sala cadastrada. Dado que a sala est� cadastrada; Quando o
 * usu�rio solicita a exclus�o; Ent�o o sistema deve eliminar os registros da
 * sala, E informar o sucesso da exclus�o.
 * 
 * Cen�rio 2: N�o existe sala cadastrada. Dado que n�o existe o registro da
 * sala; Quando o usu�rio solicita exclus�o; Ent�o o sistema n�o exclui nenhum
 * registro de sala, E informa que n�o h� o registro.
 */

public class US08_ExcluirSala {

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
        SalaDAO.getInstance().incluir(sala);

        index = SalaDAO.getInstance().buscarTodos().size() - 1;

        window.button("Sala").click();
        dialog = window.dialog("SalaView");

    }

    @After public void tearDown() throws SQLException, PatrimonyException {
        if (sala != null)
            SalaDAO.getInstance().excluir(sala);
        window.cleanUp();
    }

    public void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testCenario1() throws SQLException, ClientException{
        dialog.table("tabelaPatrimonio").selectRows(index);
        dialog.button("Excluir").click();
        dialog.optionPane().requireMessage("Deseja mesmo excluir Sala: " + sala.getDescriptionEquipment() + "?");
        sleep();
        dialog.optionPane().yesButton().click();
        sleep();
        dialog.optionPane().requireMessage("Sala excluida com sucesso");
        
        sala = null;
    }
    
    @Test
    public void testCenario2() throws SQLException, ClientException{
        
        dialog.button("Excluir").click();
        dialog.optionPane().requireMessage("Selecione uma linha!");
        sleep();
        dialog.optionPane().okButton().click();
    }
    
   }
