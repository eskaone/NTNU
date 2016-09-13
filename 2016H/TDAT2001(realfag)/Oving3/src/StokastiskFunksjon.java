import java.util.Arrays;

/**
 * Created by asdfLaptop on 13.09.2016.
 */
class StokastiskFunksjon {
    private double[] tabell0;
    private double[] tabell1;

    public StokastiskFunksjon(double[] tabell0, double[] tabell1) {
        this.tabell0 = tabell0;
        this.tabell1 = tabell1;
    }

    public double finnForventning() {
        double sum = 0;
        for(int i = 0; i < tabell0.length;i++) {
            sum += tabell0[i] * tabell1[i];
        }
        return sum;
    }

    public double finnVariansen() {
        double sum = 0;
        for(int i = 0; i < tabell0.length;i++) {
            sum += (tabell0[i]*tabell0[i]) * tabell1[i];
        }
        return sum - (finnForventning() * finnForventning());
    }

    public double finnStandardavvik() {
        return Math.sqrt(finnVariansen());
    }

    public double finnFordelingsfunksjon(double x) {
        double sum = -1;
        int teller = 0;
        for(int i = 0; i < tabell0.length; i++) {
            teller++;
            if(x == tabell0[i]) {
                sum = 0;
                for(int j = 0; j < teller; j++) {
                    sum += tabell1[j];
                }
            }
        }
        return sum;
    }

    public String toString() {
        return Arrays.toString(tabell0) + "\n" + Arrays.toString(tabell1);
    }

}
