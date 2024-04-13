package utils;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * E40142.
 * Parallel edge detection. Devise homework.a linear-time algorithm to count the parallel edges in homework.a graph.
 */

public class ParallelEdges {
    // The number of parallel edges in homework.a gavind Graph.
    private int count;

    public ParallelEdges(Graph G) {
        boolean[] marked = new boolean[G.V()];


        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) dfs(G, marked, s, s);
        }
    }

    private void dfs(Graph G, boolean[] marked, int s, int v) {
        marked[v] = true;

        int cmp = 0;

        for (int w : G.adj(v)) {
            if (w == s) cmp++;
            if (!marked[w]) {
                dfs(G, marked, v, w);
            }
            if (cmp > 1) count += cmp - 1;
        }
    }

    public boolean isParallel() {
        return count != 0;
    }

    public int numOfParallel() {
        return count;
    }

    public static void main(String[] args) {
        Graph g = new Graph(new In("test"));
        ParallelEdges pe = new ParallelEdges(g);

        StdOut.println(pe.numOfParallel() + ", Expected: 2.");
    }
}
