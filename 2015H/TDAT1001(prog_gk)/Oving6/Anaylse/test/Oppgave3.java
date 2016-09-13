import static javax.swing.JOptionPane.*;
class Oppgave3{
	public static void main(String[] args){
		while(true){
			try{
				String inputTekst = showInputDialog("Skriv inn en tekst:");
				Tekstanalyse tekstA = new Tekstanalyse(inputTekst);
				String inputBokstav = showInputDialog("Skrivn inn en bokstav:");

				showMessageDialog(null, "Det er " + tekstA.antallForskjelligeBokstaver() + " forskjellige bokstaver");
				showMessageDialog(null, "Det er " + tekstA.antallBokstaver() + " bokstaver");
				showMessageDialog(null, "Det er " + tekstA.prosentIkkjeBokstaver() + " ikkje bokstaver");
				showMessageDialog(null, "Det er " + tekstA.antallAvBokstav(inputBokstav) + " stk. av " + inputBokstav.toLowerCase());
				showMessageDialog(null, tekstA.bokstavForekommerOftest());
			} catch (Exception ex){
				showMessageDialog(null, ex);
				return;
			}
		}
	}
}