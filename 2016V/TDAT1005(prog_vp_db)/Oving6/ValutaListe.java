import java.util.*;

class ValutaListe {
	private ArrayList<Valuta> valutaList = new ArrayList<Valuta>();
	
	public ValutaListe() {
		valutaList.add(new Valuta("Euro", 8.10)); //8.10
		valutaList.add(new Valuta("US Dollar", 6.23)); //6.23
		valutaList.add(new Valuta("Britiske pund", 12.27));
		valutaList.add(new Valuta("Svenske kroner", 0.89));
		valutaList.add(new Valuta("Danske kroner", 1.09));
		valutaList.add(new Valuta("Yen", 0.05));
		valutaList.add(new Valuta("Islandske kroner", 0.09));
		valutaList.add(new Valuta("Norske kroner", 1));
	}
	
	public boolean regNyValuta(String valuta, double kurs) {
		for (int i = 0; i < valutaList.size(); i++) {
			if (valuta.equals(valutaList.get(i).getValuta())) {
				return false;
			}
		}
		valutaList.add(new Valuta(valuta, kurs));
		return true;
	}
	
	public int getAntValuta() {
		return valutaList.size();
	}
	
	public Valuta getValutaIndex(int index) {
		for (int i = 0; i < valutaList.size(); i++) {
			if (valutaList.get(i) == null) {
				return null;
			}
		}
		return valutaList.get(index);
	}
	
	public double valutaToValuta(String navnFra, String navnTil, double belop) {
		double belopFra = 0;
		double belopTil = 0;
		for (int i = 0; i < valutaList.size(); i++) {
			if (navnFra.equals(valutaList.get(i).getValuta())) {
				belopFra = valutaList.get(i).getKurs() * belop;
			}
			if (navnTil.equals(valutaList.get(i).getValuta())) {
				belopTil = valutaList.get(i).getKurs() * belop;
			}
		}
		return ((belopFra/belopTil) * belop);
	}
	
}