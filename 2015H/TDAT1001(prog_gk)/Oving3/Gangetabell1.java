//importerer verktøy som tillater input fra bruker
import java.util.Scanner; 

class Gangetabell1 {
	public static void main(String[] args) {
	
		//får input fra bruker
		Scanner sc = new Scanner(System.in);
		System.out.println("Hvilken gangetabell vil du vise?");
		int tabellnr = sc.nextInt();		
		
		//bruker for
		for (int i = 1; i <= 10; i++) {
			System.out.println(i + " * "+ tabellnr + " = " + (tabellnr * i));
		} 	
	}
}