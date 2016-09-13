class Tekstanalyse{
	String teksten;
	public int[] antallTegn = new int[30];

	public Tekstanalyse(String teksten){
		this.teksten = teksten.toLowerCase();
		puttIArray();
	}

	private void puttIArray(){
		for(int i = 0; i<teksten.length(); i++){
			char tegn = teksten.charAt(i);
			int tegnVerdi = tegn;

			switch(tegnVerdi){
				case(230):
					antallTegn[26]++;
					break;
				case(248):
					antallTegn[27]++;
					break;
				case(229):
					antallTegn[28]++;
					break;
				default:
					if(tegnVerdi > 96 && tegnVerdi < 123){
						antallTegn[tegnVerdi - 97]++;
					} else {
						antallTegn[29]++;
					}
			}
		}
	}

	public int antallForskjelligeBokstaver(){
		int antallBokstaver = 0;
		for(int i = 0; i < 29; i++){
			if(antallTegn[i] != 0){
				antallBokstaver++;
			}
		}
		return antallBokstaver;
	}

	public int antallBokstaver(){
		int totaltBokstaver = 0;
		for(int i = 0; i < 29; i++){
			if(antallTegn[i] != 0){
				totaltBokstaver += antallTegn[i];
			}
		}
		return totaltBokstaver;
	}

	public String prosentIkkjeBokstaver(){
		double totaltTegn = 0.0;
		for(int i = 0; i < 30; i++){
			if(antallTegn[i] != 0){
				totaltTegn += antallTegn[i];
			}
		}
		double prosentAnt = (antallTegn[29]/totaltTegn)*100;
		return String.format("%.2f", prosentAnt) + "%";
	}

	public int antallAvBokstav(String bokstav){
		char tegn = bokstav.toLowerCase().charAt(0);
		int tegnVerdi = tegn;// - 97;
		int antallAvBokstaven = 0;

		switch(tegnVerdi){
			case(230):
				antallAvBokstaven = antallTegn[26];
				break;
			case(248):
				antallAvBokstaven = antallTegn[27];
				break;
			case(229):
				antallAvBokstaven = antallTegn[28];
				break;
			default:
				if(tegnVerdi > 96 && tegnVerdi < 123){
					antallAvBokstaven = antallTegn[tegnVerdi - 97];
				} else {
					antallAvBokstaven = antallTegn[29];
				}
		}

		return antallAvBokstaven;
	}

	public String bokstavForekommerOftest(){
		int storsteTall = 0;
		String forekommerOftest = "";
		for(int i = 0; i < 29; i++){
			if(antallTegn[i] > storsteTall){
				forekommerOftest = "";
				storsteTall = antallTegn[i];
				switch(antallTegn[i]){
					case(26):
						forekommerOftest = "æ";
						break;
					case(27):
						forekommerOftest = "ø";
						break;
					case(28):
						forekommerOftest = "å";
						break;
					default:
						forekommerOftest += (char)(i + 97);
				}
			} else if(antallTegn[i] == storsteTall){
				switch(antallTegn[i]){
					case(26):
						forekommerOftest += " og æ";
						break;
					case(27):
						forekommerOftest += " og ø";
						break;
					case(28):
						forekommerOftest += " og å";
						break;
					default:
						forekommerOftest += " og " + (char)(i + 97);
				}
			}
		}
		forekommerOftest += " forekommer oftest";
		return forekommerOftest;
	}
}