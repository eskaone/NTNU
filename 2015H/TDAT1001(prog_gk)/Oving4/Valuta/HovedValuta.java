import java.util.Scanner;

public class HovedValuta {
	public static void main(String[] args) {
		
		double BELOP;
		while (true) {
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("=======================================");
			System.out.println("=======================================\n");
			System.out.println("Velg valuta:");
			System.out.println("1: Dollar");
			System.out.println("2: Euro");
			System.out.println("3: Svenske kroner");
			System.out.println("4: Avslutt");
			int choice = sc.nextInt();
			
			switch (choice) {
				case 1:
					System.out.println("\nVelg handling:\n1: USD til NOK\n2: NOK til USD");
					int choice1 = sc.nextInt();
					
					switch (choice1) {
						case 1: 
						System.out.println("\nUSD til NOK valgt...\nHvor mange dollar?");
						BELOP = sc.nextDouble();
						Valuta usd = new Valuta(BELOP, 8.27);
						System.out.println(BELOP + " USD er " + usd.calcToNOK()+ " NOK.");
						break;
						
						case 2:
						System.out.println("\nNOK til USD valgt...\nHvor mange norske kroner?");
						BELOP = sc.nextDouble();
						usd = new Valuta(BELOP, 8.27);
						System.out.println(BELOP + " NOK er " + usd.calcFromNOK()+ " USD.");
						break;
						
						default:
						System.out.println("Feil input!");
						break;
					}
					
					break;
					
				case 2:
					System.out.println("Velg handling:\n1: Euro til NOK\n2: NOK til Euro");
					int choice2 = sc.nextInt();
				
					switch (choice2) {
						case 1: 
						System.out.println("\nEuro til NOK valgt...\nHvor mange euro?");
						BELOP = sc.nextDouble();
						Valuta eu = new Valuta(BELOP, 9.22);
						System.out.println(BELOP + " euro er " + eu.calcToNOK()+ " NOK.");
						break;
						
						case 2:
						System.out.println("\nNOK til Euro valgt...\nHvor mange norske kroner?");
						BELOP = sc.nextDouble();
						eu = new Valuta(BELOP, 9.22);
						System.out.println(BELOP + " NOK er " + eu.calcFromNOK()+ " euro.");
						break;
						
						default:
						System.out.println("Feil input!");
						break;
					}
						
					break;
					
				case 3:
					System.out.println("Velg handling:\n1: SEK til NOK\n2: NOK til SEK");
					int choice3 = sc.nextInt();
				
					switch (choice3) {
						case 1: 
						System.out.println("\nSEK til NOK valgt...\nHvor mange SEK?");
						BELOP = sc.nextDouble();
						Valuta sek = new Valuta(BELOP, 0.97);
						System.out.println(BELOP + " SEK er " + sek.calcToNOK() + " NOK.");
						break;
						
						case 2:
						System.out.println("\nNOK til SEK valgt...\nHvor mange norske kroner?");
						BELOP = sc.nextDouble();
						sek = new Valuta(BELOP, 0.97);
						System.out.println(BELOP + " NOK er " + sek.calcFromNOK() + " SEK.");
						break;
						
						default:
						System.out.println("Feil input!");
						break;
					}
					
					break;
					
				case 4: 
					System.out.println("Avslutter...");
					return;
					
				default:
					System.out.println("Feil input!");
					break;
			}
			
		}

	}
}