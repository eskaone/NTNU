import java.util.*;

class Oppgaveoversikt {
	private Student[] studenter;
	private int antStud = 0;
	Scanner sc = new Scanner(System.in);
	
	
	public Oppgaveoversikt() {
		studenter = new Student[1];
	}
	
	public void printTab() {
		for (int i = 0; i < studenter.length; i++) {
			if (studenter[i] != null) {
				System.out.println(studenter[i]);
			}
		}
	}
	
	public int getAntStud() {
		return antStud;
	}
	
	public int getAntOppg(String navn) {
		int antOppg = 0;
		for (int i = 0; i < studenter.length; i++) {
			if (studenter[i].getNavn().equals(navn)) {
				antOppg = studenter[i].getAntOppg();
			} else {
				System.out.println("Ingen student med dette navnet...");
			}
		}
		return antOppg;
	}
	
	public int okAntOppg(String navn, int okning) {
		int antOppg = getAntOppg(navn);
		for (int i = 0; i < studenter.length; i++) {
			if (studenter[i].getNavn().equals(navn)) {
				studenter[i].okAntOppg(okning);
			} else {
				System.out.println("Ingen student med dette navnet...");
			}
		}
		return antOppg;
	}
	
	public boolean regNyStud(String navn, int antOppg) {
		
		if (antStud == studenter.length) {
			utvidTabell();
		}

		studenter[antStud] = new Student(navn, antOppg);
		antStud++;
		return true;		
	}
	
	public String toString() {
		return "Antall studenter: " + getAntStud() + "\n";
	}
	
	public boolean sjekkTab() {
		boolean tabOK = false;
		for (int i = 0; i < studenter.length; i++) {
			if (studenter[i] == null) {
				tabOK = false;
			} else {
				tabOK = true;
			}
		}
		return tabOK;
	}
	
	private void utvidTabell() {
		Student[] nyTab = new Student[studenter.length + 1];
		for (int i = 0; i < studenter.length; i++) {
			nyTab[i] = studenter[i];
		}
		studenter = nyTab;
	}
}