import java.time.LocalDate;

class SoelvMedlem extends BonusMedlem {

    public SoelvMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato, int poeng) {
        super(medlNr, pers, innmeldtDato, poeng, 1.2);
    }
}