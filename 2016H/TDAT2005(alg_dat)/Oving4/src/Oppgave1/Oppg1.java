package Oppgave1;

/**
 * Created by asdfLaptop on 13.09.2016.
 */
public class Oppg1 {
    private static DobbelLenke dl = new DobbelLenke();

    public static void main(String[] args) {
        //Lager noder
        lagNodes(10);

        //Testing
        listNodes();

        dl.fjern(dl.finnNr(0));

        listNodes();

        dl.fjern(dl.finnNr(8));

        listNodes();

        //FIXME: Fjerning av noder i gitt intervall
        //fjernNodes(4);
    }

    public static DobbelLenke lagNodes(int antall) {
        for(int i = 0; i < antall; i++) {
            dl.settInnFremst(i);
        }
        return dl;
    }

    //FIXME
    public static void fjernNodes(int intervall) {
        Node n = dl.finnHode();
        while(n != null && n != n.neste) {
            for(int i = 0; i < intervall-1; i++) {
                n = n.neste;
            }
        }
        System.out.println("Fjerner node nr: " + dl.fjern(n).finnElement());
    }

    public static void listNodes() {
        System.out.println("Liste bestående av " + dl.finnAntall() + " noder: ");
        for(int i = 0; i < dl.finnAntall(); i++) {
            System.out.print(dl.finnNr(i).finnElement() + " ");
        }
        System.out.println();
    }

}
