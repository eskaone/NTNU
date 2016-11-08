/**
 * Created by asdfLaptop on 08.11.2016.
 */
public class DecompressLZ {
    private String fileName;

    private byte[] sourceBytes;
    private byte[] outputBuffer;
    private int outputLength;

    public void loadFile(String fileName) {
        sourceBytes = FileUtil.readAllBytes(fileName);
        outputBuffer = new byte[sourceBytes.length*5];
        this.fileName = fileName;
    }

    public void start() {
        for(int i = 0; i < sourceBytes.length; i++) {
            byte currentByte = sourceBytes[i];
            if (currentByte > 0) {
                // Compressed data
                byte length = currentByte;
                byte offset = sourceBytes[i++];
                int startIndex = outputLength - offset;
                if (startIndex < 0) {
                    System.out.println("Error! Negative start index!");
                }

                if(startIndex + length >= outputBuffer.length) {
                    System.out.println("Error");
                    break;
                }

                for (int j = startIndex; j < startIndex + length; j++) {
                    outputBuffer[outputLength++] = outputBuffer[j];
                }
                System.out.println(i + ": " + convertToString(outputBuffer, outputLength - length, length));

            } else if (currentByte < 0) {
                // Uncompressed data
                int length = -currentByte;
                for (int j = i + 1; j <= i + length; j++) {
                    outputBuffer[outputLength++] = sourceBytes[j];
                }
                System.out.println(i + ": " + convertToString(outputBuffer, outputLength - length, length));
                i += length;
            } else {
                System.out.println("Parse error.");
            }
        }
        //System.out.println(convertToString(outputBuffer, 0, outputLength));
        FileUtil.writeAllBytes(fileName.replace(".comp", ".uncomp"), outputBuffer, outputLength);
    }

    private static String convertToString(byte[] buffer, int startIndex, int count) {
        String s = "";
        for (int i = startIndex; i < startIndex + count; i++) {
            s += (char)(buffer[i]);
        }
        return s;
    }

    public static void main(String[] args) {
        String path = "testfiler/komprimert/???";
        DecompressLZ lz = new DecompressLZ();
        lz.loadFile(path);
        lz.start();
    }
}
