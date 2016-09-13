import java.time.LocalDate;
import java.time.Period;

class BonusMedlem {
	private final int medlNr;
	private final Personalia pers;
	private final LocalDate innmeldtDato; 
	private int poeng = 0;
    private final double FAKTOR;
	
	public BonusMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato) {
		this.medlNr = medlNr;
		this.pers = pers;
		this.innmeldtDato = innmeldtDato;
        FAKTOR = 1;
	}

    public BonusMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato, int poeng, double faktor) {
        this.medlNr = medlNr;
        this.pers = pers;
        this.innmeldtDato = innmeldtDato;
        this.poeng = poeng;
        FAKTOR = faktor;
    }

    public int getMedlnr() {
        return medlNr;
    }

    public Personalia getPers() {
        return pers;
    }

    public LocalDate getInnmeldtDato() {
        return innmeldtDato;
    }

    public int getPoeng() {
        return poeng;
    }

    public int finnKvalPoeng(LocalDate dato) {
        int aarMellom = Period.between(innmeldtDato, dato).getYears();
        if (aarMellom > 0 || aarMellom < 0) {
            return 0;
        } else {
            return poeng;
        }
    }

    public boolean okPassord(String passordet) {
        return pers.okPassord(passordet);
    }

    public void registrerPoeng(int poeng) {
        this.poeng += poeng * FAKTOR;
    }
}