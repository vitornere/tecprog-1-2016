package user_stories;

import java.awt.Dimension;
import java.sql.SQLException;

import model.Equipment;
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

/**US10
T�tulo: Alterar cadastro de equipamento
Como usu�rio
Eu gostaria de alterar dados de equipamentos
Para que haja confiabilidade nos dados contidos no sistema.

Cen�rio 1: Existe equipamento cadastrado e dados novos s�o v�lidos.
Dado que o equipamento est� cadastrado;
Quando o usu�rio edita algum campo
E todos os dados s�o v�lidos,
E solicita altera��o;
Ent�o o sistema deve alterar os registros do equipamento.
E informar o sucesso da altera��o.

Cen�rio 2: N�o existe equipamento cadastrado.
Dado que n�o existe o registro do equipamento;
Quando o usu�rio solicita altera��o;
Ent�o o sistema informa que n�o h� o registro.

Cen�rio 3: Existe equipamento cadastrado e dados novos n�o s�o v�lidos.
Dado que o equipamento est� cadastrado;
Quando o usu�rio edita algum campo
E algum dado n�o � v�lido,
E solicita altera��o;
Ent�o o sistema deve exibir a seguinte mensagem: �O campo [campo] � inv�lido�,
E n�o realizar altera��o.
*/

public class US10_AlterarEquipamento {
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

@Test public void testCancelar() {
    dialog.table("tabelaPatrimonio").selectRows(index);
    dialog.button("Alterar").click();
    DialogFixture cadastro = dialog.dialog("AlterarEquipamento");
    cadastro.button("Cancelar").click();
}

@Test public void testCenario1() throws SQLException, PatrimonyException {

    dialog.table("tabelaPatrimonio").selectRows(index);

    dialog.button("Alterar").click();
    DialogFixture cadastro = dialog.dialog("AlterarEquipamento");

    cadastro.textBox("Codigo").setText("123");

    cadastro.button("Alterar").click();
    cadastro.optionPane().requireMessage("Equipamento alterado com sucesso");
    sleep();
    cadastro.optionPane().okButton().click();

    equipamento = EquipmentDAO.getNewEquipment().searchAll().get(index);
}

@Test public void testCenario2() throws SQLException, PatrimonyException {

    if (equipamento != null)
        EquipmentDAO.getNewEquipment().delete(equipamento);
    equipamento = null;
    dialog.button("Alterar").click();
    dialog.optionPane().requireMessage("Selecione uma linha!");
    sleep();

}

@Test public void testCenario3CodigoBranco() throws SQLException, PatrimonyException {

    dialog.table("tabelaPatrimonio").selectRows(index);
    dialog.button("Alterar").click();
    DialogFixture cadastro = dialog.dialog("AlterarEquipamento");

    
    cadastro.textBox("Codigo").setText("");
    cadastro.textBox("Descricao").setText("Equipamento para testes de aceitacao");

    cadastro.button("Alterar").click();
    cadastro.optionPane().requireMessage("Codigo em Branco.");
    sleep();
    cadastro.optionPane().okButton().click();
    equipamento = EquipmentDAO.getNewEquipment().searchAll().get(index);
}

@Test public void testCenario3DescricaoBranco() throws SQLException, PatrimonyException {

    dialog.table("tabelaPatrimonio").selectRows(index);
    dialog.button("Alterar").click();
    DialogFixture cadastro = dialog.dialog("AlterarEquipamento");

    
    cadastro.textBox("Codigo").setText("code");
    cadastro.textBox("Descricao").setText("");

    cadastro.button("Alterar").click();
    cadastro.optionPane().requireMessage("Descricao em Branco.");
    sleep();
    cadastro.optionPane().okButton().click();
    equipamento = EquipmentDAO.getNewEquipment().searchAll().get(index);
}

}
