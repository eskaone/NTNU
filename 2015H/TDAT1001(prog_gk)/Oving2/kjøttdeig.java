/**
dette er et program som sjekker hvilket merke kj�ttdeig som er billigst. �velse 2.
Lag et program som hjelper oss i forhold til f�lgende promblemstilling:
Kj�ttdeig av merke A koster kr 35,90 for 450 gram, mens kj�ttdeig av merke B koster kr 39,50 for 500 gram.
Hvilket merke er billigst? */

import static javax.swing.JOptionPane.*;

class kj�ttdeig {
	public static void main(String[] args) {

		//bruker input
		String gramInput_1 = showInputDialog("Vekt av den f�rste varen (g):");
		double gram_1 = Double.parseDouble(gramInput_1);

		String prisInput_1 = showInputDialog("Pris av den f�rste varen (kr):");
		double pris_1 = Double.parseDouble(prisInput_1);

		String gramInput_2 = showInputDialog("Vekt av den andre varen (g):");
		double gram_2 = Double.parseDouble(gramInput_2);

		String prisInput_2 = showInputDialog("Pris av den andre varen (kr):");
		double pris_2 = Double.parseDouble(prisInput_2);


		//utregning
		double forhold_1 = pris_1 / gram_1;
		double forhold_2 = pris_2 / gram_2;

		if (forhold_1 > forhold_2) showMessageDialog(null,"Den andre varen er billigst.");
		else showMessageDialog(null,"Den f�rste varen er billigst.");
	}
}


