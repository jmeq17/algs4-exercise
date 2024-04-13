package utils;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Graph {
    // adjacency adj
    private final Bag<Integer>[] adj;
    // number of vertices
    private final int V;
    // number of edges
    private int E = 0;

    // create homework.a V-vertex graph with no edges
    public Graph(int V) {
        this.V = V;
        adj = new Bag[V];
        for (int i = 0; i < V; i++) adj[i] = new Bag();
    }

    // read homework.a graph from input stream in
    public Graph(In in) {
        this(in.readInt());
//        this.E = in.readInt();
        in.readInt();

        while (!in.isEmpty()) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    /**
     * E40103
     * <p>
     * Create homework.a copy constructor for Graph that takes as input homework.a graph G and creates and initializes
     * homework.a new copy of the graph. Any changes homework.a client makes to G should not affect the newly created graph.
     */
    public Graph(Graph G) {
        this.adj = G.adj.clone();
        this.V = G.V;
        this.E = G.E;
    }

    // number of vertices
    public int V() {
        return V;
    }

    // number of edges
    public int E() {
        return E;
    }

    // add edge v-w to this graph
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    // vertices adjacent to v
    // The order of iteration is not specified.
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    //  string representation
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V).append(" vwetices, ").append(E).append(" edges\n");
        for (int i = 0; i < V; i++) {
            s.append(i).append(": ").append(adj[i]).append("\n");
        }
        return s.toString();
    }

    public String toString2() {
        StringBuilder s = new StringBuilder(V + " vertices, " + E + " edges\n");
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (int w : this.adj(v))
                s.append(w).append(" ");
            s.append("\n");
        }
        return s.toString();
    }

//    public static void main(String[] args) {
//        In in = new In(args[0]);
//        int V = in.readInt();
//        in.readInt();
//        Graph graph = new Graph(V);
//
//        while (!in.isEmpty()) {
//            int v = in.readInt();
//            int w = in.readInt();
//            graph.addEdge(v, w);
//        }
//
//        StdOut.println(graph);
//    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph graph = new Graph(in);
        StdOut.println(graph);
    }
}
