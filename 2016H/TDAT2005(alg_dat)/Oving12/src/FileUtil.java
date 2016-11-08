/**
 * Created by asdfLaptop on 08.11.2016.
 */
public class FileUtil {
    private byte[] data;
    private int position;
    private int amount;

    public FileUtil(byte[] data, int position, int amount) {
        this.data = data;
        this.position = position;
        this.amount = amount;
    }


    public static byte[] readAllBytes(String fileName) {
        return null;
    }

    public static void writeAllBytes(String fileName, byte[] asd, int amount) {

    }
}
