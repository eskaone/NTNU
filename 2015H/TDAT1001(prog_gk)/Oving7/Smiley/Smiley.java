import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;


 /**					
  *						
  *		VINDUET
  *  
  */
class Vindu extends JFrame {
	public Vindu(String tittel) {
		setTitle(tittel);
		setSize(350,350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Tegning tegning = new Tegning();
		add(tegning);
	}
}


 /**					
  *						
  *		TEGNINGEN
  *  
  */						
class Tegning extends JPanel {
	public void paintComponent(Graphics tegneflate) {
		super.paintComponent(tegneflate);
		setBackground(Color.WHITE);
		tegneflate.setColor(Color.YELLOW);
		tegneflate.fillOval(0,0,275,275);
		
		tegneflate.setColor(Color.BLACK);
		tegneflate.fillOval(50,75,50,50);
		tegneflate.fillOval(150,75,50,50);
		tegneflate.fillArc(70,100,120,150,0,-180);
		
		
	}
}


 /**					
  *						
  *		PROGRAMMET
  *  
  */
class Smiley {
	public static void main(String[] args) {
		Vindu vindu = new Vindu("Smiley");
		vindu.setVisible(true);
	}
}