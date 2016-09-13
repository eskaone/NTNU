import java.util.Date;
import java.util.Random;

/**
 * Created by asdfLaptop on 07.09.2016.
 */
public class Oving3 {
    public static void main(String[] args) {
        testTid();
    }

    public static void oppg1(int datamengde) {
        int[] t = genererData(datamengde);
        int v = 0;
        int h = t.length-1;
        quickSort(t, v, h);
        /*
        for(int i = 0; i < datamengde; i++) {
            System.out.println(t[i]);
        }
        */
    }

    public static void quickSort(int[] t, int v, int h) {
        if(h - v > 30) {
            int delepos = splitt(t, v, h);
            quickSort(t, v, delepos - 1);
            quickSort(t, delepos + 1, h);
        } else {
            shellSort(t, v, h);
        }
    }

    public static void shellSort(int[] t, int fra, int til) {
        int s = (til-fra)/2;
        while(s > 0) {
            for(int i = s+fra; i < til+1; i++) {
                int j = i;
                int flytt = t[i];
                while(j >= fra+s && flytt < t[j-s]) {
                    t[j] = t[j-s];
                    j -= s;
                }
                t[j] = flytt;
            }
            s = (s==2) ? 1 : (int)(s / 2.2);
        }
    }

    private static int median3sort(int[] t, int v, int h){
        int m = (v+h)/2;
        if(t[v] > t[m]) {
            bytt(t, v, m);
        }
        if(t[m] > t[h]){
            bytt(t, m, h);
            if(t[v] > t[m]) {
                bytt(t, v, m);
            }
        }
        return m;
    }

    public static void bytt(int[]t, int i, int j){
        int k = t[j];
        t[j]=t[i];
        t[i]= k;
    }

    private static int splitt(int[]t, int v, int h){
        int iv, ih;
        int m = median3sort(t,v,h);
        int dv = t[m];
        bytt(t,m,h-1);
        for (iv = v, ih = h -1;;){
            while(t[++iv]<dv);
            while(t[--ih]>dv);
            if(iv>=ih)break;
            bytt(t,iv,ih);
        }
        bytt(t,iv,h-1);
        return iv;
    }

    public static int[] genererData(int n) {
        Random r = new Random();
        int[] tabell = new int[n];
        for(int i = 0; i < n; i++) {
            tabell[i] = r.nextInt(10);
        }
        return tabell;
    }

    public static void testTid() {
        double tid = 1.0;
        int start = 100;
        for (int n=start; n < 1000*start; n*=2){
            Date starttid = new Date();
            Date sluttid = new Date();
            int antRunder = 0;
            while (sluttid.getTime()-starttid.getTime()<10) {
                oppg1(50000);
                sluttid = new Date();
                antRunder++;
            }
            double forrigeTid = tid;
            tid = (double)(sluttid.getTime()-starttid.getTime())/antRunder;
            System.out.format("Antall: %6d, tid: %8.2f ms, forholdstall: %6.2f, antall runder: %6d%n",n,tid,tid/forrigeTid, antRunder);
        }
    }
}
