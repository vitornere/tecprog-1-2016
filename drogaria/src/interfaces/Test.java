package interfaces;

import entidades.Cliente;
	//Testando a Interface
	public class Teste {
		public static void main(String[] args) {
			Cliente novoCliente = new Cliente("13.999.888-DF","444.555.666", 11, "Teste", "Teste2" , "Gama qd. 1000", "(61)-1234-5678","cliente1@gmail.com");
		    novoCliente.remediosRecomendados("GENERICO", "PEDIATRICO");
		    //Lembrando que pode alterar os tipos de remédios, que são: "TARJA PRETA" e "GENÉRICO"
		    //Também vale o mesmo para o uso: "ADULTO" e "PEDIÁTRICO"
		}

}

