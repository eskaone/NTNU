import java.util.Scanner;

public class HovedTabell {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		while (true) {
			Tabell tabell = new Tabell();
			System.out.println("1: Vis alt\n2: Bare antall og sum\n3: Avslutt");
			int choice = sc.nextInt();
			System.out.println("-------");
			switch (choice) {
			case 1:
				tabell.printTable();
				tabell.readTable();
				System.out.println("= " + tabell.sumCounts() + "\n");
				break;
				
			case 2:
				tabell.readTable();
				System.out.println("= " + tabell.sumCounts() + "\n");
				break;
				
			case 3:
				System.out.println("Avslutter...");
				return;
				
			default:
				System.out.println("Feil input. Try again!");
				break;
			}
		}
	}
}