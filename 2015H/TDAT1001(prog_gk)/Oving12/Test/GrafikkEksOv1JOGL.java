import javax.swing.*; // klassene JFrame og JPanel

class Vindu extends JFrame {
 public Vindu(String tittel) {
 setTitle(tittel);
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 TegningOv1_1JOGL tegning = new TegningOv1_1JOGL();
 tegning.setSize(800, 600);
 add(tegning);
 pack();
 }
}

/*Klassen som inneholder main*/
class GrafikkEksOv1JOGL {
 public static void main(String[] args) {
 Vindu etVindu = new Vindu("V2005 Øving 1: Enkel grafikk");
 etVindu.setVisible(true);
 }
}