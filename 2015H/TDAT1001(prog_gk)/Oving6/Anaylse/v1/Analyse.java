import java.util.Scanner;

class Analyse {
	Scanner sc = new Scanner(System.in);
	private String tekst = "";
	private int[] antallTegn = new int[30];
	
	
	//konstruktør
	public Analyse(String tekst) {
		this.tekst = tekst;
		for (int i = 0; i < tekst.length(); i++) {
			char bokstav = tekst.charAt(i);
			int verdi = analyserTekst(bokstav);
			antallTegn[i]++;
		}
	}
	
	
	//metoder
	public void inputTekst() {
		System.out.println("Skriv inn tekst som skal analyseres:");
		tekst = sc.nextLine();
		
	}
	
	public int analyserTekst(char bokstav) {
		int verdi = bokstav;
		return verdi;
	}
	
	public void printAnalyse() {
		System.out.println("a: " + antallTegn[0]);
	}
	
	public void printTekst() {
		System.out.println(tekst);
	}
	
	public void printArray() {
		for (int i = 0; i < antallTegn.length; i++) {
			System.out.println(antallTegn[i]);
		}
	}
	
	public void unicodeVerdi() {
		for (int i = 0; i < tekst.length(); i++) {
			char tegn = tekst.charAt(i);
			int verdi = tegn;
			System.out.println("Unicode-verdien til tegnet " + tegn + " pA posisjon " + i + " er " + verdi + ".");
		}
	}
}