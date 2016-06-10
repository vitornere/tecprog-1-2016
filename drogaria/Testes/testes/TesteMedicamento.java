package testes;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Medicament;

public class TesteMedicamento {

	@Before
	public void setUp() throws Exception {
		System.out.println("Inicio.");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Fim.");
	}

	@Test
	public void testGetNome() {
<<<<<<< HEAD
		Medicamento medicamento = new Medicamento ("Paracetamol", "LaboratorioTeste", "Adulto", "Comprimido", "10/10/2020","1cp. a cada 8 horas");
		assertEquals(medicamento.getName(),"Paracetamol");
=======
		Medicament medicament = new Medicament ("Paracetamol", "LaboratorioTeste", "Adulto", "Comprimido", "10/10/2020","1cp. a cada 8 horas");
		assertEquals(medicament.getNome(),"Paracetamol");
>>>>>>> devel
	}

	@Test
	public void testGetLaboratorio() {
<<<<<<< HEAD
		Medicamento medicamento = new Medicamento ("NomeTeste", "Gen�rico", "Adulto", "Comprimido", "10/10/2020","1cp. a cada 8 horas");
		assertEquals(medicamento.getManufacturer(),"Gen�rico");
=======
		Medicament medicament = new Medicament ("NomeTeste", "Gen�rico", "Adulto", "Comprimido", "10/10/2020","1cp. a cada 8 horas");
		assertEquals(medicament.getFabricante(),"Gen�rico");
>>>>>>> devel
	}

	@Test
	public void testGetRecomendacao() {
<<<<<<< HEAD
		Medicamento medicamento = new Medicamento ("NomeTeste", "LaboratorioTeste", "Crian�a com menos de 12 anos", "Comprimido", "10/10/2020","1cp. a cada 8 horas");
		assertEquals(medicamento.getRecommendation(),"Crian�a com menos de 12 anos");
=======
		Medicament medicament = new Medicament ("NomeTeste", "LaboratorioTeste", "Crian�a com menos de 12 anos", "Comprimido", "10/10/2020","1cp. a cada 8 horas");
		assertEquals(medicament.getRecomendacao(),"Crian�a com menos de 12 anos");
>>>>>>> devel
	}

	@Test
	public void testGetTipo() {
<<<<<<< HEAD
		Medicamento medicamento = new Medicamento ("NomeTeste", "LaboratorioTeste", "Adulto", "Efervescente", "10/10/2020","1cp. a cada 8 horas");
		assertEquals(medicamento.getMedicamentType(),"Efervescente");
=======
		Medicament medicament = new Medicament ("NomeTeste", "LaboratorioTeste", "Adulto", "Efervescente", "10/10/2020","1cp. a cada 8 horas");
		assertEquals(medicament.getTipo(),"Efervescente");
>>>>>>> devel
	}

	@Test
	public void testGetValidade() {
<<<<<<< HEAD
		Medicamento medicamento = new Medicamento ("NomeTeste", "LaboratorioTeste", "Adulto", "Efervescente", "01/07/2014","1cp. a cada 8 horas");
		assertEquals(medicamento.getExpirationDate(),"01/07/2014");
	}
	public void testGetPosologia() {
		Medicamento medicamento = new Medicamento ("NomeTeste", "LaboratorioTeste", "Adulto", "L�quido", "01/07/2014","5 ml de 6 em 6 horas");
		assertEquals(medicamento.getPosologia(),"5 ml de 6 em 6 horas");
=======
		Medicament medicament = new Medicament ("NomeTeste", "LaboratorioTeste", "Adulto", "Efervescente", "01/07/2014","1cp. a cada 8 horas");
		assertEquals(medicament.getValidade(),"01/07/2014");
	}
	public void testGetPosologia() {
		Medicament medicament = new Medicament ("NomeTeste", "LaboratorioTeste", "Adulto", "L�quido", "01/07/2014","5 ml de 6 em 6 horas");
		assertEquals(medicament.getPosologia(),"5 ml de 6 em 6 horas");
>>>>>>> devel
	}
}
