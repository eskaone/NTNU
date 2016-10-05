import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by asdfLaptop on 05.10.2016.
 */
public class Main  {
    static Node[] node;
    int N;
    int K;

    public static void main(String[] args) throws IOException {
        new Main().runBfs();
        //new Main().runTopo();
    }

    public void runBfs() throws IOException {
        lesInn();
        bfs(node[5]);
        for(int i = 0; i<node.length; i++){
            Integer[] t = getData(node[i]);
            System.out.println("Node: " + t[0]+ " Forgjenger: " + t[1] + " Distanse: " + t[2]);
        }
    }

    public void runTopo() throws IOException {
        lesInn();
        for(int i = 0; i<node.length; i++){
            Integer[] t = getData(node[i]);
            System.out.println("Node: " + t[0]+ " Forgjenger: " + t[1] + " Distanse: " + t[2]);
        }
    }

    public void lesInn() throws IOException{
        File fil = new File("graphs/L7g1.txt");
        //File fil = new File("graphs/L7g2.txt");
        //File fil = new File("graphs/L7g3.txt)";
        //File fil = new File("graphs/L7g4.txt)";
        //File fil = new File("graphs/L7g5.txt");

        BufferedReader br = new BufferedReader(new FileReader(fil));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        node = new Node[N];
        for(int i = 0; i< N; i++){
            node[i] = new Node();
        }
        K = Integer.parseInt(st.nextToken());
        for(int i = 0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int fra = Integer.parseInt(st.nextToken());
            int til = Integer.parseInt(st.nextToken());
            Kant k = new Kant(node[til], node[fra].kant1);
            node[fra].kant1 = k;
        }
    }

    public static Integer[] getData(Node n){
        Integer[] t = new Integer[3];
        Forgj f = (Forgj)n.d;
        boolean funnet1 = false;
        boolean funnet2 = false;
        int i = 0;
        while(!funnet1 || !funnet2 && i<node.length){
            if(node[i].equals(n)){
                t[0] = i;
                funnet1 = true;
            }
            if(node[i].equals(f.forgj)){
                t[1] = i;
                funnet2 = true;
            }
            i++;
        }
        t[2] = f.dist;
        return t;
    }

    public void initforgj(Node s){
        for(int i = N;i--> 0;){
            node[i].d = new Forgj();
        }
        ((Forgj)s.d).dist = 0;
    }

    public void bfs(Node s) {
        initforgj(s);
        Kø kø = new Kø(N-1);
        kø.leggIKø(s);
        while(!kø.tom()) {
            Node n = (Node)kø.nesteIKø();
            for(Kant k = n.kant1; k != null; k = k.neste) {
                Forgj f = (Forgj)k.til.d;
                if(f.dist == f.uendelig) {
                    f.dist = ((Forgj)n.d).dist + 1;
                    f.forgj = n;
                    kø.leggIKø(k.til);
                }
            }
        }
    }

    public Node df_topo(Node n, Node l) {
        Topo_lst nd = (Topo_lst)n.d;
        if(nd.funnet) return l;
        nd.funnet = true;
        for(Kant k = n.kant1; k != null; k = k.neste) {
            l = df_topo(k.til, l);
        }
        nd.neste = l;
        return n;
    }

    public Node topologiSort() {
        Node l = null;
        for(int i = N; i-- > 0;) {
            node[i].d = new Topo_lst();
        }
        for(int i = N; i-- >0;) {
            l = df_topo(node[i], l);
        }
        return l;
    }

}
