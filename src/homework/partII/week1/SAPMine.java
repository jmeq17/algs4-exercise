package homework.partII.week1;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class SAPMine {
    private final Digraph g;
    private final int V;

    // constructor takes homework.a digraph (not necessarily homework.a DAG)
    public SAPMine(Digraph G) {
        if (G == null) throw new IllegalArgumentException();

        V = G.V();
        g = new Digraph(V);

        for (int v = 0; v < V; v++)
            for (int w : G.adj(v)) g.addEdge(v, w);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
//    public int length(int v, int w) {
//        if (v < 0 || v >= V || w < 0 || w >= V) throw new IllegalArgumentException();
//
//        int distance = V;
//
//        int[] distanceToA = new int[V];
//        boolean[] markedA = new boolean[V];
//
//        Queue<Integer> queue = new Queue<>();
//
//        distanceToA[v] = 0;
//        markedA[v] = true;
//        queue.enqueue(v);
//
//        while (!queue.isEmpty()) {
//            int homework.a = queue.dequeue();
//
//            for (int b : g.adj(homework.a)) {
//                if (!markedA[b]) {
//                    distanceToA[b] = distanceToA[homework.a] + 1;
//                    markedA[b] = true;
//                    queue.enqueue(b);
//                }
//            }
//        }
//
//        int[] distanceToB = new int[V];
//        boolean[] markedB = new boolean[V];
//
//        distanceToB[w] = 0;
//        markedB[w] = true;
//
//        queue.enqueue(w);
//
//        if (markedA[w]) distance = distanceToA[w];
//
//        while (!queue.isEmpty()) {
//            int homework.a = queue.dequeue();
//
//            for (int b : g.adj(homework.a)) {
//                if (!markedB[b]) {
//                    distanceToB[b] = distanceToB[homework.a] + 1;
//                    markedB[b] = true;
//                    queue.enqueue(b);
//                }
//                if (markedA[b] && distanceToA[b] + distanceToB[b] < distance)
//                    distance = distanceToA[b] + distanceToB[b];
//            }
//        }
//
//        return distance != V ? distance : -1;
//    }

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
//    public int ancestor(int v, int w) {
//        if (v < 0 || v >= V || w < 0 || w >= V) throw new IllegalArgumentException();
//
//        int distance = V;
//        int ancestor = -1;
//
//        int[] distanceToA = new int[V];
//        boolean[] markedA = new boolean[V];
//
//        Queue<Integer> queue = new Queue<>();
//
//        distanceToA[v] = 0;
//        markedA[v] = true;
//        queue.enqueue(v);
//
//        while (!queue.isEmpty()) {
//            int homework.a = queue.dequeue();
//
//            for (int b : g.adj(homework.a)) {
//                if (!markedA[b]) {
//                    distanceToA[b] = distanceToA[homework.a] + 1;
//                    markedA[b] = true;
//                    queue.enqueue(b);
//                }
//            }
//        }
//
//        int[] distanceToB = new int[V];
//        boolean[] markedB = new boolean[V];
//
//        distanceToB[w] = 0;
//        markedB[w] = true;
//
//        queue.enqueue(w);
//
//        if (markedA[w]) {
//            distance = distanceToA[w];
//            ancestor = w;
//        }
//
//        while (!queue.isEmpty()) {
//            int homework.a = queue.dequeue();
//
//            for (int b : g.adj(homework.a)) {
//                if (!markedB[b]) {
//                    distanceToB[b] = distanceToB[homework.a] + 1;
//                    markedB[b] = true;
//                    queue.enqueue(b);
//                }
//                if (markedA[b] && distanceToA[b] + distanceToB[b] < distance) {
//                    distance = distanceToA[b] + distanceToB[b];
//                    ancestor = b;
//                }
//            }
//        }
//
//        return ancestor;
//    }

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
//    public int length(Iterable<Integer> v, Iterable<Integer> w) {
//        if (v == null || w == null) throw new IllegalArgumentException();
//        for (Integer t : v) if (t == null || t < 0 || t >= V) throw new IllegalArgumentException();
//        for (Integer t : w) if (t == null || t < 0 || t >= V) throw new IllegalArgumentException();
//
//        int distance = V;
//
//        int[] distanceToA = new int[V];
//        boolean[] markedA = new boolean[V];
//
//        Queue<Integer> queue = new Queue<>();
//
//        for (int homework.a : v) {
//            distanceToA[homework.a] = 0;
//            markedA[homework.a] = true;
//            queue.enqueue(homework.a);
//        }
//
//        while (!queue.isEmpty()) {
//            int homework.a = queue.dequeue();
//
//            for (int b : g.adj(homework.a)) {
//                if (!markedA[b]) {
//                    distanceToA[b] = distanceToA[homework.a] + 1;
//                    markedA[b] = true;
//                    queue.enqueue(b);
//                }
//            }
//        }
//
//        int[] distanceToB = new int[V];
//        boolean[] markedB = new boolean[V];
//
//        for (int homework.a : w) {
//            distanceToB[homework.a] = 0;
//            markedB[homework.a] = true;
//            queue.enqueue(homework.a);
//
//            if (markedA[homework.a] && distanceToA[homework.a] < distance) distance = distanceToA[homework.a];
//        }
//
//
//        while (!queue.isEmpty()) {
//            int homework.a = queue.dequeue();
//
//            for (int b : g.adj(homework.a)) {
//                if (!markedB[b]) {
//                    distanceToB[b] = distanceToB[homework.a] + 1;
//                    markedB[b] = true;
//                    queue.enqueue(b);
//                }
//                if (markedA[b] && distanceToA[b] + distanceToB[b] < distance)
//                    distance = distanceToA[b] + distanceToB[b];
//            }
//        }
//
//        return distance != V ? distance : -1;
//    }

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
//    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
//        if (v == null || w == null) throw new IllegalArgumentException();
//        for (Integer t : v) if (t == null || t < 0 || t >= V) throw new IllegalArgumentException();
//        for (Integer t : w) if (t == null || t < 0 || t >= V) throw new IllegalArgumentException();
//
//        int distance = V;
//        int ancestor = -1;
//
//        int[] distanceToA = new int[V];
//        boolean[] markedA = new boolean[V];
//
//        Queue<Integer> queue = new Queue<>();
//
//        for (int homework.a : v) {
//            distanceToA[homework.a] = 0;
//            markedA[homework.a] = true;
//            queue.enqueue(homework.a);
//        }
//
//        while (!queue.isEmpty()) {
//            int homework.a = queue.dequeue();
//
//            for (int b : g.adj(homework.a)) {
//                if (!markedA[b]) {
//                    distanceToA[b] = distanceToA[homework.a] + 1;
//                    markedA[b] = true;
//                    queue.enqueue(b);
//                }
//            }
//        }
//
//        int[] distanceToB = new int[V];
//        boolean[] markedB = new boolean[V];
//
//        for (int homework.a : w) {
//            distanceToB[homework.a] = 0;
//            markedB[homework.a] = true;
//            queue.enqueue(homework.a);
//
//            if (markedA[homework.a] && distanceToA[homework.a] < distance) {
//                distance = distanceToA[homework.a];
//                ancestor = homework.a;
//            }
//        }
//
//        while (!queue.isEmpty()) {
//            int homework.a = queue.dequeue();
//
//            for (int b : g.adj(homework.a)) {
//                if (!markedB[b]) {
//                    distanceToB[b] = distanceToB[homework.a] + 1;
//                    markedB[b] = true;
//                    queue.enqueue(b);
//                }
//                if (markedA[b] && distanceToA[b] + distanceToB[b] < distance) {
//                    distance = distanceToA[b] + distanceToB[b];
//                    ancestor = b;
//                }
//            }
//        }
//
//        return ancestor;
//    }

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
        In in = new In("HW_Project\\wordnet\\hypernyms3InvalidCycle.txt");
        Digraph G = new Digraph(82192);
        while (!in.isEmpty()) {
            String[] s = in.readLine().split(",");
            int v = Integer.parseInt(s[0]);
            for (int w = 1; w < s.length; w++) G.addEdge(v, Integer.parseInt(s[w]));
        }
        SAP sap = new SAP(G);

        StdOut.println(sap.length(9582, 21238));
        sap.length(9582, 21238);


//        while (!StdIn.isEmpty()) {
//            int v = StdIn.readInt();
//            int w = StdIn.readInt();
//            int length = sap.length(v, w);
//            int ancestor = sap.ancestor(v, w);
//            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
//        }
    }
}
