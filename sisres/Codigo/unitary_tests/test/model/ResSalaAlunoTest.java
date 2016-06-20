package test.model;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

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

<<<<<<< HEAD
import exception.ClientException;
import exception.PatrimonyException;
import exception.ReserveException;
=======
import exception.ClienteException;
import exception.PatrimonyException;
import exception.ReservaException;
>>>>>>> devel

public class ResSalaAlunoTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
<<<<<<< HEAD
	public void testInstance() throws PatrimonyException, ClientException, ReserveException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent(this.dataAtual(), this.horaAtual(), sala,
=======
	public void testInstance() throws PatrimonyException, ClienteException, ReservaException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		ReservaSalaAluno reserva = new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala,
>>>>>>> devel
				"Grupo de Estudos", "120", aluno);
		assertTrue("Teste de Instancia.", reserva instanceof ReserveClassroomForStudent);
	}
	
	
	
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testAlunoNulo() throws PatrimonyException, ClientException, ReserveException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Student aluno = null;
		new ReserveClassroomForStudent(this.dataAtual(), this.horaAtual(), sala, "Grupo de Estudos", "30", aluno);
=======
	@Test (expected= ReservaException.class)
	public void testAlunoNulo() throws PatrimonyException, ClienteException, ReservaException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Aluno aluno = null;
		new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala, "Grupo de Estudos", "30", aluno);
>>>>>>> devel
	}
	
	
	
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testCadeirasNula() throws PatrimonyException, ClientException, ReserveException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		new ReserveClassroomForStudent(this.dataAtual(), this.horaAtual(), sala, "Grupo de Estudos", null, aluno);
	}
	
	@Test (expected= ReserveException.class)
	public void testCadeirasVazias() throws PatrimonyException, ClientException, ReserveException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		new ReserveClassroomForStudent(this.dataAtual(), this.horaAtual(), sala, "Grupo de Estudos", "     ", aluno);
	}
	
	@Test (expected= ReserveException.class)
	public void testCadeirasDespadronizadas() throws PatrimonyException, ClientException, ReserveException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		new ReserveClassroomForStudent(this.dataAtual(), this.horaAtual(), sala, "Grupo de Estudos", "3A-", aluno);
	}
	
	@Test (expected= ReserveException.class)
	public void testCadeirasAcimaCapacidade() throws PatrimonyException, ClientException, ReserveException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		new ReserveClassroomForStudent(this.dataAtual(), this.horaAtual(), sala, "Grupo de Estudos", "121", aluno);
=======
	@Test (expected= ReservaException.class)
	public void testCadeirasNula() throws PatrimonyException, ClienteException, ReservaException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala, "Grupo de Estudos", null, aluno);
	}
	
	@Test (expected= ReservaException.class)
	public void testCadeirasVazias() throws PatrimonyException, ClienteException, ReservaException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala, "Grupo de Estudos", "     ", aluno);
	}
	
	@Test (expected= ReservaException.class)
	public void testCadeirasDespadronizadas() throws PatrimonyException, ClienteException, ReservaException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala, "Grupo de Estudos", "3A-", aluno);
	}
	
	@Test (expected= ReservaException.class)
	public void testCadeirasAcimaCapacidade() throws PatrimonyException, ClienteException, ReservaException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala, "Grupo de Estudos", "121", aluno);
>>>>>>> devel
	}
	
	
	
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testFinalidadeNula() throws PatrimonyException, ClientException, ReserveException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		new ReserveClassroomForStudent(this.dataAtual(), this.horaAtual(), sala, null, "11", aluno);
	}
	@Test (expected= ReserveException.class)
	public void testFinalidadeVazia() throws PatrimonyException, ClientException, ReserveException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		new ReserveClassroomForStudent(this.dataAtual(), this.horaAtual(), sala, "     ", "11", aluno);
=======
	@Test (expected= ReservaException.class)
	public void testFinalidadeNula() throws PatrimonyException, ClienteException, ReservaException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala, null, "11", aluno);
	}
	@Test (expected= ReservaException.class)
	public void testFinalidadeVazia() throws PatrimonyException, ClienteException, ReservaException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala, "     ", "11", aluno);
>>>>>>> devel
	}
	
	
	
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testSalaNula() throws PatrimonyException, ClientException, ReserveException {
		Classroom sala = null;
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		new ReserveClassroomForStudent(this.dataAtual(), this.horaAtual(), sala, "Grupo de Estudos", "30", aluno);
=======
	@Test (expected= ReservaException.class)
	public void testSalaNula() throws PatrimonyException, ClienteException, ReservaException {
		Classroom sala = null;
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala, "Grupo de Estudos", "30", aluno);
>>>>>>> devel
	}
	
	
	
	@Test
<<<<<<< HEAD
	public void testHora() throws PatrimonyException, ClientException, ReserveException {
		String hora = this.horaAtualAMais(100000000);
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent(this.dataAtual(),
=======
	public void testHora() throws PatrimonyException, ClienteException, ReservaException {
		String hora = this.horaAtualAMais(100000000);
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		ReservaSalaAluno reserva = new ReservaSalaAluno(this.dataAtual(),
>>>>>>> devel
				hora, sala,
				"Grupo de Estudos", "120", aluno);
		assertTrue("", reserva.getHour() == hora);
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testHoraNula() throws PatrimonyException, ClientException, ReserveException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		new ReserveClassroomForStudent(this.dataAtual(), null, sala, "Grupo de Estudos", "120", aluno);
	}
	@Test (expected= ReserveException.class)
	public void testHoraVazia() throws PatrimonyException, ClientException, ReserveException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		new ReserveClassroomForStudent(this.dataAtual(), "    ", sala, "Grupo de Estudos", "120", aluno);
	}
	@Test (expected= ReserveException.class)
	public void testHoraDespadronizada() throws PatrimonyException, ClientException, ReserveException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		new ReserveClassroomForStudent(this.dataAtual(), "1000", sala, "Grupo de Estudos", "120", aluno);
=======
	@Test (expected= ReservaException.class)
	public void testHoraNula() throws PatrimonyException, ClienteException, ReservaException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno(this.dataAtual(), null, sala, "Grupo de Estudos", "120", aluno);
	}
	@Test (expected= ReservaException.class)
	public void testHoraVazia() throws PatrimonyException, ClienteException, ReservaException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno(this.dataAtual(), "    ", sala, "Grupo de Estudos", "120", aluno);
	}
	@Test (expected= ReservaException.class)
	public void testHoraDespadronizada() throws PatrimonyException, ClienteException, ReservaException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno(this.dataAtual(), "1000", sala, "Grupo de Estudos", "120", aluno);
>>>>>>> devel
	}
	
	
	
	@Test
<<<<<<< HEAD
	public void testData() throws PatrimonyException, ClientException, ReserveException {
		String data = "12/2/33";
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent(data,
=======
	public void testData() throws PatrimonyException, ClienteException, ReservaException {
		String data = "12/2/33";
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		ReservaSalaAluno reserva = new ReservaSalaAluno(data,
>>>>>>> devel
				"8:00", sala, "Grupo de Estudos", "120", aluno);

		assertTrue("", reserva.getDate().equals("12/02/2033"));
	}
<<<<<<< HEAD
	@Test (expected= ReserveException.class)
	public void testDataNula() throws PatrimonyException, ClientException, ReserveException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		new ReserveClassroomForStudent(null, this.horaAtual(), sala, "Grupo de Estudos", "120", aluno);
	}
	@Test (expected= ReserveException.class)
	public void testDataVazia() throws PatrimonyException, ClientException, ReserveException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		new ReserveClassroomForStudent("    ", this.horaAtual(), sala, "Grupo de Estudos", "120", aluno);
	}
	@Test (expected= ReserveException.class)
	public void testDataDespadronizada() throws PatrimonyException, ClientException, ReserveException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		new ReserveClassroomForStudent("12/q2/2030", this.horaAtual(), sala, "Grupo de Estudos", "120", aluno);
=======
	@Test (expected= ReservaException.class)
	public void testDataNula() throws PatrimonyException, ClienteException, ReservaException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno(null, this.horaAtual(), sala, "Grupo de Estudos", "120", aluno);
	}
	@Test (expected= ReservaException.class)
	public void testDataVazia() throws PatrimonyException, ClienteException, ReservaException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno("    ", this.horaAtual(), sala, "Grupo de Estudos", "120", aluno);
	}
	@Test (expected= ReservaException.class)
	public void testDataDespadronizada() throws PatrimonyException, ClienteException, ReservaException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno("12/q2/2030", this.horaAtual(), sala, "Grupo de Estudos", "120", aluno);
>>>>>>> devel
	}
	
	
	@Test
<<<<<<< HEAD
	public void testEqualsTrue() throws PatrimonyException, ClientException, ReserveException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent(this.dataAtual(), this.horaAtual(), sala,
=======
	public void testEqualsTrue() throws PatrimonyException, ClienteException, ReservaException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		ReservaSalaAluno reserva = new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala,
>>>>>>> devel
				"Grupo de Estudos", "120", aluno);
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent(this.dataAtual(), this.horaAtual(), sala,
				"Grupo de Estudos", "120", aluno);
		assertTrue("Teste de Equals.", reserva.equals(reserva2));
	}
	@Test
<<<<<<< HEAD
	public void testEqualsFalseSala() throws PatrimonyException, ClientException, ReserveException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Classroom sala2 = new Classroom("1233", "Sala de Aula", "120");
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent(this.dataAtual(), this.horaAtual(), sala,
=======
	public void testEqualsFalseSala() throws PatrimonyException, ClienteException, ReservaException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Classroom sala2 = new Classroom("1233", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		ReservaSalaAluno reserva = new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala,
>>>>>>> devel
				"Grupo de Estudos", "120", aluno);
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent(this.dataAtual(), this.horaAtual(), sala2,
				"Grupo de Estudos", "120", aluno);
		assertFalse("Teste de Equals False.", reserva.equals(reserva2));
	}
	@Test
<<<<<<< HEAD
	public void testEqualsFalseAluno() throws PatrimonyException, ClientException, ReserveException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		Student aluno2 = new Student("testInstanceD", "501.341.852-69", "456678", "", "");
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent(this.dataAtual(), this.horaAtual(), sala,
=======
	public void testEqualsFalseAluno() throws PatrimonyException, ClienteException, ReservaException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		Aluno aluno2 = new Aluno("testInstanceD", "501.341.852-69", "456678", "", "");
		ReservaSalaAluno reserva = new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala,
>>>>>>> devel
				"Grupo de Estudos", "120", aluno);
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent(this.dataAtual(), this.horaAtual(), sala,
				"Grupo de Estudos", "120", aluno2);
		assertFalse("Teste de Equals False.", reserva.equals(reserva2));
	}
	@Test
<<<<<<< HEAD
	public void testEqualsFalseData() throws PatrimonyException, ClientException, ReserveException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent(this.dataAtualAMais(100000000), this.horaAtual(), sala,
=======
	public void testEqualsFalseData() throws PatrimonyException, ClienteException, ReservaException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		ReservaSalaAluno reserva = new ReservaSalaAluno(this.dataAtualAMais(100000000), this.horaAtual(), sala,
>>>>>>> devel
				"Grupo de Estudos", "120", aluno);
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent(this.dataAtual(), this.horaAtual(), sala,
				"Grupo de Estudos", "120", aluno);
		assertFalse("Teste de Equals False.", reserva.equals(reserva2));
	}
	@Test
<<<<<<< HEAD
	public void testEqualsFalseHora() throws PatrimonyException, ClientException, ReserveException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent(this.dataAtual(), this.horaAtualAMais(10000000), sala,
=======
	public void testEqualsFalseHora() throws PatrimonyException, ClienteException, ReservaException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		ReservaSalaAluno reserva = new ReservaSalaAluno(this.dataAtual(), this.horaAtualAMais(10000000), sala,
>>>>>>> devel
				"Grupo de Estudos", "120", aluno);
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent(this.dataAtual(), this.horaAtual(), sala,
				"Grupo de Estudos", "120", aluno);
		assertFalse("Teste de Equals False.", reserva.equals(reserva2));
	}
	@Test
<<<<<<< HEAD
	public void testEqualsFalseFinalidade() throws PatrimonyException, ClientException, ReserveException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent(this.dataAtual(), this.horaAtual(), sala,
=======
	public void testEqualsFalseFinalidade() throws PatrimonyException, ClienteException, ReservaException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		ReservaSalaAluno reserva = new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala,
>>>>>>> devel
				"Grupo de Estudos So q n", "120", aluno);
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent(this.dataAtual(), this.horaAtual(), sala,
				"Grupo de Estudos", "120", aluno);
		assertFalse("Teste de Equals False.", reserva.equals(reserva2));
	}
	@Test
<<<<<<< HEAD
	public void testEqualsFalseCadierasReservadas() throws PatrimonyException, ClientException, ReserveException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Student aluno = new Student("testInstance", "501.341.852-69", "456678", "", "");
		ReserveClassroomForStudent reserva = new ReserveClassroomForStudent(this.dataAtual(), this.horaAtual(), sala,
=======
	public void testEqualsFalseCadierasReservadas() throws PatrimonyException, ClienteException, ReservaException {
		Classroom sala = new Classroom("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		ReservaSalaAluno reserva = new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala,
>>>>>>> devel
				"Grupo de Estudos", "120", aluno);
		ReserveClassroomForStudent reserva2 = new ReserveClassroomForStudent(this.dataAtual(), this.horaAtual(), sala,
				"Grupo de Estudos", "1", aluno);
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
