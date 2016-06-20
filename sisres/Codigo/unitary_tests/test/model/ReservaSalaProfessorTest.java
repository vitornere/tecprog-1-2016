package test.model;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.Professor;
<<<<<<< HEAD
import model.ReserveClassroomForProfessor;
=======
import model.ReservaSalaProfessor;
>>>>>>> devel
import model.Classroom;

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

public class ReservaSalaProfessorTest {

	
	@Test
<<<<<<< HEAD
	public void testInstance() throws PatrimonyException, ClientException, ReserveException {
=======
	public void testInstance() throws PatrimonyException, ClienteException, ReservaException {
>>>>>>> devel
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor(this.dataAtual(), this.horaAtual(), sala,
				"Reuniao", professor);
		assertTrue("Teste de Instancia.", reserva instanceof ReserveClassroomForProfessor);
	}

	
	
	
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testProfessorNulo() throws PatrimonyException, ClientException, ReserveException {
=======
	@Test (expected= ReservaException.class)
	public void testProfessorNulo() throws PatrimonyException, ClienteException, ReservaException {
>>>>>>> devel
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Professor professor = null;
		new ReserveClassroomForProfessor(this.dataAtual(), this.horaAtual(), sala, "Pesquisa", professor);
	}
	
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testFinalidadeNula() throws PatrimonyException, ClientException, ReserveException {
=======
	@Test (expected= ReservaException.class)
	public void testFinalidadeNula() throws PatrimonyException, ClienteException, ReservaException {
>>>>>>> devel
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		new ReserveClassroomForProfessor(this.dataAtual(), this.horaAtual(), sala, null, professor);
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testFinalidadeVazia() throws PatrimonyException, ClientException, ReserveException {
=======
	@Test (expected= ReservaException.class)
	public void testFinalidadeVazia() throws PatrimonyException, ClienteException, ReservaException {
>>>>>>> devel
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		new ReserveClassroomForProfessor(this.dataAtual(), this.horaAtual(), sala, "     ", professor);
	}
	
	
	
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testSalaNula() throws PatrimonyException, ClientException, ReserveException {
=======
	@Test (expected= ReservaException.class)
	public void testSalaNula() throws PatrimonyException, ClienteException, ReservaException {
>>>>>>> devel
		Classroom sala = null;
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		new ReserveClassroomForProfessor(this.dataAtual(), this.horaAtual(), sala, "Pesquisa", professor);
	}
	
	
	
	@Test
<<<<<<< HEAD
	public void testHora() throws PatrimonyException, ClientException, ReserveException {
=======
	public void testHora() throws PatrimonyException, ClienteException, ReservaException {
>>>>>>> devel
		String hora = this.horaAtualAMais(100000000);
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor(this.dataAtual(),
				hora, sala, "Reuniao", professor);
		assertTrue("", reserva.getHour() == hora);
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testHoraNula() throws PatrimonyException, ClientException, ReserveException {
=======
	@Test (expected= ReservaException.class)
	public void testHoraNula() throws PatrimonyException, ClienteException, ReservaException {
>>>>>>> devel
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		new ReserveClassroomForProfessor(this.dataAtual(), null, sala, "Reuniao", professor);
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testHoraVazia() throws PatrimonyException, ClientException, ReserveException {
=======
	@Test (expected= ReservaException.class)
	public void testHoraVazia() throws PatrimonyException, ClienteException, ReservaException {
>>>>>>> devel
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		new ReserveClassroomForProfessor(this.dataAtual(), "    ", sala, "Pesquisa", professor);
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testHoraDespadronizada() throws PatrimonyException, ClientException, ReserveException {
=======
	@Test (expected= ReservaException.class)
	public void testHoraDespadronizada() throws PatrimonyException, ClienteException, ReservaException {
>>>>>>> devel
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		new ReserveClassroomForProfessor(this.dataAtual(), "1000", sala, "Reuniao", professor);
	}
	
	@Test
<<<<<<< HEAD
	public void testData() throws PatrimonyException, ClientException, ReserveException {
=======
	public void testData() throws PatrimonyException, ClienteException, ReservaException {
>>>>>>> devel
		String data = "12/2/33";
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor(data,
				this.horaAtual(), sala, "Aula de DS", professor);

		assertTrue("", reserva.getDate().equals("12/02/2033"));
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testDataNula() throws PatrimonyException, ClientException, ReserveException {
=======
	@Test (expected= ReservaException.class)
	public void testDataNula() throws PatrimonyException, ClienteException, ReservaException {
>>>>>>> devel
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		new ReserveClassroomForProfessor(null, this.horaAtual(), sala, "Aula de C1", professor);
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testDataVazia() throws PatrimonyException, ClientException, ReserveException {
=======
	@Test (expected= ReservaException.class)
	public void testDataVazia() throws PatrimonyException, ClienteException, ReservaException {
>>>>>>> devel
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		new ReserveClassroomForProfessor("    ", this.horaAtual(), sala, "Aula de fisica", professor);
	}
	
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testDataComChar() throws PatrimonyException, ClientException, ReserveException {
=======
	@Test (expected= ReservaException.class)
	public void testDataComChar() throws PatrimonyException, ClienteException, ReservaException {
>>>>>>> devel
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "501.341.852-69", "456678", "", "");
		new ReserveClassroomForProfessor("12/q2/2030", this.horaAtual(), sala, "Grupo de Estudos", professor);
	}
	
	@Test
<<<<<<< HEAD
	public void testEqualsTrue() throws PatrimonyException, ClientException, ReserveException {
=======
	public void testEqualsTrue() throws PatrimonyException, ClienteException, ReservaException {
>>>>>>> devel
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor(this.dataAtual(), this.horaAtual(), sala,
				"Reforco", professor);
		ReserveClassroomForProfessor reserva2 = new ReserveClassroomForProfessor(this.dataAtual(), this.horaAtual(), sala,
				"Reforco", professor);
		assertTrue("Teste de Equals.", reserva.equals(reserva2));
	}
	@Test
<<<<<<< HEAD
	public void testEqualsFalseSala() throws PatrimonyException, ClientException, ReserveException {//mesma reserva mas em salas dif
=======
	public void testEqualsFalseSala() throws PatrimonyException, ClienteException, ReservaException {//mesma reserva mas em salas dif
>>>>>>> devel
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Classroom sala2 = new Classroom("1233", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor(this.dataAtual(), this.horaAtual(), sala,
				"Reuniao", professor);
		ReserveClassroomForProfessor reserva2 = new ReserveClassroomForProfessor(this.dataAtual(), this.horaAtual(), sala2,
				"Reuniao", professor);
		
		assertFalse("Teste de Equals False.", reserva.equals(reserva2));
	}
	@Test
<<<<<<< HEAD
	public void testEqualsFalseProfessor() throws PatrimonyException, ClientException, ReserveException {
=======
	public void testEqualsFalseProfessor() throws PatrimonyException, ClienteException, ReservaException {
>>>>>>> devel
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		Professor professor2 = new Professor("testInstanceD", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor(this.dataAtual(), this.horaAtual(), sala,
				"Reuniao", professor);
		ReserveClassroomForProfessor reserva2 = new ReserveClassroomForProfessor(this.dataAtual(), this.horaAtual(), sala,
				"Reuniao", professor2);
		assertFalse("Teste de Equals False.", reserva.equals(reserva2));
	}
	@Test
<<<<<<< HEAD
	public void testEqualsFalseData() throws PatrimonyException, ClientException, ReserveException {
=======
	public void testEqualsFalseData() throws PatrimonyException, ClienteException, ReservaException {
>>>>>>> devel
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor(this.dataAtualAMais(100000000), this.horaAtual(), sala,
				"Grupo de Estudos", professor);
		ReserveClassroomForProfessor reserva2 = new ReserveClassroomForProfessor(this.dataAtual(), this.horaAtual(), sala,
				"Grupo de Estudos", professor);
		assertFalse("Teste de Equals False.", reserva.equals(reserva2));
	}
	@Test
<<<<<<< HEAD
	public void testEqualsFalseHora() throws PatrimonyException, ClientException, ReserveException {
=======
	public void testEqualsFalseHora() throws PatrimonyException, ClienteException, ReservaException {
>>>>>>> devel
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor(this.dataAtual(), this.horaAtualAMais(10000000), sala,
				"Reuniao", professor);
		ReserveClassroomForProfessor reserva2 = new ReserveClassroomForProfessor(this.dataAtual(), this.horaAtual(), sala,
				"Reuniao", professor);
		assertFalse("Teste de Equals False.", reserva.equals(reserva2));
	}
	@Test
<<<<<<< HEAD
	public void testEqualsFalseFinalidade() throws PatrimonyException, ClientException, ReserveException {
=======
	public void testEqualsFalseFinalidade() throws PatrimonyException, ClienteException, ReservaException {
>>>>>>> devel
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		ReserveClassroomForProfessor reserva = new ReserveClassroomForProfessor(this.dataAtual(), this.horaAtual(), sala,
				"Reuniao", professor);
		ReserveClassroomForProfessor reserva2 = new ReserveClassroomForProfessor(this.dataAtual(), this.horaAtual(), sala,
				"Pesquisa", professor);
		assertFalse("Teste de Equals False.", reserva.equals(reserva2));
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

}
