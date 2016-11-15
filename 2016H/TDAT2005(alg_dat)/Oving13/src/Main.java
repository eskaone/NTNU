import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Kant {
    Kant neste;
    Node til;
    public Kant(Node n, Kant nst) {
        til = n;
        neste = nst;
    }
}

class VKant extends Kant {
    int vekt;
    public VKant(Node n, VKant nst, int vkt) {
        super(n, nst);
        vekt = vkt;
    }
}

class Node {
    double lat;
    double lng;
    int id;
    Kant kant1;
    Object d;
}

class Graf {
    static int N, K;
    static Node[] node;
    static int sjekket = 0;

    static void astar(Node s, Node m, boolean dijkstra) {
        PriorityQueue<Node> priko = new PriorityQueue<>(N, (o1, o2) -> ((Forgj)o1.d).prio - ((Forgj)o2.d).prio);
        s.d = new Forgj();
        ((Forgj)s.d).dist = 0;
        priko.add(s);

        while(!priko.isEmpty()) {
            Node curr = priko.poll();
            sjekket++;
            if (curr == m) {
                skrivPath(m, s); // Vi har nådd målnoden og må jobbe oss tilbake til start
            }
            for (VKant k = (VKant)curr.kant1; k != null; k = (VKant)k.neste) {
                Node n = k.til;
                if (curr.d != null && n.d != null && ((Forgj)curr.d).dist + k.vekt >= ((Forgj)n.d).dist) continue; // Not a better match
                n.d = new Forgj();
                ((Forgj)n.d).forgj = curr;
                ((Forgj)n.d).time = ((Forgj)curr.d).time + k.vekt;
                ((Forgj)n.d).dist = ((Forgj)curr.d).dist + k.vekt;
                ((Forgj)n.d).prio = ((Forgj)curr.d).dist + k.vekt + (!dijkstra ? (int)forventetAvstand(curr, m) : 0);
                priko.remove(n);
                priko.add(n);
            }

        }
    }

    public static double forventetAvstand(Node start, Node goal) {
        return (2 * 6371 * Math.asin(
                Math.sqrt(
                        Math.sin((Math.toRadians(start.lat)-Math.toRadians(goal.lat))/2) *
                                Math.sin((Math.toRadians(start.lat)-Math.toRadians(goal.lat))/2) +
                                Math.cos(Math.toRadians(start.lat)) *
                                        Math.cos(Math.toRadians(goal.lat)) *
                                        Math.sin((Math.toRadians(start.lng)-Math.toRadians(goal.lng))/2) *
                                        Math.sin((Math.toRadians(start.lng)-Math.toRadians(goal.lng))/2)
                )
        ));
    }

    private static void skrivPath(Node n, Node s) {
        Node m = n;
        while(m != s) {
            System.out.println(m.lat + "," + m.lng); // Skriv ut alle noder baklengs
            m = ((Forgj)m.d).forgj;
        }
        System.out.println(s.lat + "," + s.lng); // Skriv ut startnode
        System.out.println("Antall sjekkede noder: " + sjekket);
        System.out.println("Kjøretid: " + ((Forgj)n.d).time + "ms");
    }

}

class Forgj {
    int dist;
    int prio;
    int time;
    Node forgj;
    static int uendelig = Integer.MAX_VALUE;
    public Forgj() {
        dist = uendelig;
    }
}


public class Main {
    public void vgraf(String nodePath, String edgePath) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(nodePath))) {
            for(String line; (line = br.readLine()) != null; ) {
                // process the line.
                String[] data = line.trim().split("\\s+");
                if (data.length == 1) {
                    Graf.N = Integer.parseInt(data[0]);
                    Graf.node = new Node[Graf.N];
                } else {
                    int id = Integer.parseInt(data[0]);
                    Graf.node[id] = new Node();
                    Graf.node[id].id = id;
                    Graf.node[id].lat = Double.parseDouble(data[1]);
                    Graf.node[id].lng = Double.parseDouble(data[2]);
                }
            }
        }

        try(BufferedReader br = new BufferedReader(new FileReader(edgePath))) {
            for(String line; (line = br.readLine()) != null; ) {
                // process the line.
                String[] data = line.trim().split("\\s+");
                if (data.length == 1) {
                    Graf.K = Integer.parseInt(data[0]);
                } else {
                    int fra = Integer.parseInt(data[0]);
                    int til = Integer.parseInt(data[1]);
                    int vekt = Integer.parseInt(data[2]);
                    VKant k = new VKant(Graf.node[til], (VKant)Graf.node[fra].kant1, vekt);
                    Graf.node[fra].kant1 = k;
                }
            }
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        String fileRoot = new File("").getAbsolutePath(); // Project root folder
        String nodePath = fileRoot.concat("/data/noder.txt"); // Add project relative path to file
        String edgePath = fileRoot.concat("/data/kanter.txt"); // Add project relative path to file

        try {
            m.vgraf(nodePath, edgePath);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


        Graf.astar(Graf.node[2146837], Graf.node[2009903], false); // Oslo til Bergen

        //Graf.astar(Graf.node[2418547], Graf.node[2633550], false); // Gjemnes til Kårvåg

        //Graf.astar(Graf.node[2058549], Graf.node[1051859], false); // Kristiansund til Helsinki
        //Graf.astar(Graf.node[2058549], Graf.node[1051859], true); // Samme med djikstra


        //Graf.astar(Graf.node[3237536], Graf.node[1881040], false); // Test


    }
}