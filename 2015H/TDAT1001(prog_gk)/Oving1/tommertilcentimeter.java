/** dette er et program som regner om tommer til centimeter. Øvelse 1 */

import static javax.swing.JOptionPane.*;

class tommertilcentimeter {
	public static void main(String[] args) {
		
		//input fra bruker
		String tommerLest = showInputDialog("Antall tommer:");
		double tommer = Double.parseDouble(tommerLest);
		
		//utregning
		double centimeter = tommer * 2.54;
		
		//print av result
		showMessageDialog(null,"Lengden i centimeter er " + centimeter + " cm.");
	}
}
