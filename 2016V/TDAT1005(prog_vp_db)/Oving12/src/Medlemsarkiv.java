import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by qwop on 11.02.2016.
 */
public class Medlemsarkiv {
    private ArrayList<BonusMedlem> medlemmer = new ArrayList<BonusMedlem>();
    private Random r = new Random();

    public int finnPoeng(int medlNr, String passord) {
        for(int i = 0; i < medlemmer.size(); i++) {
            if(medlemmer.get(i).getMedlnr() == medlNr && medlemmer.get(i).okPassord(passord)) {
                return medlemmer.get(i).getPoeng();
            }
        }
        return -1;
    }

    public boolean registrerPoeng(int medlNr, int poeng) {
        for(int i = 0; i < medlemmer.size(); i++) {
            if(medlemmer.get(i).getMedlnr() == medlNr) {
                medlemmer.get(i).registrerPoeng(poeng);
                return true;
            }
        }
        return false;
    }

    public int nyMedlem(Personalia pers, LocalDate innmeldt) {
        int medlNr = finnLedigNr();
        BasicMedlem bm = new BasicMedlem(medlNr, pers, innmeldt);
        medlemmer.add(bm);
        return medlNr;
    }

    public void sjekkMedlemmer() {
        for (int i = 0; i < medlemmer.size(); i++) {
            if (medlemmer.get(i) instanceof BasicMedlem) {
                if (medlemmer.get(i).finnKvalPoeng(LocalDate.now()) >= 25000) {
                    if (medlemmer.get(i).finnKvalPoeng(LocalDate.now()) >= 75000) {
                        GullMedlem gm = new GullMedlem(medlemmer.get(i).getMedlnr(), medlemmer.get(i).getPers(), medlemmer.get(i).getInnmeldtDato(), medlemmer.get(i).getPoeng());
                        medlemmer.set(i, gm);
                    } else {
                        SoelvMedlem sm = new SoelvMedlem(medlemmer.get(i).getMedlnr(), medlemmer.get(i).getPers(), medlemmer.get(i).getInnmeldtDato(), medlemmer.get(i).getPoeng());
                        medlemmer.set(i, sm);
                    }
                }
            } else if (medlemmer.get(i) instanceof SoelvMedlem) {
                if (medlemmer.get(i).finnKvalPoeng(LocalDate.now()) >= 75000) {
                    GullMedlem gm = new GullMedlem(medlemmer.get(i).getMedlnr(), medlemmer.get(i).getPers(), medlemmer.get(i).getInnmeldtDato(), medlemmer.get(i).getPoeng());
                    medlemmer.set(i, gm);
                }
            }
        }
    }

    public BonusMedlem getBonusMedlem(int medlNr) {
        for (int i = 0; i < medlemmer.size(); i++) {
            if (medlemmer.get(i).getMedlnr() == medlNr) {
                return medlemmer.get(i);
            }
        }
        return null;
    }
/**
    private int finnLedigNr() {
        int nyttMedlNr = -1;
        boolean ok = false;
        while(!ok) {
            nyttMedlNr = (r.nextInt(1000000) + 1);
            for(int i = 0; i < medlemmer.size(); i++) {
                if(medlemmer.get(i).getMedlnr() != nyttMedlNr) {
                    ok = true;
                }
            }
        }
        return nyttMedlNr;
    }
 */

private int finnLedigNr() {
    int nyttMedlNr = (r.nextInt(1000000) + 1);
    return nyttMedlNr;
}

}
