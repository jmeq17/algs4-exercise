package utils;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BreadthFirstPaths {
    private final boolean[] marked;
    private final int[] edgeTo;
    private final int s;
    private int size;
    private int farthest;
    private final int[] distTo;

    public BreadthFirstPaths(Graph G, int s) {
        this.s = s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        marked[s] = true;
        size++;

        while (!queue.isEmpty()) {
            int k = queue.dequeue();

            for (int i : G.adj(k)) {
                if (!marked[i]) {
                    marked[i] = true;
                    size++;
                    edgeTo[i] = k;
                    distTo[i] = distTo[k] + 1;
                    queue.enqueue(i);
                }
            }
            if (distTo[k] > farthest) farthest = distTo[k] + 1;
        }
    }

    // The number of vectics connected to source (include source)
    public int size() {
        return size;
    }

    public int getFarthest() {
        return farthest;
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!marked[v]) return null;
        Stack<Integer> path = new Stack<>();

        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    /**
     * E40113
     * Add homework.a distTo() method to the BreadthFirstPaths API and implementation,
     * which returns the number of edges on the shortest path from the source
     * to homework.a given vertex. A distTo() query should run in constant time.
     * <p>
     * Define homework.a instance variable named distTo that is an array of int to reserve
     * the distance of v to source.
     */
    public int distTo(int v) {
        if (!marked[v]) return -1;
        return distTo[v];
    }

    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        BreadthFirstPaths search = new BreadthFirstPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            StdOut.print(s + " to " + v + ": ");
            if (search.hasPathTo(v))
                for (int x : search.pathTo(v))
                    if (x == s) StdOut.print(x);
                    else StdOut.print("-" + x);
            StdOut.println("\n" + search.distTo(v));
            StdOut.println();
        }

        StdOut.println(search.getFarthest());
    }
}
