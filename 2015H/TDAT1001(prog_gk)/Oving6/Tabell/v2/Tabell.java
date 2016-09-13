import java.util.Scanner;
import java.util.Random;

public class Tabell {
	public static void main(String[] args) {
		Random r = new Random();
		Scanner sc = new Scanner(System.in);
		System.out.println("Hvor mange tilfeldige tall skal leses?");
		int inputTo = sc.nextInt();
		
		int[] antall = new int[10];
		for (int i = 0; i < inputTo; i++) {
			int tall = r.nextInt(10);
			antall[tall]++;
		}
		for (int i = 0; i < 10; i++) {
			System.out.println(i + ": " + antall[i]);
		}
	}
}