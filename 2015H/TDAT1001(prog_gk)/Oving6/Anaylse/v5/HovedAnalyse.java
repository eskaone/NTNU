import java.util.Scanner;

class HovedAnalyse {
	public static void main(String[] args) {
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("\n--TEKSTANALYSE--\n");
			System.out.println("Skriv inn en tekst: ");
			String tekstInput = sc.nextLine();
			System.out.println("Skriv inn en bokstav: ");
			String letterInput = sc.nextLine();
			Analyse analyse = new Analyse(tekstInput.toLowerCase());
			

			System.out.println("Antall bokstaver: " + analyse.getAntBoks());
			System.out.println("Antall bokstaver totalt: " + analyse.getTotBoks());
			System.out.println("Antall tegn totalt: " + analyse.getTotTegn());
			System.out.println("Prosentandel med tegn: " + analyse.getPrecent() + "%");
			System.out.println("Forekomster av bokstaven '" + letterInput.charAt(0) + "': " + analyse.getLetterSum(letterInput));
			System.out.println("Bokstaven(e) som forekommer mest er '" + analyse.getMaksLetter() + "'. Bokstaven(e) forekommer " + analyse.getMaksAntall() + " ganger.");
			
		}
	}
}