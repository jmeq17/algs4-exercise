package ch4.topic1;

import utils.DepthFirstPathsIteration;
import utils.Graph;
import utils.SymbolGraph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Write homework.a SymbolGraph client like DegreesOfSeparation that uses depth-first search instead of breadth-first search to find paths connecting two performers, producing output like that shown on the facing page.
 */

public class E40126 {
    public static void main(String[] args) {
        String filename = args[0];
        String delimiter = args[1];
        String source = args[2];

        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph G = sg.G();
        if (!sg.contains(source)) {
            StdOut.println(source + " not in database.");
            return;
        }

        int s = sg.index(source);
        DepthFirstPathsIteration bfs = new DepthFirstPathsIteration(G, s);

        while (!StdIn.isEmpty()) {
            String sink = StdIn.readLine();
            if (sg.contains(sink)) {
                int t = sg.index(sink);
                if (bfs.hasPathTo(t)) {
                    for (int v : bfs.pathTo(t)) {
                        StdOut.println("   " + sg.name(v));
                    }
                } else {
                    StdOut.println("Not connected");
                }
            } else {
                StdOut.println("   Not in database.");
            }
        }
    }
}
