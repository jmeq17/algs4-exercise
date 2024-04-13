package homework.partII.week1;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class SAPFirstAdvanced {
    private final Digraph g;
    private final int V;

    // constructor takes homework.a digraph (not necessarily homework.a DAG)
    public SAPFirstAdvanced(Digraph G) {
        if (G == null) throw new IllegalArgumentException();

        V = G.V();
        g = new Digraph(V);

        for (int v = 0; v < V; v++)
            for (int w : G.adj(v)) g.addEdge(v, w);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v < 0 || v >= V || w < 0 || w >= V) throw new IllegalArgumentException();

        int distance = V;

        int[] distanceToA = new int[V];
        boolean[] marked = new boolean[V];

        Queue<Integer> queue = new Queue<>();

        distanceToA[v] = 0;
        marked[v] = true;
        queue.enqueue(v);

        while (!queue.isEmpty()) {
            int a = queue.dequeue();

            for (int b : g.adj(a)) {
                if (!marked[b]) {
                    distanceToA[b] = distanceToA[a] + 1;
                    marked[b] = true;
                    queue.enqueue(b);
                }
            }
        }

        if (marked[w]) distance = distanceToA[w];
        else marked[w] = true;

        int[] distanceToB = new int[V];

        distanceToB[w] = 0;

        queue.enqueue(w);

        while (!queue.isEmpty()) {
            int a = queue.dequeue();

            for (int b : g.adj(a)) {
                distanceToB[b] = distanceToB[a] + 1;
                if (!marked[b]) {
                    marked[b] = true;
                    queue.enqueue(b);
                } else {
                    int tem = distanceToA[b] + distanceToB[b];
                    if (tem > distance) queue.enqueue(b);
                    else distance = tem;
                }
            }
        }

        return distance != V ? distance : -1;
    }

    // homework.a common ancestor of v and w that participates in homework.a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if (v < 0 || v >= V || w < 0 || w >= V) throw new IllegalArgumentException();

        int distance = V;
        int ancestor = -1;

        int[] distanceToA = new int[V];
        boolean[] marked = new boolean[V];

        Queue<Integer> queue = new Queue<>();

        distanceToA[v] = 0;
        marked[v] = true;
        queue.enqueue(v);

        while (!queue.isEmpty()) {
            int a = queue.dequeue();

            for (int b : g.adj(a)) {
                if (!marked[b]) {
                    distanceToA[b] = distanceToA[a] + 1;
                    marked[b] = true;
                    queue.enqueue(b);
                }
            }
        }

        if (marked[w]) {
            distance = distanceToA[w];
            ancestor = w;
        } else marked[w] = true;

        int[] distanceToB = new int[V];

        distanceToB[w] = 0;

        queue.enqueue(w);

        while (!queue.isEmpty()) {
            int a = queue.dequeue();

            for (int b : g.adj(a)) {
                distanceToB[b] = distanceToB[a] + 1;
                if (!marked[b]) {
                    marked[b] = true;
                    queue.enqueue(b);
                } else {
                    int tem = distanceToA[b] + distanceToB[b];
                    if (tem > distance) queue.enqueue(b);
                    else {
                        distance = tem;
                        ancestor = b;
                    }
                }
            }
        }

        return ancestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) throw new IllegalArgumentException();
        for (Integer t : v) if (t == null || t < 0 || t >= V) throw new IllegalArgumentException();
        for (Integer t : w) if (t == null || t < 0 || t >= V) throw new IllegalArgumentException();

        int distance = V;

        int[] distanceToA = new int[V];
        boolean[] marked = new boolean[V];

        Queue<Integer> queue = new Queue<>();

        for (int a : v) {
            distanceToA[a] = 0;
            marked[a] = true;
            queue.enqueue(a);
        }

        while (!queue.isEmpty()) {
            int a = queue.dequeue();

            for (int b : g.adj(a)) {
                if (!marked[b]) {
                    distanceToA[b] = distanceToA[a] + 1;
                    marked[b] = true;
                    queue.enqueue(b);
                }
            }
        }

        int[] distanceToB = new int[V];
        for (int a : w) {
            distanceToB[a] = 0;
            queue.enqueue(a);

            if (marked[a]) {
                if (distanceToA[a] < distance) distance = distanceToA[a];
            } else marked[a] = true;
        }

        while (!queue.isEmpty()) {
            int a = queue.dequeue();

            for (int b : g.adj(a)) {
                distanceToB[b] = distanceToB[a] + 1;
                if (!marked[b]) {
                    marked[b] = true;
                    queue.enqueue(b);
                } else {
                    int tem = distanceToA[b] + distanceToB[b];
                    if (tem > distance) queue.enqueue(b);
                    else distance = tem;
                }
            }
        }

        return distance != V ? distance : -1;
    }

    // homework.a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) throw new IllegalArgumentException();
        for (Integer t : v) if (t == null || t < 0 || t >= V) throw new IllegalArgumentException();
        for (Integer t : w) if (t == null || t < 0 || t >= V) throw new IllegalArgumentException();

        int distance = V;
        int ancestor = -1;

        int[] distanceToA = new int[V];
        boolean[] marked = new boolean[V];

        Queue<Integer> queue = new Queue<>();

        for (int a : v) {
            distanceToA[a] = 0;
            marked[a] = true;
            queue.enqueue(a);
        }

        while (!queue.isEmpty()) {
            int a = queue.dequeue();

            for (int b : g.adj(a)) {
                if (!marked[b]) {
                    distanceToA[b] = distanceToA[a] + 1;
                    marked[b] = true;
                    queue.enqueue(b);
                }
            }
        }

        int[] distanceToB = new int[V];
        for (int a : w) {
            distanceToB[a] = 0;

            queue.enqueue(a);

            if (marked[a]) {
                if (distanceToA[a] < distance) {
                    distance = distanceToA[a];
                    ancestor = a;
                }
            } else marked[a] = true;
        }

        while (!queue.isEmpty()) {
            int a = queue.dequeue();

            for (int b : g.adj(a)) {
                distanceToB[b] = distanceToB[a] + 1;
                if (!marked[b]) {
                    marked[b] = true;
                    queue.enqueue(b);
                } else {
                    int tem = distanceToA[b] + distanceToB[b];
                    if (tem > distance) queue.enqueue(b);
                    else {
                        distance = distanceToA[b] + distanceToB[b];
                        ancestor = b;
                    }
                }
            }
        }

        return ancestor;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        String filename = "HW_Project\\wordnet\\digraph3.txt";
        In in = new In(filename);
        Digraph G = new Digraph(in);
        SAPFirstAdvanced sap = new SAPFirstAdvanced(G);
        sap.length(4, 9);
        sap.length(4, 9);
        StdOut.println(sap.length(4, 6));
        StdOut.println(sap.ancestor(4, 9));


//        while (!StdIn.isEmpty()) {
//            int v = StdIn.readInt();
//            int w = StdIn.readInt();
//            int length = sap.length(v, w);
//            int ancestor = sap.ancestor(v, w);
//            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
//        }
    }
}
