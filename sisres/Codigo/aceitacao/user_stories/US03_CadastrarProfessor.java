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
 * US3 T�tulo: Cadastrar Professor. Como professor Eu quero me cadastrar Para poder
 * realizar reservas de salas.
 * 
 * Cen�rio 1: N�o h� cadastro e dados inseridos s�o v�lidos. Dado que n�o h�
 * cadastro do professor, E os dados inseridos todos s�o v�lidos, Quando o usu�rio
 * solicitar o cadastro do professor, Ent�o o sistema deve registrar o novo
 * cadastro, E informar o sucesso da opera��o.
 * 
 * Cen�rio 2: H� cadastro e dados inseridos s�o v�lidos. Dado que h� o cadastro
 * do professor, E os dados do novo cadastro s�o v�lidos, Quando o usu�rio solicitar
 * o cadastro do professor, Ent�o o sistema deve informar que o professor j� est�
 * cadastrado, E n�o deve registrar um novo cadastro.
 * 
 * Cen�rio 3: N�o h� cadastro e dados inseridos s�o inv�lidos. Dado que n�o h� o
 * cadastro do professor, E os dados do novo cadastro s�o inv�lidos, Quando o
 * usu�rio solicitar o cadastro do professor, Ent�o o sistema deve exibir a seguinte
 * mensagem: �O campo [campo] � inv�lido�, E n�o deve registrar um novo
 * cadastro.
 * 
 */

public class US03_CadastrarProfessor {

	private FrameFixture window;
	private Robot robot;
	private Professor professor;
	private DialogFixture dialog;
	private int index;
	
	@Before
	public void setUp() {
		robot = BasicRobot.robotWithNewAwtHierarchy();
		robot.settings().delayBetweenEvents(5);

		window = new FrameFixture(robot, new Main2());
		window.show(new Dimension(900, 500)); // shows the frame to test
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
	public void testCancelar() {
		dialog.button("Cadastrar").click();
		DialogFixture cadastro = dialog.dialog("CadastroProfessor");
		cadastro.button("Cancelar").click();
	}
		
	@Test
	public void testCenario1() throws SQLException, ClientException {
		dialog.button("Cadastrar").click();
		DialogFixture cadastro = dialog.dialog("CadastroProfessor");

		cadastro.textBox("Nome").enterText("Teste");
		cadastro.textBox("Telefone").enterText("9211-2144");
		cadastro.textBox("CPF").enterText("658.535.144-40");
		cadastro.textBox("Matricula").enterText("110038096");
		cadastro.textBox("E-mail").enterText("Teste automatizado");

		cadastro.button("Cadastrar").click();
		cadastro.optionPane().requireMessage("Professor Cadastrado com sucesso");
		sleep();
		cadastro.optionPane().okButton().click();

		index = ProfessorDAO.getNewProfessor().seachAll().size() - 1;
		professor = ProfessorDAO.getNewProfessor().seachAll().get(index);
	}
	
	@Test
	public void testCenario2() throws SQLException, ClientException {

		professor = new Professor("Teste", "658.535.144-40", "110038096","9211-2144", "teste incluir repetido");
		ProfessorDAO.getNewProfessor().include(professor);
		
		dialog.button("Cadastrar").click();
		DialogFixture cadastro = dialog.dialog("CadastroProfessor");

		cadastro.textBox("Nome").enterText("Teste");
		cadastro.textBox("Telefone").enterText("9211-2144");
		cadastro.textBox("CPF").enterText("658.535.144-40");
		cadastro.textBox("Matricula").enterText("110038096");
		cadastro.textBox("E-mail").enterText("Teste automatizado");

		cadastro.button("Cadastrar").click();
		cadastro.optionPane().requireMessage("Ja existe um professor cadastrado com esse CPF.");
		sleep();
		cadastro.optionPane().okButton().click();
	}

	@Test
	public void testCenario3NomeInvalido() throws SQLException,
			ClientException {

		dialog.button("Cadastrar").click();
		DialogFixture cadastro = dialog.dialog("CadastroProfessor");

		cadastro.textBox("Nome").enterText("123");
		cadastro.textBox("Telefone").enterText("9211-2144");
		cadastro.textBox("CPF").enterText("658.535.144-40");
		cadastro.textBox("Matricula").enterText("110038096");
		cadastro.textBox("E-mail").enterText("Teste automatizado");

		cadastro.button("Cadastrar").click();
		cadastro.optionPane().requireMessage("Nome Invalido.");
		sleep();
		cadastro.optionPane().okButton().click();

	}

	@Test
	public void testCenario3NomeBranco() throws SQLException,
			ClientException {

		dialog.button("Cadastrar").click();
		DialogFixture cadastro = dialog.dialog("CadastroProfessor");

		cadastro.textBox("Nome").enterText("");
		cadastro.textBox("Telefone").enterText("9211-2144");
		cadastro.textBox("CPF").enterText("658.535.144-40");
		cadastro.textBox("Matricula").enterText("110038096");
		cadastro.textBox("E-mail").enterText("Teste automatizado");

		cadastro.button("Cadastrar").click();
		cadastro.optionPane().requireMessage("Nome em Branco.");
		sleep();
		cadastro.optionPane().okButton().click();
	}

	@Test
	public void testCenario3CpfInvalido() throws SQLException,
			ClientException {
		
		dialog.button("Cadastrar").click();
		DialogFixture cadastro = dialog.dialog("CadastroProfessor");

		cadastro.textBox("Nome").enterText("Teste");
		cadastro.textBox("Telefone").enterText("9211-2144");
		cadastro.textBox("CPF").enterText("03435543132");
		cadastro.textBox("Matricula").enterText("110038096");
		cadastro.textBox("E-mail").enterText("Teste automatizado");

		cadastro.button("Cadastrar").click();
		cadastro.optionPane().requireMessage("CPF Invalido.");
		sleep();
		cadastro.optionPane().okButton().click();
	}
	
	@Test
	public void testCenario3CpfBranco() throws SQLException,
			ClientException {
		
		dialog.button("Cadastrar").click();
		DialogFixture cadastro = dialog.dialog("CadastroProfessor");

		cadastro.textBox("Nome").enterText("Teste");
		cadastro.textBox("Telefone").enterText("9211-2144");
		cadastro.textBox("CPF").enterText("");
		cadastro.textBox("Matricula").enterText("110038096");
		cadastro.textBox("E-mail").enterText("Teste automatizado");

		cadastro.button("Cadastrar").click();
		cadastro.optionPane().requireMessage("CPF em Branco.");
		sleep();
		cadastro.optionPane().okButton().click();
	}
	
	@Test
	public void testCenario3TelefoneInvalido() throws SQLException,
			ClientException {
		
		dialog.button("Cadastrar").click();
		DialogFixture cadastro = dialog.dialog("CadastroProfessor");

		cadastro.textBox("Nome").enterText("Teste");
		cadastro.textBox("Telefone").enterText("123");
		cadastro.textBox("CPF").enterText("658.535.144-40");
		cadastro.textBox("Matricula").enterText("110038096");
		cadastro.textBox("E-mail").enterText("Teste automatizado");

		cadastro.button("Cadastrar").click();
		cadastro.optionPane().requireMessage("Telefone Invalido.");
		sleep();
		cadastro.optionPane().okButton().click();
	}
	
}