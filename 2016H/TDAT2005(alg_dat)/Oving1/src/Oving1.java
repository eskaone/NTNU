import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by asdfLaptop on 22.08.2016.
 */
public class Oving1 {
    public static void main(String[] args) {
        int kurs = 0;

        ArrayList<Integer> kursEndring = new ArrayList<>();
        ArrayList<Integer> kursListe = new ArrayList<>();

        kursEndring.add(-1);
        kursEndring.add(3);
        kursEndring.add(-9);
        kursEndring.add(2);
        kursEndring.add(2);
        kursEndring.add(-1);
        kursEndring.add(2);
        kursEndring.add(-1);
        kursEndring.add(-5);

        for(int i = 0; i < kursEndring.size(); i++) {
            kursListe.add((kurs + kursEndring.get(i)));
            kurs += kursEndring.get(i);
        }

        //oppgave1(kursListe);
        testKompleksitet();

    }

    //Oppg. 1
    public static void oppgave1(ArrayList<Integer> priser) {
        int maksDiff = -1;                                          //1
        int selg = -1;                                              //1
        int kjop = -1;                                              //1
        for (int i = 0; i < priser.size(); i++) {                   //1 + 2n
            for (int j = i + 1; j < priser.size(); j++) {           //(1 + 2n) * 2n
                if((priser.get(i) - priser.get(j)) < maksDiff) {    //1
                    maksDiff = priser.get(i) - priser.get(j);       //1
                    selg = j + 1;                                   //1
                    kjop = i + 1;                                   //1
                }
            }
        }
        //System.out.println("Kjøp på dag nr. " + kjop);
        //System.out.println("Selg på dag nr. " + selg);
    }

    //Oppg. 2
    //4n^2 + 4n + 9 -> n^2 er størst og vil utgjøre en forskjell

    public static void testKompleksitet() {
        double tid = 1.0;
        int start = 100;
        for (int n=start; n < 1000*start; n*=2){
            ArrayList<Integer> liste = genererData(n);
            Date starttid = new Date();
            Date sluttid = new Date();
            int antRunder = 0;
            while (sluttid.getTime()-starttid.getTime()<10) {
                oppgave1(liste);
                sluttid = new Date();
                antRunder++;
            }
            double forrigeTid = tid;
            tid = (double)(sluttid.getTime()-starttid.getTime())/antRunder;
            System.out.format("Antall: %6d, tid: %8.2f ms, forholdstall: %6.2f, antall runder: %6d%n",n,tid,tid/forrigeTid, antRunder);
        }
    }

    public static ArrayList<Integer> genererData(int n) {
        Random r = new Random();
        ArrayList<Integer> randomListe = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            randomListe.add(r.nextInt());
        }
        return randomListe;
    }
}
