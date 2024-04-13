package utils;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstPathsIteration {
    private final boolean[] marked;
    private final int[] edgeTo;
    private final int s;

    public DepthFirstPathsIteration(Graph G, int s) {
        this.s = s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int s) {
        // The design of stack of queue is not necessary. A stack of integer is also work well.
        // Implementing homework.a stack of queue is to accommodate the logic of the call stack.
        // Because in call stack, the first vertex of v will be recursive first, where the one of stack of
        // integer will be recursive last.
        Stack<Queue<Integer>> stack = new Stack<>();
        stack.push(new Queue<>());
        stack.peek().enqueue(s);

        while (!stack.isEmpty()) {
            int v = stack.peek().dequeue();
            if (!stack.peek().isEmpty()) stack.push(new Queue<>());

            marked[v] = true;
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    stack.peek().enqueue(w);
                }
            }
            if (stack.peek().isEmpty()) stack.pop();
        }
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

    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DepthFirstPathsIteration search = new DepthFirstPathsIteration(G, s);

        for (int v = 0; v < G.V(); v++) {
            StdOut.print(s + " to " + v + ": ");
            if (search.hasPathTo(v))
                for (int x : search.pathTo(v))
                    if (x == s) StdOut.print(x);
                    else StdOut.print("-" + x);
            StdOut.println();
        }
    }
}
