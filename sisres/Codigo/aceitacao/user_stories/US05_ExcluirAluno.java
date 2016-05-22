package user_stories;

import java.awt.Dimension;
import java.sql.SQLException;

import model.Student;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.StudentDAO;
import view.Main2;
import exception.ClientException;

/**
 * US5 T�tulo: Excluir Aluno. Como aluno Eu quero solicitar a exclus�o do meu
 * cadastro Para n�o utilizar o servi�o.
 * 
 * Cen�rio 1: Aluno cadastrado. Dado que o aluno est� cadastrado; Quando o
 * usu�rio solicita a exclus�o do registro; Ent�o o sistema deve eliminar os
 * registros do aluno, E informar o sucesso da exclus�o.
 * 
 * Cen�rio 2: N�o existe aluno cadastrado. Dado que n�o existe o registro do
 * aluno, Quando o usu�rio solicita a exclus�o do registro, Ent�o o sistema n�o
 * exclui nenhum registro de aluno, E informa que n�o h� o registro.
 */

public class US05_ExcluirAluno {
	private FrameFixture window;
	private Robot robot;
	private Student aluno;
	private DialogFixture dialog;
	private int index;
	
	@Before
	public void setUp() throws ClientException, SQLException {
		robot = BasicRobot.robotWithNewAwtHierarchy();
		robot.settings().delayBetweenEvents(5);

		window = new FrameFixture(robot, new Main2());
		window.show(new Dimension(900, 500)); // shows the frame to test

		aluno = new Student("Teste", "658.535.144-40", "110038096","9211-2144", "teste incluir repetido");
		StudentDAO.getNewStudent().include(aluno);

		index = StudentDAO.getNewStudent().searchAll().size() - 1;
		
		window.button("Aluno").click();
		dialog = window.dialog("AlunoView");

	}
	
	@After
	public void tearDown() throws SQLException, ClientException {
		if(aluno != null)
			StudentDAO.getNewStudent().delete(aluno);
		window.cleanUp();
	}

	public void sleep(){
		try {
			Thread.sleep(1000);
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
		dialog.optionPane().requireMessage("Deseja mesmo excluir Aluno: " + aluno.getNamePerson() + "?");
		sleep();
		dialog.optionPane().yesButton().click();
		sleep();
		dialog.optionPane().requireMessage("Aluno excluido com sucesso");
		dialog.optionPane().okButton().click();
		aluno = null;
	}
}
