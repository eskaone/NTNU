package Oppg2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Hashtabell {
    private int[] hashTab;
    private int[] randTab;
    private static final double A = (Math.sqrt(5)-1)/2;
    private static final int ELEMENT_COUNT = 10000000;
    private static final int TABLE_SIZE = 12499999;

    public Hashtabell() {
        hashTab = new int[TABLE_SIZE];
        randTab = new int[ELEMENT_COUNT];
    }

    public static void main(String[] args) {
        Hashtabell ht = new Hashtabell();
        Random r = new Random();
        for(int i = 0; i < ELEMENT_COUNT; i++) {
            ht.getRandTab()[i] = r.nextInt(Integer.MAX_VALUE-1);
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < ht.getRandTab().length; i++) {
            int key = ht.getRandTab()[i];
            ht.addElement(key, ht.getHashTab());
        }
        long stop = System.currentTimeMillis();
        System.out.println("Total: " + (stop - start) + " ms");

        System.out.println();

        start = System.currentTimeMillis();

        HashMap hm = new HashMap(TABLE_SIZE);
        for(int i = 0; i < 10000000; i++) {
            hm.put(ht.getRandTab()[i], ht.getRandTab()[i]);
        }
        stop = System.currentTimeMillis();
        System.out.println("Total time for hashmap: " + (stop - start) + " ms");
    }

    public int[] getHashTab() {
        return hashTab;
    }

    public int[] getRandTab() {
        return randTab;
    }

    public int addElement(int k, int[] ht) {
        int m = ht.length;
        int h1 = h1(k, m);
        if (ht[h1] == 0) {
            ht[h1] = k;
            return h1;
        } else {
            int h2 = h2(k, m);
            for(int i = 0; i < m; i++) {
                int j = probe(h2, i, m);
                if (ht[j] == 0) {
                    ht[j] = k;
                    return j;
                }
            }
        }
        return -1;
    }

    public int h1(int key, int m) {
        return key % m;
    }

    public int h2(int key, int m) {
        return (key % (m - 1)) + 1;
    }

    public int probe(int h2, int i, int m) {
        return (i + h2) % m;
    }
}