package testes;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import entidades.Medicamento;

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
		Medicamento medicamento = new Medicamento ("Paracetamol", "LaboratorioTeste", "Adulto", "Comprimido", "10/10/2020","1cp. a cada 8 horas");
		assertEquals(medicamento.getNome(),"Paracetamol");
	}

	@Test
	public void testGetLaboratorio() {
		Medicamento medicamento = new Medicamento ("NomeTeste", "Genérico", "Adulto", "Comprimido", "10/10/2020","1cp. a cada 8 horas");
		assertEquals(medicamento.getFabricante(),"Genérico");
	}

	@Test
	public void testGetRecomendacao() {
		Medicamento medicamento = new Medicamento ("NomeTeste", "LaboratorioTeste", "Criança com menos de 12 anos", "Comprimido", "10/10/2020","1cp. a cada 8 horas");
		assertEquals(medicamento.getRecomendacao(),"Criança com menos de 12 anos");
	}

	@Test
	public void testGetTipo() {
		Medicamento medicamento = new Medicamento ("NomeTeste", "LaboratorioTeste", "Adulto", "Efervescente", "10/10/2020","1cp. a cada 8 horas");
		assertEquals(medicamento.getTipo(),"Efervescente");
	}

	@Test
	public void testGetValidade() {
		Medicamento medicamento = new Medicamento ("NomeTeste", "LaboratorioTeste", "Adulto", "Efervescente", "01/07/2014","1cp. a cada 8 horas");
		assertEquals(medicamento.getValidade(),"01/07/2014");
	}
	public void testGetPosologia() {
		Medicamento medicamento = new Medicamento ("NomeTeste", "LaboratorioTeste", "Adulto", "Líquido", "01/07/2014","5 ml de 6 em 6 horas");
		assertEquals(medicamento.getPosologia(),"5 ml de 6 em 6 horas");
	}
}
