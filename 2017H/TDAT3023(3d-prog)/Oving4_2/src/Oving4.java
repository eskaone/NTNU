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

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class Oving4 extends GLCanvas implements GLEventListener, KeyListener{

    private double angle;
    private GLU glu = new GLU();
    private GLUT glut = new GLUT();
    private float number = 1.0f;
    private double rotDeg = 0;
    private boolean rotDir = true;
    private boolean rotSide = true;
    private boolean swapSide = false;
    private float hei = 1;

    public Oving4(GLCapabilities c){
        super(c);
        this.addGLEventListener(this);
        this.addKeyListener(this);
        final FPSAnimator animator = new FPSAnimator(this, 120);
        //animator.start();

    }

    public void init(GLAutoDrawable glDrawable) {
        GL2 gl = glDrawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(90.0,1.25,1.0,20.0);
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();

        //LIGHT

        float [] ambient = {0.5f, 0.5f, 0.5f, 0.0f};
        float [] diffuse0 = {0.3f, 0.3f, 0.3f, 1.0f};
        float [] position0 = {0f, 0f, 0.0f, 1.0f};

        gl.glEnable(GL_LIGHT0);
        gl.glEnable(GL_LIGHTING);
        gl.glEnable(GL_NORMALIZE);
        gl.glEnable(GL_COLOR_MATERIAL);
        gl.glEnable(GL_DEPTH_TEST);

        gl.glLightfv(GL_LIGHT0, GL_AMBIENT, ambient, 0);
        gl.glLightfv(GL_LIGHT0, GL_DIFFUSE, diffuse0, 0);
        gl.glLightfv(GL_LIGHT0, GL_POSITION, position0, 0);

        gl.glShadeModel(GL_SMOOTH);
        gl.glClearDepth(1.0);
        gl.glEnableClientState(GL_COLOR_ARRAY);
        gl.glEnableClientState(GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL_NORMAL_ARRAY);
        gl.glDepthFunc(GL_LEQUAL);
        gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        gl.glColorMaterial(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE);
    }

    public void reshape(GLAutoDrawable glDrawable, int i, int i1, int width, int height) {}
    public void dispose(GLAutoDrawable d){}

    public void drawGLScene(GLAutoDrawable glDrawable) {
        GL2 gl = glDrawable.getGL().getGL2();
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        glu.gluLookAt(0, 0, 6, 0, 0, 0, 0, 1, 0); //(pos x, pos y, pos z, look x, look y, look z, boolean x, y, z up);

        //gl.glRotated(angle, 0, 1, 0);

        //x,y,z-axis
        drawAxis(gl, 10);

        float[][][] vertices = {{{0,0,0},{1,0,0},{1,1,0},{0,1,0}},
                                {{1,0,0},{2,0,0},{2,1,0},{1,1,0}},
                                {{0,1,0},{1,1,0},{1,2,0},{0,2,0}},
                                {{1,1,0},{2,1,0},{2,2,0},{1,2,0}}};


        //white 0
        //red 1
        //green 2
        //blue 3
        //yellow 4
        //orange 5

        //TODO: create algorythm to update blocks with color
        float[][] colors = {{1, 1, 1, hei}, {0, 0, 0, 0}, {3, 3, 3, 3}, {5, 5, 5, 5}, {4, 4, 4, 4}, {2, 2, 2, 2}};
        float[][] colorsChanged = {{1, 1, 2, 2}, {0, 0, 0, 0}, {3, 3, 1, 1}, {5, 5, 3, 3}, {4, 4, 4, 4}, {2, 2, 5, 5}};


        /*
        float[][][] side0 = updateVertices(vertices, colorsChanged[0]);
        float[][][] side1 = updateVertices(vertices, colorsChanged[1]);
        float[][][] side2 = updateVertices(vertices, colorsChanged[2]);
        float[][][] side3 = updateVertices(vertices, colorsChanged[3]);
        float[][][] side4 = updateVertices(vertices, colorsChanged[4]);
        float[][][] side5 = updateVertices(vertices, colorsChanged[5]);
         */

        /*

         */

        float[][][] side0 = updateVertices(vertices, colors[0]);
        float[][][] side1 = updateVertices(vertices, colors[1]);
        float[][][] side2 = updateVertices(vertices, colors[2]);
        float[][][] side3 = updateVertices(vertices, colors[3]);
        float[][][] side4 = updateVertices(vertices, colors[4]);
        float[][][] side5 = updateVertices(vertices, colors[5]);

        gl.glTranslatef(-1, -1, 1);

        drawSingleSurface(gl, side0);

        gl.glPushMatrix();
        gl.glTranslatef(0, 2, 0);
        //gl.glRotatef(-90, 1, 0, 0);
        drawSingleSurface(gl, side1);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(2, 0, 0);
        //gl.glRotatef(90, 0, 1, 0);
        drawSingleSurface(gl, side2);

        gl.glTranslatef(2, 0, 0);
        //gl.glRotatef(90, 0, 1, 0);
        drawSingleSurface(gl, side3);
        gl.glPopMatrix();

        gl.glPushMatrix();
        //gl.glRotatef(90, 1, 0, 0);
        gl.glTranslatef(0, -2, 0);
        drawSingleSurface(gl, side4);
        gl.glPopMatrix();

        gl.glPushMatrix();
        //gl.glRotatef(-90, 0, 1, 0);
        gl.glTranslatef(-2, 0, 0);
        drawSingleSurface(gl, side5);
        gl.glPopMatrix();
    }

    private float[][][] updateVertices(float[][][] a, float[] posColor) {
        float colors[][] = {{1.0f, 1.0f, 1.0f}, //white 0
                {1.0f, 0.0f, 0.0f}, //red 1
                {0.0f, 1.0f, 0.0f}, //green 2
                {0.0f, 0.0f, 1.0f}, //blue 3
                {1.0f, 1.0f, 0.0f}, //yellow 4
                {1.0f, 0.5f, 0.0f}}; //orange 5
        float[][][] b = new float[a.length][posColor.length + 1][3];
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a[i].length; j++) {
                for(int k = 0; k < a[i][j].length; k++) {
                    b[i][j][k] = a[i][j][k];
                }
            }
        }

        for(int i = 0; i < b.length; i++) {
            b[i][b.length] = colors[(int)posColor[i]];
        }
        return b;
    }


    private void drawSurfaceBlock(GL2 gl, float[][] coords, float[] color) {
        gl.glColor3fv(color, 0);
        gl.glBegin(GL_QUADS);
        gl.glVertex3fv(coords[0], 0);
        gl.glVertex3fv(coords[1], 0);
        gl.glVertex3fv(coords[2], 0);
        gl.glVertex3fv(coords[3], 0);
        gl.glEnd();

        gl.glColor3f(0, 0, 0);
        gl.glLineWidth(3f);
        gl.glBegin(GL_LINE_LOOP);
        gl.glVertex3fv(coords[0], 0);
        gl.glVertex3fv(coords[1], 0);
        gl.glVertex3fv(coords[2], 0);
        gl.glVertex3fv(coords[3], 0);
        gl.glEnd();
        gl.glLineWidth(1f);

    }

    private void drawSingleSurface(GL2 gl, float[][][] vertices) {
        for(int i = 0; i < vertices.length; i++ ) {
            //System.out.println(vertices[i]);
            gl.glPushMatrix();
            drawSurfaceBlock(gl, vertices[i], vertices[i][4]);
            gl.glPopMatrix();
        }
    }


    private void update() {
        //cam
        angle += 0.3;
        if(angle > 360) {
            angle -= 360;
        }

        //rubiks
        double[] bp = {-360, -270, -180, -90, 0, 90, 180, 270, 360};
        for(int i = 0; i < bp.length; i++) {
            if(!rotSide && rotDeg == bp[i]) {
                swapSide = true;
            } else if(rotSide && rotDeg == bp[i]) {
                swapSide = false;
            }
        }

        if(rotDir) {
            rotDeg += 0.5;
        } else {
            rotDeg -= 0.5;
        }
        if(rotDeg > 360 || rotDeg < -360) {
            rotDeg = 0;
        }
        //System.out.println("rotDeg: " + rotDeg);
    }

    private void drawAxis(GL2 gl, float length) {
        //axis
        gl.glBegin(GL_LINES);
        gl.glColor3f(1f, 0f, 0f);
        gl.glVertex3f(length, 0f, 0f);
        gl.glVertex3f(-length, 0f, 0f);
        gl.glColor3f(0f, 1f, 0f);
        gl.glVertex3f(0f, length, 0f);
        gl.glVertex3f(0f, -length, 0f);
        gl.glColor3f(0f, 0f, 1f);
        gl.glVertex3f(0f, 0f, length);
        gl.glVertex3f(0f, 0f, -length);
        gl.glEnd();

        //counters
        gl.glColor3f(1f, 1f, 1f);
        gl.glLineWidth(4f);
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
        gl.glLineWidth(1f);
    }


    public void display(GLAutoDrawable glDrawable) {
        update();
        drawGLScene(glDrawable);                      // Calls drawGLScene
    }


    public void displayChanged(GLAutoDrawable glDrawable, boolean b, boolean b1) {}

    public static void main(String[] args){
        GLCanvas canvas = new Oving4(null);
        canvas.setPreferredSize(new Dimension(1280,1024));

        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(canvas);

        frame.setTitle("Oving4");
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;

            case KeyEvent.VK_UP:
                rotSide = true;
                rotDir = true;
                break;

            case KeyEvent.VK_DOWN:
                rotSide = true;
                rotDir = false;
                break;

            case KeyEvent.VK_RIGHT:
                hei++;
                if(hei > 5) {
                    hei = 0;
                }
                System.out.println(hei);
                rotSide = false;
                rotDir = true;
                repaint();
                break;

            case KeyEvent.VK_LEFT:
                rotSide = false;
                rotDir = false;
                break;

            case KeyEvent.VK_SPACE:
                if(getAnimator().isPaused()) {
                    getAnimator().resume();
                } else {
                    getAnimator().pause();
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
