import java.util.Scanner;

class Oppg5 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Skriv inn et tall for A sjekke om det er et primtall:");
		int tall = sc.nextInt();
		boolean repeat = true;
		boolean sjekk = false;

		if (tall <= 1) {
			sjekk = false;
		} else if (tall <= 3) {
			sjekk = true;
		} else if (tall % 2 == 0 || tall % 3 == 0) {
			sjekk = false;
		} else {
			int i = 5;
		while (i * i <= tall) {
			if (tall % i == 0 || tall % (i + 2) == 0) {
				sjekk = false;
			}
			i = i + 6;
		}

		sjekk = true;
		}

	if (sjekk == false) {
		System.out.println("Tallet "+ tall + " er ikke et primtall.");
	} else
		System.out.println("Tallet "+ tall + " er et primtall.");

	}
}