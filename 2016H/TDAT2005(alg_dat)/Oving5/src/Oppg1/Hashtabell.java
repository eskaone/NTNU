package Oppg1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by asdfLaptop on 20.09.2016.
 */
public class Hashtabell {
    private int[] ht;
    private String[] values;
    private int kollisjoner = 0;

    public Hashtabell(int n) {
        ht = new int[n];
        values = new String[n];
    }

    public static void main(String[] args) throws IOException {
        Path fil = Paths.get("src/Oppg1/name.txt");
        String[] names = new String(Files.readAllBytes(fil)).split("\\r?\\n");

        Hashtabell ht = new Hashtabell(101);

        for(int i = 0; i < names.length; i++) {
            int key = ht.convertToKey(names[i]);
            ht.addElement(key, names[i]);
        }
        System.out.println("Kollisjoner: " + ht.getKollisjoner());
        System.out.println("Lastfaktor: " + ht.finnLastfaktor(ht.getHt()));

        System.out.println(ht.getIndex(ht.convertToKey("Dalen, Even")));

    }

    public int[] getHt() {
        return ht;
    }

    public String[] getValues() {
        return values;
    }

    public int convertToKey(String text) {
        int key = 0;
        for(int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            key += (int) character * (i + 1);
        }
        return key;

    }

    public int addElement(int k, String v) {
        int m = ht.length;
        int h1 = h1(k, m);
        if (ht[h1] == 0) {
            ht[h1] = k;
            values[h1] = v;
            return h1;
        } else {
            System.out.println("Kollisjon: " + values[h1] + " og " + v);
            kollisjoner++;
            int h2 = h2(k, m);
            for(int i = 1; i < (m-1); i++) {
                int j = probe(h1, h2, i, m);
                if (ht[j] == 0) {
                    ht[j] = k;
                    values[j] = v;
                    return j;
                } else {
                    kollisjoner++;
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

    public int probe(int h1, int h2, int i, int m) {
        return (h1 + i * h2) % m;
    }

    public double finnLastfaktor(int[] ht) {
        int ant = 0;
        for(int i = 0; i < ht.length; i++) {
            if(ht[i] != 0) {
                ant++;
            }
        }
        return ((double)ant/ht.length);
    }

    public int getKollisjoner() {
        return kollisjoner;
    }

    private int getIndex(int s) {
        int m = ht.length;
        int h1 = h1(s, m);
        int h2 = h2(s, m);
        for (int i = 0; i < m; ++i) {
            int j = probe(h1, h2, i, m);
            if(ht[j] == 0) return -1;
            if(ht[j] == s) return j;
        }
        return -1;
    }
}
