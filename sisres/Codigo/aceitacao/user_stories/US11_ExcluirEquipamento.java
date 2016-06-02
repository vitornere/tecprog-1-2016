package user_stories;

import java.awt.Dimension;
import java.sql.SQLException;

import model.Equipamento;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.EquipamentoDAO;
import view.Main2;
import exception.ClienteException;
import exception.PatrimonyException;

/**
 * US11
T�tulo: Excluir equipamento.
Como um usu�rio,
Eu gostaria de excluir o equipamento no sistema,
Para que o equipamento seja indisponibilizado para reserva.

Cen�rio 1: Existe equipamento cadastrado.
Dado que o equipamento est� cadastrado;
Quando o usu�rio solicita a exclus�o;
Ent�o o sistema deve eliminar os registros do equipamento.
E informar o sucesso da exclus�o.

Cen�rio 2: N�o existe equipamento cadastrado.
Dado que n�o existe o registro do equipamento;
Quando o usu�rio solicita exclus�o;
Ent�o o sistema n�o exclui nenhum registro de equipamento,
E informa que n�o h� o registro.

*/

public class US11_ExcluirEquipamento {
    private FrameFixture window;
    private Robot robot;
    private Equipamento equipamento;
    private DialogFixture dialog;
    private int index;

    @Before public void setUp() throws PatrimonyException, SQLException {
        robot = BasicRobot.robotWithNewAwtHierarchy();
        robot.settings().delayBetweenEvents(5);

        window = new FrameFixture(robot, new Main2());
        window.show(new Dimension(900, 500)); // shows the frame to test

        equipamento = new Equipamento("code", "Equipamento para testes de aceitacao");
        EquipamentoDAO.getInstance().incluir(equipamento);

        index = EquipamentoDAO.getInstance().buscarTodos().size() - 1;

        window.button("Equipamento").click();
        dialog = window.dialog("EquipamentoView");

    }

    @After public void tearDown() throws SQLException, PatrimonyException {
        if (equipamento != null)
            EquipamentoDAO.getInstance().excluir(equipamento);
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
    public void testCenario1() throws SQLException, ClienteException{
        dialog.table("tabelaPatrimonio").selectRows(index);
        dialog.button("Excluir").click();
        dialog.optionPane().requireMessage("Deseja mesmo excluir Equipamento: " + equipamento.getDescription() + "?");
        sleep();
        dialog.optionPane().yesButton().click();
        sleep();
        dialog.optionPane().requireMessage("Equipamento excluido com sucesso");
        
        equipamento = null;
    }
    
    @Test
    public void testCenario2() throws SQLException, ClienteException{
        
        dialog.button("Excluir").click();
        dialog.optionPane().requireMessage("Selecione uma linha!");
        sleep();
        dialog.optionPane().okButton().click();
    }
    
}
