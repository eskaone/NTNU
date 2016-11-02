/**
 * Created by asdfLaptop on 01.11.2016.
 */
public class Automat {
    private char[] ia;
    private int[] at;
    int[][] ntt;

    public Automat(char[] ia, int[] at, int[][] ntt) {
        this.ia = ia;
        this.at = at;
        this.ntt = ntt;
    }

    public char[] getIa() {
        return ia;
    }

    public int[] getAt() {
        return at;
    }

    public int[][] getNtt() {
        return ntt;
    }

    public boolean checkInput(char[] input) {
        int current = 0;
        for(int i = 0; i < input.length; i++) {
            if(input[i] == getIa()[0]) {
                current = getNtt()[current][0];
            } else if(input[i] == getIa()[1]) {
                current = getNtt()[current][1];
            }
        }
        return current == at[0];
    }

}
