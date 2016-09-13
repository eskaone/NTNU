import java.sql.*;
import static javax.swing.JOptionPane.*;
import javax.swing.*;

class DatabaseInsertClient {
	public static void main(String[] args) throws Exception {
		String databasedriver = "com.mysql.jdbc.Driver";
		Class.forName(databasedriver);  // laster inn driverklassen

		String databasenavn = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/haakonrp?user=haakonrp&password=pKxODeje";
		Connection forbindelse = DriverManager.getConnection(databasenavn);

		Statement setning = forbindelse.createStatement();

		boolean bool = true;
		while (bool) {
			String navn = DataLeser.lesTekst("Navn: ");
			String isbn = DataLeser.lesTekst("Isbn: ");
			int nr = DataLeser.lesHeltall("Nr: ");
			String sqlsetn = "update eksemplar set laant_av = '" + navn + "' where isbn = '" + isbn + "' and eks_nr = " + nr + " and laant_av is null;";
			if (setning.executeUpdate(sqlsetn) != 0) {
				showMessageDialog(null, "Databasen ble oppdatert.");
			} else {
				showMessageDialog(null, "Databasen ble ikke oppdatert.");
			}
			
			int svar = showConfirmDialog(null, "Skal flere personer registreres? ", "Database", YES_NO_OPTION);
			bool = (svar == YES_OPTION);
		}
		setning.close();
		forbindelse.close();
	}
}

class DataLeser {
	public static String lesTekst(String ledetekst) {
		String tekst = showInputDialog(ledetekst);
		while (tekst == null || tekst.trim().equals("")) {
			showMessageDialog(null, "Du må oppgi data.");
			tekst = showInputDialog(ledetekst);
		}
		return tekst.trim();
	}

	public static int lesHeltall(String ledetekst) {
		int tall = 0;
		boolean ok = false;
		String melding = ledetekst;
		do {
			String tallSomTekst = lesTekst(melding);
			try {
				tall = Integer.parseInt(tallSomTekst);
				ok = true;
			} catch (NumberFormatException e) {
				melding = "Du skrev: " + tallSomTekst
				+ ".\nDet er et ugyldig heltall. Prov på nytt.\n" + ledetekst;
			}
		} while (!ok);
		return tall;
	}
}

