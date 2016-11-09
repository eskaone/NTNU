import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Arrays;

/**
 * Created by asdfRig on 09.11.2016.
 */
public class LZ77Decompress {
    private String fileName;

    private byte[] input;
    private byte[] output;
    private int inputLenght;
    private int outputLenght;

    public static void main(String[] args) throws IOException {
        new LZ77Decompress().run();
    }

    public void run() throws IOException {
        String path = "testfiler/test.txt.compressed";
        readFile(path);
        decompress();
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
        } catch(IOException ioe) {
            System.out.println("Error with reading file: " + ioe);
        } finally {
            dis.close();
        }
    }

    //TODO: fix algorithm
    public void decompress() {
        output = new byte[input.length];
        for(int i = 0; i < output.length; i++) {
            output[i] = input[i];
        }
    }

    public void writeFile() throws IOException {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName.replace(".compressed", ".decompressed"))));
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
