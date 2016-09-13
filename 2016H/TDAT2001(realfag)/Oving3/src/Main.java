/**
 * Created by asdfLaptop on 13.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        double[] tabell0 =
                {0.0, 0.3, 0.5,
                0.6, 0.7, 0.8,
                1.0, 1.2, 1.6};
        double[] tabell1 =
                {1.0/28.0, 2.0/28.0, 5.0/28.0,
                8.0/28.0, 5.0/28.0, 3.0/28.0,
                2.0/28.0, 3.0/56.0, 1.0/56.0};

        StokastiskFunksjon sf = new StokastiskFunksjon(tabell0, tabell1);

        System.out.println("E(X) = " + sf.finnForventning());
        System.out.println("Var(X) = " + sf.finnVariansen());
        System.out.println("Standardavvik = " + sf.finnStandardavvik());
        System.out.println("Fordelingsfunksjon fra 0.7 til 1.2 = " + (sf.finnFordelingsfunksjon(1.2) - sf.finnFordelingsfunksjon(0.7)));
    }
}
