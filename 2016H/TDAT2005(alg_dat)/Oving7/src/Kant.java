/**
 * Created by asdfLaptop on 05.10.2016.
 */
public class Kant {
    Kant neste;
    Node til;

    public Kant(Node n, Kant nst){
        til = n;
        neste = nst;
    }
}
