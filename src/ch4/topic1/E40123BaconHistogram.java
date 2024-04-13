package ch4.topic1;

import utils.BreadthFirstPaths;
import utils.Graph;
import utils.SymbolGraph;
import edu.princeton.cs.algs4.StdDraw;

/**
 * Write homework.a program BaconHistogram that prints homework.a histogram of Kevin Bacon numbers, indicating how many performers from movies.txt have homework.a Bacon number of 0, 1, 2, 3, ... . Include homework.a category for those who have an infinite number (not connected to Kevin Bacon).
 */

public class E40123BaconHistogram {
    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph("movies", "/");
        Graph g = sg.G();
        BreadthFirstPaths bfs = new BreadthFirstPaths(g, sg.index("Bacon, Kevin"));
        int len = bfs.getFarthest() / 2 + 2, max = 0;
        int[] dists = new int[len];

        for (int i = 0; i < g.V(); i++) {
            int dist = bfs.distTo(i);

            if (dist == -1) {
                if (++dists[len - 1] > max) max++;
            } else if ((dist & 1) == 1) {
            } else if (++dists[dist / 2] > max) max++;
        }

        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(-len / 10.0, len + len / 10.0);
        StdDraw.setYscale(-max / 10.0, max + max / 10.0);
        StdDraw.line(-len / 10.0, 0, len + len / 10.0, 0);
        for (int i = 0; i < len; i++) {
            StdDraw.rectangle(i, dists[i] / 2.0, 0.5, dists[i] / 2.0);
        }

        for (int i = 0; i < len - 1; i++) {
            StdDraw.text(i, -3000, i + "");
            StdDraw.text(i, dists[i] + 3000, dists[i] + "");
        }
        StdDraw.text(len - 1, -3000, "Inf");
        StdDraw.text(len - 1, dists[len - 1] + 3000, dists[len - 1] + "");

        StdDraw.show();
    }
}
