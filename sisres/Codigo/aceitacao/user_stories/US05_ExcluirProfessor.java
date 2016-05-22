package user_stories;

import java.awt.Dimension;
import java.sql.SQLException;

import model.Professor;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.ProfessorDAO;
import view.Main2;
import exception.ClientException;

/**
 * US5 T�tulo: Excluir Professor. Como professor Eu quero solicitar a exclus�o do meu
 * cadastro Para n�o utilizar o servi�o.
 * 
 * Cen�rio 1: Professor cadastrado. Dado que o professor est� cadastrado; Quando o
 * usu�rio solicita a exclus�o do registro; Ent�o o sistema deve eliminar os
 * registros do professor, E informar o sucesso da exclus�o.
 * 
 * Cen�rio 2: N�o existe professor cadastrado. Dado que n�o existe o registro do
 * professor, Quando o usu�rio solicita a exclus�o do registro, Ent�o o sistema n�o
 * exclui nenhum registro de professor, E informa que n�o h� o registro.
 */

public class US05_ExcluirProfessor {
	private FrameFixture window;
	private Robot robot;
	private Professor professor;
	private DialogFixture dialog;
	private int index;
	
	@Before
	public void setUp() throws ClientException, SQLException {
		robot = BasicRobot.robotWithNewAwtHierarchy();
		robot.settings().delayBetweenEvents(5);

		window = new FrameFixture(robot, new Main2());
		window.show(new Dimension(900, 500)); // shows the frame to test

		professor = new Professor("Teste", "658.535.144-40", "110038096","9211-2144", "teste incluir repetido");
		ProfessorDAO.getNewProfessor().include(professor);

		index = ProfessorDAO.getNewProfessor().seachAll().size() - 1;
		
		window.button("Professor").click();
		dialog = window.dialog("ProfessorView");

	}
	
	@After
	public void tearDown() throws SQLException, ClientException {
		if(professor != null)
			ProfessorDAO.getNewProfessor().delete(professor);
		window.cleanUp();
	}

	public void sleep(){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testCenario1() throws SQLException, ClientException{
		dialog.button("Excluir").click();
		dialog.optionPane().requireMessage("Selecione uma linha!");
		sleep();
		dialog.optionPane().okButton().click();
	}
	
	@Test
	public void testCenario2() throws SQLException, ClientException{
		dialog.table("tabelaCliente").selectRows(index);
		dialog.button("Excluir").click();
		dialog.optionPane().requireMessage("Deseja mesmo excluir Professor: " + professor.getNamePerson() + "?");
		sleep();
		dialog.optionPane().yesButton().click();
		sleep();
		dialog.optionPane().requireMessage("Professor excluido com sucesso");
		dialog.optionPane().okButton().click();
		professor = null;
	}
}
