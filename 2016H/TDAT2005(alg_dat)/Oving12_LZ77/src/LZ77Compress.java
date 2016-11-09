import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Arrays;

/**
 * Created by asdfRig on 09.11.2016.
 */
public class LZ77Compress {
    private static final byte MATCH_LENGTH = 5;
    private String fileName;

    private byte[] input;
    private byte[] output;
    private int inputLenght;
    private int outputLenght;

    public static void main(String[] args) throws IOException {
        new LZ77Compress().run();
    }

    public void run() throws IOException {
        String path = "testfiler/test.txt";
        readFile(path);
        compress();
        writeFile();

        //test methods
        listArrays();
    }

    public void readFile(String fileName) throws IOException {
        this.fileName = fileName;
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
            input = IOUtils.toByteArray(dis);
            System.out.println("Byte array size: " + input.length);
        } catch(IOException ioe) {
            System.out.println("Error with reading file: " + ioe);
        } finally {
            dis.close();
        }
    }

    //TODO: fix algorithm
    public void compress() {
        output = new byte[input.length];
        for(int i = 0; i < output.length; i++) {
            output[i] = input[i];
        }
    }

    public void writeFile() throws IOException {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName + ".compressed")));
            IOUtils.write(output, dos);
        } catch(IOException ioe) {
            System.out.println("Error with writing file: " + ioe);
        } finally {
            dos.close();
        }
    }

    //Test methods
    private void listArrays() {
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Output: " + Arrays.toString(output));
    }


}
