import javax.swing.*;
import java.awt.*;

public class SmileyApplet extends JApplet {
	public void init() {
		add(new Tegning());
	}
}

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