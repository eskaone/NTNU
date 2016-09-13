/**
 * Created by qwop on 12.02.2016.
 */
import java.time.*;

class Main {
    public static void main(String[] args) throws Exception {
        Medlemsarkiv arkiv = new Medlemsarkiv();

        // Testdata
        Personalia ole = new Personalia("Olsen", "Ole", "ole.olsen@dot.com", "ole");
        Personalia tove = new Personalia("Hansen", "Tove", "tove.hansen@dot.com", "tove");
        LocalDate testdato = LocalDate.of(2008, 2, 10);

        int medl1 = arkiv.nyMedlem(ole, LocalDate.now().minusDays(5)); // Kan oppgradere
        int medl2 = arkiv.nyMedlem(tove, LocalDate.now().minusYears(2)); // Kan ikke oppgradere

        // Begge får nok poeng til å oppgradere
        arkiv.registrerPoeng(medl1, 26000);
        arkiv.registrerPoeng(medl2, 26000);

        arkiv.sjekkMedlemmer();

        // Ole skal nå være sølvmedlem, mens tove er fortsatt basicmedlem
        if (arkiv.getBonusMedlem(medl1) instanceof SoelvMedlem) {
            System.out.println("Ole ble oppgradert til Sølvmedlem");
        }
        if (arkiv.getBonusMedlem(medl2) instanceof BasicMedlem) {
            System.out.println("Tove ble IKKE oppgradert til Sølvmedlem");
        }

    }
}