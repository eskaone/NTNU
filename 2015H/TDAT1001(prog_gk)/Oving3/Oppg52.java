import java.util.Scanner;

class Oppg52 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Skriv inn et tall for A sjekke om det er primtall: (Opp til 200 ish)");
		int int1 = sc.nextInt();
		
		if 
		
		(
		int1 == 2 || int1 == 3 || int1 == 5 || int1 == 7 || int1 == 11
		 || int1 == 13 || int1 == 17 || int1 == 19 || int1 == 23 || int1 == 29
		  || int1 == 31 || int1 == 37 || int1 == 41 || int1 == 43 || int1 == 47
		   || int1 == 53 || int1 == 59 || int1 == 61 || int1 == 67 || int1 == 71
		    || int1 == 73 || int1 == 79 || int1 == 83 || int1 == 89 || int1 == 97
			 || int1 == 101 || int1 == 103 || int1 == 107 || int1 == 109 || int1 == 113
			  || int1 == 127 || int1 == 131 || int1 == 137 || int1 == 139 || int1 == 149
			   || int1 == 151 || int1 == 157 || int1 == 163 || int1 == 167 || int1 == 173
			    || int1 == 179 || int1 == 181 || int1 == 191|| int1 == 193 || int1 == 197 
			     || int1 == 199|| int1 == 211 || int1 == 223 || int1 == 227|| int1 == 229
			   ) 
			   
			   {
			System.out.println(int1 +" er et primtall.");
		} else System.out.println(int1 + " er ikke et primtall.");
	}
}