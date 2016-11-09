import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 * Created by asdfLaptop on 08.11.2016.
 */
public class CompressLZ {
    private static final byte MINIMUM_MATCH_LENGTH = 5;
    private String fileName;

    private byte[] sourceBytes;
    private byte[] outputBuffer;
    private int outputLength;

    public void loadFile(String fileName) throws IOException {
        sourceBytes = FileUtils.readFileToByteArray(new File(fileName));
        outputBuffer = new byte[sourceBytes.length];
        this.fileName = fileName;
    }

    public void start() throws IOException {
        byte uncompressedCount = 0;
        int uncompressedStartIndex = -1;
        for(int i = 0; i < sourceBytes.length; i++) {
            byte matchLength = 0;
            int matchIndex = -1;
            int newOutputLength = outputLength + 2; // Reserve 2 bytes for offset and length


            // For the current position:
            // Search for a similar pattern up to 127 bytes behind self
            final int searchStartIndex = Math.max(0, i - 127);
            for(int j = searchStartIndex; j < i; j++) {
                if(i + matchLength >= sourceBytes.length)
                    break;

                if(sourceBytes[j] == sourceBytes[i + matchLength]) {
                    if(matchIndex == -1) matchIndex = j;
                    matchLength++;
                    if(newOutputLength >= outputBuffer.length) {
                        System.out.println("Error");
                    }
                    if(j >= sourceBytes.length) {
                        System.out.println("Error");
                    }
                    outputBuffer[newOutputLength++] = sourceBytes[j];
                } else if(matchIndex != -1) {
                    if(matchLength >= MINIMUM_MATCH_LENGTH) {
                        // Long enough, return
                        break;
                    }
                    // Reset
                    matchIndex = -1;
                    matchLength = 0;
                }
            }

            // Any matches?
            if(matchIndex != -1 && matchLength >= MINIMUM_MATCH_LENGTH) {
                // Check if we have started making an uncompressed block
                if (uncompressedCount > 0) {
                    // Finish block and reset counters
                    outputBuffer[uncompressedStartIndex] = (byte)-uncompressedCount;
                    uncompressedStartIndex = -1;
                    uncompressedCount = 0;
                    outputLength++; // 1 byte for length
                }

                // Add reference to match and length of match to output
                outputBuffer[outputLength++] = matchLength; // length
                outputBuffer[outputLength++] = (byte)(i - matchIndex); // offset behind

                System.out.println("Found match! \"" + convertToString(sourceBytes, i, matchLength) + "\" (" + i + ")"
                        + " with \"" + convertToString(sourceBytes, matchIndex, matchLength) + "\" (" + matchIndex + ")");

                i += matchLength - 1; // -1 because i++ in for-loop
            } else {
                // Didn't find any matches, uncompressed
                //System.out.println("No match found for pos " + i + ".");
                // Set uncompressed start
                if(uncompressedStartIndex == -1) {
                    uncompressedStartIndex = outputLength;
                }
                uncompressedCount++;
                outputBuffer[++outputLength] = sourceBytes[i]; // Reserve 1 byte for length
            }

            // Check if uncompressed block is full
            if (uncompressedCount == 127) {
                // Finish block and reset counters
                outputBuffer[uncompressedStartIndex] = -127;
                uncompressedStartIndex = -1;
                uncompressedCount = 0;
                outputLength++; // 1 byte for length
            }
        }

        // Check if any leftover uncompressed data
        if (uncompressedCount > 0) {
            // Finish block
            outputBuffer[uncompressedStartIndex] = (byte)-uncompressedCount;
            outputLength++; // 1 byte for length
        }
        //System.out.println(convertToString(outputBuffer, 0, outputLength));
        FileUtils.writeByteArrayToFile(new File(fileName + ".comp"), outputBuffer);
    }

    private static String convertToString(byte[] buffer, int startIndex, int count) {
        String s = "";
        for (int i = startIndex; i < startIndex + count; i++) {
            s += (char)(buffer[i]);
        }
        return s;
    }

    public static void main(String[] args) throws IOException {
        String path = "testfiler/diverse.txt";
        CompressLZ lz = new CompressLZ();
        lz.loadFile(path);
        lz.start();
    }
}
