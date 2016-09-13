import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Skriv inn tekst: ");
		String tekstInput = sc.nextLine();
		TextEdit textEdit = new TextEdit(tekstInput);
		
		while (true) {
			System.out.println("\n---TEKSTBEHANDLING---");
			System.out.println("Input: '" + tekstInput + "'");
			System.out.println("\nVelg et alternativ: "); 
			System.out.println("1: Antall ord i teksten"); 
			System.out.println("2: Gjennomsnittlig ordlenge"); 
			System.out.println("3: Gjennomsnitt ord pr. periode(skilles med '.', '!', ':' og '?')");
			System.out.println("4: Erstatt ord i teksten");
			System.out.println("5: Skriv ut teksten");
			System.out.println("6: Skriv ut teksten i store bokstaver");
			System.out.println("7: Avslutt");

			int inputChoice = sc.nextInt();

			switch (inputChoice) {
			case 1:
				System.out.println("\nSjekker antall ord: "); 
				System.out.println(textEdit.getWordCount());
				break;
				
			case 2:
				System.out.println("\nSjekker gjennomsnittlig ordlengde: "); 
				System.out.println(textEdit.getAvgWordLength());
				break;
				
			case 3:
				System.out.println("\nSjekker gjennomsnitt ord pr. periode: "); 
				System.out.println(textEdit.getAvgWordPeriod());
				break;
				
			case 4:
				System.out.println("\nErstatt:");
				textEdit.ByttUt();
				break;
				
			case 5:
				System.out.println("\nPrinter ut teksten: "); 
				textEdit.getText();
				break;
				
			case 6:
				System.out.println("\nPrinter ut teksten i store bokstaver: ");
				textEdit.getALLCAPSText();
				break;
				
			case 7:
				System.out.println("Avslutter...");
				return;
				
			default:
				System.out.println("Feil input...");
				break;
			}
		}
		
		
		
	}
}