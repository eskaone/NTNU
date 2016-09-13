//Work in progress 

import java.util.Scanner;

class Stjernev2 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Hvor mange linjer med stjerner vil du vise?");
		int linjer = sc.nextInt();
		String teller = " * ";
		String tempTeller = "";
		String spaceTemp = "";
		String space = " ";
		
		for (int j = 0; j < linjer; j++) {
			if (space.length() > 0)  space.substring(1);
			 
			System.out.print(spaceTemp += space);
			//variabelnavn.length() > 0 og variabelnavn.substring(1)
			for (int i = 0; i < linjer; i++) {
			System.out.println(tempTeller += teller);
			}
			
		}
	}
}