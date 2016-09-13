public class Valuta {
	private final double KURS;
	private final double BELOP;
	
	//Konstruktør
	public Valuta(double BELOP, double KURS) {
		this.KURS = KURS;
		this.BELOP = BELOP;
	}
	
	//metoder
	public double calcToNOK() {
		return BELOP * KURS;
	}
	
	public double calcFromNOK() {
		return BELOP / KURS;
	}
}