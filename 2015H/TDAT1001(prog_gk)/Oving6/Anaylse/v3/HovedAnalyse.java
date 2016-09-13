import java.util.Scanner;

class HovedAnalyse {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n--TEKSTANALYSE--\n");
		
		System.out.println("Skriv inn en setning som skal analyseres:");
		String tekst = sc.nextLine();
		
		System.out.println("Hvilken bokstav vil du sjekke forekomster av?");
		String bokstav = sc.nextLine();
		
		Analyse analyse  = new Analyse(tekst);
		
		//Antall forskjellige bokstaver metode
		System.out.println("\nDet er " + analyse.getAntallForskjelligeBokstaver() + " forskjellige bokstaver i teksten.");
		
		//Antall totalt bokstaver metode
		System.out.println("Det er " + analyse.getAntallBokstaver() + " bokstaver totalt i teksten.");
		
		//Antall prosent ikke bokstaver metode
		System.out.println(analyse.getProsentIkkeBokstaver() + " % er ikke bokstaver i teksten.");
		
		//Antall forekomster av valgt bokstav metode
		System.out.println("Bokstaven '" + bokstav + "' forekommer " + analyse.getForekomsterAvBestemtBokstav(bokstav) + " ganger i teksten.");
		
		//Hvilken bokstav forekommer oftest i teksten metode
		System.out.println("Bokstaven(e) " + analyse.getHvilkenBokstavForekommerOftes() + " forekommer oftest i teksten.");
		
	}
}