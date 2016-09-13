import java.util.Scanner;

class Stjerne {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Hvor mange linjer med stjerner vil du vise?");
		int linjer = sc.nextInt();
		String teller = "*";
		String tempTeller = "";
		
		for (int i = 0; i < linjer; i++) {
			System.out.println(tempTeller += teller);
		}
		
	}
}