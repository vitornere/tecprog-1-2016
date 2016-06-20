/**
 * Name: Test.java
 * Class that tests the creation of a new Client.
 */

package interfaces;

import entities.Client;

public class Test {
	public static void main(String[] args) {
		Client newClient = new Client("13.999.888-DF", "444.555.666", 11,
				"Test", "Test2", "Gama qd. 1000", "(61)-1234-5678",
				"cliente1@gmail.com");
		newClient.medicineRecommended("GENERIC", "PEDIATRIC");
		/**
		 * Recalling that can change the types of remedies that are
		 * "BLACK TARGE" and "GENERIC". It is also worth it for use: "ADULT" and "PEDIATRIC".
		 */
	}

}
