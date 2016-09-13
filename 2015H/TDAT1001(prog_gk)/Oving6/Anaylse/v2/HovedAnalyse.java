import java.util.Scanner;

class HovedAnalyse {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean nySetning = true;
		System.out.println("Skriv inn en setning som skal analyseres:");
		String tekstInput = sc.nextLine();
		System.out.println("\n");
		Analyse analyse = new Analyse(tekstInput);
		
		
		while (true) {
			
			
			System.out.println("Hva vil du analysere?\n1: Antall forskjellige bokstaver\n2: Antall bokstaver (totalt)\n3: Prosentandel som er andre tegn enn bokstaver\n4: Finn antall forekomster av en bestemt bokstav\n5: Hvilke(n) bokstav(er) forekommer oftest i teksten\n6: AVSLUTT");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Det er " + analyse.getAntallForskjelligeBokstaver() + " forskjellige bokstaver i teksten.\n");
				break;
				
			case 2:
				System.out.println("Det er " + analyse.getAntallBokstaver() + " bokstaver i teksten.\n");
				break;
				
			case 3:
				System.out.println(analyse.getProsentIkkeBokstaver() + " % er ikke bokstaver i teksten.\n");
				break;
				
			case 4:
				System.out.println("Hvilken bokstav vil du sjekke?");
				char bokstavInput = sc.next().charAt(0);
				System.out.println(bokstavInput + " forekommer " + analyse.getForekomsterAvBestemtBokstav(bokstavInput) + " ganger i teksten.\n");
				break;
				
			case 5:
				System.out.println("Bokstaven(e) " + analyse.getHvilkenBokstavForekommerOftes() + " forekommer oftest i teksten.\n");
				break;
		
			case 6:
				System.out.println("Avslutter...\n");
				return;
				
			default: 
				System.out.println("Feil input.\n");
				break;
			}
		}
	}
}