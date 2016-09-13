import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Transaksjon t = new Transaksjon();
		try {
			int backupSaldo = t.getSaldo();
			while (true) {
				System.out.println("Saldo: " + t.getSaldo());
				System.out.println("1. Inn");
				System.out.println("2. Ut");
				System.out.println("3. Avslutt og oppdater saldo");
				int choice = s.nextInt();
			
				switch (choice) {
					case 1: 
						t.fillTransactionInn();
						break;
					
					case 2:
						t.fillTransactionUt();
						break;
					
					case 3:
						System.out.println("Avslutter og oppdaterer saldo...");
						t.updateSaldo();
						if(t.getSaldo() >= 0) {
							System.out.println("Ny saldo: " + t.getSaldo());
							return;
						} else {
							System.out.println("Saldo i minus. Sletter transaksjoner. Oppdaterer ikke saldo.");
							t.saldoMinus();
							t.backupSaldo(backupSaldo);
							return;
						}
					default:
						System.out.println("Ikke et alternativ...");
						break;
				}
			}
		} catch (IOException e) {
			System.out.println("ERROR");
		}	
	}
}