class Analyse {
	private String tekst = "";
	private int[] antallTegn;
	private final int ALFABETLIMIT = 29;
	
	
	//konstruktør
	public Analyse(String tekst) {
		this.tekst = tekst.toLowerCase();
	}
	
	
	//public metoder
	public int getDiffLetters() {
		int diffLetters = 0;
		for (int i = 0; i < ALFABETLIMIT; i++) {
			if (antallTegn[i] != 0) {
				diffLetters++;
			}
		}
		return antallForskjelligeBokstaver;
	}
	
	public int getTotLetters() {
		int totLetters = 0;
		for (int i = 0; i < ALFABETLIMIT; i++) {
			totLetters += antallTegn[i];
		}
		return totLetters;
	}
	
	public int getPrecentNonLetters() {
		int precentNonLetters = 0;
		int nonLetters = 0;
		nonLetters = antallTegn[29];
		
		precentNonLetters = (nonLetters * 100) / (nonLetters + getTotLetters());
		
		return precentNonLetters;
	}
	
	public int getSingleLetter(String bokstav) {
		char tegn = bokstav.charAt(0);
		int tegnVerdi = tegn;
		int singleLetter = 0;
		
		switch (tegnVerdi) {
		case 230:
			singleLetter = antallTegn[26];
			
		case 248:
			singleLetter = antallTegn[27];
			
		case 229:
			singleLetter = antallTegn[28];
			
		default:
			if (tegnVerdi > 96 && tegnVerdi < 123) {
				singleLetter -= 'a';
			}
		}
		return singleLetter;
	}
	
	public String getLetterMostOften() {
		String letterMostOften = "";
		
		
		return letterMostOften;
	}
	
	//private metoder
	private int getNum(char tegn) {
		switch (tegn) {
		case 'æ':
			return 26;
			
		case 'ø':
			return 27;
			
		case 'å':
			return 28;
			
		default:
			return (int) (tegn-97);
		}
	}
	
	private char getChar(int tall) {
		switch (tall) {
		case 26:
			return 'æ';
			
		case 27:
			return 'ø';
			
		case 28:
			return 'å';
			
		default:
			return (char) (tall+97);
		}
	}
}