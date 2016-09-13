import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Vindu extends JFrame {
	private JTextField tekstfelt = new JTextField(20);
	private JLabel hilsen = new JLabel("________________________________________");

	public Vindu(String tittel) {
		setTitle(tittel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());

		JLabel ledetekst = new JLabel("Skriv inn tekst:");
		add(ledetekst);
		add(tekstfelt);

		JButton knappSansSerif = new JButton("SansSerif");
		add(knappSansSerif);

		JButton knappSerif = new JButton("Serif");
		add(knappSerif);

		JButton knappDialog = new JButton("Dialog");
		add(knappDialog);
		
		JButton knappMono = new JButton("Monospaced");
		add(knappMono);
		
		Knappelytter knappelytteren = new Knappelytter();
		knappSansSerif.addActionListener(knappelytteren);
		knappSerif.addActionListener(knappelytteren);
		knappDialog.addActionListener(knappelytteren);
		knappMono.addActionListener(knappelytteren);

		add(hilsen);
		pack();
	}

	private class Knappelytter implements ActionListener {
		public void actionPerformed(ActionEvent hendelse) {
			JButton valgtKnapp = (JButton) hendelse.getSource();
			String fontnavn = valgtKnapp.getText();
			Font font;
			if (fontnavn.equals("SansSerif")) {
				font = new Font("SansSerif", Font.BOLD, 12);
			} else if (fontnavn.equals("Serif")) {
				font = new Font("Serif", Font.PLAIN, 12);
			} else if (fontnavn.equals("Dialog")) {
				font = new Font("Dialog", Font.ITALIC, 12);
			} else {
				font = new Font("Monospaced", Font.ITALIC, 12);
			}
			hilsen.setFont(font);
			String tekst = tekstfelt.getText();
			hilsen.setText(tekst);
		}
	}
}

class Main {
	public static void main(String[] args) {
		Vindu etVindu = new Vindu("Tekster og fire fonter");
		etVindu.setVisible(true);
	}
}