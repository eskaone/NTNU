import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by asdfLaptop on 05.10.2016.
 */
public class Main  {
    static Node[] node;
    int N;
    int K;

    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    public void run() throws IOException {
        lesInn();
        djikstra(node[1]);
        for(int i = 0; i<node.length; i++){
            Integer[] t = getData(node[i]);
            System.out.println("Node: " + t[0]+ " Forgjenger: " + t[1] + " Distanse: " + t[2]);
        }
    }


    public void lesInn() throws IOException{
        File fil = new File("graphs/vg1.txt");

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
            int vekt = Integer.parseInt(st.nextToken());
            VKant k = new VKant(node[til], (VKant)node[fra].kant1, vekt);
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

    public void forkort(Node n, VKant k, PriorityQueue pq) {
        Forgj nd = (Forgj)n.d, md = (Forgj)k.til.d;
        if(md.dist > nd.dist + k.vekt) {
            md.dist = nd.dist + k.vekt;
            md.forgj = n;
            pq.remove(md.forgj);
            pq.add(md.forgj);
        }
    }

    public void initforgj(Node s){
        for(int i = N;i-- > 0;){
            node[i].d = new Forgj();
        }
        ((Forgj)s.d).dist = 0;
    }

    public PriorityQueue lag_priko(Node[] nodes) {
        PriorityQueue<Node> priko = new PriorityQueue<Node>(nodes.length, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return ((Forgj)o1.d).dist - ((Forgj)o2.d).dist;
            }
        });
        priko.addAll(new ArrayList<Node>(Arrays.asList(node)));
        return priko;
    }

    public Object hent_min(PriorityQueue pri) {
        return pri.poll();
    }

    public void djikstra(Node s) {
        initforgj(s);
        Node[] nodes = new Node[N];
        PriorityQueue pq = lag_priko(nodes);
        for(int i = N; i > 1; i--) {
            Node n = (Node)hent_min(pq);
            System.out.println(n);
            for(VKant k = (VKant)n.kant1; k != null; k = (VKant)k.neste) {
                forkort(n, k, pq);
            }
        }
    }
}
