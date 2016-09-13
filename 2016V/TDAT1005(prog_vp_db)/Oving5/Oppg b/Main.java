import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Vindu extends JFrame {
	private JTextField tekstfelt = new JTextField(20);
	private JLabel hilsen = new JLabel("_______________________");

	public Vindu(String tittel) {
		setTitle(tittel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());

		JLabel ledetekst = new JLabel("Skriv inn belop:");
		add(ledetekst);
		add(tekstfelt);

		JButton knappSEK = new JButton("Til SEK fra NOK");
		add(knappSEK);

		JButton knappNOK = new JButton("Til NOK fra SEK");
		add(knappNOK);
		
		Knappelytter knappelytteren = new Knappelytter();
		knappSEK.addActionListener(knappelytteren);
		knappNOK.addActionListener(knappelytteren);

		add(hilsen);
		pack();
	}

	private class Knappelytter implements ActionListener {
		public void actionPerformed(ActionEvent hendelse) {
			try {
				JButton valgtKnapp = (JButton) hendelse.getSource();
				String valutanavn = valgtKnapp.getText();
				double kr = Double.parseDouble(tekstfelt.getText());
				if (valutanavn.equals("Til SEK fra NOK")) {
					kr *= 0.9659;
				} else {
					kr *= 1.0352;
				}
				
				String tekst = Double.toString(kr); 
				hilsen.setText(tekst);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(hilsen, "Feil format!\nError message:\n" + e);
			}
		}
	}
}

class Main {
	public static void main(String[] args) {
		Vindu etVindu = new Vindu("Valutakalkulator");
		etVindu.setVisible(true);
	}
}