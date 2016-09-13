class OppgaveOversikt {
	private Student[] studenter = new Student[5];
	private int antStud = 0;
	
	public boolean regNyStudent(String navn) {
		if (antStud == studenter.length) {
			utvidTabell();
		}
		studenter[antStud] = new Student(navn, 0);
		antStud++;
		return true;
	}
	
	private void utvidTabell() {
		Student[] nyTab = new Student[studenter.length + 5];
		for (int i = 0; i < studenter.length; i++) {
			nyTab[i] = studenter[i];
		}
		studenter = nyTab;
	}
	
	public int getAntStud() {
		return antStud;
	}
	
	public int getAntOppg(String navn) {
		for (int i = 0; i < studenter.length; i++) {
			if (studenter[i].getNavn().equals(navn)) {
				return studenter[i].getAntOppg();
			}
		}
		return -1;
	}
	
	public void okAntOppg(String navn, int okning) {
		for (int i = 0; i < studenter.length; i++) {
			if (studenter[i] != null && studenter[i].getNavn().equals(navn)) {
				studenter[i].setAntOppg(studenter[i].getAntOppg() + okning);
			}
		}
	}
	
	public String[] finnAlleNavn() {
		String[] alleNavn = new String[antStud];
		for (int i = 0; i < studenter.length; i++) {
			if (studenter[i] != null) {
				alleNavn[i] = studenter[i].getNavn();
			}
		}
		return alleNavn;
	}
	
	public String toString() {
		String studListe = "";
		for (int i = 0; i < studenter.length; i++) {
			if (studenter[i] != null) {
				studListe += studenter[i] + "\n";
			}
		}
		return "Antall studenter: " + getAntStud() + "\n" + studListe;
	}
}