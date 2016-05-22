package user_stories;

import java.awt.Dimension;
import java.sql.SQLException;

import model.Equipment;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.EquipmentDAO;
import view.Main2;
import exception.PatrimonyException;

/**
 * US2 Titulo: Reservar equipamento. Como professor, Eu quero reservar um
 * equipamento, Para que eu possa usufruir, sempre que necess�rio, dos recursos
 * dispon�veis na FGA.
 * 
 * Cen�rio 1: Aluno deseja reservar equipamento. Dado que um aluno possui
 * cadastro, E solicita reserva de equipamento, Quando o usu�rio solicitar a
 * reserva pelo aluno, Ent�o o sistema deve negar a reserva.
 * 
 * Cen�rio 2: Professor deseja reservar equipamento dispon�vel. Dado que o
 * professor est� cadastrado, E o equipamento est� cadastrado, E o equipamento
 * est� dispon�vel, Quando o usu�rio solicitar a reserva do equipamento pelo
 * professor, Ent�o o sistema reserva o equipamento, E informar que a reserva
 * foi realizada com sucesso.
 * 
 * Cen�rio 3: Professor deseja reservar equipamento indispon�vel. Dado que o
 * professor est� cadastrado, E o equipamento est� cadastrado, E o equipamento
 * j� est� reservado, Quando o usu�rio solicitar a reserva do equipamento pelo
 * professor, Ent�o o sistema dever� negar a reserva, E o sistema deve informar
 * que o equipamento est� indispon�vel para o dia e hor�rio escolhido. E o
 * sistema n�o deve substituir a reserva.
 */
public class US02_ReservarEquipamento {
    private FrameFixture window;
    private Robot robot;
    private Equipment equipamento;
    private DialogFixture dialog;
    private int index;

    @Before public void setUp() throws PatrimonyException, SQLException {

        robot = BasicRobot.robotWithNewAwtHierarchy();
        robot.settings().delayBetweenEvents(5);

        window = new FrameFixture(robot, new Main2());
        window.show(new Dimension(900, 500)); // shows the frame to test

        equipamento = new Equipment("code", "Equipamento para testes de aceitacao");
        EquipmentDAO.getNewEquipment().include(equipamento);

        index = EquipmentDAO.getNewEquipment().searchAll().size() - 1;

        window.button("Equipamento").click();
        dialog = window.dialog("EquipamentoView");

    }

    @After public void tearDown() throws SQLException, PatrimonyException {
        if (equipamento != null)
            EquipmentDAO.getNewEquipment().delete(equipamento);
        window.cleanUp();
    }

    public void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test public void testCenario1() {
        sleep();
    }
}
