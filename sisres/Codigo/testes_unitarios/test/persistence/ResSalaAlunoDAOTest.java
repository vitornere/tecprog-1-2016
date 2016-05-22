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

import model.Student;
import model.ReserveClassroomForStudent;
import model.Classroom;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.StudentDAO;
import persistence.FactoryConnection;
import persistence.ReserveClassroomForStudentDAO;
import persistence.ClassroomDAO;

import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;

public class ResSalaAlunoDAOTest {

	private static Classroom sala1;
	private static Classroom sala2;
	private static Student aluno1;
	private static Student aluno2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sala1 = new Classroom("123", "Sala de Aula", "120");
		sala2 = new Classroom("543", "Laboratorio", "30");
		aluno1 = new Student("testInstance", "501.341.852-69", "456678", "", "");
		aluno2 = new Student("Incluindo Matricula Igual", "490.491.781-20", "345543", "2222-2222", "aluno2@email");
		
		StudentDAO.getNewStudent().include(aluno1);
		StudentDAO.getNewStudent().include(aluno2);
		ClassroomDAO.getClassroom().include(sala1);
		ClassroomDAO.getClassroom().include(sala2);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		StudentDAO.getNewStudent().delete(aluno1);
		StudentDAO.getNewStudent().delete(aluno2);
		ClassroomDAO.getClassroom().delete(sala1);
		ClassroomDAO.getClassroom().delete(sala2);
	}

	@Test
	public void testInstance() {
		assertTrue("Teste de Instancia", ReserveClassroomForStudentDAO.getReserveClassroomForStudent() instanceof ReserveClassroomForStudentDAO);
	}
	@Test
	public void testSingleton() {
		assertSame("Teste de Singleton", ReserveClassroomForStudentDAO.getReserveClassroomForStudent(), ReserveClassroomForStudentDAO.getReserveClassroomForStudent());
	}
	
	
	@Test
	public void testIncluir() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", aluno1);
		
		ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reserva);
		
		boolean resultado = this.inDB(reserva);
		
		if(resultado)
			this.delete_from(reserva);
		assertTrue("Teste de Inclusao.", resultado);
	}
	@Test (expected= ReserveException.class)
	public void testIncluirNulo() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(null);
	}
	@Test (expected= ReserveException.class)
	public void testIncluirAlunoInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", new Student("tepp", "501.341.852-69", "456678", "", ""));
		
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reserva);
		} finally {
		boolean resultado = this.inDB(reserva);

		if(resultado)
			this.delete_from(reserva);
		}
	}
	@Test (expected= ReserveException.class)
	public void testIncluirSalaInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", new Classroom("22277883", "Laboratorio", "120"),
				"Grupo de Estudos", "120", aluno1);
		
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reserva);
		} finally {
		boolean resultado = this.inDB(reserva);

		if(resultado)
			this.delete_from(reserva);
		}
	}
	@Test (expected= ReserveException.class)
	public void testIncluirSalaReservadaProf() throws ReserveException, ClientException, 
											PatrimonyException, SQLException 
	{
		this.executeQuery("INSERT INTO professor (nome, cpf, matricula) " +
		"VALUES (\"ProfessorDAO\", \"257.312.954-33\", \"11009988\");");
		this.executeQuery("INSERT INTO reserva_sala_professor (id_professor,id_sala,finalidade,hora,data) "+
		"VALUES ((SELECT id_professor FROM professor WHERE cpf = \"257.312.954-33\")," +
				"(SELECT id_sala FROM sala WHERE codigo = \"123\")," +
				"\"Aula de Calculo\", \"08:00\", \"20/12/2034\");");
		
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "60", aluno1);
		
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reserva);
		} finally {
		if(this.inDB(reserva))
			this.delete_from(reserva);
		
		this.executeQuery("DELETE FROM professor WHERE cpf = \"257.312.954-33\";");
		this.executeQuery("DELETE FROM reserva_sala_professor WHERE data = \"20/12/2034\";");
		
		}
		
	}
	@Test (expected= ReserveException.class)
	public void testIncluirAlunoOcupado() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", aluno1);
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent("20/12/34", "8:00", sala2,
				"Grupo de Estudos", ""+sala2.getCapacity(), aluno1);
		ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reserva);
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reserva2);
		} finally {
			if(this.inDB(reserva))
				this.delete_from(reserva);
			if(this.inDB(reserva2))
				this.delete_from(reserva2);
		}
	}
	@Test (expected= ReserveException.class)
	public void testIncluirSalaCheia() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "60", aluno1);
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "70", aluno2);
		ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reserva);
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reserva2);
		} finally {
			if(this.inDB(reserva))
				this.delete_from(reserva);
			if(this.inDB(reserva2))
				this.delete_from(reserva2);
		}
	}
	@Test (expected= ReserveException.class)
	public void testIncluirDataPassouAno() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/1990", "8:00", sala1,
				"Grupo de Estudos", "60", aluno1);
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reserva);
		} finally {
			if(this.inDB(reserva))
				this.delete_from(reserva);
		}
	}
	@Test (expected= ReserveException.class)
	public void testIncluirDataPassouMes() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/01/2013", "8:00", sala1,
				"Grupo de Estudos", "60", aluno1);
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reserva);
		} finally {
			if(this.inDB(reserva))
				this.delete_from(reserva);
		}
	}
	@Test (expected= ReserveException.class)
	public void testIncluirDataPassouDia() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent(this.dataAtualAMais(-100000000), this.horaAtual(), sala1,
				"Grupo de Estudos", "60", aluno1);
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reserva);
		} finally {
			if(this.inDB(reserva))
				this.delete_from(reserva);
		}
	}
	@Test (expected= ReserveException.class)
	public void testIncluirHoraPassouHora() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent(this.dataAtual(),
				 this.horaAtualAMais(-10000000), sala1,
				"Grupo de Estudos", "60", aluno1);
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reserva);
		} finally {
			if(this.inDB(reserva))
				this.delete_from(reserva);
		}
	}
	@Test (expected= ReserveException.class)
	public void testIncluirHoraPassouMinutos() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent(this.dataAtual(),
				this.horaAtualAMais(-100000), sala1,
				"Grupo de Estudos", "60", aluno1);
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reserva);
		} finally {
			if(this.inDB(reserva))
				this.delete_from(reserva);
		}
	}
	
	
	@Test
	public void testAlterar() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", aluno1);
		
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent("21/12/34", "19:00", sala1,
				"Grupo de Estudos", "120", aluno1);
		
		this.insert_into(reserva);
		
		ReserveClassroomForStudentDAO.getReserveClassroomForStudent().updateReserveClassroomForStudent(reserva, reserva2);
		
		boolean resultado = this.inDB(reserva2);
		
		if(resultado)
			this.delete_from(reserva2);
		if(this.inDB(reserva))
			this.delete_from(reserva);
		assertTrue("Teste de Inclusao.", resultado);
	}
	@Test (expected= ReserveException.class)
	public void testAlterarNulo1() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", aluno1);
		ReserveClassroomForStudentDAO.getReserveClassroomForStudent().updateReserveClassroomForStudent(null, reserva);
	}
	@Test (expected= ReserveException.class)
	public void testAlterarNulo2() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
			"Grupo de Estudos", "120", aluno1);
		ReserveClassroomForStudentDAO.getReserveClassroomForStudent().updateReserveClassroomForStudent(reserva, null);
	}
	@Test (expected= ReserveException.class)
	public void testAlterarInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", aluno1);
		
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", aluno1);
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().updateReserveClassroomForStudent(reserva, reserva2);
		} finally {
			if(this.inDB(reserva2))
				this.delete_from(reserva2);
		}
	}
	@Test (expected= ReserveException.class)
	public void testAlterarJaInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", aluno1);
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent("27/12/34", "9:00", sala2,
				"Grupo d", ""+sala2.getCapacity(), aluno2);
		this.insert_into(reserva);
		this.insert_into(reserva2);
		
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().updateReserveClassroomForStudent(reserva2, reserva);
		} finally {
		
		if(this.inDB(reserva))
			this.delete_from(reserva);
		if(this.inDB(reserva2))
			this.delete_from(reserva2);
		}
	}
	@Test (expected= ReserveException.class)
	public void testAlterarHoraDifAlunoOcupado() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", aluno1);
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent("20/12/34", "9:00", sala1,
				"Grupo de Estudos", "120", aluno1);
		this.insert_into(reserva);
		this.insert_into(reserva2);
		
		ReserveClassroomForStudent reserva3 = new ReserveClassroomForStudent("20/12/34", "8:00", sala2,
				"Grupo de Estudos", ""+sala2.getCapacity(), aluno1);
		
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().updateReserveClassroomForStudent(reserva2, reserva3);
		} finally {
		
		if(this.inDB(reserva))
			this.delete_from(reserva);
		if(this.inDB(reserva2))
			this.delete_from(reserva2);
		if(this.inDB(reserva3))
			this.delete_from(reserva3);
		}
	}
	@Test (expected= ReserveException.class)
	public void testAlterarDataDifSalaOcupado() throws ReserveException, ClientException, PatrimonyException, SQLException {
		this.executeQuery("INSERT INTO professor (nome, cpf, matricula) " +
				"VALUES (\"ProfessorDAO\", \"257.312.954-33\", \"11009988\");");
		this.executeQuery("INSERT INTO reserva_sala_professor (id_professor,id_sala,finalidade,hora,data) "+
				"VALUES ((SELECT id_professor FROM professor WHERE cpf = \"257.312.954-33\")," +
						"(SELECT id_sala FROM sala WHERE codigo = \"123\")," +
						"\"Aula de Calculo\", \"08:00\", \"20/12/2034\");");
				
		
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("21/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", aluno1);
		this.insert_into(reserva);
		
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", aluno1);
		
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().updateReserveClassroomForStudent(reserva, reserva2);
		} finally {
		
		if(this.inDB(reserva))
			this.delete_from(reserva);
		if(this.inDB(reserva2))
			this.delete_from(reserva2);
		
		this.executeQuery("DELETE FROM professor WHERE cpf = \"257.312.954-33\";");
		this.executeQuery("DELETE FROM reserva_sala_professor WHERE data = \"20/12/2034\";");
		}
	}
	@Test (expected= ReserveException.class)
	public void testAlterarAlunoInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("21/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", aluno1);
		this.insert_into(reserva);
		
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", new Student("tepp", "501.341.852-69", "456678", "", ""));
		
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().updateReserveClassroomForStudent(reserva, reserva2);
		} finally {
		
		if(this.inDB(reserva))
			this.delete_from(reserva);
		if(this.inDB(reserva2))
			this.delete_from(reserva2);
		}
	}
	@Test (expected= ReserveException.class)
	public void testAlterarSalaInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("21/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", aluno1);
		this.insert_into(reserva);
		
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent("20/12/34", "8:00", new Classroom("22277883", "Laboratorio", "120"),
				"Grupo de Estudos", "120", aluno1);
		
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().updateReserveClassroomForStudent(reserva, reserva2);
		} finally {
		
		if(this.inDB(reserva))
			this.delete_from(reserva);
		if(this.inDB(reserva2))
			this.delete_from(reserva2);
		}
	}
	@Test (expected= ReserveException.class)
	public void testAlterarNaoHaCadeira() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "30", aluno1);
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "20", aluno2);
		ReserveClassroomForStudent reserva3 = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", aluno2);
		this.insert_into(reserva);
		this.insert_into(reserva2);
		
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().updateReserveClassroomForStudent(reserva2, reserva3);
		} finally {
		
		if(this.inDB(reserva))
			this.delete_from(reserva);
		if(this.inDB(reserva2))
			this.delete_from(reserva2);
		if(this.inDB(reserva3))
			this.delete_from(reserva3);
		}
	}
	@Test (expected= ReserveException.class)
	public void testAlterarDataPassouAno() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "30", aluno1);
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent("20/12/1990", "8:00", sala1,
				"Grupo de Estudos", "20", aluno2);
		this.insert_into(reserva);
		
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().updateReserveClassroomForStudent(reserva, reserva2);
		} finally {
		
		if(this.inDB(reserva))
			this.delete_from(reserva);
		if(this.inDB(reserva2))
			this.delete_from(reserva2);
		}
	}
	@Test (expected= ReserveException.class)
	public void testAlterarDataPassouMes() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "30", aluno1);
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent("20/01/2013", "8:00", sala1,
				"Grupo de Estudos", "20", aluno2);
		this.insert_into(reserva);
		
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().updateReserveClassroomForStudent(reserva, reserva2);
		} finally {
		
		if(this.inDB(reserva))
			this.delete_from(reserva);
		if(this.inDB(reserva2))
			this.delete_from(reserva2);
		}
	}
	@Test (expected= ReserveException.class)
	public void testAlterarDataPassouDia() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "30", aluno1);
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent(this.dataAtualAMais(-100000000), this.horaAtual(), sala1,
				"Grupo de Estudos", "60", aluno1);
		this.insert_into(reserva);
		
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().updateReserveClassroomForStudent(reserva, reserva2);
		} finally {
		
		if(this.inDB(reserva))
			this.delete_from(reserva);
		if(this.inDB(reserva2))
			this.delete_from(reserva2);
		}
	}
	@Test (expected= ReserveException.class)
	public void testAlterarHoraPassouHora() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "30", aluno1);
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent(this.dataAtual(),
				 this.horaAtualAMais(-10000000), sala1,
				"Grupo de Estudos", "60", aluno1);
		this.insert_into(reserva);
		
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().updateReserveClassroomForStudent(reserva, reserva2);
		} finally {
		
		if(this.inDB(reserva))
			this.delete_from(reserva);
		if(this.inDB(reserva2))
			this.delete_from(reserva2);
		}
	}
	@Test (expected= ReserveException.class)
	public void testAlterarHoraPassouMinutos() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "30", aluno1);
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent(this.dataAtual(),
				this.horaAtualAMais(-100000), sala1,
				"Grupo de Estudos", "60", aluno1);
		this.insert_into(reserva);
		
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().updateReserveClassroomForStudent(reserva, reserva2);
		} finally {
		
		if(this.inDB(reserva))
			this.delete_from(reserva);
		if(this.inDB(reserva2))
			this.delete_from(reserva2);
		}
	}
	
	
	
	@Test
	public void testExcluir() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", aluno1);
		this.insert_into(reserva);
		
		ReserveClassroomForStudentDAO.getReserveClassroomForStudent().delete(reserva);
		
		boolean resultado = this.inDB(reserva);
		
		if(resultado)
			this.delete_from(reserva);
		assertFalse("Teste de Exclusao.", resultado);
	}
	@Test (expected= ReserveException.class)
	public void testExcluirNulo() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudentDAO.getReserveClassroomForStudent().delete(null);
	}
	@Test (expected= ReserveException.class)
	public void testExcluirInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", aluno1);

		ReserveClassroomForStudentDAO.getReserveClassroomForStudent().delete(reserva);
		
		boolean resultado = this.inDB(reserva);
		
		if(resultado)
			this.delete_from(reserva);
	}
	
	
	@Test
	public void testBuscarPorDia() throws SQLException, PatrimonyException, ClientException, ReserveException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("21/12/34", "8:00", sala1,
				"Grupo de Estudos", "40", aluno1);
		
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent("21/12/34", "19:00", sala1,
				"Grupo de Estudos", "50", aluno1);
		
		this.insert_into(reserva);
		this.insert_into(reserva2);
		Vector<ReserveClassroomForStudent> vet = ReserveClassroomForStudentDAO.getReserveClassroomForStudent().searchForDay("21/12/34");
		this.delete_from(reserva);
		this.delete_from(reserva2);
		
		boolean resultado = false;
		boolean resultado2 = false;
		
		Iterator<ReserveClassroomForStudent> it = vet.iterator();
		while(it.hasNext()){
			ReserveClassroomForStudent obj = it.next();
			if(obj.equals(reserva))
				resultado = true;
			else if(obj.equals(reserva2))
				resultado2 = true;
		}
		
		assertTrue("Teste de busca", resultado && resultado2);
	}
	@Test
	public void testBuscarPorHora() throws SQLException, PatrimonyException, ClientException, ReserveException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "9:00", sala1,
				"Grupo de Estudos", "40", aluno1);
		
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent("21/12/34", "09:00", sala1,
				"Grupo de Estudos", "50", aluno1);
		
		this.insert_into(reserva);
		this.insert_into(reserva2);
		Vector<ReserveClassroomForStudent> vet = ReserveClassroomForStudentDAO.getReserveClassroomForStudent().searchForHour("09:00");
		this.delete_from(reserva);
		this.delete_from(reserva2);
		
		boolean resultado = false;
		boolean resultado2 = false;
		
		Iterator<ReserveClassroomForStudent> it = vet.iterator();
		while(it.hasNext()){
			ReserveClassroomForStudent obj = it.next();
			if(obj.equals(reserva))
				resultado = true;
			else if(obj.equals(reserva2))
				resultado2 = true;
		}
		
		assertTrue("Teste de busca", resultado && resultado2);
	}
	
	
	@Test
	public void testCadeirasDisponiveis() throws SQLException, PatrimonyException, ClientException, ReserveException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("21/12/34", "19:00", sala1,
				"Grupo de Estudos", "40", aluno1);
		
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent("21/12/34", "19:00", sala1,
				"Grupo de Estudos", "50", aluno1);
		
		this.insert_into(reserva);
		this.insert_into(reserva2);
		int c = ReserveClassroomForStudentDAO.getReserveClassroomForStudent().chairsAvailable(sala1, "21/12/34", "19:00");
		this.delete_from(reserva);
		this.delete_from(reserva2);
		assertEquals("Teste de disponibilidade de Cadeiras", c, 30);
	}

	
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
	
	
	private String select_id_aluno(Student a){
		return "SELECT id_aluno FROM aluno WHERE " +
				"aluno.nome = \"" + a.getNamePerson() + "\" and " +
				"aluno.cpf = \"" + a.getCpfPerson() + "\" and " +
				"aluno.telefone = \"" + a.getPhonePerson() + "\" and " +
				"aluno.email = \"" + a.getEmailPerson() + "\" and " +
				"aluno.matricula = \"" + a.getIdRegister() + "\"";
	}
	private String select_id_sala(Classroom sala){
		return "SELECT id_sala FROM sala WHERE " +
				"sala.codigo = \"" + sala.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + sala.getDescriptionEquipment() +  "\" and " +
				"sala.capacidade = " + sala.getCapacity();
	}
	private String where_reserva_sala_aluno(ReserveClassroomForStudent r){
		return " WHERE " +
		"id_aluno = ( " + select_id_aluno(r.getStudent()) + " ) and " +
		"id_sala = ( " + select_id_sala(r.getClassroom()) + " ) and " +
		"finalidade = \"" + r.getFinality() + "\" and " +
		"hora = \"" + r.getHour() + "\" and " +
		"data = \"" + r.getDate() + "\" and " +
		"cadeiras_reservadas = " + r.getReservedChairs();
	}
	private String values_reserva_sala_aluno(ReserveClassroomForStudent r){
		return "( " + select_id_aluno(r.getStudent()) + " ), " +
		"( " + select_id_sala(r.getClassroom()) + " ), " +
		"\"" + r.getFinality() + "\", " +
		"\"" + r.getHour() + "\", " +
		"\"" + r.getDate() + "\", " +
		r.getReservedChairs();
	}
	private void insert_into(ReserveClassroomForStudent r){
		try {
			this.executeQuery("INSERT INTO " +
					"reserva_sala_aluno (id_aluno, id_sala, finalidade, hora, data, cadeiras_reservadas) " +
					"VALUES ( " + values_reserva_sala_aluno(r) + " );");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void delete_from(ReserveClassroomForStudent r){
		try {
			this.executeQuery("DELETE FROM reserva_sala_aluno " + 
								this.where_reserva_sala_aluno(r) + " ;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private boolean inDB(ReserveClassroomForStudent r) throws SQLException{
		return this.inDBGeneric("SELECT * FROM reserva_sala_aluno " + 
								this.where_reserva_sala_aluno(r) + " ;");
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
