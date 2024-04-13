package homework.partII.week1;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;

import java.util.HashMap;

public class SAP {
    private final HashMap<Integer, int[]> adj;
    private final int V;

    // constructor takes homework.a digraph (not necessarily homework.a DAG)
    public SAP(Digraph G) {
        if (G == null) throw new IllegalArgumentException();

        V = G.V();

        adj = new HashMap<>();

        for (int v = 0; v < V; v++) {
            int n = G.outdegree(v);
            int[] tem = new int[n];
            int i = 0;
            for (int w : G.adj(v)) {
                tem[i] = w;
                i++;
            }
            adj.put(v, tem);
        }
    }

    private int[] lenAndAnc(Queue<Integer> queueV, Queue<Integer> queueW) {

        int[] ret = {V, -1};

        int distance = V;
        int ancestor = -1;

        int[] distanceToV = new int[V];
        int[] distanceToW = new int[V];
        boolean[] markedV = new boolean[V];
        boolean[] markedW = new boolean[V];

        for (int v : queueV) markedV[v] = true;
        for (int w : queueW) {
            if (markedV[w]) return new int[]{0, w};
            markedW[w] = true;
        }

        boolean isV = true;
        int a;

        while (!queueV.isEmpty() || !queueW.isEmpty()) {
            if (isV && !queueV.isEmpty() || queueW.isEmpty()) {
                a = queueV.dequeue();

                for (int b : adj.get(a)) {
                    if (!markedV[b]) {
                        markedV[b] = true;
                        distanceToV[b] = distanceToV[a] + 1;
                        if (markedW[b]) {
                            int tem = distanceToV[b] + distanceToW[b];
                            if (tem < distance) {
                                distance = tem;
                                ancestor = b;
                            }
                        }
                        if (distanceToV[b] < distance) queueV.enqueue(b);
                    }
                }
            } else {
                a = queueW.dequeue();

                for (int b : adj.get(a)) {
                    if (!markedW[b]) {
                        markedW[b] = true;
                        distanceToW[b] = distanceToW[a] + 1;
                        if (markedV[b]) {
                            int tem = distanceToV[b] + distanceToW[b];
                            if (tem < distance) {
                                distance = tem;
                                ancestor = b;
                            }
                        }
                        if (distanceToW[b] < distance) queueW.enqueue(b);
                    }
                }
            }
            isV = !isV;
        }
        if (distance != V) ret[0] = distance;
        ret[1] = ancestor;
        return ret;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v < 0 || v >= V || w < 0 || w >= V) throw new IllegalArgumentException();

        Queue<Integer> queueV = new Queue<>();
        Queue<Integer> queueW = new Queue<>();

        queueV.enqueue(v);
        queueW.enqueue(w);

//        return length(queueV, queueW);

        return lenAndAnc(queueV, queueW)[0];
    }

    // homework.a common ancestor of v and w that participates in homework.a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if (v < 0 || v >= V || w < 0 || w >= V) throw new IllegalArgumentException();

        Queue<Integer> queueV = new Queue<>();
        Queue<Integer> queueW = new Queue<>();

        queueV.enqueue(v);
        queueW.enqueue(w);

//        return ancestor(queueV, queueW);

        return lenAndAnc(queueV, queueW)[1];
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) throw new IllegalArgumentException();
        for (Integer t : v) if (t == null || t < 0 || t >= V) throw new IllegalArgumentException();
        for (Integer t : w) if (t == null || t < 0 || t >= V) throw new IllegalArgumentException();

        Queue<Integer> queueV = new Queue<>();
        Queue<Integer> queueW = new Queue<>();
        for (int i : v) queueV.enqueue(i);
        for (int i : w) queueW.enqueue(i);

//        return length(queueV, queueW);

        return lenAndAnc(queueV, queueW)[0];
    }

    // homework.a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) throw new IllegalArgumentException();
        for (Integer t : v) if (t == null || t < 0 || t >= V) throw new IllegalArgumentException();
        for (Integer t : w) if (t == null || t < 0 || t >= V) throw new IllegalArgumentException();

        Queue<Integer> queueV = new Queue<>();
        Queue<Integer> queueW = new Queue<>();
        for (int i : v) queueV.enqueue(i);
        for (int i : w) queueW.enqueue(i);

//        return ancestor(queueV, queueW);

        return lenAndAnc(queueV, queueW)[1];
    }
}
