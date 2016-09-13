import java.time.LocalDate;

class GullMedlem extends BonusMedlem {

    public GullMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato, int poeng) {
        super(medlNr, pers, innmeldtDato, poeng, 1.5);
    }
}