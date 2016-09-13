import static javax.swing.JOptionPane.*;

class Main {
	
	public static void main(String[] args) {
		
		Konferansesenter k = new Konferansesenter();
		int valg;
		String[] arg = {"Registrer rom", "Reserver rom", "Skriv ut registrert informasjon", "Info om spesifikt rom", "Avslutt"};
		
		do {
			valg = showOptionDialog(null, "Hva vil du gjore?", "Konferansesenter", YES_NO_OPTION,INFORMATION_MESSAGE, null, arg, arg[0]);
			switch (valg) {
			case -1:
				return;
			case 0:
				int plass = Integer.parseInt(showInputDialog("Plass:"));
				int romNr = Integer.parseInt(showInputDialog("Romnummer:"));
				if (k.regRom(plass, romNr)) {
					showMessageDialog(null, "Rom registrert.");
				} else {
					showMessageDialog(null, "Ikke registrert. Det finnes allerede et rom med dette romnr.");
				}
				break;
				
			case 1:
				int antKunder = Integer.parseInt(showInputDialog("Antall:"));
				long fraDatoL = Long.parseLong(showInputDialog("Dato fra:\nFormat: YYYYMMDDHHMM"));
				long tilDatoL = Long.parseLong(showInputDialog("Dato til:\nFormat: YYYYMMDDHHMM"));
				String navn = showInputDialog("Navn:");
				String tlf = showInputDialog("Tlf:");
				
				Tidspunkt fraTid = new Tidspunkt(fraDatoL);
				Tidspunkt tilTid = new Tidspunkt(tilDatoL);
				Kunde kunde = new Kunde(navn, tlf);
				if (k.resRom(fraTid, tilTid, kunde, antKunder)) {
					showMessageDialog(null, "Rom reservert.");
				} else {
					showMessageDialog(null, "Rom ble ikke reservert. Enten er det ingen ledige rom, eller så overlapper det en allerede eksisterende reservasjon.");
				}
				break;
				
			case 2:
				showMessageDialog(null, k);
				break;
				
			case 3:
				romNr = Integer.parseInt(showInputDialog("Romnr.:"));
				if (k.finnRomNr(romNr) != null) {
					showMessageDialog(null, k.finnRomNr(romNr));
				} else {
					showMessageDialog(null, "Ingen rom pa dette nummeret.");
				}
				break;
				
			case 4:
				return;
			default:
				break;
			}
			
		} while (valg != -1 || valg != 4);
		
	}
}