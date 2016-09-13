class Kast {
	private int sumPoeng = 0;
	private int terningkast;
	public boolean ferdig = false;
	
	java.util.Random terning = new java.util.Random();
	
	
	//konstruktør
	public Kast() {
	}
	
	//metoder
	public int getSumPoeng() {
		return sumPoeng;
	}
	
	public void kastTerning() {
		terningkast = terning.nextInt(6) + 1;
		if (terningkast == 1) {
			sumPoeng = 0;
		} else {
			sumPoeng += terningkast;
		} 
	}
	
	public boolean erFerdig() {
		if (sumPoeng >= 100) {
			ferdig = true;
		} else {
			ferdig = false;
		}
		return ferdig;
	}
}