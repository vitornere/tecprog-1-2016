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

	/**
	 * Test if get name valid.
	 */
	@Test
	public void testGetNome() {
		Medicament medicament = new Medicament ("Paracetamol", "LaboratorioTeste", "Adulto", "Comprimido", "10/10/2020","1cp. a cada 8 horas");
		assertEquals(medicament.getNameMedicament(),"Paracetamol");
	}
	
	/**
	 * Test if get name invalid.
	 */
	@Test
	public void testGetNomeInvalid() {
		Medicament medicament = new Medicament ("Paracetamol", "LaboratorioTeste", "Adulto", "Comprimido", "10/10/2020","1cp. a cada 8 horas");
		assertNotEquals(medicament.getNameMedicament(),"Dipirona");
	}
	
	/**
	 * Test if get fabricant valid.
	 */
	@Test
	public void testGetFabricant() {
		Medicament medicament = new Medicament ("Paracetamol", "LaboratorioTeste", "Adulto", "Comprimido", "10/10/2020","1cp. a cada 8 horas");
		assertEquals(medicament.getManufacturer(),"LaboratorioTeste");
	}
	
	/**
	 * Test if get fabricant invalid.
	 */
	@Test
	public void testGetFabricantInvalid() {
		Medicament medicament = new Medicament ("Paracetamol", "LaboratorioTeste", "Adulto", "Comprimido", "10/10/2020","1cp. a cada 8 horas");
		assertNotEquals(medicament.getManufacturer(),"LaboratorioTesteInvalid");
	}
	
	/**
	 * Test if get recommendation valid.
	 */
	@Test
	public void testGetRecommendation() {
		Medicament medicament = new Medicament ("Paracetamol", "LaboratorioTeste", "Adulto", "Comprimido", "10/10/2020","1cp. a cada 8 horas");
		assertEquals(medicament.getRecommendation(),"Adulto");
	}
	
	/**
	 * Test if get recommendation invalid.
	 */
	@Test
	public void testGetRecommendationInvalid() {
		Medicament medicament = new Medicament ("Paracetamol", "LaboratorioTeste", "Adulto", "Comprimido", "10/10/2020","1cp. a cada 8 horas");
		assertNotEquals(medicament.getRecommendation(),"children");
	}
	
	/**
	 * Test if get expiration date valid.
	 */
	@Test
	public void testGetExpirationDate() {
		Medicament medicament = new Medicament ("Paracetamol", "LaboratorioTeste", "Adulto", "10-10-2020", "comprimido","1cp. a cada 8 horas");
		assertEquals(medicament.getExpirationDate(), "10-10-2020");
	}
	
	/**
	 * Test if get expiration date invalid.
	 */
	@Test
	public void testGetExpirationDateInvalid() {
		Medicament medicament = new Medicament ("Paracetamol", "LaboratorioTeste", "Adulto", "10-10-2020", "comprimido","1cp. a cada 8 horas");
		assertNotEquals(medicament.getExpirationDate(),"10/10/2021");
	}
	
	/**
	 * Test if get type valid.
	 */
	@Test
	public void testGetType() {
		Medicament medicament = new Medicament ("Paracetamol", "LaboratorioTeste", "Adulto", "10-10-2020", "comprimido","1cp. a cada 8 horas");
		assertEquals(medicament.getTipo(), "comprimido");
	}
	
	/**
	 * Test if get type invalid.
	 */
	@Test
	public void testGetTypeInvalid() {
		Medicament medicament = new Medicament ("Paracetamol", "LaboratorioTeste", "Adulto", "10-10-2020", "comprimido","1cp. a cada 8 horas");
		assertNotEquals(medicament.getTipo(),"gotas");
	}
	
	/**
	 * Test if get posology valid.
	 */
	@Test
	public void testGetPosology() {
		Medicament medicament = new Medicament ("Paracetamol", "LaboratorioTeste", "Adulto", "10-10-2020", "comprimido","1cp. a cada 8 horas");
		assertEquals(medicament.getPosologia(), "1cp. a cada 8 horas");
	}
	
	/**
	 * Test if get posology invalid.
	 */
	@Test
	public void testGetPosologyInvalid() {
		Medicament medicament = new Medicament ("Paracetamol", "LaboratorioTeste", "Adulto", "10-10-2020", "comprimido","1cp. a cada 8 horas");
		assertNotEquals(medicament.getPosologia(),"1cp. a cada 5 horas");
	}
}
