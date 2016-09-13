/** dette er et program som sjekker om et år er skuddår eller ikke. Øvelse 2. edit: prøve å rydde litt i koden og trur æ ødela den.. heh */
import static javax.swing.JOptionPane.*;

class skuddaar {
	public static void main(String[] args){
		
		/*Brukeren av programmet skriver inn et årstall*/
		String årLest = showInputDialog("Skriv et år: ");

		/*Tallet gjøres om fra String til int*/
		int år = Integer.parseInt(årLest);

		if(år >= 0){
			/*Sjekke om årstallet er delelig med 4*/
			if(år%4 != 0){
				showMessageDialog(null, "Året er ikke et skuddår.");
			} else if (år%100 != 0){
				showMessageDialog(null, "Året er et skuddår.");
			} else if (år%400 != 0){
				showMessageDialog(null, "Året er ikke et skuddår.");
			}else{
				showMessageDialog(null, "Året er et skuddår.");
			}
		} else {
			showMessageDialog(null, "Du må skrive inn et positivt år.");
		}
	}
}