import static javax.swing.JOptionPane.*;

class tidsberegning {
	public static void main(String[] args) {

		//bruker input
		String sekunderLest = showInputDialog("Sekunder:");
		int sekunder = Integer.parseInt(sekunderLest);
		int startSekunder = sekunder;

		//utregning
		int timer = sekunder / 3600;
		sekunder = sekunder % 3600;
		int minutter = sekunder / 60;
		sekunder = sekunder % 60;

		//resultat heihoo
		showMessageDialog(null,+ startSekunder + " sekunder er " + timer + " time(r), " + minutter + " minutt(er) og " + sekunder + " sekund(er).");
	}
}