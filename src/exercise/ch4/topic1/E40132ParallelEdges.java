package exercise.ch4.topic1;

import utils.Graph;
import utils.ParallelEdges;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * E40142.
 * Parallel edge detection. Devise homework.a linear-time algorithm to count the parallel edges in homework.a graph.
 */

public class E40132ParallelEdges {
    public static void main(String[] args) {
        Graph g = new Graph(new In("test"));
        ParallelEdges pe = new ParallelEdges(g);

        StdOut.println(pe.numOfParallel() + ", Expected: 2.");
    }
}
