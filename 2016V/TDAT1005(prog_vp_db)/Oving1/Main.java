import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Restaurant res = new Restaurant("Sesam", 1996, 4);
		Scanner sc = new Scanner(System.in);
		int[] ryddeTab;
		
		for(;;) {
			System.out.println("-RESTAURANT-\n");
			System.out.println("Velg alternativ:");
			System.out.println("1. Reserver bord");
			System.out.println("2. Finn hvilke bord en bestemt person har reservert");
			System.out.println("3. Frigi bord");
			System.out.println("4. Avslutt\n");
			int valg = sc.nextInt();
			
			switch (valg) {
				case 1:
				System.out.println("Reserver bord valgt...");
				System.out.println("Hva heter du?");
				String navn = sc.next();
				System.out.println("Hvor mange bord?");
				int antBord = sc.nextInt();
				if (res.reserverBord(navn, antBord)) {
					System.out.println("Bord reservert.");
				} else {
					System.out.println("Bord ble ikke reservert.");
				}
				break;
				
				case 2:
				System.out.println("Hva er navnet paa personen?");
				String person = sc.next();
				System.out.println(person + " har resevert bordnr.: " + res.finnBord(person) + "\n");
				break;
				
				case 3:
				System.out.println("Hvor mange bord skal ryddes?");
				int antall = sc.nextInt();
				ryddeTab = new int[antall];
				for (int i = 0; i < ryddeTab.length; i++) {
					System.out.println("Angi bordnr. som skal ryddes:");
					ryddeTab[i] = sc.nextInt();
				}
				res.frigiBord(ryddeTab);
				break;
				
				case 4:
				System.out.println("Avslutter...");
				return;
				
				default:
				res.listTab();
				System.out.println("Navn: " + res.getNavn());
				System.out.println("Alder: " + res.finnAlder() + " aar");
				res.setNavn("Simsalabim");
				System.out.println("Nytt navn: " + res.getNavn() + "\n");
				break;
			}
		}
	}
}