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

<<<<<<< HEAD
import model.Student;
import model.ReserveClassroomForStudent;
=======
import model.Aluno;
import model.ReservaSalaAluno;
>>>>>>> devel
import model.Classroom;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.StudentDAO;
import persistence.FactoryConnection;
<<<<<<< HEAD
import persistence.ReserveClassroomForStudentDAO;
import persistence.ClassroomDAO;

import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;
=======
import persistence.ResSalaAlunoDAO;
import persistence.ClassroomDAO;

import exception.ClienteException;
import exception.PatrimonyException;
import exception.ReservaException;
>>>>>>> devel

public class ResSalaAlunoDAOTest {

	private static Classroom sala1;
	private static Classroom sala2;
<<<<<<< HEAD
	private static Student aluno1;
	private static Student aluno2;
=======
	private static Aluno aluno1;
	private static Aluno aluno2;
>>>>>>> devel
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sala1 = new Classroom("123", "Sala de Aula", "120");
		sala2 = new Classroom("543", "Laboratorio", "30");
<<<<<<< HEAD
		aluno1 = new Student("testInstance", "501.341.852-69", "456678", "", "");
		aluno2 = new Student("Incluindo Matricula Igual", "490.491.781-20", "345543", "2222-2222", "aluno2@email");
		
		StudentDAO.getNewStudent().include(aluno1);
		StudentDAO.getNewStudent().include(aluno2);
		ClassroomDAO.getClassroom().include(sala1);
		ClassroomDAO.getClassroom().include(sala2);
=======
		aluno1 = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		aluno2 = new Aluno("Incluindo Matricula Igual", "490.491.781-20", "345543", "2222-2222", "aluno2@email");
		
		AlunoDAO.getInstance().incluir(aluno1);
		AlunoDAO.getInstance().incluir(aluno2);
		ClassroomDAO.getInstance().add(sala1);
		ClassroomDAO.getInstance().add(sala2);
>>>>>>> devel
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
<<<<<<< HEAD
		StudentDAO.getNewStudent().delete(aluno1);
		StudentDAO.getNewStudent().delete(aluno2);
		ClassroomDAO.getClassroom().delete(sala1);
		ClassroomDAO.getClassroom().delete(sala2);
=======
		AlunoDAO.getInstance().excluir(aluno1);
		AlunoDAO.getInstance().excluir(aluno2);
		ClassroomDAO.getInstance().delete(sala1);
		ClassroomDAO.getInstance().delete(sala2);
>>>>>>> devel
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
<<<<<<< HEAD
	public void testIncluir() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
=======
	public void testIncluir() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("20/12/34", "8:00", sala1,
>>>>>>> devel
				"Grupo de Estudos", "120", aluno1);
		
		ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reserva);
		
		boolean resultado = this.inDB(reserva);
		
		if(resultado)
			this.delete_from(reserva);
		assertTrue("Teste de Inclusao.", resultado);
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testIncluirNulo() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(null);
	}
	@Test (expected= ReserveException.class)
	public void testIncluirAlunoInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", new Student("tepp", "501.341.852-69", "456678", "", ""));
=======
	@Test (expected= ReservaException.class)
	public void testIncluirNulo() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ResSalaAlunoDAO.getInstance().incluir(null);
	}
	@Test (expected= ReservaException.class)
	public void testIncluirAlunoInexistente() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", new Aluno("tepp", "501.341.852-69", "456678", "", ""));
>>>>>>> devel
		
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reserva);
		} finally {
		boolean resultado = this.inDB(reserva);

		if(resultado)
			this.delete_from(reserva);
		}
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testIncluirSalaInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", new Classroom("22277883", "Laboratorio", "120"),
=======
	@Test (expected= ReservaException.class)
	public void testIncluirSalaInexistente() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("20/12/34", "8:00", new Classroom("22277883", "Laboratorio", "120"),
>>>>>>> devel
				"Grupo de Estudos", "120", aluno1);
		
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reserva);
		} finally {
		boolean resultado = this.inDB(reserva);

		if(resultado)
			this.delete_from(reserva);
		}
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testIncluirSalaReservadaProf() throws ReserveException, ClientException, 
=======
	@Test (expected= ReservaException.class)
	public void testIncluirSalaReservadaProf() throws ReservaException, ClienteException, 
>>>>>>> devel
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
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testIncluirAlunoOcupado() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", aluno1);
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent("20/12/34", "8:00", sala2,
				"Grupo de Estudos", ""+sala2.getCapacity(), aluno1);
		ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reserva);
=======
	@Test (expected= ReservaException.class)
	public void testIncluirAlunoOcupado() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", aluno1);
		ReservaSalaAluno reserva2 = new ReservaSalaAluno("20/12/34", "8:00", sala2,
				"Grupo de Estudos", ""+sala2.getCapacity(), aluno1);
		ResSalaAlunoDAO.getInstance().incluir(reserva);
>>>>>>> devel
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reserva2);
		} finally {
			if(this.inDB(reserva))
				this.delete_from(reserva);
			if(this.inDB(reserva2))
				this.delete_from(reserva2);
		}
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testIncluirSalaCheia() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
=======
	@Test (expected= ReservaException.class)
	public void testIncluirSalaCheia() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("20/12/34", "8:00", sala1,
>>>>>>> devel
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
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testIncluirDataPassouAno() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/1990", "8:00", sala1,
=======
	@Test (expected= ReservaException.class)
	public void testIncluirDataPassouAno() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("20/12/1990", "8:00", sala1,
>>>>>>> devel
				"Grupo de Estudos", "60", aluno1);
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reserva);
		} finally {
			if(this.inDB(reserva))
				this.delete_from(reserva);
		}
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testIncluirDataPassouMes() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/01/2013", "8:00", sala1,
=======
	@Test (expected= ReservaException.class)
	public void testIncluirDataPassouMes() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("20/01/2013", "8:00", sala1,
>>>>>>> devel
				"Grupo de Estudos", "60", aluno1);
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reserva);
		} finally {
			if(this.inDB(reserva))
				this.delete_from(reserva);
		}
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testIncluirDataPassouDia() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent(this.dataAtualAMais(-100000000), this.horaAtual(), sala1,
=======
	@Test (expected= ReservaException.class)
	public void testIncluirDataPassouDia() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno(this.dataAtualAMais(-100000000), this.horaAtual(), sala1,
>>>>>>> devel
				"Grupo de Estudos", "60", aluno1);
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reserva);
		} finally {
			if(this.inDB(reserva))
				this.delete_from(reserva);
		}
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testIncluirHoraPassouHora() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent(this.dataAtual(),
=======
	@Test (expected= ReservaException.class)
	public void testIncluirHoraPassouHora() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno(this.dataAtual(),
>>>>>>> devel
				 this.horaAtualAMais(-10000000), sala1,
				"Grupo de Estudos", "60", aluno1);
		try{
			ReserveClassroomForStudentDAO.getReserveClassroomForStudent().include(reserva);
		} finally {
			if(this.inDB(reserva))
				this.delete_from(reserva);
		}
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testIncluirHoraPassouMinutos() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent(this.dataAtual(),
=======
	@Test (expected= ReservaException.class)
	public void testIncluirHoraPassouMinutos() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno(this.dataAtual(),
>>>>>>> devel
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
<<<<<<< HEAD
	public void testAlterar() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
=======
	public void testAlterar() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("20/12/34", "8:00", sala1,
>>>>>>> devel
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
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarNulo1() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
=======
	@Test (expected= ReservaException.class)
	public void testAlterarNulo1() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("20/12/34", "8:00", sala1,
>>>>>>> devel
				"Grupo de Estudos", "120", aluno1);
		ReserveClassroomForStudentDAO.getReserveClassroomForStudent().updateReserveClassroomForStudent(null, reserva);
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarNulo2() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
=======
	@Test (expected= ReservaException.class)
	public void testAlterarNulo2() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("20/12/34", "8:00", sala1,
>>>>>>> devel
			"Grupo de Estudos", "120", aluno1);
		ReserveClassroomForStudentDAO.getReserveClassroomForStudent().updateReserveClassroomForStudent(reserva, null);
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
=======
	@Test (expected= ReservaException.class)
	public void testAlterarInexistente() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("20/12/34", "8:00", sala1,
>>>>>>> devel
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
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarJaInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", aluno1);
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent("27/12/34", "9:00", sala2,
=======
	@Test (expected= ReservaException.class)
	public void testAlterarJaInexistente() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("20/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", aluno1);
		ReservaSalaAluno reserva2 = new ReservaSalaAluno("27/12/34", "9:00", sala2,
>>>>>>> devel
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
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarHoraDifAlunoOcupado() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
=======
	@Test (expected= ReservaException.class)
	public void testAlterarHoraDifAlunoOcupado() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("20/12/34", "8:00", sala1,
>>>>>>> devel
				"Grupo de Estudos", "120", aluno1);
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent("20/12/34", "9:00", sala1,
				"Grupo de Estudos", "120", aluno1);
		this.insert_into(reserva);
		this.insert_into(reserva2);
		
<<<<<<< HEAD
		ReserveClassroomForStudent reserva3 = new ReserveClassroomForStudent("20/12/34", "8:00", sala2,
=======
		ReservaSalaAluno reserva3 = new ReservaSalaAluno("20/12/34", "8:00", sala2,
>>>>>>> devel
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
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarDataDifSalaOcupado() throws ReserveException, ClientException, PatrimonyException, SQLException {
=======
	@Test (expected= ReservaException.class)
	public void testAlterarDataDifSalaOcupado() throws ReservaException, ClienteException, PatrimonyException, SQLException {
>>>>>>> devel
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
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarAlunoInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("21/12/34", "8:00", sala1,
=======
	@Test (expected= ReservaException.class)
	public void testAlterarAlunoInexistente() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("21/12/34", "8:00", sala1,
>>>>>>> devel
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
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarSalaInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("21/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", aluno1);
		this.insert_into(reserva);
		
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent("20/12/34", "8:00", new Classroom("22277883", "Laboratorio", "120"),
=======
	@Test (expected= ReservaException.class)
	public void testAlterarSalaInexistente() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("21/12/34", "8:00", sala1,
				"Grupo de Estudos", "120", aluno1);
		this.insert_into(reserva);
		
		ReservaSalaAluno reserva2 = new ReservaSalaAluno("20/12/34", "8:00", new Classroom("22277883", "Laboratorio", "120"),
>>>>>>> devel
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
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarNaoHaCadeira() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
=======
	@Test (expected= ReservaException.class)
	public void testAlterarNaoHaCadeira() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("20/12/34", "8:00", sala1,
>>>>>>> devel
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
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarDataPassouAno() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
=======
	@Test (expected= ReservaException.class)
	public void testAlterarDataPassouAno() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("20/12/34", "8:00", sala1,
>>>>>>> devel
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
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarDataPassouMes() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
=======
	@Test (expected= ReservaException.class)
	public void testAlterarDataPassouMes() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("20/12/34", "8:00", sala1,
>>>>>>> devel
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
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarDataPassouDia() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
=======
	@Test (expected= ReservaException.class)
	public void testAlterarDataPassouDia() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("20/12/34", "8:00", sala1,
>>>>>>> devel
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
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarHoraPassouHora() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
=======
	@Test (expected= ReservaException.class)
	public void testAlterarHoraPassouHora() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("20/12/34", "8:00", sala1,
>>>>>>> devel
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
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarHoraPassouMinutos() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
=======
	@Test (expected= ReservaException.class)
	public void testAlterarHoraPassouMinutos() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("20/12/34", "8:00", sala1,
>>>>>>> devel
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
<<<<<<< HEAD
	public void testExcluir() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
=======
	public void testExcluir() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("20/12/34", "8:00", sala1,
>>>>>>> devel
				"Grupo de Estudos", "120", aluno1);
		this.insert_into(reserva);
		
		ReserveClassroomForStudentDAO.getReserveClassroomForStudent().delete(reserva);
		
		boolean resultado = this.inDB(reserva);
		
		if(resultado)
			this.delete_from(reserva);
		assertFalse("Teste de Exclusao.", resultado);
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testExcluirNulo() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudentDAO.getReserveClassroomForStudent().delete(null);
	}
	@Test (expected= ReserveException.class)
	public void testExcluirInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "8:00", sala1,
=======
	@Test (expected= ReservaException.class)
	public void testExcluirNulo() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ResSalaAlunoDAO.getInstance().excluir(null);
	}
	@Test (expected= ReservaException.class)
	public void testExcluirInexistente() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("20/12/34", "8:00", sala1,
>>>>>>> devel
				"Grupo de Estudos", "120", aluno1);

		ReserveClassroomForStudentDAO.getReserveClassroomForStudent().delete(reserva);
		
		boolean resultado = this.inDB(reserva);
		
		if(resultado)
			this.delete_from(reserva);
	}
	
	
	@Test
<<<<<<< HEAD
	public void testBuscarPorDia() throws SQLException, PatrimonyException, ClientException, ReserveException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("21/12/34", "8:00", sala1,
=======
	public void testBuscarPorDia() throws SQLException, PatrimonyException, ClienteException, ReservaException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("21/12/34", "8:00", sala1,
>>>>>>> devel
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
<<<<<<< HEAD
	public void testBuscarPorHora() throws SQLException, PatrimonyException, ClientException, ReserveException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("20/12/34", "9:00", sala1,
=======
	public void testBuscarPorHora() throws SQLException, PatrimonyException, ClienteException, ReservaException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("20/12/34", "9:00", sala1,
>>>>>>> devel
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
<<<<<<< HEAD
	public void testCadeirasDisponiveis() throws SQLException, PatrimonyException, ClientException, ReserveException {
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent("21/12/34", "19:00", sala1,
=======
	public void testCadeirasDisponiveis() throws SQLException, PatrimonyException, ClienteException, ReservaException {
		ReservaSalaAluno reserva = new ReservaSalaAluno("21/12/34", "19:00", sala1,
>>>>>>> devel
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
<<<<<<< HEAD
				"sala.codigo = \"" + sala.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + sala.getDescriptionEquipment() +  "\" and " +
=======
				"sala.codigo = \"" + sala.getCode() + "\" and " +
				"sala.descricao = \"" + sala.getDescription() +  "\" and " +
>>>>>>> devel
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
