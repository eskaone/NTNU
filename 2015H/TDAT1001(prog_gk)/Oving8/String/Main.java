import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Skriv inn tekst:");
		String inputTekst = sc.nextLine();
		NyString nyString = new NyString(inputTekst);
		
		System.out.println("\nSjekker teksten: "); 
		nyString.getTekst();
		
		System.out.println("\nSjekker forkort-funksjonen: ");
		nyString.forkort();
		
		System.out.println("\nSjekker fjern-funksjonen: ");
		nyString.fjernTegn();
		
		System.out.println("\nSjekker teksten igjen: "); 
		nyString.getTekst();
		
	}
}