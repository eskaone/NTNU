import javax.swing.*; // klassene JFrame og JPanel

class Vindu extends JFrame {
	public Vindu(String tittel) {
		setTitle(tittel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TegningOv1 tegningen = new TegningOv1();
		tegningen.setSize(1024, 900);
		add(tegningen);
		pack();
	}
} // Vindu

public class OpenGL_Ov1 {
	public static void main(String[] args){
		Vindu etVindu = new Vindu("Oving 1");
		etVindu.setVisible(true);
	}
} // OpenGL_Ov1