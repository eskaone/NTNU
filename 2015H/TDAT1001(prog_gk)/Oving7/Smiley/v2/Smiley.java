import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Random;
import java.util.Scanner;

public class Smiley extends JPanel {
	
	int xE1 = 50;
	int yE1 = 75;
	int xE1a = 1;
	int yE1a = 1;
	
	int xE2 = 150;
	int yE2 = 75;
	int xE2a = 1;
	int yE2a = 1;
	
	int xH = 10;
	int yH = 10;
	
	int xM = 70;
	int yM = 100;
	int xMa = 1;
	int yMa = 1;
	int fromDeg = 0;
	int toDeg = -180;
	
	
	
	private void animateEye1() {
		if (xE1 + xE1a < 100) 
		xE1a = 1;
		if (xE1 + xE1a > 120)
		xE1a = -1;
		
		xE1 = xE1 + xE1a;
		
	}
	
	private void animateEye2() {
		if (xE2 + xE2a < 200) 
		xE2a = 1;
		if (xE2 + xE2a > 230)
		xE2a = -1;
		
		xE2 = xE2 + xE2a;
	}
	
	private void animateMouth() {
		if (xM + xMa < 120) 
		xMa = 1;
		if (xM + xMa > 150)
		xMa = -1;
		
		xM = xM + xMa;
	}
	
	private void animateHead() {
		yH++;
		
		if (xH > 20) {
			xH--;
		} else xH++;
	}
	
	private void rave() {
		System.out.println("Rave?");
		
	}
	
	

	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		Random r = new Random();
		Color randomColor = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		
		setBackground(randomColor);
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(Color.BLACK);
		g2d.fillOval(0,0,300,300);
		
		g2d.setColor(randomColor);
		g2d.fillOval(10,10,275,275);
		
		
		g2d.setColor(Color.BLACK);
		g2d.fillOval(xE1,75,50,50);
		g2d.fillOval(xE2,75,50,50);
		g2d.fillArc(xM,100,120,150,0, -180);
		
		
		
		

	}
	
	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Rave?");
		System.out.println("1: ayy");
		System.out.println("2: naa");
		int input = sc.nextInt();
		JFrame frame = new JFrame("Smiley");
		Smiley smiley = new Smiley();
		Random r = new Random();
		frame.add(smiley);
		frame.setSize(350, 350);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		switch (input) {
		case 1:
			while (true) {
				smiley.animateEye1();
				smiley.animateEye2();
				smiley.animateMouth();
				smiley.repaint();
				Thread.sleep(r.nextInt(50));
			}
			
		case 2:
			break;
		}
		
	}
}