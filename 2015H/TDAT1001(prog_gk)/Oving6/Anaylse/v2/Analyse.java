class Analyse {
	private String tekst = "";
	private int[] antallTegn = new int[30];
	private final int ALFABETGRENSE = 29;
	
	
	//konstruktør
	public Analyse(String tekst) {
		this.tekst = tekst;
		
		for (int i = 0; i < tekst.length(); i++) {
			char tegn = tekst.charAt(i);
			if (tegn > 64 && tegn < 91) {
				antallTegn[tegn - 'A']++;
			} else if (tegn > 96 && tegn < 123) {
				antallTegn[tegn - 'a']++;
			} else if (tegn == 230 || tegn == 198) {
				antallTegn[26]++;
			} else if (tegn == 248 || tegn == 216) {
				antallTegn[27]++;
			} else if (tegn == 229 || tegn == 197) {
				antallTegn[28]++;
			} else if (tegn > 31 && tegn < 65 || tegn > 90 && tegn < 97 || tegn > 122 && tegn < 127) {
				antallTegn[29]++;
			}
		}
	}
	
	//metoder
	public int getAntallForskjelligeBokstaver() {
		int antallForskjelligeBokstaver = 0;
		for (int i = 0; i < ALFABETGRENSE; i++) {
			if (antallTegn[i] != 0) {
				antallForskjelligeBokstaver++;
			}
		}
		return antallForskjelligeBokstaver;
	}
	
	public int getAntallBokstaver() {
		int antallBokstaver = 0;
		for (int i = 0; i < ALFABETGRENSE; i++) {
			antallBokstaver += antallTegn[i];
		}
		return antallBokstaver;
	}
	
	public int getProsentIkkeBokstaver() {
		int prosentIkkeBokstaver = 0;
		int ikkeBokstaver = 0;
		ikkeBokstaver = antallTegn[29];
		
		prosentIkkeBokstaver = (ikkeBokstaver * 100) / (ikkeBokstaver + getAntallBokstaver());
		
		return prosentIkkeBokstaver;
	}
	
	public int getForekomsterAvBestemtBokstav(char bokstav) {
		int forekomsterAvBestemtBokstav = 0;
		
		return forekomsterAvBestemtBokstav;
	}
	
	public char getHvilkenBokstavForekommerOftes() {
		char hvilkenBokstavForekommerOftes = 'a';
		
		return hvilkenBokstavForekommerOftes;
	}
}