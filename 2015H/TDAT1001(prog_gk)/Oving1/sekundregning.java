import static javax.swing.JOptionPane.*;

class sekundregning {
	public static void main(String[] args) {
		
		//input fra bruker
		String timerLest = showInputDialog("Timer:");
		String minutterLest = showInputDialog("Minutter:");
		String sekunderLest = showInputDialog("Sekunder:");
		double timer = Double.parseDouble(timerLest);
		double minutter = Double.parseDouble(minutterLest);
		double sekunder = Double.parseDouble(sekunderLest);
		
		//utregning
		double timertilsekund = timer * 3600;
		double minuttertilsekund = minutter * 60;
		double sekundtilsekund = sekunder * 1;
		double antallsekunder = timertilsekund + minuttertilsekund +sekundtilsekund;
		
		//print av resultat
		showMessageDialog(null,"Omregnet til sekunder er alt dette " + antallsekunder + "  sekunder.");
	}
}