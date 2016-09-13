import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		String fileSaldo = "Saldo.txt";
		String fileTransaksjon = "Transaksjon.txt";
		Scanner sc = new Scanner(System.in);
		
		
		FileReader readStream = new FileReader(fileSaldo);
		BufferedReader reader = new BufferedReader(readStream);
		Scanner scan = new Scanner(reader);
		
		int saldo = 0;
		while(scan.hasNext()) {
			saldo = scan.nextInt();
		}
		scan.close();
		reader.close();
		// System.out.println(saldo);
		
		
		
		while (true) {
			FileWriter writeStream = new FileWriter(fileTransaksjon, true);
			PrintWriter writer = new PrintWriter(new BufferedWriter(writeStream));
			int sumInn = 0;
			int sumUt = 0;
			System.out.println("1. Inn");
			System.out.println("2. Ut");
			System.out.println("3. Avslutt og lagre belop");
			int choice = sc.nextInt();
			switch (choice) {
				case 1:
				System.out.println("Belop inn: ");
				int belopInn = sc.nextInt();
				sumInn += belopInn;
				writer.println("I " + belopInn);
				writer.close();
				System.out.println("Sum inn: " + sumInn);
				break;
				
				case 2:
				System.out.println("Belop ut: ");
				int belopUt = sc.nextInt();
				sumUt += belopUt;
				writer.println("U " + belopUt);
				writer.close();
				System.out.println("Sum ut: " + sumUt);
				break;
				
				case 3:
				System.out.println("Avslutter og lagrer belop...");
				System.out.println("Totalt inn: " + sumInn);
				System.out.println("Totalt ut: " + sumUt);
				
				return;
				
				default:
				System.out.println("Ikke et alternativ...");
				break;
			}
		}
		
	}
}