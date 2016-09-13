import java.util.Random;
import java.util.Scanner;

public class Tabell {
	private Random r;
	private int[] tabell;
	int count0 = 0;
	int count1 = 0;
	int count2 = 0;
	int count3 = 0;
	int count4 = 0;
	int count5 = 0;
	int count6 = 0;
	int count7 = 0;
	int count8 = 0;
	int count9 = 0;
	
	
	
	
	public Tabell() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Hvor mange tilfeldige tall skal leses?");
		int inputTo = sc.nextInt();
		r = new Random();
		tabell = new int[inputTo];
		for (int i = 0; i < tabell.length; i++) {
			tabell[i] = r.nextInt(10);
		}
	}
	
	public void printTable() {
		for (int i = 0; i < tabell.length;i++) {
			System.out.println(tabell[i]);
		}
		System.out.println("-------");
	}
	
	public void readTable() {
		for (int i = 0; i < tabell.length;i++) {
			
			switch (tabell[i]) {
			case 0:
				count0++;
				continue;
			case 1:
				count1++;
				continue;
			case 2:
				count2++;
				continue;
			case 3:
				count3++;
				continue;
			case 4:
				count4++;
				continue;
			case 5:
				count5++;
				continue;
			case 6:
				count6++;
				continue;
			case 7:
				count7++;
				continue;
			case 8:
				count8++;
				continue;
			case 9:
				count9++;
				break;
			}
			
			
		}
		System.out.println("\n0: " +count0);
		System.out.println("1: " +count1);
		System.out.println("2: " +count2);
		System.out.println("3: " +count3);
		System.out.println("4: " +count4);
		System.out.println("5: " +count5);
		System.out.println("6: " +count6);
		System.out.println("7: " +count7);
		System.out.println("8: " +count8);
		System.out.println("9: " +count9);
		
	}
	
	public int sumCounts() {
		return count0 + count1 + count2 + count3 + count4 + count5 + count6 + count7 + count8 + count9;
	}
}