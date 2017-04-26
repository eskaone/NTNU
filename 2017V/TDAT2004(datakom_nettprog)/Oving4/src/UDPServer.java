import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

class UDPServer {
  static final int PORT = 1250;

  public static void main(String[] args) throws Exception {
    System.out.println("Log for server side. Waiting for clients to connect...");

    byte[] receiveData = new byte[1024];
    byte[] sendData = new byte[1024];
    DatagramSocket ds = new DatagramSocket(PORT);

    String data = "";

    while(!data.equals("exit")) {
      DatagramPacket dp = new DatagramPacket(receiveData, receiveData.length);

      ds.receive(dp);
      data = new String(dp.getData(), 0, dp.getLength());
      System.out.println(data);

      String[] dataTbl = data.split(" ");

      InetAddress addressPacket = dp.getAddress();
      int portPacket = dp.getPort();

      if(dataTbl[1].equals("+")) {
        String add = data + " = " + (Integer.parseInt(dataTbl[0]) + Integer.parseInt(dataTbl[2]));
        System.out.println(add);
        sendData = add.getBytes();
        dp = new DatagramPacket(sendData, sendData.length, addressPacket, portPacket);
        ds.send(dp);
      } else if(dataTbl[1].equals("-")) {
        String sub = data + " = " + (Integer.parseInt(dataTbl[0]) - Integer.parseInt(dataTbl[2]));
        System.out.println(sub);
        sendData = sub.getBytes();
        dp = new DatagramPacket(sendData, sendData.length, addressPacket, portPacket);
        ds.send(dp);
      } else {
        String error = "Wrong input. Try again...";
        sendData = error.getBytes();
        dp = new DatagramPacket(sendData, sendData.length, addressPacket, portPacket);
        ds.send(dp);
        System.out.println("Wrong input from client...");
      }
    }
  }
}
