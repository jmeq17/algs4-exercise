package utils;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SymbolGraph {
    private final ST<String, Integer> st = new ST<>();
    private final String[] index;
    private final Graph G;

    public SymbolGraph(String filename, String delim) {
        In in = new In(filename);
        Queue<Integer> queue = new Queue<>();
        int v1 = 0;

        // Bulid String --> index
        while (!in.isEmpty()) {
            String[] line = in.readLine().split(delim);
            String key = line[0];
            if (!st.contains(key)) st.put(key, v1++);

            for (int i = 1; i < line.length; i++) {
                String val = line[i];
                if (!st.contains(val)) st.put(val, v1++);

                queue.enqueue(st.get(key));
                queue.enqueue(st.get(val));
            }
        }

        // Build index --> String
        index = new String[st.size()];
        for (String s : st) index[st.get(s)] = s;

        // Bulid Graph
        G = new Graph(v1);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            int w = queue.dequeue();
            G.addEdge(v, w);
        }
    }

    public boolean contains(String key) {
        return st.contains(key);
    }

    public int index(String key) {
        if (!st.contains(key)) throw new IllegalArgumentException("key is not contained.");
        return st.get(key);
    }

    public String name(int v) {
        return index[v];
    }

    public Graph G() {
        return G;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String delim = args[1];
        SymbolGraph sg = new SymbolGraph(filename, delim);
        Graph G = sg.G();
        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            for (int v : G.adj(sg.index(source)))
                StdOut.println(" " + sg.name(v));
        }
    }
}
