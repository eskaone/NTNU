import java.util.*;

class Main {
	public static void main(String[] args) {
		String navn = "";
		int antOppg = 0;
		Student student = new Student(navn, antOppg);
		Scanner sc = new Scanner(System.in);
		Oppgaveoversikt oppgoversikt = new Oppgaveoversikt();
		
		while (true) {
			System.out.println("\n--STUDENT--");
			System.out.println("Velg et alternativ: ");
			System.out.println("1. Finn antall studenter registrert");
			System.out.println("2. Finn antall oppgaver en bestemt student har lost");
			System.out.println("3. Registrer en ny student");
			System.out.println("4. Ok antall oppgaver for en bestemt student");
			System.out.println("5. Avslutt");
			int menuInput = sc.nextInt();
			
			switch (menuInput) {
			case 1:
				oppgoversikt.printTab();
				System.out.println("Antall studenter: " + oppgoversikt.getAntStud());
				break;
				
			case 2:
				if (oppgoversikt.getAntStud() > 0) {
					oppgoversikt.printTab();
					System.out.println("Skriv inn navn for A sjekke antall oppgaver: ");
					navn = sc.next();
					
					System.out.println(oppgoversikt.getAntOppg(navn));
					break;
				} else {
					System.out.println("Registrer en student forst...");
					break;
				}
				
			case 3:
				System.out.println("Registrer en ny student: ");
				
				System.out.println("Skriv inn navn: ");
				navn = sc.next();
				
				System.out.println("Skriv inn antall oppgaver studenten har lost: ");
				antOppg = sc.nextInt();
				
				oppgoversikt.regNyStud(navn, antOppg);
				
				oppgoversikt.printTab();
				System.out.println("Registrering fullfort!");
				break;
				
			case 4:
				if (oppgoversikt.getAntStud() > 0) {
					System.out.println("Ok antall oppgaver for en bestemt student: ");
					oppgoversikt.printTab();
					System.out.println("Skriv inn navn for A oke antall oppgaver: ");
					navn = sc.next();
					
					System.out.println("Ok med hvor mye: ");
					int okning = sc.nextInt();
					
					
					System.out.println("Antall oppgaver etter okning: " + (oppgoversikt.okAntOppg(navn, okning) + okning));
					break;
				} else {
					System.out.println("Registrer en student forst...");
					break;
				}
				
			case 5:
				System.out.println("Avslutter...");
				return;
				
			default:
				System.out.println("Ikke et alternativ...");
				break;
				
			}
		}
	}
}