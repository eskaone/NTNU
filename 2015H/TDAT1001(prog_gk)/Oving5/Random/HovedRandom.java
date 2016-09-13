import java.util.Scanner;

public class HovedRandom {
	public static void main(String[] args) {
		int nedreInputHeltall = 0;
		int ovreInputHeltall = 0;
		double nedreInputDesimal = 0;
		double ovreInputDesimal = 0;
		
		Scanner sc = new Scanner(System.in);
		
		Random tall = new Random();
		
		while (true) {
			System.out.println("Velg heltall eller desimaltall:");
			System.out.println("1: Heltall\n2: Desimaltall\n3: Avslutt");
			int choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				System.out.println("\nHeltall valgt...");
				System.out.println("Skriv inn nedre grense: ");
				nedreInputHeltall = sc.nextInt();
				System.out.println("Skriv inn ovre grense: ");
				ovreInputHeltall = sc.nextInt();
				
				if (nedreInputHeltall > ovreInputHeltall) {
					System.out.println("\nNedre grense er storre enn ovre grense. Dette er ikke lov...\nProv igjen!\n");
					break;
				} else {
					System.out.println("\nTilfeldig heltall mellom " + nedreInputHeltall + " og " + ovreInputHeltall + " er " + tall.getRandomHeltall(nedreInputHeltall, ovreInputHeltall) + ".\n");
				}
				
				break;
				
			case 2:
				System.out.println("\nDesimaltall valgt...");
				System.out.println("Skriv inn nedre grense: ");
				nedreInputDesimal = sc.nextDouble();
				System.out.println("Skriv inn ovre grense: ");
				ovreInputDesimal = sc.nextDouble();
				
				if (nedreInputDesimal > ovreInputDesimal) {
					System.out.println("\nNedre grense er storre enn ovre grense. Dette er ikke lov...\nProv igjen!\n");
					break;
				} else {
					System.out.println("\nTilfeldig desimaltall mellom " + nedreInputDesimal + " og " + ovreInputDesimal + " er " + tall.getRandomDesimal(nedreInputDesimal, ovreInputDesimal) + ".");
				}
				
				break;
				
			case 3:
				System.out.println("Avslutter...");
				return;
				
			default:
				System.out.println("Feil input.");
				break;
			}
		}
	}
}