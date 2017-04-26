import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by wizard man on 08.03.2017.
 */
public class ThreadClientHandler extends Thread {
    Socket s = null;
    InputStreamReader readConn = null;
    BufferedReader reader = null;
    PrintWriter writer = null;

    public ThreadClientHandler(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            readConn = new InputStreamReader(s.getInputStream());
            reader = new BufferedReader(readConn);
            writer = new PrintWriter(s.getOutputStream(), true);
        } catch(IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(reader.readLine());
            writer.println("Connected with server side.");

            String data = reader.readLine();
            System.out.println("Input from client: " + data);

            while (!data.equals("exit")) {
                String[] dataTbl = data.split(" ");
                if(dataTbl[1].equals("+")) {
                    writer.println(data + " = " + (Integer.parseInt(dataTbl[0]) + Integer.parseInt(dataTbl[2])));
                } else if(dataTbl[1].equals("-")) {
                    writer.println(data + " = " + (Integer.parseInt(dataTbl[0]) - Integer.parseInt(dataTbl[2])));
                } else {
                    writer.println("Wrong input. Try again...");
                    System.out.println("Wrong input from client...");
                }
                data = reader.readLine();
                System.out.println("Input from client: " + data);
            }

            reader.close();
            writer.close();
            s.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
