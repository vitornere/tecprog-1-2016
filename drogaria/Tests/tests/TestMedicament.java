package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Medicament;

public class TestMedicament {

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
		Medicament medicament = new Medicament ("Paracetamol", "LaboratorioTeste", "Adulto", "Comprimido", "10/10/2020","1cp. a cada 8 horas");
		assertEquals(medicament.getNameMedicament(),"Paracetamol");
	}

	@Test
	public void testGetLaboratorio() {
		Medicament medicament = new Medicament ("NomeTeste", "Gen�rico", "Adulto", "Comprimido", "10/10/2020","1cp. a cada 8 horas");
		assertEquals(medicament.getManufacturer(),"Gen�rico");
	}

	@Test
	public void testGetRecomendacao() {
		Medicament medicament = new Medicament ("NomeTeste", "LaboratorioTeste", "Crian�a com menos de 12 anos", "Comprimido", "10/10/2020","1cp. a cada 8 horas");
		assertEquals(medicament.getRecommendation(),"Crian�a com menos de 12 anos");
	}

	@Test
	public void testGetTipo() {
		Medicament medicament = new Medicament ("NomeTeste", "LaboratorioTeste", "Adulto", "10/10/2020", "Efervescente","1cp. a cada 8 horas");
		assertEquals(medicament.getTipo(),"Efervescente");
	}

	@Test
	public void testGetValidade() {
		Medicament medicament = new Medicament ("NomeTeste", "LaboratorioTeste", "Adulto", "01/07/2014", "L�quido","5 ml de 6 em 6 horas");
		assertEquals(medicament.getExpirationDate(),"01/07/2014");
	}
	
	public void testGetPosologia() {
		Medicament medicament = new Medicament ("NomeTeste", "LaboratorioTeste", "Adulto", "L�quido", "01/07/2014","5 ml de 6 em 6 horas");
		assertEquals(medicament.getPosologia(),"5 ml de 6 em 6 horas");
	}
}
