package Oppgave1;

/**
 * Created by asdfLaptop on 13.09.2016.
 */
public class Oppg1 {
    private static DobbelLenke dl = new DobbelLenke();

    public static void main(String[] args) {
        //Lager noder
        lagNodes(41);

        //Fjerner noder i gitt intervall
        fjernNodes(3);
    }

    public static DobbelLenke lagNodes(int antall) {
        for(int i = 0; i < antall; i++) {
            dl.settInnBakerst(i);
        }
        System.out.println(dl.finnAntall());
        return dl;
    }

    public static void fjernNodes(int intervall) {
        Node n = dl.finnHode();
        while(n != null && n.neste != n) {
            for(int i = 0; i < intervall-1; i++) {
                n = n.neste;
            }
            System.out.println("Fjerner: " + dl.fjern(n.neste).finnElement());
            //listNodes();
        }
        if(dl.finnAntall() == 1) {
            System.out.println(n.finnElement());
        }
    }

    public static void listNodes() {
        for(int i = 0; i < dl.finnAntall(); i++) {
            System.out.print(dl.finnNr(i).finnElement() + " ");
        }
        System.out.println();
    }

}
