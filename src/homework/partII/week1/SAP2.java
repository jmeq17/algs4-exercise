package homework.partII.week1;

import edu.princeton.cs.algs4.*;

public class SAP2 {
    private final Digraph g;
    private final int V;

    // constructor takes homework.a digraph (not necessarily homework.a DAG)
    public SAP2(Digraph G) {
        if (G == null) throw new IllegalArgumentException();

        g = G;
        V = g.V();
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v < 0 || v > V || w < 0 || w > V) throw new IllegalArgumentException();

        int distance = V;

        int[] distanceToA = new int[V];
        boolean[] markedA = new boolean[V];

        Queue<Integer> queue = new Queue<>();

        distanceToA[v] = 0;
        markedA[v] = true;
        queue.enqueue(v);

        while (!queue.isEmpty()) {
            int a = queue.dequeue();

            for (int b : g.adj(a)) {
                if (!markedA[b]) {
                    distanceToA[b] = distanceToA[a] + 1;
                    markedA[b] = true;
                    queue.enqueue(b);
                }
            }
        }

        int[] distanceToB = new int[V];
        boolean[] markedB = new boolean[V];

        distanceToB[w] = 0;
        markedB[w] = true;

        queue.enqueue(w);

        if (markedA[w]) distance = distanceToA[w];

        while (!queue.isEmpty()) {
            int a = queue.dequeue();

            for (int b : g.adj(a)) {
                if (!markedB[b]) {
                    distanceToB[b] = distanceToB[a] + 1;
                    markedB[b] = true;
                    queue.enqueue(b);
                }
                if (markedA[b])
                    if (distanceToA[b] + distanceToB[b] < distance)
                        distance = distanceToA[b] + distanceToB[b];
            }
        }

        return distance != V ? distance : -1;
    }

    // homework.a common ancestor of v and w that participates in homework.a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if (v < 0 || v > V || w < 0 || w > V) throw new IllegalArgumentException();

        int distance = V;
        int ancestor = -1;

        int[] distanceToA = new int[V];
        boolean[] markedA = new boolean[V];

        Queue<Integer> queue = new Queue<>();

        distanceToA[v] = 0;
        markedA[v] = true;
        queue.enqueue(v);

        while (!queue.isEmpty()) {
            int a = queue.dequeue();

            for (int b : g.adj(a)) {
                if (!markedA[b]) {
                    distanceToA[b] = distanceToA[a] + 1;
                    markedA[b] = true;
                    queue.enqueue(b);
                }
            }
        }

        int[] distanceToB = new int[V];
        boolean[] markedB = new boolean[V];

        distanceToB[w] = 0;
        markedB[w] = true;

        queue.enqueue(w);

        if (markedA[w]) {
            distance = distanceToA[w];
            ancestor = w;
        }

        while (!queue.isEmpty()) {
            int a = queue.dequeue();

            for (int b : g.adj(a)) {
                if (!markedB[b]) {
                    distanceToB[b] = distanceToB[a] + 1;
                    markedB[b] = true;
                    queue.enqueue(b);
                }
                if (markedA[b])
                    if (distanceToA[b] + distanceToB[b] < distance) {
                        distance = distanceToA[b] + distanceToB[b];
                        ancestor = b;
                    }
            }
        }

        return ancestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> a, Iterable<Integer> b) {
        if (a == null || b == null) throw new IllegalStateException();

        int distance = V;

        int[] distanceToA = new int[V];
        boolean[] markedA = new boolean[V];

        Queue<Integer> queue = new Queue<>();

        for (int v : a) {
            distanceToA[v] = 0;
            markedA[v] = true;
            queue.enqueue(v);
        }

        while (!queue.isEmpty()) {
            int v = queue.dequeue();

            for (int w : g.adj(v)) {
                if (!markedA[w]) {
                    distanceToA[w] = distanceToA[v] + 1;
                    markedA[w] = true;
                    queue.enqueue(w);
                }
            }
        }

        int[] distanceToB = new int[V];
        boolean[] markedB = new boolean[V];

        for (int v : b) {
            distanceToB[v] = 0;
            markedB[v] = true;
            queue.enqueue(v);

            if (markedA[v] && distanceToA[v] < distance) distance = distanceToA[v];
        }


        while (!queue.isEmpty()) {
            int v = queue.dequeue();

            for (int w : g.adj(v)) {
                if (!markedB[w]) {
                    distanceToB[w] = distanceToB[v] + 1;
                    markedB[w] = true;
                    queue.enqueue(w);
                }
                if (markedA[w])
                    if (distanceToA[w] + distanceToB[w] < distance)
                        distance = distanceToA[w] + distanceToB[w];
            }
        }

        return distance != V ? distance : -1;
    }

    // homework.a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) throw new IllegalStateException();

        int distance = V;
        int ancestor = -1;

        int[] distanceToA = new int[V];
        boolean[] markedA = new boolean[V];

        Queue<Integer> queue = new Queue<>();

        for (int a : v) {
            distanceToA[a] = 0;
            markedA[a] = true;
            queue.enqueue(a);
        }

        while (!queue.isEmpty()) {
            int a = queue.dequeue();

            for (int b : g.adj(a)) {
                if (!markedA[b]) {
                    distanceToA[b] = distanceToA[a] + 1;
                    markedA[b] = true;
                    queue.enqueue(b);
                }
            }
        }

        int[] distanceToB = new int[V];
        boolean[] markedB = new boolean[V];

        for (int a : w) {
            distanceToB[a] = 0;
            markedB[a] = true;
            queue.enqueue(a);

            if (markedA[a] && distanceToA[a] < distance) {
                distance = distanceToA[a];
                ancestor = a;
            }
        }

        while (!queue.isEmpty()) {
            int a = queue.dequeue();

            for (int b : g.adj(a)) {
                if (!markedB[b]) {
                    distanceToB[b] = distanceToB[a] + 1;
                    markedB[b] = true;
                    queue.enqueue(b);
                }
                if (markedA[b])
                    if (distanceToA[b] + distanceToB[b] < distance) {
                        distance = distanceToA[b] + distanceToB[b];
                        ancestor = b;
                    }
            }
        }

        return ancestor;
    }

    // do unit testing of this class
//    public static void main(String[] args) {
//        In in = new In(args[0]);
//        Digraph G = new Digraph(in);
//        HW.PartII.Week1.SAP sap = new HW.PartII.Week1.SAP(G);
//        while (!StdIn.isEmpty()) {
//            int v = StdIn.readInt();
//            int w = StdIn.readInt();
//            int length = sap.length(v, w);
//            int ancestor = sap.ancestor(v, w);
//            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
//        }
//    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(82192);

        while (!in.isEmpty()) {
            String[] s = in.readLine().split(",");
            int v = Integer.parseInt(s[0]);
            for (int i = 1; i < s.length; i++)
                G.addEdge(v, Integer.parseInt(s[i]));
        }

        SAP sap = new SAP(G);

        Bag<Integer> v = new Bag<>();
        Bag<Integer> w = new Bag<>();

        v.add(68015);
        v.add(49952);
        v.add(46600);
        v.add(46599);
        v.add(28075);
        w.add(82086);
        sap.length(v, w);
        StdOut.println(sap.length(v, w));
    }

}
