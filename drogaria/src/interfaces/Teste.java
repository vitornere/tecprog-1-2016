package interfaces;

import entidades.Client;
	//Testando a Interface
	public class Teste {
		public static void main(String[] args) {
			Client novoCliente = new Client("13.999.888-DF","444.555.666", 11, "Teste", "Teste2" , "Gama qd. 1000", "(61)-1234-5678","cliente1@gmail.com");
		    novoCliente.remediosRecomendados("GENERICO", "PEDIATRICO");
		    //Lembrando que pode alterar os tipos de rem�dios, que s�o: "TARJA PRETA" e "GEN�RICO"
		    //Tamb�m vale o mesmo para o uso: "ADULTO" e "PEDI�TRICO"
		}

}

