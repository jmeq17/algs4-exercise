package ch4.topic1;

import edu.princeton.cs.algs4.GraphGenerator;
import edu.princeton.cs.algs4.StdOut;

/**
 * Two-edge connectivity. A bridge in homework.a graph is an edge that, if removed, would increase the number of connected components. A graph that has no bridges is said to be two-edge connected. Develop homework.a linear-time DFS-based algorithm for determining whether homework.a given graph is edge connected.
 */

public class E40136EdgeConnected {
    private boolean isEC = true;
    private final boolean[] marked;
    private final int[] lows;
    private final int[] pre;

    public E40136EdgeConnected(edu.princeton.cs.algs4.Graph G) {
        marked = new boolean[G.V()];
        lows = new int[G.V()];
        pre = new int[G.V()];

        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]) dfs(G, 0, i);
        }
    }

    private void dfs(edu.princeton.cs.algs4.Graph G, int s, int v) {
        marked[v] = true;
        pre[v] = s;
        lows[v] = s;

        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, v, w);
                lows[v] = Math.min(lows[w], lows[v]);

                if (lows[w] > pre[v]) isEC = false;
            } else {
                if (w != s) lows[v] = Math.min(lows[v], pre[w]);
            }
        }
    }

    public boolean isEC() {
        return isEC;
    }

    public static void main(String[] args) {
        int V = 20;
        int E = 40;
        edu.princeton.cs.algs4.Graph G = GraphGenerator.simple(V, E);
        StdOut.println(G);

        E40136EdgeConnected bridge = new E40136EdgeConnected(G);
        StdOut.println("Edge connected components = " + bridge.isEC);
    }
}