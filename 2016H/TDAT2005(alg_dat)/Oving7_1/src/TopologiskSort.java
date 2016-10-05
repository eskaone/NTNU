import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class TopologiskSort {
    public static void main(String[] args) {
        Path fil = Paths.get("graphs/L7g5.txt");
        List<String> kanterLest = null;
        try {
            kanterLest = Files.readAllLines(fil);
        } catch (Exception e) {
            System.out.println(e.toString() + "\n" + e.getMessage());
            return;
        }

        String førsteLinje = kanterLest.get(0).trim();
        int kantAnt = Integer.parseInt(førsteLinje.substring(førsteLinje.lastIndexOf(' ') + 1));
        int[][] kanter = new int[2][kantAnt];
        for (int i = 1; i < kanterLest.size(); i++) {
            String streng = kanterLest.get(i).trim();
            int node1 = Integer.parseInt(streng.substring(0, streng.indexOf(' ')));
            int node2 = Integer.parseInt(streng.substring(streng.lastIndexOf(' ') + 1));
            kanter[0][i - 1] = node1;
            kanter[1][i - 1] = node2;
        }

        int nodeAnt = Integer.parseInt(førsteLinje.substring(0, førsteLinje.indexOf(' ')));
        TopoGraf graf = new TopoGraf(nodeAnt, kanter);
        System.out.println(graf.toString());
    }
}
class TopoGraf {
    private TopoNode[] noder;
    private int[][] kanter;

    TopoGraf(int antNoder, int[][] kanter) {
        this.kanter = kanter;
        noder = new TopoNode[antNoder];
        for (int i = 0; i < antNoder; i++) {
            noder[i] = new TopoNode(i);
        }
    }

    private TopoNode Søk(TopoNode start, TopoNode først) {
        if (start.isFunnet()) {
            return først;
        }
        start.setFunnet(true);
        for (int i = 0; i < kanter[0].length; i++) {
            if (kanter[0][i] == start.getIndex() && !(noder[kanter[1][i]].isFunnet())) {
                først = Søk(noder[kanter[1][i]], først);
            }
        }
        start.setNeste(først);
        return start;
    }
    TopoNode TopoSort() {
        TopoNode node = null;
        for (TopoNode t : noder) {
            t.setFunnet(false);
            t.setNeste(null);
        }
        for (TopoNode t : noder) {
            node = Søk(t, node);
        }
        return node;
    }
    public String toString() {
        String ret = "";
        TopoNode node = TopoSort();
        while (node != null) {
            ret += node.getIndex() + "\n";
            node = node.getNeste();
        }
        return ret;
    }
}
class TopoNode {
    private boolean funnet;
    private TopoNode neste;
    private final int index;

    TopoNode(int index) {
        this.index = index;
        funnet = false;
        neste = null;
    }

    public int getIndex() {
        return index;
    }
    public boolean isFunnet() {
        return funnet;
    }
    public void setFunnet(boolean funnet) {
        this.funnet = funnet;
    }
    public TopoNode getNeste() {
        return neste;
    }
    public void setNeste(TopoNode neste) {
        this.neste = neste;
    }
}