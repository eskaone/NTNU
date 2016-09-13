class Analyse {
	private int[] tabellTegn = new int[30];
	private String tekst;
	private String letter;
	private char tegn;
	private int verdi;
	
	// konstrukt�r
	public Analyse(String tekst) {
		this.tekst = tekst.toLowerCase();
		
		for (int i = 0; i < tekst.length(); i++) {
			tegn = tekst.charAt(i);
			verdi = tegn - 97;
			
			switch (verdi) { // � = 8119, � = 8153, � = 8127
			case 8119:
				tabellTegn[26]++;
				break;
				
			case 8153:
				tabellTegn[27]++;
				break;
				
			case 8127:
				tabellTegn[28]++;
				break;
				
			default:
				if (verdi > -1 && verdi < 26) { //a = 0 -- z = 25
					tabellTegn[verdi]++;
				} else if (verdi > 25 && verdi < 30 || verdi > -65 && verdi < 0) {
					tabellTegn[29]++;
				}
				break;
			}
		}
	}
	
	// metoder
	public int getAntBoks() {
		int antBoks = 0;
		for (int i = 0; i < 29; i++) {
			if (tabellTegn[i] != 0) {
				antBoks++;
			}
		}
		return antBoks;
	}
	
	public int getTotBoks() {
		int totBoks = 0;
		for (int i = 0; i < 29; i++) {
			totBoks += tabellTegn[i];
		}
		return totBoks;
	}
	
	public int getTotTegn() {
		return tabellTegn[29];
	}

	public double getPrecent() {
		double totSum = getTotTegn() + getTotBoks();
		double precent = 0;
		precent = (getTotTegn() / totSum) * 100;
		return precent;
	}
	
	public int getLetterSum(String letter) {
		int letterSum = 0;
		tegn = letter.charAt(0);
		verdi = tegn - 97; 
		
		switch (verdi) { // � = 8119, � = 8153, � = 8127
		case 8119:
			letterSum = tabellTegn[26];
			break;
			
		case 8153:
			letterSum = tabellTegn[27];
			break;
			
		case 8127:
			letterSum = tabellTegn[28];
			break;
			
		default:
			letterSum = tabellTegn[verdi];
			break;
		}
		return letterSum;
	}
	
	public int getMaksAntall() {
		int maksAntall = 0;
		for (int i = 0; i < 29; i++) {
			if (maksAntall < tabellTegn[i]) {
				maksAntall = tabellTegn[i];
			}
		}
		return maksAntall;
	}
	
	public char getMaksLetter() {
		char maksLetter = 'a';
		String tmp = "";
		int maks = 0;
		for (int i = 0; i < 29; i++) {
			if (maks < tabellTegn[i]) {
				maks = tabellTegn[i];
				
				switch (i) { // � = 8119, � = 8153, � = 8127
				case 26:
					maksLetter = 8216;
					break;
					
				case 27:
					maksLetter = 8250;
					break;
					
				case 28:
					maksLetter = 8224;
					break;
					
				default:
					maksLetter = (char) (i + 97);
					break;
				}
			} else if (maks == tabellTegn[i]) {
				switch (i) { // � = 8119, � = 8153, � = 8127
				case 26:
					maksLetter += 8216;
					break;
					
				case 27:
					maksLetter += 8250;
					break;
					
				case 28:
					maksLetter += 8224;
					break;
					
				default:
					maksLetter += (char) (i + 97);
					break;
					
				}	
			}	
		}
		return maksLetter;
	}


	public void getUnicode() {
		for (int i = 0; i < tekst.length(); i++) {
			char tegn = tekst.charAt(i);
			int verdi = tegn;
			System.out.println("Unicode-verdien til tegnet " + tegn + " paa posisjon " + i + " er " + verdi);
		}
	}

}