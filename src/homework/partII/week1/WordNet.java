package homework.partII.week1;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;

public class WordNet {
    private final SAP sap;
    private final HashMap<String, Bag<Integer>> st;
    private final String[] synsets;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) throw new IllegalArgumentException();

        In in1 = new In(synsets);
        In in2 = new In(hypernyms);

        st = new HashMap<>();

        String[] tem = in1.readAllLines();
        int v1 = tem.length;

        Digraph g = new Digraph(v1);
        while (!in2.isEmpty()) {
            String[] s = in2.readLine().split(",");
            int v = Integer.parseInt(s[0]);
            for (int i = 1; i < s.length; i++) {
                int w = Integer.parseInt(s[i]);
                g.addEdge(v, w);
            }
        }
        boolean[] marked = new boolean[v1];
        boolean[] stack = new boolean[v1];
        int numOfRoot = 0;

        for (int v = 0; v < v1; v++)
            if (!marked[v]) numOfRoot += dfs(g, v, marked, stack);
        if (numOfRoot != 1) throw new IllegalArgumentException();

        sap = new SAP(g);

        this.synsets = new String[v1];
        for (int i = 0; i < tem.length; i++) {
            String[] s1 = tem[i].split(",");
            String[] s2 = s1[1].split("\\s+");
            int s = Integer.parseInt(s1[0]);

            for (String value : s2) {
                if (!st.containsKey(value)) {
                    Bag<Integer> tem1 = new Bag<>();
                    tem1.add(s);
                    st.put(value, tem1);
                } else st.get(value).add(s);
            }

            this.synsets[i] = s1[1];
        }
    }

    private int dfs(Digraph g, int v, boolean[] marked, boolean[] stack) {
        int numOfRoot = 0;
        marked[v] = true;
        stack[v] = true;
        int count = 0;

        for (int w : g.adj(v)) {
            count++;
            if (!marked[w]) numOfRoot += dfs(g, w, marked, stack);
            else if (stack[w]) throw new IllegalArgumentException();
        }

        stack[v] = false;
        if (count == 0) numOfRoot++;
        return numOfRoot;
    }

    // returns all HW.PartII.Week1.WordNet nouns
    public Iterable<String> nouns() {
        return st.keySet();
    }

    // is the word homework.a HW.PartII.Week1.WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) throw new IllegalArgumentException();
        return st.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new IllegalArgumentException();

        Bag<Integer> a = st.get(nounA);
        Bag<Integer> b = st.get(nounB);

        return sap.length(a, b);
    }

    // homework.a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in homework.a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new IllegalArgumentException();

        Bag<Integer> a = st.get(nounA);
        Bag<Integer> b = st.get(nounB);

        int ancestor = sap.ancestor(a, b);
        return ancestor != -1 ? synsets[ancestor] : null;
    }

    public static void main(String[] args) {
        String filename1 = "HW_Project\\wordnet\\synsets15.txt";
        String filename2 = "HW_Project\\wordnet\\hypernyms15Tree.txt";

        WordNet a = new WordNet(filename1, filename2);

        String nounA = "k";
        String nounB = "l";
        a.distance(nounA, nounB);
        StdOut.println(a.distance(nounA, nounB));
        StdOut.println(a.sap(nounA, nounB));
    }
}
