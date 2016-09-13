import javax.swing.*; // klassene JFrame og JPanel

class Vindu extends JFrame {
	public Vindu(String tittel) {
		setTitle(tittel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TegningOv2 tegningen = new TegningOv2();
		tegningen.setSize(1280, 720);
		add(tegningen);
		pack();
	}
} // Vindu

public class OpenGL_Ov2 {
	public static void main(String[] args){
		Vindu etVindu = new Vindu("Oving 2");
		etVindu.setVisible(true);
	}
} // OpenGL_Ov2