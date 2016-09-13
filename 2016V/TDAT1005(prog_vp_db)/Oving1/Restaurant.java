class Restaurant {
	private String navn;
	private int aar;
	Bord b0rd;
	
	public Restaurant(String navn, int aar, int antBord) {
		this.navn = navn;
		this.aar = aar;
		b0rd = new Bord(antBord);
	}
	
	public String getNavn() {
		return navn;
	}
	
	public void setNavn(String nyttNavn) {
		this.navn = nyttNavn;
	}
	
	public int getAar() {
		return aar;
	}
	
	public int finnAlder() {
		int alder = 2016 - getAar();
		return alder;
	}	
	
	public int ledigBord() {
		return b0rd.ledigBord();
	}
	
	public int opptattBord() {
		return b0rd.opptattBord();
	}
	
	public boolean reserverBord(String navn, int antall) {
		return b0rd.reserverBord(navn, antall);
	}
	
	public void listTab() {
		System.out.println("Bordliste: " + b0rd);
	}
	
	
	public String finnBord(String person) {
		return b0rd.finnBord(person);
	}
	
	public void frigiBord(int[] ryddeTab) {
		b0rd.frigiBord(ryddeTab);
	}	
}