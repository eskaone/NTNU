import java.util.Date;

/**
 * Created by asdfLaptop on 29.08.2016.
 */
public class Main {
    public static void main(String[] args) {
        //Oppg1
        System.out.println("Oppg 1: " + oppg1(10, 2));

        //Oppg2
        System.out.println("Oppg 2: " + oppg2(10, 2));

        //Test
        System.out.println("Test: " + Math.pow(10, 2));

        //Tidstest
        //testTid();
    }

    public static double oppg1(int x, int n) {
        if(n == 0) {
            return 1;
        } else {
            return x*oppg1(x, n-1);
        }
    }

    public static double oppg2(int x, int n) {
        if(n == 0) {
            return 1;
        } else if(n%2 == 0) {
            return oppg2(x*x, n/2);
        } else {
            return x*oppg2(x*x, (n-1)/2);
        }
    }

    public static void testTid() {
        for(int i = 0; i < 10; i++) {
            long starttid = System.nanoTime();
            oppg1(2, 5000);
            long sluttid = System.nanoTime();
            System.out.println("Runde: "+ (i+1) + " | Tid(ns): " + (sluttid-starttid));
        }
    }
}
