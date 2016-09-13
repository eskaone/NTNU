import static javax.swing.JOptionPane.*;
import javax.swing.*;
import java.sql.*;

class VisDatabaseClient {
	public static void main(String[] args) throws Exception {
		String databasedriver = "com.mysql.jdbc.Driver";
		Class.forName(databasedriver);

		String databasenavn = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/haakonrp?user=haakonrp&password=pKxODeje";
		Connection forbindelse = DriverManager.getConnection(databasenavn);

		Statement setning = forbindelse.createStatement();
		int antall = 0;
		String isbn = showInputDialog("Skriv inn isbn: ");
		String out = "";
		ResultSet res = setning.executeQuery("select forfatter, tittel from boktittel where isbn = '" + isbn + "';");
		while (res.next()) {
			String forfatter = res.getString("forfatter");
			String tittel = res.getString("tittel");
			out += "Forfatter: " + forfatter + "\nTittel: " + tittel;
		}
		
		res = setning.executeQuery("select count(*) eks_nr from eksemplar where isbn = '" + isbn + "';");
		while (res.next()) {
			antall = res.getInt("eks_nr");
			out += "\nAntall: " + antall;
		}
		
		if (antall > 0) {
			showMessageDialog(null, out);
		} else {
			showMessageDialog(null, "Finnes ikke.");
		}
		
		res.close();
		setning.close();
		forbindelse.close();
	}
}