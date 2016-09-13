import java.util.*;

class OppgaveOversiktArrayList {
	private ArrayList<Student> studenter = new ArrayList<Student>();
	
	public boolean regNyStudent(String navn) {
		studenter.add(new Student(navn, 0));
		return true;
	}
	
	public int getAntStud() {
		return antStud;
	}
	
	public int getAntOppg(String navn) {
		for (int i = 0; i < studenter.size(); i++) {
			if (studenter.get(i).getNavn().equals(navn)) {
				return studenter.get(i).getAntOppg();
			}
		}
		return -1;
	}
	
	public void okAntOppg(String navn, int okning) {
		for (int i = 0; i < antStud; i++) {
			if (studenter.get(i) != null && studenter.get(i).getNavn().equals(navn)) {
				studenter.get(i).setAntOppg(studenter.get(i).getAntOppg() + okning);
			}
		}
	}
	
	public String[] finnAlleNavn() {
		String[] alleNavn = new String[antStud];
		for (int i = 0; i < antStud; i++) {
			if (studenter.get(i) != null) {
				alleNavn[i] = studenter.get(i).getNavn();
			}
		}
		return alleNavn;
	}
	
	public String toString() {
		String studListe = "";
		for (int i = 0; i < antStud; i++) {
			if (studenter.get(i) != null) {
				studListe += studenter.get(i) + "\n";
			}
		}
		return "Antall studenter: " + getAntStud() + "\n" + studListe;
	}
}