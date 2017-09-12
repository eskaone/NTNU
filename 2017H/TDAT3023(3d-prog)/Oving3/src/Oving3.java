import javax.swing.*;
import static com.jogamp.opengl.GL2.*;
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

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Oving3 extends GLCanvas implements GLEventListener, KeyListener{

    private GLU glu = new GLU();
    private float number = 1.0f;
    private GLUT glut = new GLUT();
    private double rotDeg = 0;
    private float cUpDown = 0f;
    private float cLeftRight = 0f;

    public Oving3(GLCapabilities c){
        super(c);
        this.addGLEventListener(this);
        this.addKeyListener(this);
        final FPSAnimator animator = new FPSAnimator(this, 120);
        animator.start();
    }

    public void init(GLAutoDrawable glDrawable) {
        System.out.println("init()");
        GL2 gl = glDrawable.getGL().getGL2();

        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // Sets the background color to white

        gl.glMatrixMode(GL_PROJECTION);       // Select The Projection Matrix
        gl.glLoadIdentity(); 					  // Reset the view matrix to the identity matrix
        glu.gluPerspective(60.0,1.25,1.0,150);// Spesifize the projection matrix (fov, w/h, near plane, far plane)
        glu.gluLookAt(2, 2, 7, 0, 0, 0, 0, 1, 0);

        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void reshape(GLAutoDrawable glDrawable, int i, int i1, int width, int height) {}
    public void dispose(GLAutoDrawable d){};//lag til TOMAS

    public void drawGLScene(GLAutoDrawable glDrawable) {
        GL2 gl = glDrawable.getGL().getGL2();//GL gl = glDrawable.getGL(); TOMAS
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);  //Clear The Screen And The Depth Buffer
        gl.glLoadIdentity();                                    // Reset The View matrix
        gl.glRotated(rotDeg, 0, 1, 0);

        //FIXME: buggy
        //glu.gluLookAt(cLeftRight*0.1f,0, 1+cUpDown*0.1f, 0, 0, 0, 0, 1, 0);

        //x,y,z-axis
        gl.glColor3f(0f, 0f, 0f);
        drawAxis(gl, 10);

        gl.glColor3f(1f, 0f, 0f);

        //oppg. 3.1a)
        drawCube(gl);

        //oppg. 3.1b)
        //glut.glutWireCube(2.0f);

        //oppg 3.2
        /*
        gl.glTranslatef(1f, 0f, 0f);
        gl.glRotatef(45f, 1f, 0f, 0f);
        gl.glScalef(0.5f, 0.5f, 0.5f);
        drawCube(gl);
        */

        //oppg 3.3 (deaktiver rotasjon)
        //glu.gluLookAt(2, 2, 7, 0, 0, 0, 0, 1, 0);
        //drawCube(gl);


        //cameraControl(event);



    }


    public void drawAxis(GL2 gl, float length) {
        //axis
        gl.glBegin(GL_LINES);
        gl.glVertex3f(length, 0f, 0f);
        gl.glVertex3f(-length, 0f, 0f);
        gl.glVertex3f(0f, length, 0f);
        gl.glVertex3f(0f, -length, 0f);
        gl.glVertex3f(0f, 0f, length);
        gl.glVertex3f(0f, 0f, -length);
        gl.glEnd();

        gl.glColor3f(1f, 0f, 0f);
        //counters
        gl.glBegin(GL_LINES);
        for(int i = (int) -length; i < length; i++) {
            gl.glVertex3f(((float) i), 0.05f, 0f);
            gl.glVertex3f(((float) i), -0.05f, 0f);
        }

        for(int i = (int) -length; i < length; i++) {
            gl.glVertex3f(0.05f, ((float) i), 0f);
            gl.glVertex3f(-0.05f, ((float) i), 0f);
        }

        for(int i = (int) -length; i < length; i++) {
            gl.glVertex3f(0f, 0.05f, ((float) i));
            gl.glVertex3f(0f, -0.05f, ((float) i));
        }
        gl.glEnd();
    }

    public void drawCube(GL2 gl) {
        gl.glBegin(GL_LINE_LOOP);
        gl.glVertex3f(-1f, -1f, 1f);
        gl.glVertex3f(-1f, -1f, -1f);
        gl.glVertex3f(-1f, -1f, 1f);

        gl.glVertex3f(1f, -1f, 1f);
        gl.glVertex3f(1f, -1f, -1f);
        gl.glVertex3f(1f, -1f, 1f);

        gl.glVertex3f(1f, 1f, 1f);
        gl.glVertex3f(1f, 1f, -1f);
        gl.glVertex3f(1f, 1f, 1f);


        gl.glVertex3f(-1f, 1f, 1f);
        gl.glVertex3f(-1f, 1f, -1f);
        gl.glVertex3f(-1f, 1f, 1f);

        gl.glVertex3f(-1f, 1f, -1f);
        gl.glVertex3f(1f, 1f, -1f);
        gl.glVertex3f(1f, -1f, -1f);
        gl.glVertex3f(-1f, -1f, -1f);
        gl.glVertex3f(-1f, 1f, -1f);
        gl.glVertex3f(-1f, 1f, 1f);
        gl.glVertex3f(-1f, -1f, 1f);
        gl.glEnd();
    }

    public void display(GLAutoDrawable glDrawable) {
        update();
        drawGLScene(glDrawable);
    }

    private void update() {
        rotDeg = (rotDeg > 360) ? 0 : rotDeg + 0.5;
    }

    public void displayChanged(GLAutoDrawable glDrawable, boolean b, boolean b1) {}

    public static void main(String[] args){
        GLCanvas canvas = new Oving3(null);
        canvas.setPreferredSize(new Dimension(1280,1024));

        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(canvas);

        frame.setTitle("Oving3");
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            cUpDown++;
            System.out.println("z-axis: " + cUpDown);
            repaint();
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            cUpDown--;
            System.out.println("z-axis: " + cUpDown);
            repaint();
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            cLeftRight--;
            System.out.println("x-axis: " + cLeftRight);
            repaint();
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            cLeftRight++;
            System.out.println("x-axis: " + cLeftRight);
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
