/**
 * Created by asdfLaptop on 01.11.2016.
 */
public class Main {
    public static void main(String[] args) {
        //oppg 3a
        char[] ia1 = {'0', '1'};
        int[] at1 = {2};
        int[][] ntt1 = {{1,3}, {1,2}, {2,3}, {3,3}};
        char[] c0 = {};
        char[] c1 = {'0', '1', '0'};
        char[] c2 = {'1', '1', '1'};
        char[] c3 = {'0', '1', '0', '1', '1', '0'};
        char[] c4 = {'0', '0', '1', '0', '0', '0'};
        Automat a1 = new Automat(ia1, at1, ntt1);
        System.out.println("Empty: " + a1.checkInput(c0));
        System.out.println("010: " + a1.checkInput(c1));
        System.out.println("111: " + a1.checkInput(c2));
        System.out.println("010110: " + a1.checkInput(c3));
        System.out.println("001000: " + a1.checkInput(c4));

        System.out.println("----------");
        //oppg 3b
        char[] ia2 = {'a', 'b'};
        int[] at2 = {3};
        int[][] ntt2 = {{1,2}, {4,3}, {3,4}, {3,3}, {4,4}};
        char[] abbb = {'a', 'b', 'b', 'b'};
        char[] aaab = {'a', 'a', 'a', 'b'};
        char[] babab = {'b', 'a', 'b', 'a', 'b'};
        Automat a2 = new Automat(ia2, at2, ntt2);
        System.out.println("abbb: " + a2.checkInput(abbb));
        System.out.println("aaab: " + a2.checkInput(aaab));
        System.out.println("babab: " + a2.checkInput(babab));
    }
}
