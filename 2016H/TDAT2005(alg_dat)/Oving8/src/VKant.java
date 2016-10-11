/**
 * Created by asdfLaptop on 11.10.2016.
 */
public class VKant extends Kant {
    VKant neste;
    int vekt;

    public VKant(Node n, VKant neste, int vekt) {
        super(n, neste);
        this.vekt = vekt;
    }
}
