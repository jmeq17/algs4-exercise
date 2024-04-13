package exercise.ch4.topic1;

import utils.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Develop homework.a test client for Graph that reads homework.a graph from the input stream named
 * as command-line argument and then prints it, relying on toString().
 */

public class E40107 {
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph g = new Graph(in);

        StdOut.println(g);
    }
}
