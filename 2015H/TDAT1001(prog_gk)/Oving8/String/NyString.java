import java.util.Scanner;

class NyString {
	private String tekst;
	
	//Konstruktoer
	public NyString(String tekst) {
		this.tekst = tekst;
	}
	
	//Metoder
	public void getTekst() {
		System.out.println(tekst);
	}
	
	public void forkort() {
		String[] ord = tekst.split(" ");
		for (int i = 0; i < ord.length; i++) {
			System.out.print(ord[i].charAt(0));
		}
		System.out.println();
	}
	
	public void fjernTegn() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Hvilket tegn skal fjernes?");
		String inputTegn = sc.nextLine();
		String nyTekst = tekst.replaceAll(inputTegn, "");
		System.out.println("Ny tekst uten '" + inputTegn + "': " + nyTekst);
	}
}