//package jogl_test2;

/*

TegningOv1_1JOGL.java   JHN 2015-08-10 Tomas Holt 2015-08-15

Draws one triangle  JOGL versjon 2 binding towards OpenGL

*/


import javax.swing.*; // klassene JFrame og JPanel

//NYE IMPORTS AV TOMAS
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
import java.awt.Dimension;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Random;


public class Oving2 extends GLCanvas implements GLEventListener{

    	/* interfacet GLEventListener innholder de 5 metodene som må implemeenteres:
                - display(GLDrawable drawable)
	          	Called by the drawable to initiate OpenGL rendering by the client
                - displayChanged(GLDrawable drawable, boolean modeChanged, boolean deviceChanged)
	          	Called by the drawable when the display mode or the display device associated with the GLDrawable has changed.
                - init(GLDrawable drawable)
	          	Called by the drawable immediately after the OpenGL context is initialized.
                - reshape(GLDrawable drawable, int x, int y, int width, int height)
	          	Called by the drawable during the first repaint after the component has been resized.
                -dispose(GLAutoDrawable d)
      */

    private float angle;
    private GLU glu = new GLU();
    private float number = 1.0f;
    private double rotDeg = 0;


    //konstruktor
    public Oving2(GLCapabilities c){
        super(c);
        this.addGLEventListener(this);

        final FPSAnimator animator = new FPSAnimator(this, 120);
        animator.start(); // start the animation loop

    }

    public void init(GLAutoDrawable glDrawable) {
        System.out.println("init()");
        GL2 gl = glDrawable.getGL().getGL2();	//GL gl = glDrawable.getGL(); TOMAS	//Get the GL object from glDrawable

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // Sets the background color to white

        gl.glMatrixMode(GL_PROJECTION);       // Select The Projection Matrix
        gl.glLoadIdentity(); 					  // Reset the view matrix to the identity matrix
        //glu.gluPerspective(90.0,1.25,2.0,9.0);// Spesifize the projection matrix (fov, w/h, near plane, far plane)
        //gl.glFrustum(- 3, 3, -3,  3, 1, 9);	  // Definerer en matrise for perspektivtransformasjon
        gl.glOrtho(0.0, 50.0, 0.0,  50.0, -500, 500);	  // Ortogonalprojeksjon
        glu.gluLookAt(0, 0, 7, 0, 0, 0, 0, 1, 0);

        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void reshape(GLAutoDrawable glDrawable, int i, int i1, int width, int height) {}
    public void dispose(GLAutoDrawable d){};//lag til TOMAS

    public void drawGLScene(GLAutoDrawable glDrawable)  {
        GL2 gl = glDrawable.getGL().getGL2();//GL gl = glDrawable.getGL(); TOMAS
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);  //Clear The Screen And The Depth Buffer
        gl.glLoadIdentity();                                    // Reset The View matrix
        gl.glRotated(rotDeg, 0, 1, 0);
        gl.glTranslatef(0.0f,0.0f,-8.0f);       		// Move Left 1.5 Units and into The Screen 7 units

        //gl.glColor3f(1.0f,0.0f,0.0f);
        float[][] vertices = {{0.0f, 0.0f}, {25.0f, 50.0f}, {50.0f, 0.0f}};
        float[] p = {7.5f, 5.0f};

        //gl.glPointSize(1.5f);
        gl.glBegin(GL_POINTS);
        for(int k = 0; k < 50000; k++) {
            Random r = new Random();
            int  j = r.nextInt(3);
            gl.glColor3f(1f - ((float) j) * 0.5f, 0f + ((float) j) * 0.5f, 0.5f + ((float) j) * 0.5f);

            p[0] = (p[0] + vertices[j][0])/2.0f;
            p[1] = (p[1] + vertices[j][1])/2.0f;

            gl.glVertex2f(p[0], p[1]);
        }
        gl.glEnd();

    }





    /** void display() Draw to the canvas. */
    /* Purely a Java thing. Simple calls drawGLScene once GL is initialized */

    public void display(GLAutoDrawable glDrawable) {
        System.out.println("display()");
        GL2 gl = glDrawable.getGL().getGL2();
        //update();
        drawGLScene(glDrawable);                      // Calls drawGLScene

    }

    private void update() {
        rotDeg = (rotDeg > 360) ? 0 : rotDeg + 0.5;
    }


    public void displayChanged(GLAutoDrawable glDrawable, boolean b, boolean b1) {
        //  Must be present due to the GLEventListener interface
    }

    public static void main(String[] args){
        GLCanvas canvas = new Oving2(null);//null => setter ingen egenskaper for context
        canvas.setPreferredSize(new Dimension(1280,1024));

        final JFrame frame = new JFrame(); // Swing's JFrame or AWT's Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//lukk appen med kryss
        frame.getContentPane().add(canvas);

        frame.setTitle("Oving2");
        frame.pack();
        frame.setVisible(true);

    }
}
