class Bord {
	private String[] navn;
	
	public Bord(int antBord) {
		navn = new String[antBord];
	}
	
	public int ledigBord() {
		int ledig = 0;
		for (int i = 0; i < navn.length; i++) {
			if (navn[i] == null) {
				ledig++;
			}
		}
		return ledig;
	}
	
	public int opptattBord() {
		int opptatt = navn.length - ledigBord();
		return opptatt;
	}
	
	public boolean reserverBord(String person, int antBord) {
		if (antBord <= ledigBord() && antBord != 0) {
			for (int i = 0; i < antBord; i++) {
				int plass = finnLedigBord();
				if (plass >= 0 && !alleredeReg(person)) {
					navn[plass] = person;
				}
			}
			return true;
		}
		return false;
	}
	
	public void frigiBord(int[] ryddeTab) {
		for (int i : ryddeTab) {
			if (i < navn.length) {
				navn[i] = null;
			}
			
		}
	}
	
	public int finnLedigBord() {
		for (int i = 0; i < navn.length; i++) {
			if (navn[i] == null) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean alleredeReg(String person) {
		for (int i = 0; i < navn.length; i++) {
			if (person != null && navn.equals(navn[i])) {
				return true;
			}
		}
		return false;
	}
	
	public String finnBord(String person) {
		String reservert = "";
		for (int i = 0; i < navn.length; i++) {
			if (person.equals(navn[i])) {
				reservert += i + " ";
			}
		}
		return reservert;
	}
	
	public String toString() {
		String navnList = "";
		for (int i = 0; i < navn.length; i++) {
			navnList += navn[i] + " ";
		}
		return navnList;
	}
}