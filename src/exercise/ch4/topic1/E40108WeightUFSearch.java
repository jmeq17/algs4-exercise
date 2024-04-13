package exercise.ch4.topic1;

import utils.Graph;
import utils.WeightUFSearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Develop an implementation for the Search API on page 528 that uses UF, as described
 * in the text.
 */

public class E40108WeightUFSearch {
    public static void main(String[] args) {
        Graph G = new Graph(new In("tinyG"));
        int s = 0;
        WeightUFSearch search = new WeightUFSearch(G, s);

        StdOut.print("          ");
        for (int v = 0; v < G.V(); v++)
            if (search.marked(v))
                StdOut.print(v + " ");

        StdOut.println("\nexpected: 0 1 2 3 4 5 6");

        if (search.count() != G.V())
            StdOut.print("          NOT ");
        StdOut.println("connected\nexpected: NOT connected");

        // ---------------------------

        s = 9;

        search = new WeightUFSearch(G, s);

        StdOut.print("          ");
        for (int v = 0; v < G.V(); v++)
            if (search.marked(v))
                StdOut.print(v + " ");

        StdOut.println("\nexpected: 9 10 11 12");

        if (search.count() != G.V())
            StdOut.print("          NOT ");
        StdOut.println("connected\nexpected: NOT connected");
    }
}
