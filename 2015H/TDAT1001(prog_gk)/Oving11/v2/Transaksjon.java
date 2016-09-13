import java.io.*;
import java.util.*;

class Transaksjon {
	private int belopInn;
	private int belopUt;
	private int sumInn;
	private int sumUt;
	private String fileSaldo = "Saldo.txt";
	private String fileTransaksjon = "Transaksjon.txt";
	Scanner sc = new Scanner(System.in);
	
	public int getSaldo() throws IOException {
		FileReader readStream = new FileReader(fileSaldo);
		BufferedReader reader = new BufferedReader(readStream);
		Scanner scan = new Scanner(reader);
		int saldo = 0;
		while(scan.hasNext()) {
			saldo = scan.nextInt();
		}
		scan.close();
		reader.close();
		return saldo;
	}
	
	public void updateSaldo() throws IOException {
		FileWriter writeStream = new FileWriter(fileSaldo, true);
		PrintWriter writer = new PrintWriter(new BufferedWriter(writeStream));
		writer.println(getSaldo() + getSumTot());
		writer.close();
	}
	
	public void fillTransactionInn() throws IOException {
		FileWriter writeStream = new FileWriter(fileTransaksjon, true);
		PrintWriter writer = new PrintWriter(new BufferedWriter(writeStream));
		System.out.println("Belop inn: ");
		belopInn = sc.nextInt();
		writer.println("I " + belopInn);
		writer.close();
		sumInn += belopInn;
	} 

	public void fillTransactionUt() throws IOException {
		FileWriter writeStream = new FileWriter(fileTransaksjon, true);
		PrintWriter writer = new PrintWriter(new BufferedWriter(writeStream));
		System.out.println("Belop ut: ");
		belopUt = sc.nextInt();
		writer.println("U " + belopUt);
		writer.close();
		sumUt += belopUt;
	} 
	
	public int getSumInn() throws IOException {
		return sumInn;
	}
	
	public int getSumUt() throws IOException {
		return sumUt;
	}
	
	public int getSumTot() throws IOException {
		return (getSumInn() - getSumUt());
	}
	
	public void saldoMinus() throws IOException {
		FileWriter writeStream = new FileWriter(fileTransaksjon, false);
		PrintWriter writer = new PrintWriter(new BufferedWriter(writeStream));
		writer.println("");
		writer.close();
	}
	
	public void backupSaldo(int backupSaldo) throws IOException {
		FileWriter writeStream = new FileWriter(fileSaldo, false);
		PrintWriter writer = new PrintWriter(new BufferedWriter(writeStream));
		writer.println(backupSaldo);
		writer.close();
	}
	
}