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
import model.ReserveClassroomForProfessor;

import model.Classroom;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

<<<<<<< HEAD
import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;
=======
import exception.ClienteException;
import exception.PatrimonyException;
import exception.ReservaException;
>>>>>>> devel

import persistence.ProfessorDAO;

import persistence.FactoryConnection;

<<<<<<< HEAD
import persistence.ReserveClassroomForStudentDAO;
import persistence.ReserveClassroomForProfessorDAO;
=======
import persistence.ResSalaAlunoDAO;
import persistence.ReserveClassroomProfessorDAO;
>>>>>>> devel
import persistence.ClassroomDAO;

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
		
<<<<<<< HEAD
		ClassroomDAO.getClassroom().include(sala_a);
		ClassroomDAO.getClassroom().include(sala_b);
		ProfessorDAO.getNewProfessor().include(professor1);
		ProfessorDAO.getNewProfessor().include(professor2);		
=======
		ClassroomDAO.getInstance().add(sala_a);
		ClassroomDAO.getInstance().add(sala_b);
		ProfessorDAO.getInstance().incluir(professor1);
		ProfessorDAO.getInstance().incluir(professor2);		
>>>>>>> devel
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
<<<<<<< HEAD
		ClassroomDAO.getClassroom().delete(sala_a);
		ClassroomDAO.getClassroom().delete(sala_b);
		ProfessorDAO.getNewProfessor().delete(professor1);
		ProfessorDAO.getNewProfessor().delete(professor2);	
=======
		ClassroomDAO.getInstance().delete(sala_a);
		ClassroomDAO.getInstance().delete(sala_b);
		ProfessorDAO.getInstance().excluir(professor1);
		ProfessorDAO.getInstance().excluir(professor2);	
>>>>>>> devel
	}

	@Test
	public void testInstance() {
<<<<<<< HEAD
		assertTrue("Teste de Instancia", ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor() instanceof ReserveClassroomForProfessorDAO);
	}
	
	@Test
	public void testIncluir() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("20/12/34", "8:00", sala_a,
				"Aula de reforco", professor1);
		
		ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().incluir(reserva);
=======
		assertTrue("Teste de Instancia", ReserveClassroomProfessorDAO.getInstance() instanceof ReserveClassroomProfessorDAO);
	}
	
	@Test
	public void testIncluir() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Aula de reforco", professor1);
		
		ReserveClassroomProfessorDAO.getInstance().add(reserva);
>>>>>>> devel
		
		boolean resultado = this.inDB(reserva);
		
		if(resultado)
			this.executeQuery("DELETE FROM reserva_sala_professor WHERE data = \"20/12/34\";");
		
		assertTrue("Teste de Inclusao.", resultado);
	}
	
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testIncluirNulo() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().incluir(null);
	}
	@Test (expected= ReserveException.class)
	public void testReservaPorProfessorInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("20/12/34", "8:00", sala_a,
				"Reuniao", new Professor("Inexistente", "501.341.852-69", "456678", "", ""));
		
		try{
			ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().incluir(reserva);
=======
	@Test (expected= ReservaException.class)
	public void testIncluirNulo() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReserveClassroomProfessorDAO.getInstance().add(null);
	}
	@Test (expected= ReservaException.class)
	public void testReservaPorProfessorInexistente() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Reuniao", new Professor("Inexistente", "501.341.852-69", "456678", "", ""));
		
		try{
			ReserveClassroomProfessorDAO.getInstance().add(reserva);
>>>>>>> devel
		} finally {
			this.executeQuery("DELETE FROM reserva_sala_professor;");
		}
	}
	
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testIncluirSalaInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("20/12/34", "8:00", new Classroom("222", "Laboratorio", "20"),
				"Grupo de Estudos", professor1);
		
		try{
			ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().incluir(reserva);
=======
	@Test (expected= ReservaException.class)
	public void testIncluirSalaInexistente() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", new Classroom("222", "Laboratorio", "20"),
				"Grupo de Estudos", professor1);
		
		try{
			ReserveClassroomProfessorDAO.getInstance().add(reserva);
>>>>>>> devel
		} finally {
			this.executeQuery("DELETE FROM reserva_sala_professor;");
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
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("20/12/34", "8:00", sala_a,
				"Aula de MDS",  professor1);
<<<<<<< HEAD
		ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().incluir(reserva);
=======
		ReserveClassroomProfessorDAO.getInstance().add(reserva);
>>>>>>> devel
		
		ReserveClassroomForProfessor reserva2 = new ReserveClassroomForProfessor("20/12/34", "8:00", sala_a,
				"Aula de PDS",  professor2);
		
		try{
<<<<<<< HEAD
			ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().incluir(reserva2);
=======
			ReserveClassroomProfessorDAO.getInstance().add(reserva2);
>>>>>>> devel
		} finally {
				
		this.executeQuery("DELETE FROM reserva_sala_professor;");
		
		}
		
	}
	@Test
<<<<<<< HEAD
	public void testIncluirSalaReservadaAluno() throws ReserveException, ClientException, 
=======
	public void testIncluirSalaReservadaAluno() throws ReservaException, ClienteException, 
>>>>>>> devel
											PatrimonyException, SQLException 
	{
		this.executeQuery("INSERT INTO aluno (nome, cpf, matricula) " +
		"VALUES (\"Aluno\", \"257.312.954-33\", \"33108\");");
		this.executeQuery("INSERT INTO reserva_sala_aluno (id_aluno,id_sala,finalidade,hora,data, cadeiras_reservadas) "+
		"VALUES ((SELECT id_aluno FROM aluno WHERE cpf = \"257.312.954-33\")," +
				"(SELECT id_sala FROM sala WHERE codigo = \"S2\")," +
				"\"Estudo de Fisica\", \"08:00\", \"20/12/2013\", 20);");
		
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("20/12/13", "8:00", sala_a,
				"Aula de EA",  professor1);
		
<<<<<<< HEAD
		ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().incluir(reserva);
=======
		ReserveClassroomProfessorDAO.getInstance().add(reserva);
>>>>>>> devel
		
			
		boolean resultadoProf = this.inDB(reserva);
		boolean resultadoAluno = this.inDBGeneric("SELECT * FROM reserva_sala_aluno " +
				"INNER JOIN sala ON sala.id_sala = reserva_sala_aluno.id_sala " +
				"INNER JOIN aluno ON aluno.id_aluno = reserva_sala_aluno.id_aluno;");
		
				
		this.executeQuery("DELETE FROM aluno;");
		this.executeQuery("DELETE FROM reserva_sala_aluno;");
		this.executeQuery("DELETE FROM reserva_sala_professor;");
		
		
		assertTrue("Sala reservada por aluno", (resultadoProf && !resultadoAluno));
		
		}
<<<<<<< HEAD
	public void testIncluirDataPassouAno() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("20/12/1990", "8:00", sala_a,
				"Grupo de Estudos", professor1);
		try{
			ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().incluir(reserva);
=======
	public void testIncluirDataPassouAno() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/1990", "8:00", sala_a,
				"Grupo de Estudos", professor1);
		try{
			ReserveClassroomProfessorDAO.getInstance().add(reserva);
>>>>>>> devel
		} finally {
			if(this.inDB(reserva))
				this.delete_from_professor(reserva);
		}
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testIncluirDataPassouMes() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("20/01/2013", "8:00", sala_a,
				"Grupo de Estudos", professor1);
		try{
			ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().incluir(reserva);
=======
	@Test (expected= ReservaException.class)
	public void testIncluirDataPassouMes() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/01/2013", "8:00", sala_a,
				"Grupo de Estudos", professor1);
		try{
			ReserveClassroomProfessorDAO.getInstance().add(reserva);
>>>>>>> devel
		} finally {
			if(this.inDB(reserva))
				this.delete_from_professor(reserva);
		}
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testIncluirDataPassouDia() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor(this.dataAtualAMais(-100000000), this.horaAtual(), sala_a,
				"Grupo de Estudos", professor1);
		try{
			ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().incluir(reserva);
=======
	@Test (expected= ReservaException.class)
	public void testIncluirDataPassouDia() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor(this.dataAtualAMais(-100000000), this.horaAtual(), sala_a,
				"Grupo de Estudos", professor1);
		try{
			ReserveClassroomProfessorDAO.getInstance().add(reserva);
>>>>>>> devel
		} finally {
			if(this.inDB(reserva))
				this.delete_from_professor(reserva);
		}
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testIncluirHoraPassouHora() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor(this.dataAtual(),
				 this.horaAtualAMais(-10000000), sala_a,
				"Grupo de Estudos",  professor1);
		try{
			ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().incluir(reserva);
=======
	@Test (expected= ReservaException.class)
	public void testIncluirHoraPassouHora() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor(this.dataAtual(),
				 this.horaAtualAMais(-10000000), sala_a,
				"Grupo de Estudos",  professor1);
		try{
			ReserveClassroomProfessorDAO.getInstance().add(reserva);
>>>>>>> devel
		} finally {
			if(this.inDB(reserva))
				this.delete_from_professor(reserva);
		}
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testIncluirHoraPassouMinutos() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor(this.dataAtual(),
				this.horaAtualAMais(-100000), sala_a,
				"Grupo de Estudos", professor1);
		try{
			ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().incluir(reserva);
=======
	@Test (expected= ReservaException.class)
	public void testIncluirHoraPassouMinutos() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor(this.dataAtual(),
				this.horaAtualAMais(-100000), sala_a,
				"Grupo de Estudos", professor1);
		try{
			ReserveClassroomProfessorDAO.getInstance().add(reserva);
>>>>>>> devel
		} finally {
			if(this.inDB(reserva))
				this.delete_from_professor(reserva);
		}
	}
	
	
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testIncluirProfessorOcupado() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("20/12/13", "8:00", sala_a,
=======
	@Test (expected= ReservaException.class)
	public void testIncluirProfessorOcupado() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/13", "8:00", sala_a,
>>>>>>> devel
				"Aulao pre-prova", professor1);
		ReserveClassroomForProfessor reserva2 = new ReserveClassroomForProfessor("20/12/13", "8:00", sala_a,
				"Aulao pre-prova", professor1);
<<<<<<< HEAD
		ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().incluir(reserva);
		try{
			ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().incluir(reserva2);
=======
		ReserveClassroomProfessorDAO.getInstance().add(reserva);
		try{
			ReserveClassroomProfessorDAO.getInstance().add(reserva2);
>>>>>>> devel
		} finally {
			this.executeQuery("DELETE FROM reserva_sala_professor;");
		}
		
		
	}
	@Test
<<<<<<< HEAD
	public void testAlterar() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva1 = new ReserveClassroomForProfessor("20/12/13", "8:00", sala_a,
=======
	public void testAlterar() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva1 = new ReservaSalaProfessor("20/12/13", "8:00", sala_a,
>>>>>>> devel
				"Pesquisa", professor1);
		
		ReserveClassroomForProfessor reserva2 = new ReserveClassroomForProfessor("21/12/34", "19:00", sala_a,
				"Pesquisa", professor1);
		
		this.executeQuery("INSERT INTO " +
				"reserva_sala_professor (id_professor, id_sala, finalidade, hora, data) " +
				"VALUES ( " + values_reserva_sala_professor(reserva1) + " );");
		
		
<<<<<<< HEAD
		ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().updateReserveClassroomForProfessor(reserva1, reserva2);
=======
		ReserveClassroomProfessorDAO.getInstance().change(reserva1, reserva2);
>>>>>>> devel
		
		boolean resultado = this.inDB(reserva2);
		
		this.executeQuery("DELETE FROM reserva_sala_professor;");
		
		assertTrue("Teste de Alteracao.", resultado);
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterar_AntigoNulo() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("20/12/34", "8:00", sala_a,
				"Grupo de pesquisa", professor1);
		ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().updateReserveClassroomForProfessor(null, reserva);
	}
	@Test (expected= ReserveException.class)
	public void testAlterar_NovoNulo() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("20/12/34", "8:00", sala_a,
			"Grupo de pesquisa", professor1);
		ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().updateReserveClassroomForProfessor(reserva, null);
	}
	@Test (expected= ReserveException.class)
	public void testAlterarInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("20/12/34", "8:00", sala_a,
=======
	@Test (expected= ReservaException.class)
	public void testAlterar_AntigoNulo() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Grupo de pesquisa", professor1);
		ReserveClassroomProfessorDAO.getInstance().change(null, reserva);
	}
	@Test (expected= ReservaException.class)
	public void testAlterar_NovoNulo() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
			"Grupo de pesquisa", professor1);
		ReserveClassroomProfessorDAO.getInstance().change(reserva, null);
	}
	@Test (expected= ReservaException.class)
	public void testAlterarInexistente() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
>>>>>>> devel
				"Grupo de pesquisa", professor1);
		
		ReserveClassroomForProfessor reserva2 = new ReserveClassroomForProfessor("20/12/34", "8:00", sala_a,
				"Grupo de pesquisa", professor1);
		try{
<<<<<<< HEAD
			ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().updateReserveClassroomForProfessor(reserva, reserva2);
=======
			ReserveClassroomProfessorDAO.getInstance().change(reserva, reserva2);
>>>>>>> devel
		} finally {
			this.executeQuery("DELETE FROM reserva_sala_professor;");
		}
		
	}
<<<<<<< HEAD
	public void testAlterarDataPassouAno() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("20/12/34", "8:00", sala_a,
=======
	public void testAlterarDataPassouAno() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
>>>>>>> devel
				"Grupo de Estudos",  professor1);
		ReserveClassroomForProfessor reserva2 = new ReserveClassroomForProfessor("20/12/1990", "8:00", sala_a,
				"Grupo de Estudos", professor2);
		this.insert_into(reserva);
		
		try{
<<<<<<< HEAD
			ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().updateReserveClassroomForProfessor(reserva, reserva2);
=======
			ReserveClassroomProfessorDAO.getInstance().change(reserva, reserva2);
>>>>>>> devel
		} finally {
		
		if(this.inDB(reserva))
			this.delete_from_professor(reserva);
		if(this.inDB(reserva2))
			this.delete_from_professor(reserva2);
		}
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarDataPassouMes() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("20/12/34", "8:00", sala_a,
=======
	@Test (expected= ReservaException.class)
	public void testAlterarDataPassouMes() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
>>>>>>> devel
				"Grupo de Estudos",  professor1);
		ReserveClassroomForProfessor reserva2 = new ReserveClassroomForProfessor("20/01/2013", "8:00", sala_a,
				"Grupo de Estudos", professor2);
		this.insert_into(reserva);
		
		try{
<<<<<<< HEAD
			ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().updateReserveClassroomForProfessor(reserva, reserva2);
=======
			ReserveClassroomProfessorDAO.getInstance().change(reserva, reserva2);
>>>>>>> devel
		} finally {
		
		if(this.inDB(reserva))
			this.delete_from_professor(reserva);
		if(this.inDB(reserva2))
			this.delete_from_professor(reserva2);
		}
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarDataPassouDia() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("20/12/34", "8:00", sala_a,
=======
	@Test (expected= ReservaException.class)
	public void testAlterarDataPassouDia() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
>>>>>>> devel
				"Grupo de Estudos",  professor1);
		ReserveClassroomForProfessor reserva2 = new ReserveClassroomForProfessor(this.dataAtualAMais(-100000000), this.horaAtual(), sala_a,
				"Grupo de Estudos",  professor1);
		this.insert_into(reserva);
		
		try{
<<<<<<< HEAD
			ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().updateReserveClassroomForProfessor(reserva, reserva2);
=======
			ReserveClassroomProfessorDAO.getInstance().change(reserva, reserva2);
>>>>>>> devel
		} finally {
		
		if(this.inDB(reserva))
			this.delete_from_professor(reserva);
		if(this.inDB(reserva2))
			this.delete_from_professor(reserva2);
		}
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarHoraPassouHora() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("20/12/34", "8:00", sala_a,
=======
	@Test (expected= ReservaException.class)
	public void testAlterarHoraPassouHora() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
>>>>>>> devel
				"Grupo de Estudos",  professor1);
		ReserveClassroomForProfessor reserva2 = new ReserveClassroomForProfessor(this.dataAtual(),
				 this.horaAtualAMais(-10000000), sala_a,
				"Grupo de Estudos",  professor1);
		this.insert_into(reserva);
		
		try{
<<<<<<< HEAD
			ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().updateReserveClassroomForProfessor(reserva, reserva2);
=======
			ReserveClassroomProfessorDAO.getInstance().change(reserva, reserva2);
>>>>>>> devel
		} finally {
		
		if(this.inDB(reserva))
			this.delete_from_professor(reserva);
		if(this.inDB(reserva2))
			this.delete_from_professor(reserva2);
		}
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarHoraPassouMinutos() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("20/12/34", "8:00", sala_a,
=======
	@Test (expected= ReservaException.class)
	public void testAlterarHoraPassouMinutos() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
>>>>>>> devel
				"Grupo de Estudos",  professor1);
		ReserveClassroomForProfessor reserva2 = new ReserveClassroomForProfessor(this.dataAtual(),
				this.horaAtualAMais(-100000), sala_a,
				"Grupo de Estudos",  professor1);
		this.insert_into(reserva);
		
		try{
<<<<<<< HEAD
			ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().updateReserveClassroomForProfessor(reserva, reserva2);
=======
			ReserveClassroomProfessorDAO.getInstance().change(reserva, reserva2);
>>>>>>> devel
		} finally {
		
		if(this.inDB(reserva))
			this.delete_from_professor(reserva);
		if(this.inDB(reserva2))
			this.delete_from_professor(reserva2);
		}
	}
	
	
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarJaInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("20/12/34", "8:00", sala_a,
=======
	@Test (expected= ReservaException.class)
	public void testAlterarJaInexistente() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
>>>>>>> devel
				"Grupo de pesquisa", professor1);
		ReserveClassroomForProfessor reserva2 = new ReserveClassroomForProfessor("27/12/34", "9:00", sala_b,
				"Grupo d", professor2);
		this.insert_into(reserva);
		this.insert_into(reserva2);
		
		try{
<<<<<<< HEAD
			ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().updateReserveClassroomForProfessor(reserva2, reserva);
=======
			ReserveClassroomProfessorDAO.getInstance().change(reserva2, reserva);
>>>>>>> devel
		} finally {
			this.executeQuery("DELETE FROM reserva_sala_professor;");
		}
		
	}
	
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarHoraReservaFeita() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("20/12/34", "8:00", sala_a,
=======
	@Test (expected= ReservaException.class)
	public void testAlterarHoraReservaFeita() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
>>>>>>> devel
				"Grupo de pesquisa", professor1);
		ReserveClassroomForProfessor reserva2 = new ReserveClassroomForProfessor("20/12/34", "9:00", sala_a,
				"Grupo de pesquisa", professor1);
		this.insert_into(reserva);
		this.insert_into(reserva2);
		
		ReserveClassroomForProfessor reserva3 = new ReserveClassroomForProfessor("20/12/34", "8:00", sala_b,
				"Grupo de Estudos", professor1);
		
		try{
<<<<<<< HEAD
			ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().updateReserveClassroomForProfessor(reserva2, reserva3);
=======
			ReserveClassroomProfessorDAO.getInstance().change(reserva2, reserva3);
>>>>>>> devel
		} finally {
		
			this.executeQuery("DELETE FROM reserva_sala_professor;");
		}
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarDataDifSalaOcupada() throws ReserveException, ClientException, PatrimonyException, SQLException {
=======
	@Test (expected= ReservaException.class)
	public void testAlterarDataDifSalaOcupada() throws ReservaException, ClienteException, PatrimonyException, SQLException {
>>>>>>> devel
		this.executeQuery("INSERT INTO professor (nome, cpf, matricula) " +
				"VALUES (\"Professor\", \"257.312.954-33\", \"11009988\");");
		this.executeQuery("INSERT INTO reserva_sala_professor (id_professor,id_sala,finalidade,hora,data) "+
				"VALUES ((SELECT id_professor FROM professor WHERE cpf = \"257.312.954-33\")," +
						"(SELECT id_sala FROM sala WHERE codigo = \"S2\")," +
						"\"Aula de Calculo\", \"8:00\", \"20/12/34\");");
		
				
		
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("21/12/34", "8:00", sala_a,
				"Grupo de Pesquisa", professor1);
		this.insert_into(reserva);
		
		ReserveClassroomForProfessor reserva2 = new ReserveClassroomForProfessor("20/12/34", "8:00", sala_a,
				"Grupo de Estudos", professor1);
		
		try{
<<<<<<< HEAD
			ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().updateReserveClassroomForProfessor(reserva, reserva2);
=======
			ReserveClassroomProfessorDAO.getInstance().change(reserva, reserva2);
>>>>>>> devel
		} finally {
				
		this.executeQuery("DELETE FROM professor WHERE cpf = \"257.312.954-33\"");
		this.executeQuery("DELETE FROM reserva_sala_professor");
		}
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarProfessorInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("21/12/34", "8:00", sala_a,
=======
	@Test (expected= ReservaException.class)
	public void testAlterarProfessorInexistente() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("21/12/34", "8:00", sala_a,
>>>>>>> devel
				"Grupo de pesquisa", professor1);
		this.insert_into(reserva);
		
		ReserveClassroomForProfessor reserva2 = new ReserveClassroomForProfessor("20/12/34", "8:00", sala_a,
				"Grupo de pesquisa", new Professor("Nao Existe", "501.341.852-69", "456678", "", ""));
		
		try{
<<<<<<< HEAD
			ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().updateReserveClassroomForProfessor(reserva, reserva2);
=======
			ReserveClassroomProfessorDAO.getInstance().change(reserva, reserva2);
>>>>>>> devel
		} finally {
			this.executeQuery("DELETE FROM reserva_sala_professor;");
		}
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlterarSalaInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("21/12/34", "8:00", sala_a,
				"Grupo de pesquisa", professor1);
		this.insert_into(reserva);
		
		ReserveClassroomForProfessor reserva2 = new ReserveClassroomForProfessor("20/12/34", "8:00", new Classroom("S5", "Sala de aula", "120"),
				"Grupo de Estudos", professor1);
		
		try{
			ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().updateReserveClassroomForProfessor(reserva, reserva2);
=======
	@Test (expected= ReservaException.class)
	public void testAlterarSalaInexistente() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("21/12/34", "8:00", sala_a,
				"Grupo de pesquisa", professor1);
		this.insert_into(reserva);
		
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor("20/12/34", "8:00", new Classroom("S5", "Sala de aula", "120"),
				"Grupo de Estudos", professor1);
		
		try{
			ReserveClassroomProfessorDAO.getInstance().change(reserva, reserva2);
>>>>>>> devel
		} finally {
			this.executeQuery("DELETE FROM reserva_sala_professor;");
		}
	}
	@Test
<<<<<<< HEAD
	public void testExcluir() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("20/12/34", "8:00", sala_a,
				"Grupo de Pesquisa", professor1);
		
		this.executeQuery("INSERT INTO reserva_sala_professor (id_professor,id_sala,finalidade,hora,data) "+
				"VALUES ((SELECT id_professor FROM professor WHERE cpf = \"" + reserva.getProfessor().getCpfPerson() + "\")," + 
						"(SELECT id_sala FROM sala WHERE codigo = \"" + sala_a.getIdEquipment() + "\")," +
						"\"Grupo de Pesquisa\", \"08:00\", \"20/12/2034\");");
		
		ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().delete(reserva);
=======
	public void testExcluir() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Grupo de Pesquisa", professor1);
		
		this.executeQuery("INSERT INTO reserva_sala_professor (id_professor,id_sala,finalidade,hora,data) "+
				"VALUES ((SELECT id_professor FROM professor WHERE cpf = \"" + reserva.getProfessor().getCpf() + "\")," + 
						"(SELECT id_sala FROM sala WHERE codigo = \"" + sala_a.getCode() + "\")," +
						"\"Grupo de Pesquisa\", \"08:00\", \"20/12/2034\");");
		
		ReserveClassroomProfessorDAO.getInstance().delete(reserva);
>>>>>>> devel
		
		boolean resultado = this.inDB(reserva);
		
		this.executeQuery("DELETE FROM reserva_sala_professor;");
		
		assertFalse("Teste de Exclusao.", resultado);
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testExcluirNulo() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().delete(null);
	}
	@Test (expected= ReserveException.class)
	public void testExcluirInexistente() throws ReserveException, ClientException, PatrimonyException, SQLException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("20/12/34", "8:00", sala_a,
				"Reuniao", professor1);

		ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().delete(reserva);
=======
	@Test (expected= ReservaException.class)
	public void testExcluirNulo() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReserveClassroomProfessorDAO.getInstance().delete(null);
	}
	@Test (expected= ReservaException.class)
	public void testExcluirInexistente() throws ReservaException, ClienteException, PatrimonyException, SQLException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
				"Reuniao", professor1);

		ReserveClassroomProfessorDAO.getInstance().delete(reserva);
>>>>>>> devel
		
		this.executeQuery("DELETE FROM reserva_sala_professor;");
	}
	
		
	@Test
<<<<<<< HEAD
	public void testBuscarPorData() throws SQLException, PatrimonyException, ClientException, ReserveException {
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor("20/12/34", "8:00", sala_a,
=======
	public void testBuscarPorData() throws SQLException, PatrimonyException, ClienteException, ReservaException {
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/34", "8:00", sala_a,
>>>>>>> devel
				"Reuniao", professor1);
		
		ReserveClassroomForProfessor reserva2 = new ReserveClassroomForProfessor("20/12/34", "19:00", sala_a,
				"Reuniao", professor1);
		
		this.executeQuery("INSERT INTO reserva_sala_professor (id_professor,id_sala,finalidade,hora,data) "+
<<<<<<< HEAD
				"VALUES ((SELECT id_professor FROM professor WHERE cpf = \"" + reserva.getProfessor().getCpfPerson() + "\")," + 
						"(SELECT id_sala FROM sala WHERE codigo = \"" + sala_a.getIdEquipment() + "\")," +
						"\"" + reserva.getFinality() + "\", \"" +
						reserva.getHour() + "\", \"" + reserva.getDate() +"\");");
		
		this.executeQuery("INSERT INTO reserva_sala_professor (id_professor,id_sala,finalidade,hora,data) "+
				"VALUES ((SELECT id_professor FROM professor WHERE cpf = \"" + reserva2.getProfessor().getCpfPerson() + "\")," + 
						"(SELECT id_sala FROM sala WHERE codigo = \"" + sala_a.getIdEquipment() + "\")," +
						"\"" + reserva2.getFinality() + "\", \"" +
						reserva2.getHour() + "\", \"" + reserva2.getDate() +"\");");
		
		Vector<ReserveClassroomForProfessor> vet = ReserveClassroomForProfessorDAO.getReserveClassroomForProfessor().searchForDate("20/12/2034");
=======
				"VALUES ((SELECT id_professor FROM professor WHERE cpf = \"" + reserva.getProfessor().getCpf() + "\")," + 
						"(SELECT id_sala FROM sala WHERE codigo = \"" + sala_a.getCode() + "\")," +
						"\"" + reserva.getFinalidade() + "\", \"" +
						reserva.getHora() + "\", \"" + reserva.getData() +"\");");
		
		this.executeQuery("INSERT INTO reserva_sala_professor (id_professor,id_sala,finalidade,hora,data) "+
				"VALUES ((SELECT id_professor FROM professor WHERE cpf = \"" + reserva2.getProfessor().getCpf() + "\")," + 
						"(SELECT id_sala FROM sala WHERE codigo = \"" + sala_a.getCode() + "\")," +
						"\"" + reserva2.getFinalidade() + "\", \"" +
						reserva2.getHora() + "\", \"" + reserva2.getData() +"\");");
		
		Vector<ReservaSalaProfessor> vet = ReserveClassroomProfessorDAO.getInstance().buscarPorData("20/12/2034");
>>>>>>> devel
		
		
		boolean resultado = false;
		boolean resultado2 = false;
		
		Iterator<ReserveClassroomForProfessor> it = vet.iterator();
		while(it.hasNext()){
			ReserveClassroomForProfessor obj = it.next();
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
<<<<<<< HEAD
				"sala.codigo = \"" + sala.getIdEquipment() + "\" and " +
				"sala.descricao = \"" + sala.getDescriptionEquipment() +  "\" and " +
=======
				"sala.codigo = \"" + sala.getCode() + "\" and " +
				"sala.descricao = \"" + sala.getDescription() +  "\" and " +
>>>>>>> devel
				"sala.capacidade = " + sala.getCapacity();
	}
	private String where_reserva_sala_professor(ReserveClassroomForProfessor r){
		return " WHERE " +
		"id_professor = ( " + select_id_professor(r.getProfessor()) + " ) and " +
		"id_sala = ( " + select_id_sala(r.getClassroom()) + " ) and " +
		"finalidade = \"" + r.getFinality() + "\" and " +
		"hora = \"" + r.getHour() + "\" and " +
		"data = \"" + r.getDate() + "\"";
	}
	private String values_reserva_sala_professor(ReserveClassroomForProfessor r){
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

	private String insert_into(ReserveClassroomForProfessor r){
		return "INSERT INTO " +
				"reserva_sala_professor (id_professor, id_sala, finalidade, hora, data) " +
				"VALUES ( " + values_reserva_sala_professor(r) + " );";
	}
	
	private String delete_from_professor(ReserveClassroomForProfessor r){
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
			
	private boolean inDB(ReserveClassroomForProfessor reserva) throws SQLException{
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
