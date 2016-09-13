// Java classes:
import javax.swing.*; // klassene JFrame og JPanel

//JOGL2 classes:
import static com.jogamp.opengl.GL2.*;//slipper å bruke GL2.konstant
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;



public class TegningOv2 extends GLCanvas implements GLEventListener{

	private float angle;
	private GLU glu = new GLU();
	private float number = 1.0f;
	private GLUT glut = new GLUT();

	//Konstruktor
	public TegningOv2() {
		this.addGLEventListener(this); //et objekt av klassen TegningOv1 fungerer som lytter. Det lytter på objektet selv
	}
	public void init(GLAutoDrawable glDrawable) {
		// System.out.println("init()");
		GL2 gl = glDrawable.getGL().getGL2(); //Get the GL2 object from glDrawable
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Sets the background color to white
		gl.glMatrixMode(GL_PROJECTION); // Select The Projection Matrix
		gl.glLoadIdentity(); // Reset the view matrix to the identity matrix
		glu.gluPerspective(120.0,1.25,1.0,20.0); // Spesifize the projection matrix (fov, w/h, near plane, far plane)
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	public void reshape(GLAutoDrawable glDrawable, int i, int i1, int width, int height) {
		
	}
	public void dispose(GLAutoDrawable d){
		
	}
	
	public void drawGLScene(GLAutoDrawable glDrawable) {
		GL2 gl = glDrawable.getGL().getGL2();//GL gl = glDrawable.getGL(); TOMAS
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); //Clear The Screen And The Depth Buffer
		gl.glLoadIdentity(); // Reset The View matrix
		gl.glColor3f(0.0f,1.0f,0.0f); // Set the Color to Green
		gl.glTranslatef(-5.0f, 2.5f, -5.5f); // Move Right 1.5 Units and Into The Screen 8 units
		
		glut.glutWireCube(2.5f);
		
		gl.glTranslatef(5.0f,0.0f,0.0f);
		gl.glBegin(GL_LINE_LOOP); // Start Drawing Triangles
		
		gl.glVertex3f(-1.0f, -1.0f, -1.0f); // P0 venstre nede nært
		
		gl.glVertex3f(1.0f, -1.0f, -1.0f); // P1 høyre nede nært
		
		gl.glVertex3f(1.0f, -1.0f, 1.0f); // P2 høyre nede unna
		
		gl.glVertex3f(-1.0f, -1.0f, 1.0f); // P3 venstre nede unna
		
		gl.glVertex3f(-1.0f, 1.0f, 1.0f); // P4 venstre oppe unna
		
		gl.glVertex3f(1.0f, 1.0f, 1.0f); // P5 høyre oppe unna
		
		gl.glVertex3f(1.0f, -1.0f, 1.0f); // P2 høyre nede unna
		
		gl.glVertex3f(1.0f, -1.0f, -1.0f); // P1 høyre nede nært
		
		gl.glVertex3f(1.0f, 1.0f, -1.0f); // P1 høyre oppe nært
		
		gl.glVertex3f(1.0f, 1.0f, 1.0f); // P5 høyre oppe unna
		
		gl.glVertex3f(1.0f, -1.0f, 1.0f); // P2 høyre nede unna
		
		gl.glVertex3f(1.0f, -1.0f, -1.0f); // P1 høyre nede nært
		
		gl.glVertex3f(1.0f, 1.0f, -1.0f); // P1 høyre oppe nært
		
		gl.glVertex3f(-1.0f, 1.0f, -1.0f); // P4 venstre oppe nært
		
		gl.glVertex3f(-1.0f, -1.0f, -1.0f); // P0 venstre nede nært
		
		gl.glVertex3f(-1.0f, 1.0f, -1.0f); // P4 venstre oppe nært
		
		gl.glVertex3f(-1.0f, 1.0f, 1.0f); // P4 venstre oppe unna
		
		gl.glVertex3f(-1.0f, -1.0f, 1.0f); // P4 venstre nede unna
		
		gl.glEnd(); // End Drawing the Triangle
		
		
	} // drawGLScene

	/** void display() Draw to the canvas. */
	public void display(GLAutoDrawable glDrawable) {
		// System.out.println("display()");
		GL2 gl = glDrawable.getGL().getGL2();
		drawGLScene(glDrawable);
		gl.glFlush();
	} // display()
}// TegningOv1