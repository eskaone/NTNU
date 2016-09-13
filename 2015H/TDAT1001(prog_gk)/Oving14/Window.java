import javax.swing.*;
import com.jogamp.opengl.GLCapabilities;

class Window extends JFrame {
	public Window(String title, int w, int h, Drawing d) {
		setResizable(false);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		d.setBounds(0, 0, w, h);
		add(d);
		pack();
	}
} 