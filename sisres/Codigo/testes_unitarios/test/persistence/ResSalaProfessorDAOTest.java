package test.persistence;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import model.Professor;
import model.ReserveClassroomForStudent;
import model.ReservaSalaProfessor;

import model.Classroom;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;

import persistence.ProfessorDAO;

import persistence.FactoryConnection;

import persistence.ReserveClassroomForStudentDAO;
import persistence.ResSalaProfessorDAO;
import persistence.SalaDAO;

public class ResSalaProfessorDAOTest {
	
	private static Classroom sala_a;
	private static Classroom sala_b;
	private static Professor professor1;
	private static Professor professor2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sala_a = new Classroom("S2", "Sala de aula", "130");
		sala_b = new Classroom("I6", "Laboratorio", "40");
		professor1 = new Professor("ProfessorUm", "490.491.781-20", "58801", "3333-3333", "prof@email");
		professor2 = new Professor("ProfessorDois", "040.757.021-70", "36106", "3628-3079", "prof@email");
		
		SalaDAO.getInstance().incluir(sala_a);
		SalaDAO.getInstance().incluir(sala_b);
		ProfessorDAO.getNewProfessor().include(professor1);
		ProfessorDAO.getNewProfessor().include(professor2);		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		SalaDAO.getInstance().excluir(sala_a);
		SalaDAO.getInstance().excluir(sala_b);
		ProfessorDAO.getNewProfessor().delete(professor1);
		ProfessorDAO.getNewProfessor().delete(professor2);	
	}

	@Test
	public void testInstance() {
		assertTrue("Teste de Instancia", ResSalaProfessorDAO.getInstance() instanceof ResSalaProfessorDAO);
	}
	
	@Test
	public void testIncluir() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Aula de reforco", professor1);
		
		ResSalaProfessorDAO.getInstance().incluir(reserva);
		
		boolean resultado = this.inDB(reserva);
		
		if(resultado)
			this.executeQuery("DELETE FROM reserva_sala_professor WHERE data = \"20/12/34\";");
		
		assertTrue("Teste de Inclusao.", resultado);
	}
	
	@Test (expected= ReserveException.class)
	public void testIncluirNulo() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ResSalaProfessorDAO.getInstance().incluir(null);
	}
	@Test (expected= ReserveException.class)
	public void testReservaPorProfessorInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Reuniao", new Professor("Inexistente", "501.341.852-69", "456678", "", ""));
		
		try{
			ResSalaProfessorDAO.getInstance().incluir(reserva);
		} finally {
			this.executeQuery("DELETE FROM reserva_sala_professor;");
		}
	}
	
	@Test (expected= ReserveException.class)
	public void testIncluirSalaInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", new Classroom("222", "Laboratorio", "20"),
				"Grupo de Estudos", professor1);
		
		try{
			ResSalaProfessorDAO.getInstance().incluir(reserva);
		} finally {
			this.executeQuery("DELETE FROM reserva_sala_professor;");
		}
	}
	
	@Test (expected= ReserveException.class)
	public void testIncluirSalaReservadaProf() throws ReserveException, ClientException, 
											PatrimonyException, SQLException 
	{
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Aula de MDS",  professor1);
		ResSalaProfessorDAO.getInstance().incluir(reserva);
		
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Aula de PDS",  professor2);
		
		try{
			ResSalaProfessorDAO.getInstance().incluir(reserva2);
		} finally {
				
		this.executeQuery("DELETE FROM reserva_sala_professor;");
		
		}
		
	}
	@Test
	public void testIncluirSalaReservadaAluno() throws ReserveException, ClientException, 
											PatrimonyException, SQLException 
	{
		this.executeQuery("INSERT INTO aluno (nome, cpf, matricula) " +
		"VALUES (\"Aluno\", \"257.312.954-33\", \"33108\");");
		this.executeQuery("INSERT INTO reserva_sala_aluno (id_aluno,id_sala,finalidade,hora,data, cadeiras_reservadas) "+
		"VALUES ((SELECT id_aluno FROM aluno WHERE cpf = \"257.312.954-33\")," +
				"(SELECT id_sala FROM sala WHERE codigo = \"S2\")," +
				"\"Estudo de Fisica\", \"08:00\", \"20/12/2013\", 20);");
		
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/13", "8:00", sala_a,
				"Aula de EA",  professor1);
		
		ResSalaProfessorDAO.getInstance().incluir(reserva);
		
			
		boolean resultadoProf = this.inDB(reserva);
		boolean resultadoAluno = this.inDBGeneric("SELECT * FROM reserva_sala_aluno " +
				"INNER JOIN sala ON sala.id_sala = reserva_sala_aluno.id_sala " +
				"INNER JOIN aluno ON aluno.id_aluno = reserva_sala_aluno.id_aluno;");
		
				
		this.executeQuery("DELETE FROM aluno;");
		this.executeQuery("DELETE FROM reserva_sala_aluno;");
		this.executeQuery("DELETE FROM reserva_sala_professor;");
		
		
		assertTrue("Sala reservada por aluno", (resultadoProf && !resultadoAluno));
		
		}
	public void testIncluirDataPassouAno() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/1990", "8:00", sala_a,
				"Grupo de Estudos", professor1);
		try{
			ResSalaProfessorDAO.getInstance().incluir(reserva);
		} finally {
			if(this.inDB(reserva))
				this.delete_from_professor(reserva);
		}
	}
	@Test (expected= ReserveException.class)
	public void testIncluirDataPassouMes() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/01/2013", "8:00", sala_a,
				"Grupo de Estudos", professor1);
		try{
			ResSalaProfessorDAO.getInstance().incluir(reserva);
		} finally {
			if(this.inDB(reserva))
				this.delete_from_professor(reserva);
		}
	}
	@Test (expected= ReserveException.class)
	public void testIncluirDataPassouDia() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor(this.dataAtualAMais(-100000000), this.horaAtual(), sala_a,
				"Grupo de Estudos", professor1);
		try{
			ResSalaProfessorDAO.getInstance().incluir(reserva);
		} finally {
			if(this.inDB(reserva))
				this.delete_from_professor(reserva);
		}
	}
	@Test (expected= ReserveException.class)
	public void testIncluirHoraPassouHora() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor(this.dataAtual(),
				 this.horaAtualAMais(-10000000), sala_a,
				"Grupo de Estudos",  professor1);
		try{
			ResSalaProfessorDAO.getInstance().incluir(reserva);
		} finally {
			if(this.inDB(reserva))
				this.delete_from_professor(reserva);
		}
	}
	@Test (expected= ReserveException.class)
	public void testIncluirHoraPassouMinutos() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor(this.dataAtual(),
				this.horaAtualAMais(-100000), sala_a,
				"Grupo de Estudos", professor1);
		try{
			ResSalaProfessorDAO.getInstance().incluir(reserva);
		} finally {
			if(this.inDB(reserva))
				this.delete_from_professor(reserva);
		}
	}
	
	
	@Test (expected= ReserveException.class)
	public void testIncluirProfessorOcupado() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/13", "8:00", sala_a,
				"Aulao pre-prova", professor1);
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor("20/12/13", "8:00", sala_a,
				"Aulao pre-prova", professor1);
		ResSalaProfessorDAO.getInstance().incluir(reserva);
		try{
			ResSalaProfessorDAO.getInstance().incluir(reserva2);
		} finally {
			this.executeQuery("DELETE FROM reserva_sala_professor;");
		}
		
		
	}
	@Test
	public void testAlterar() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva1 = new ReservaSalaProfessor("20/12/13", "8:00", sala_a,
				"Pesquisa", professor1);
		
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor("21/12/34", "19:00", sala_a,
				"Pesquisa", professor1);
		
		this.executeQuery("INSERT INTO " +
				"reserva_sala_professor (id_professor, id_sala, finalidade, hora, data) " +
				"VALUES ( " + values_reserva_sala_professor(reserva1) + " );");
		
		
		ResSalaProfessorDAO.getInstance().alterar(reserva1, reserva2);
		
		boolean resultado = this.inDB(reserva2);
		
		this.executeQuery("DELETE FROM reserva_sala_professor;");
		
		assertTrue("Teste de Alteracao.", resultado);
	}
	@Test (expected= ReserveException.class)
	public void testAlterar_AntigoNulo() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Grupo de pesquisa", professor1);
		ResSalaProfessorDAO.getInstance().alterar(null, reserva);
	}
	@Test (expected= ReserveException.class)
	public void testAlterar_NovoNulo() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
			"Grupo de pesquisa", professor1);
		ResSalaProfessorDAO.getInstance().alterar(reserva, null);
	}
	@Test (expected= ReserveException.class)
	public void testAlterarInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Grupo de pesquisa", professor1);
		
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Grupo de pesquisa", professor1);
		try{
			ResSalaProfessorDAO.getInstance().alterar(reserva, reserva2);
		} finally {
			this.executeQuery("DELETE FROM reserva_sala_professor;");
		}
		
	}
	public void testAlterarDataPassouAno() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Grupo de Estudos",  professor1);
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor("20/12/1990", "8:00", sala_a,
				"Grupo de Estudos", professor2);
		this.insert_into(reserva);
		
		try{
			ResSalaProfessorDAO.getInstance().alterar(reserva, reserva2);
		} finally {
		
		if(this.inDB(reserva))
			this.delete_from_professor(reserva);
		if(this.inDB(reserva2))
			this.delete_from_professor(reserva2);
		}
	}
	@Test (expected= ReserveException.class)
	public void testAlterarDataPassouMes() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Grupo de Estudos",  professor1);
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor("20/01/2013", "8:00", sala_a,
				"Grupo de Estudos", professor2);
		this.insert_into(reserva);
		
		try{
			ResSalaProfessorDAO.getInstance().alterar(reserva, reserva2);
		} finally {
		
		if(this.inDB(reserva))
			this.delete_from_professor(reserva);
		if(this.inDB(reserva2))
			this.delete_from_professor(reserva2);
		}
	}
	@Test (expected= ReserveException.class)
	public void testAlterarDataPassouDia() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Grupo de Estudos",  professor1);
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor(this.dataAtualAMais(-100000000), this.horaAtual(), sala_a,
				"Grupo de Estudos",  professor1);
		this.insert_into(reserva);
		
		try{
			ResSalaProfessorDAO.getInstance().alterar(reserva, reserva2);
		} finally {
		
		if(this.inDB(reserva))
			this.delete_from_professor(reserva);
		if(this.inDB(reserva2))
			this.delete_from_professor(reserva2);
		}
	}
	@Test (expected= ReserveException.class)
	public void testAlterarHoraPassouHora() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Grupo de Estudos",  professor1);
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor(this.dataAtual(),
				 this.horaAtualAMais(-10000000), sala_a,
				"Grupo de Estudos",  professor1);
		this.insert_into(reserva);
		
		try{
			ResSalaProfessorDAO.getInstance().alterar(reserva, reserva2);
		} finally {
		
		if(this.inDB(reserva))
			this.delete_from_professor(reserva);
		if(this.inDB(reserva2))
			this.delete_from_professor(reserva2);
		}
	}
	@Test (expected= ReserveException.class)
	public void testAlterarHoraPassouMinutos() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Grupo de Estudos",  professor1);
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor(this.dataAtual(),
				this.horaAtualAMais(-100000), sala_a,
				"Grupo de Estudos",  professor1);
		this.insert_into(reserva);
		
		try{
			ResSalaProfessorDAO.getInstance().alterar(reserva, reserva2);
		} finally {
		
		if(this.inDB(reserva))
			this.delete_from_professor(reserva);
		if(this.inDB(reserva2))
			this.delete_from_professor(reserva2);
		}
	}
	
	
	@Test (expected= ReserveException.class)
	public void testAlterarJaInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Grupo de pesquisa", professor1);
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor("27/12/34", "9:00", sala_b,
				"Grupo d", professor2);
		this.insert_into(reserva);
		this.insert_into(reserva2);
		
		try{
			ResSalaProfessorDAO.getInstance().alterar(reserva2, reserva);
		} finally {
			this.executeQuery("DELETE FROM reserva_sala_professor;");
		}
		
	}
	
	@Test (expected= ReserveException.class)
	public void testAlterarHoraReservaFeita() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Grupo de pesquisa", professor1);
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor("20/12/34", "9:00", sala_a,
				"Grupo de pesquisa", professor1);
		this.insert_into(reserva);
		this.insert_into(reserva2);
		
		ReservaSalaProfessor reserva3 = new ReservaSalaProfessor("20/12/34", "8:00", sala_b,
				"Grupo de Estudos", professor1);
		
		try{
			ResSalaProfessorDAO.getInstance().alterar(reserva2, reserva3);
		} finally {
		
			this.executeQuery("DELETE FROM reserva_sala_professor;");
		}
	}
	@Test (expected= ReserveException.class)
	public void testAlterarDataDifSalaOcupada() throws ReserveException, ClientException, PatrimonyException, SQLException {
		this.executeQuery("INSERT INTO professor (nome, cpf, matricula) " +
				"VALUES (\"Professor\", \"257.312.954-33\", \"11009988\");");
		this.executeQuery("INSERT INTO reserva_sala_professor (id_professor,id_sala,finalidade,hora,data) "+
				"VALUES ((SELECT id_professor FROM professor WHERE cpf = \"257.312.954-33\")," +
						"(SELECT id_sala FROM sala WHERE codigo = \"S2\")," +
						"\"Aula de Calculo\", \"8:00\", \"20/12/34\");");
		
				
		
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("21/12/34", "8:00", sala_a,
				"Grupo de Pesquisa", professor1);
		this.insert_into(reserva);
		
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Grupo de Estudos", professor1);
		
		try{
			ResSalaProfessorDAO.getInstance().alterar(reserva, reserva2);
		} finally {
				
		this.executeQuery("DELETE FROM professor WHERE cpf = \"257.312.954-33\"");
		this.executeQuery("DELETE FROM reserva_sala_professor");
		}
	}
	@Test (expected= ReserveException.class)
	public void testAlterarProfessorInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("21/12/34", "8:00", sala_a,
				"Grupo de pesquisa", professor1);
		this.insert_into(reserva);
		
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Grupo de pesquisa", new Professor("Nao Existe", "501.341.852-69", "456678", "", ""));
		
		try{
			ResSalaProfessorDAO.getInstance().alterar(reserva, reserva2);
		} finally {
			this.executeQuery("DELETE FROM reserva_sala_professor;");
		}
	}
	@Test (expected= ReserveException.class)
	public void testAlterarSalaInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("21/12/34", "8:00", sala_a,
				"Grupo de pesquisa", professor1);
		this.insert_into(reserva);
		
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor("20/12/34", "8:00", new Classroom("S5", "Sala de aula", "120"),
				"Grupo de Estudos", professor1);
		
		try{
			ResSalaProfessorDAO.getInstance().alterar(reserva, reserva2);
		} finally {
			this.executeQuery("DELETE FROM reserva_sala_professor;");
		}
	}
	@Test
	public void testExcluir() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Grupo de Pesquisa", professor1);
		
		this.executeQuery("INSERT INTO reserva_sala_professor (id_professor,id_sala,finalidade,hora,data) "+
				"VALUES ((SELECT id_professor FROM professor WHERE cpf = \"" + reserva.getProfessor().getCpfPerson() + "\")," + 
						"(SELECT id_sala FROM sala WHERE codigo = \"" + sala_a.getIdEquipment() + "\")," +
						"\"Grupo de Pesquisa\", \"08:00\", \"20/12/2034\");");
		
		ResSalaProfessorDAO.getInstance().excluir(reserva);
		
		boolean resultado = this.inDB(reserva);
		
		this.executeQuery("DELETE FROM reserva_sala_professor;");
		
		assertFalse("Teste de Exclusao.", resultado);
	}
	@Test (expected= ReserveException.class)
	public void testExcluirNulo() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ResSalaProfessorDAO.getInstance().excluir(null);
	}
	@Test (expected= ReserveException.class)
	public void testExcluirInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Reuniao", professor1);

		ResSalaProfessorDAO.getInstance().excluir(reserva);
		
		this.executeQuery("DELETE FROM reserva_sala_professor;");
	}
	
		
	@Test
	public void testBuscarPorData() throws SQLException, PatrimonyException, ClientException, ReserveException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Reuniao", professor1);
		
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor("20/12/34", "19:00", sala_a,
				"Reuniao", professor1);
		
		this.executeQuery("INSERT INTO reserva_sala_professor (id_professor,id_sala,finalidade,hora,data) "+
				"VALUES ((SELECT id_professor FROM professor WHERE cpf = \"" + reserva.getProfessor().getCpfPerson() + "\")," + 
						"(SELECT id_sala FROM sala WHERE codigo = \"" + sala_a.getIdEquipment() + "\")," +
						"\"" + reserva.getFinality() + "\", \"" +
						reserva.getHour() + "\", \"" + reserva.getDate() +"\");");
		
		this.executeQuery("INSERT INTO reserva_sala_professor (id_professor,id_sala,finalidade,hora,data) "+
				"VALUES ((SELECT id_professor FROM professor WHERE cpf = \"" + reserva2.getProfessor().getCpfPerson() + "\")," + 
						"(SELECT id_sala FROM sala WHERE codigo = \"" + sala_a.getIdEquipment() + "\")," +
						"\"" + reserva2.getFinality() + "\", \"" +
						reserva2.getHour() + "\", \"" + reserva2.getDate() +"\");");
		
		Vector<ReservaSalaProfessor> vet = ResSalaProfessorDAO.getInstance().buscarPorData("20/12/2034");
		
		
		boolean resultado = false;
		boolean resultado2 = false;
		
		Iterator<ReservaSalaProfessor> it = vet.iterator();
		while(it.hasNext()){
			ReservaSalaProfessor obj = it.next();
			if(obj.equals(reserva))
				resultado = true;
			else if(obj.equals(reserva2))
				resultado2 = true;
		}
		
		this.executeQuery("DELETE FROM reserva_sala_professor WHERE data = \"20/12/2034\"");
		
		assertTrue("Teste de busca por data", resultado && resultado2);
	}
		
	private String select_id_professor(Professor p){
		return "SELECT id_professor FROM professor WHERE " +
				"professor.nome = \"" + p.getNamePerson() + "\" and " +
				"professor.cpf = \"" + p.getCpfPerson() + "\" and " +
				"professor.telefone = \"" + p.getPhonePerson() + "\" and " +
				"professor.email = \"" + p.getEmailPerson() + "\" and " +
				"professor.matricula = \"" + p.getIdRegister() + "\"";
	}
	private String select_id_sala(Classroom sala){
		return "SELECT id_sala FROM sala WHERE " +
				"sala.codigo = \"" + sala.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + sala.getDescriptionEquipment() +  "\" and " +
				"sala.capacidade = " + sala.getCapacidade();
	}
	private String where_reserva_sala_professor(ReservaSalaProfessor r){
		return " WHERE " +
		"id_professor = ( " + select_id_professor(r.getProfessor()) + " ) and " +
		"id_sala = ( " + select_id_sala(r.getClassroom()) + " ) and " +
		"finalidade = \"" + r.getFinality() + "\" and " +
		"hora = \"" + r.getHour() + "\" and " +
		"data = \"" + r.getDate() + "\"";
	}
	private String values_reserva_sala_professor(ReservaSalaProfessor r){
		return "( " + select_id_professor(r.getProfessor()) + " ), " +
		"( " + select_id_sala(r.getClassroom()) + " ), " +
		"\"" + r.getFinality() + "\", " +
		"\"" + r.getHour() + "\", " +
		"\"" + r.getDate() + "\"";
	}
	/*private String atibutes_value_reserva_sala_professor(ReservaSalaProfessor r){
		return "id_professor = ( " + select_id_professor(r.getProfessor()) + " ), " +
		"id_sala = ( " + select_id_sala(r.getSala()) + " ), " +
		"finalidade = \"" + r.getFinalidade() + "\", " +
		"hora = \"" + r.getHora() + "\", " +
		"data = \"" + r.getData() + "\"";
	}*/

	private String insert_into(ReservaSalaProfessor r){
		return "INSERT INTO " +
				"reserva_sala_professor (id_professor, id_sala, finalidade, hora, data) " +
				"VALUES ( " + values_reserva_sala_professor(r) + " );";
	}
	
	private String delete_from_professor(ReservaSalaProfessor r){
		return "DELETE FROM reserva_sala_professor " + this.where_reserva_sala_professor(r) + " ;";
	}
	/*
	private String delete_from_aluno(ReservaSalaProfessor r){
		return "DELETE FROM reserva_sala_aluno WHERE " +
				"hora = \"" + r.getHora() + "\" and " +
				"data = \"" + r.getData() +  "\" ;";
	}
	
	private String update(ReservaSalaProfessor r, ReservaSalaProfessor r2){
		return "UPDATE reserva_sala_professor SET " + 
				this.atibutes_value_reserva_sala_professor(r2) +
				this.where_reserva_sala_professor(r) + " ;";
	}
*/
	
	
	private String dataAtual(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		return formatador.format(date);
	}
	
	private String horaAtual(){
		Date date = new Date(System.currentTimeMillis());
		return date.toString().substring(11, 16);
	}
	
	private String horaAtualAMais(int fator){
		Date date = new Date(System.currentTimeMillis()+fator);
		return date.toString().substring(11, 16);
	}
	
	private String dataAtualAMais(int fator){
		Date date = new Date(System.currentTimeMillis()+fator);
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		return formatador.format(date);
	}
			
	private boolean inDB(ReservaSalaProfessor reserva) throws SQLException{
		return this.inDBGeneric("SELECT * FROM reserva_sala_professor " + 
								this.where_reserva_sala_professor(reserva) + " ;");
	}
	private boolean inDBGeneric(String query) throws SQLException{
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
		if(!rs.next())
		{
			rs.close();
			pst.close();
			con.close();
			return false;
		}
		else {
			rs.close();
			pst.close();
			con.close();
			return true;
		}
	}	
	private void executeQuery(String msg) throws SQLException{
		Connection con =  FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(msg);
		pst.executeUpdate();		
		pst.close();
		con.close();
	}


}
