import java.io.*;
import java.net.*;
import java.util.Scanner;

class SocketClient {
  public static void main(String[] args) throws IOException {
    final int PORT = 1250;

    Scanner readFromCmd = new Scanner(System.in);
    InetAddress address = InetAddress.getLocalHost();

    Socket s = new Socket("localhost", PORT);
    System.out.println("Connection established.");

    InputStreamReader readConn = new InputStreamReader(s.getInputStream());
    BufferedReader reader = new BufferedReader(readConn);
    PrintWriter writer = new PrintWriter(s.getOutputStream(), true);

    writer.println("Connection established: " + address + ":" + PORT);
    String setupInfo = reader.readLine();
    System.out.println(setupInfo);

    System.out.println("\nEnter two numbers to calculate with:");
    String data = readFromCmd.nextLine();

    while (!data.equals("exit")) {
      writer.println(data);

      String response = reader.readLine();
      System.out.println("Echo from server: " + response);

      System.out.println("\nEnter two numbers to calculate with:");
      data = readFromCmd.nextLine();
    }

    reader.close();
    writer.close();
    s.close();
  }
}
