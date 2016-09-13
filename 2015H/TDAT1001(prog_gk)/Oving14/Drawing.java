import static com.jogamp.opengl.GL2.*;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;


public class Drawing extends GLCanvas implements GLEventListener {
	private GLU glu = new GLU();
	private GLUT glut = new GLUT();
	
	private double rotDeg = 0;

	//Konstruktor
	public Drawing() {
		addGLEventListener(this);
		FPSAnimator animate = new FPSAnimator(this, 120);
		animate.start();
	}
	
	public void init(GLAutoDrawable glDrawable) {
		GL2 gl = glDrawable.getGL().getGL2(); //Get the GL2 object from glDrawable
		gl.glClearColor(0, 0, 0, 1); // Sets the background color to black
		gl.glMatrixMode(GL_PROJECTION); // Select The Projection Matrix
		gl.glLoadIdentity(); // Reset the view matrix to the identity matrix
		glu.gluPerspective(60.0 ,1.25 ,1.0 ,15.0); // Spesifize the projection matrix (fov, w/h, near plane, far plane)
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity();
		
		//LIGHT
		// float [] ambient = {0.4f, 0.4f, 0.4f, 0.0f};
		// float [] lmodel_ambient = {0.4f, 0.4f, 0.4f, 1.0f};
		// float [] diffuse0 = {0f, 1.0f, 0f, 1.0f};
		// float [] position0 = {5.0f, 0.0f, 0.0f, 0.0f};
		// float [] diffuse1 = {1.0f, 0f, 0f, 1.0f};
		// float [] position1 = {-5.0f, 0.0f, 0.0f, 1.0f};

		// gl.glEnable(GL_LIGHT0);
		// gl.glEnable(GL_LIGHT1);
		// gl.glEnable(GL_LIGHTING);
		// gl.glEnable(GL_NORMALIZE);
		// gl.glLightfv(GL_LIGHT0, GL_AMBIENT, ambient, 0);
		
		// gl.glLightfv(GL_LIGHT0, GL_DIFFUSE, diffuse0, 0);
		// gl.glLightfv(GL_LIGHT0, GL_POSITION, position0, 0);
		// gl.glLightfv(GL_LIGHT1, GL_DIFFUSE, diffuse1, 0);
		// gl.glLightfv(GL_LIGHT1, GL_POSITION, position1, 0);

		gl.glShadeModel(GL_SMOOTH); 
		gl.glClearDepth(1.0); 
		gl.glEnable(GL_DEPTH_TEST);
		gl.glEnableClientState(GL_COLOR_ARRAY);
		gl.glEnableClientState(GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL_NORMAL_ARRAY);
		gl.glDepthFunc(GL_LEQUAL);
		gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		
		// MATERIAL
		// float amb[] = {0.3f,0.3f,0.0f,1.0f};
		// float diff[] = {1.0f,1.0f,0.5f,1.0f};
		// float spec[] = {0.6f,0.6f,0.5f,1.0f};
		// float shine = 3f;
		// gl.glMaterialfv(GL_FRONT,GL_AMBIENT,amb, 1);
		// gl.glMaterialfv(GL_FRONT,GL_DIFFUSE,diff, 1);
		// gl.glMaterialfv(GL_FRONT,GL_SPECULAR,spec, 1);
		// gl.glMaterialf(GL_FRONT,GL_SHININESS,shine*1000.0f);
	}
	
	public void reshape(GLAutoDrawable glDrawable, int i, int i1, int i2, int i3) {
	}
	public void dispose(GLAutoDrawable d){
	}

	public void display(GLAutoDrawable glDrawable) {
		// update();
		render(glDrawable);
	} 
	
	private void update() {
		rotDeg = (rotDeg > 360) ? 0 : rotDeg + 1;
	}
	
	public void render(GLAutoDrawable glDrawable) {
		GL2 gl = glDrawable.getGL().getGL2();
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // Clear The Screen And The Depth Buffer
		gl.glLoadIdentity(); // Reset The View matrix
		glu.gluLookAt(0, 0, 7, 0, 0, 0, 0, 1, 0); //(pos x, pos y, pos z, look x, look y, look z, boolean x, y, z up);
		gl.glColor3d(0, 0, 0);
		gl.glRotated(rotDeg, 1, 1, 0);
		{
			//Oppg. 3.1 a)
			// gl.glColor3d(0, 0, 1);
			// drawCircle(glDrawable); 
			
			//Oppg 3.1 b)
			// gl.glTranslatef(-0.5f,-4.8f,0);
			// drawCircleOfCircles(glDrawable);
			
			//Oppg 3.2
			// drawPolygon(glDrawable, 0, 4, 7, 3);
			// drawCube(glDrawable);
			drawTest(glDrawable);
		}
		gl.glFlush();		
	}
	

	//DRAWINGS
	private void drawCircle(GLAutoDrawable glDrawable) {
		GL2 gl = glDrawable.getGL().getGL2();
		gl.glBegin(GL_LINE_LOOP);
		final double PI = Math.PI;
		double angle = 0;
		for (int i = 0; i < 100; i++) {
			angle = 2 * PI * i/100;
			gl.glVertex2f((float)Math.cos(angle), (float)Math.sin(angle));
		}
		gl.glEnd();
	}
	
	private void drawCircleOfCircles(GLAutoDrawable glDrawable) {
		GL2 gl = glDrawable.getGL().getGL2();
		final double PI = Math.PI;
		double angle = 0;		
		for (int i = 0; i < 30; i++) {
			angle = 2 * PI * i/30;
			gl.glColor3d((float)Math.cos(angle), (float)Math.sin(angle),1);
			gl.glTranslatef((float)Math.cos(angle), (float)Math.sin(angle),0);
			drawCircle(glDrawable);
		}
	}
	
	private void drawPolygon(GLAutoDrawable glDrawable, int a, int b, int c, int d) {
		GL2 gl = glDrawable.getGL().getGL2();
		
		final float corners[][] = {{-1f,-1f,1f}, {-1f,1f,1f}, {1f,1f,1f}, {1f,-1f,1f}, {-1f,-1f,-1f}, {-1f,1f,-1f}, {1f,1f,-1f}, {1f,-1f,-1f}};
		final float colors[][] = {{1.0f, 0.0f, 0.0f}, {0.0f, 1.0f, 1.0f}, {1.0f, 1.0f, 0.0f}, {0.0f, 1.0f, 0.0f}, {1.0f, 0.0f, 0.0f}, {1.0f, 0.0f, 1.0f}};

		gl.glColor3fv(colors[a],0);
		gl.glBegin(GL_POLYGON);
		{
			gl.glVertex3fv(corners[a],0);
			gl.glVertex3fv(corners[b],0);
			gl.glVertex3fv(corners[c],0);
			gl.glVertex3fv(corners[d],0);
		}
		gl.glEnd();
	}
	
	private void drawCube(GLAutoDrawable glDrawable) {
		drawPolygon(glDrawable, 0, 4, 7, 3);
		drawPolygon(glDrawable, 4, 5, 6, 7);
		drawPolygon(glDrawable, 5, 4, 0, 1);
		drawPolygon(glDrawable, 1, 2, 6, 5);
		drawPolygon(glDrawable, 2, 1, 0, 3);
		drawPolygon(glDrawable, 3, 7, 6, 2);
	}
	
	public static void main(String[] args){
		Window window = new Window("Jogl Test", 800, 600, new Drawing());
		window.setVisible(true);
	}
}