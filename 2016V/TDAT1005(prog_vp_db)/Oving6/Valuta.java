public class Valuta {
	private String valuta;
	private double kurs;
	
	public Valuta(String valuta, double kurs) {
		this.valuta = valuta;
		this.kurs = kurs;
	}
	
	public String getValuta() {
		return valuta;
	}
	
	public double getKurs() {
		return kurs;
	}
}