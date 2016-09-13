import java.util.Scanner;

public class HovedBrok {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean nyBrok = true;
		int tellerInput = 0;
		int nevnerInput = 0;

		while (true) {
			
			Brok brok = new Brok(2,4);
			
			if (nyBrok == true) {
				System.out.println("\n--BROK-KALKULATOR--\n");
				System.out.println("Skriv inn brok:");
				
				System.out.println("Teller: ");
				tellerInput = sc.nextInt();
				
				System.out.println("Nevner: ");
				nevnerInput = sc.nextInt();
				
				
				if (nevnerInput == 0) {
					throw new IllegalArgumentException("Kan ikke dele pA 0.");
				}
				
				System.out.println("= (" + tellerInput + " / " + nevnerInput + ")");
				nyBrok = false;
			}
			
			int backupTeller = brok.getTeller();
			int backupNevner = brok.getNevner();
			Brok b = new Brok(tellerInput, nevnerInput);
			
			System.out.println("\nHvilken operasjon?");
			System.out.print("1: Multiplikasjon\n2: Divisjon \n3: Addisjon \n4: Subtraksjon\n5: NY BROK\n6: AVSLUTT\n");
			System.out.println("\n==> (" + brok.getTeller() + " / " + brok.getNevner() + ")  [*, /, +, -]  (" + tellerInput + " / " + nevnerInput + ")");
			int choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				System.out.println("Multiplikasjon valgt...");
				brok.mult(tellerInput, nevnerInput);
				System.out.println("(" + backupTeller + " / " + backupNevner + ")  *  (" + tellerInput + " / " + nevnerInput + ")  =  (" + brok.getTeller() + " / " + brok.getNevner() + ")");
				
				break;
				
			case 2:
				System.out.println("Divisjon valgt...");
				brok.div(tellerInput, nevnerInput);
				System.out.println("(" + backupTeller + " / " + backupNevner + ")  /  (" + tellerInput + " / " + nevnerInput + ")  =  (" + brok.getNevner() + " / " + brok.getTeller() + ")");
				
				break;
				
			case 3:
				System.out.println("Addisjon valgt...");
				brok.add(b);
				System.out.println("(" + backupTeller + " / " + backupNevner + ")  +  (" + tellerInput + " / " + nevnerInput + ")  =  (" + brok.getTeller() + " / " + brok.getNevner() + ")");
				
				break;
				
			case 4:
				System.out.println("Subtraksjon valgt...");
				brok.sub(tellerInput, nevnerInput);
				System.out.println("(" + backupTeller + " / " + backupNevner + ")  -  (" + tellerInput + " / " + nevnerInput + ")  =  (" + brok.getTeller() + " / " + brok.getNevner() + ")");
				
				break;
				
			case 5:
				System.out.println("Ny brok");
				nyBrok = true;
				break;
				
			case 6:
				System.out.println("Avslutter...");
				
				return;
				
			default:
				System.out.println("Feil input.");
				
				break;
			}
		}
	}
}