import java.util.*;

class Rom {
	private int plass;
	private int romNr;
	private ArrayList<Reservasjon> resList = new ArrayList<Reservasjon>();
	
	public Rom(int plass, int romNr) {
		this.plass = plass;
		this.romNr = romNr;
	}
	
	public int getPlass() {
		return plass;
	}
	
	public int getRomNr() {
		return romNr;
	}
	
	public int getAntRes() {
		return resList.size();
	}
	
	public boolean regNyRes(Tidspunkt fraTid, Tidspunkt tilTid, Kunde kunde) {
		for (int i = 0; i < resList.size(); i++) {
			if (resList.get(i).overlapp(fraTid, tilTid)) {
				return false;
			}
		}
		resList.add(new Reservasjon(fraTid, tilTid, kunde));
		return true;
	}
	
	public String toString() {
		String res = "";
		for(Reservasjon r : resList) {
			res += r + "\n";
		}
		String out = "Romnummer: " + romNr + "\nPlass: " + plass + "\nReservasjoner:\n" + res;
		return out;
	}
}