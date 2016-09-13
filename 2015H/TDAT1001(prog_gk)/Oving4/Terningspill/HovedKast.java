public class HovedKast {
	public static void main(String[] args) {
		Kast spillerA = new Kast();
		Kast spillerB = new Kast();
		
		int teller = 0;
		
		
		while (!spillerA.erFerdig() && !spillerB.erFerdig()) {
			teller++;
			spillerA.kastTerning();
			spillerB.kastTerning();
			
			System.out.println("Runde " + teller + ":\n");
			System.out.println("Spiller A:\nSum: " + spillerA.getSumPoeng() + "\n");
			System.out.println("Spiller B:\nSum: " + spillerB.getSumPoeng() + "\n");
			System.out.println("==============\n");	
		}
		
		if (spillerA.erFerdig()) {
			System.out.println("\nSpillet er ferdig!");
			System.out.println("Spiller A vant og har kommet over 100 poeng.");
		} else {
			System.out.println("\nSpillet er ferdig!");
			System.out.println("\nSpiller B vant og har kommet over 100 poeng.");
		}
	}
}