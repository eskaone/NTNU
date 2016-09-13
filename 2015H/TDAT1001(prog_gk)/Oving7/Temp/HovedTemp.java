import java.util.Scanner;

class HovedTemp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			Temp temp = new Temp();
			System.out.println("\n---TEMPERATURER---");
			System.out.println("Velg et alternativ:");
			System.out.println("1: Middeltemp. for hver dag i mnd.");
			System.out.println("2: Middeltemp. for hver time i mnd.");
			System.out.println("3: Middeltemp. for hele mnd.");
			System.out.println("4: Antall dager med folgende grupper");
			System.out.println("5: Avslutt");
			int inputChoice = sc.nextInt();
			
			switch (inputChoice) {
			case 1:
				System.out.println("Middeltemp. for hver dag i mnd.:");
				temp.getMiddelTempDay();
				break;
				
			case 2:
				System.out.println("Middeltemp. for hver time i mnd.:");
				temp.getMiddelTempHour();
				break;
				
			case 3:
				System.out.println("Middeltemp. for hele mnd.:");
				System.out.println(temp.getMiddelTempMonth() + " grader.");
				break;
				
			case 4:
				System.out.println("Antall dager med folgende grupper:");
				temp.getTempGroups();
				break;
				
			case 5:
				System.out.println("Avslutter...");
				return;
				
			default:
				System.out.println("Feil input...");
				break;
				
			}
		}
	}
}