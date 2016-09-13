import java.util.*;

class Konferansesenter {	
	private ArrayList<Rom> romList = new ArrayList<Rom>();
	
	public boolean resRom(Tidspunkt fraTid, Tidspunkt tilTid, Kunde kunde, int antKunde) {
		for (int i = 0; i < romList.size(); i++) {
			if (romList.get(i).getPlass() >= antKunde) {
				if (romList.get(i).regNyRes(fraTid, tilTid, kunde)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean regRom(int plass, int romNr) {
		for (int i = 0; i < romList.size(); i++) {
			if (romList.get(i).getRomNr() == romNr) {
				return false;
			}
		}
		romList.add(new Rom(plass, romNr));
		return true;
	}
	
	public int getAntRom() {
		return romList.size();
	}
	
	public Rom finnRomIndex(int index) {
		for (int i = 0; i < romList.size(); i++) {
			if (romList.get(i) == null) {
				return null;
			}
		}
		return romList.get(index);
	}
	
	public Rom finnRomNr(int romNr) {
		for (int i = 0; i < romList.size(); i++) {
			if (romList.get(i).getRomNr() == romNr) {
				return romList.get(i);
			}
		}
		return null;
	}
	
	public String toString() {
		String res = "";
		for(Rom r : romList) {
			res += r + "\n";
		}
		String out = "Antall rom: " + getAntRom() + "\n" + res;
		return out;
	}
}