/** dette er et program som sjekker om et �r er skudd�r eller ikke. �velse 2. edit: pr�ve � rydde litt i koden og trur � �dela den.. heh */
import static javax.swing.JOptionPane.*;

class skuddaar {
	public static void main(String[] args){
		
		/*Brukeren av programmet skriver inn et �rstall*/
		String �rLest = showInputDialog("Skriv et �r: ");

		/*Tallet gj�res om fra String til int*/
		int �r = Integer.parseInt(�rLest);

		if(�r >= 0){
			/*Sjekke om �rstallet er delelig med 4*/
			if(�r%4 != 0){
				showMessageDialog(null, "�ret er ikke et skudd�r.");
			} else if (�r%100 != 0){
				showMessageDialog(null, "�ret er et skudd�r.");
			} else if (�r%400 != 0){
				showMessageDialog(null, "�ret er ikke et skudd�r.");
			}else{
				showMessageDialog(null, "�ret er et skudd�r.");
			}
		} else {
			showMessageDialog(null, "Du m� skrive inn et positivt �r.");
		}
	}
}