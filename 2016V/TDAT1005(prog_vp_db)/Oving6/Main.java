import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import static javax.swing.JOptionPane.*;


class Vindu extends JFrame {
	private DefaultListModel listeInnholdFra = new DefaultListModel();
	private DefaultListModel listeInnholdTil = new DefaultListModel();
	private JList listeFra = new JList(listeInnholdFra);
	private JList listeTil = new JList(listeInnholdTil);
	private ValutaListe vl = new ValutaListe();
	
	public Vindu(String tittel) {
		setTitle(tittel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton nyValuta = new JButton("Ny valuta");
		add(nyValuta, BorderLayout.SOUTH);
		add(new TekstPanel(), BorderLayout.NORTH);
		add(new ListePanelFra(), BorderLayout.WEST);
		add(new ListePanelTil(), BorderLayout.EAST);
		Knappelytter knappelytteren = new Knappelytter();
		nyValuta.addActionListener(knappelytteren);
		pack();
		
		for (int i = 0; i < vl.getAntValuta(); i++) {
			listeInnholdFra.addElement(vl.getValutaIndex(i).getValuta());
			listeInnholdTil.addElement(vl.getValutaIndex(i).getValuta());
		}
	}


	private class TekstPanel extends JPanel {
		public TekstPanel() {
			setLayout(new GridLayout(3, 4));
			add(new JLabel("Velg fravaluta og tilvaluta fra listene:"));
		}
	}
	
	private class ListePanelFra extends JPanel {
		public ListePanelFra() {
			setLayout(new BorderLayout());
			listeFra.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scrollFra = new JScrollPane(listeFra);
			add(scrollFra, BorderLayout.CENTER);
			listeFra.addListSelectionListener(new ListeLytter());
		}
	}
	
	private class ListePanelTil extends JPanel {
		public ListePanelTil() {
			setLayout(new BorderLayout());
			listeTil.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scrollTil = new JScrollPane(listeTil);
			add(scrollTil, BorderLayout.CENTER);
			listeTil.addListSelectionListener(new ListeLytter());
		}
	}
	
	private class ListeLytter implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent event) {
			try {
				int valgFra = listeFra.getSelectedIndex();
				int valgTil = listeTil.getSelectedIndex();
				String valutaFra = "";
				String valutaTil = "";
				for (int i = 0; i < vl.getAntValuta(); i++) {
					if (valgFra == i) {
						valutaFra = vl.getValutaIndex(i).getValuta();
					}
					if (valgTil == i) {
						valutaTil = vl.getValutaIndex(i).getValuta();
					}
				}
				if (valgFra >= 0 && valgTil >= 0) {
					double belop = Double.parseDouble(showInputDialog("Oppgi belop:"));
					showMessageDialog(null, belop + " " + valutaFra + " er " + vl.valutaToValuta(valutaFra, valutaTil, belop) + " " + valutaTil + ".");
					listeFra.clearSelection();
					listeTil.clearSelection();
				}
			} catch (NumberFormatException e) {
				showMessageDialog(null, "Feil input:\n" + e);
				listeFra.clearSelection();
				listeTil.clearSelection();
			} catch (NullPointerException ne) {
				showMessageDialog(null, "Feil input:\n" + ne);
				listeFra.clearSelection();
				listeTil.clearSelection();
			} catch (Exception ex) {
				showMessageDialog(null, "Feil input:\n" + ex);
				listeFra.clearSelection();
				listeTil.clearSelection();
			}
			
		}
	}
	
	private class Knappelytter implements ActionListener {
		public void actionPerformed(ActionEvent hendelse) {
			try {
				JButton valgtKnapp = (JButton) hendelse.getSource();
				String navn = valgtKnapp.getText();
				if (navn.equals("Ny valuta")) {
					String valuta = showInputDialog("Skriv inn valutanavn: ");
					double kurs = Double.parseDouble(showInputDialog("Skriv inn kurs: \n1 NOK er lik hvor mange " + valuta + "?"));
					if (valuta != null && kurs > 0 && vl.regNyValuta(valuta, kurs)) {
						listeInnholdFra.addElement(valuta);
						listeInnholdTil.addElement(valuta);
					} else {
						showMessageDialog(null, "Finnes allerede.");
					}
				}
			} catch (NumberFormatException e) {
				showMessageDialog(null, "Feil input:\n" + e);
				listeFra.clearSelection();
				listeTil.clearSelection();
			} catch (NullPointerException ne) {
				showMessageDialog(null, "Feil input:\n" + ne);
				listeFra.clearSelection();
				listeTil.clearSelection();
			} catch (Exception ex) {
				showMessageDialog(null, "Feil input:\n" + ex);
				listeFra.clearSelection();
				listeTil.clearSelection();
			}
		}
	}
}

class Main {
	public static void main(String[] args) {
		Vindu vindu = new Vindu("Dynamisk valutakalkulator");
		vindu.setVisible(true);
	}
}