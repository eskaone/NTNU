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
public class TegningOv1 extends GLCanvas implements GLEventListener{
	/* interfacet GLEventListener innholder de 5 metodene som må implemeenteres:
- display(GLDrawable drawable)
Called by the drawable to initiate OpenGL rendering by the client
- dispose(GLAutoDrawable d)
Notifies the listener to perform the release of all OpenGL resources per GLContext, such as memory buffers
and GLSL programs.
- init(GLDrawable drawable)
Called by the drawable immediately after the OpenGL context is initialized.
- reshape(GLDrawable drawable, int x, int y, int width, int height)
Called by the drawable during the first repaint after the component has been resized.
*/
	private float angle;
	private GLU glu = new GLU();
	private float number = 1.0f;

	//Konstruktor
	public TegningOv1() {
		this.addGLEventListener(this); //et objekt av klassen TegningOv1 fungerer som lytter. Det lytter på objektet selv
	}
	public void init(GLAutoDrawable glDrawable) {
		// System.out.println("init()");
		GL2 gl = glDrawable.getGL().getGL2(); //Get the GL2 object from glDrawable
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // Sets the background color to white
		gl.glMatrixMode(GL_PROJECTION); // Select The Projection Matrix
		gl.glLoadIdentity(); // Reset the view matrix to the identity matrix
		glu.gluPerspective(60.0,1.25,1.0,15.0); // Spesifize the projection matrix (fov, w/h, near plane, far plane)
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
		gl.glTranslatef(-1.5f,0.0f,-8.0f); // Move Left 1.5 Units and Into The Screen 8 units
		
		gl.glBegin(GL_TRIANGLES); // Start Drawing Triangles
		gl.glColor3f(0.0f,0.0f,1.0f); // Set the Color to Blue
		gl.glVertex3f( 0.0f, 1.0f, 0.0f); // Top
		gl.glVertex3f(-1.0f,-1.0f, 0.0f); // Bottom Left
		gl.glVertex3f( 1.0f,-1.0f, 0.0f); // Bottom Right
		gl.glEnd(); // End Drawing the Triangle
		
		gl.glBegin(GL_TRIANGLES); // Start Drawing Triangles
		gl.glColor3f(1.0f,0.0f,0.0f); // Set the Color to Red
		gl.glVertex3f( -1.0f, 2.0f, 0.0f); // Top
		gl.glVertex3f(-2.0f,0.0f, 0.0f); // Bottom Left
		gl.glVertex3f( 0.0f,0.0f, 0.0f); // Bottom Right
		gl.glEnd(); // End Drawing the Triangle

		gl.glTranslatef(3.0f,0.0f,0.0f); // Move Right 3 Units
		gl.glColor3f(1.0f,0.0f,0.0f); // Set the Color to Red
		gl.glBegin(GL_QUADS); // Start Draw a Quad
		gl.glVertex3f(-1.0f, 1.0f, 0.0f); // Top Left
		gl.glVertex3f( 1.0f, 1.0f, 0.0f); // Top Right
		gl.glVertex3f( 1.0f,-1.0f, 0.0f); // Bottom Right
		gl.glVertex3f(-1.0f,-1.0f, 0.0f); // Bottom Left
		gl.glEnd(); // End Drawing the Quad

		gl.glTranslatef(-0.1f,-1.0f,-6.0f); // Move Left 0.1, down 1.0 units and into the screen 7 units
		final double PI = 3.1415926535898; // Initiate constant PI
		int circle_points = 100; // Initiate circle_points ( number of points to construct/draw the circle)
		gl.glColor3f(0.0f,0.0f,1.0f); // Set Colour to Blue
		gl.glBegin(GL_POLYGON); // Draw a lines between circle points using
		double angle = 0.0; // Initiate angle
		for(int i = 0; i < circle_points; i++){ // for loop
			angle = 2 * PI * i/circle_points; // calculate new angle
			gl.glVertex2f((float)Math.cos(angle), (float)Math.sin(angle)); // calculate vertex points on the circle
		}
		gl.glEnd(); // Done drawing the circle
	} // drawGLScene

	/** void display() Draw to the canvas. */
	public void display(GLAutoDrawable glDrawable) {
		// System.out.println("display()");
		GL2 gl = glDrawable.getGL().getGL2();
		drawGLScene(glDrawable);
		gl.glFlush();
	} // display()
}// TegningOv1