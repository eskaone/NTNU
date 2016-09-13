class Student {
	private final String navn;
	private int antOppg;
	
	public Student(String navn, int antOppg) {
		this.navn = navn;
		this.antOppg = antOppg;
	}
	
	public String getNavn() {
		return navn;
	}
	
	public int getAntOppg() {
		return antOppg;
	}
	
	public void setAntOppg(int antOppg) throws IllegalArgumentException {
		this.antOppg = antOppg;
	}
	
	public String toString() {
		return "Studenten heter " + navn + " og har fullfort " + antOppg + " oppgaver.";
	}
}