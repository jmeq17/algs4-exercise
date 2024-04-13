package homework.partII.week1;

import edu.princeton.cs.algs4.*;

public class WordNetMine {
    private final SAPMine sapMine;
    private final ST<String, Bag<Integer>> st;
    private final String[] synsets;

    // constructor takes the name of the two input files
    public WordNetMine(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) throw new IllegalArgumentException();

        In in1 = new In(synsets);
        In in2 = new In(hypernyms);
        st = new ST<>();

        // ReadAllLine
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

        sapMine = new SAPMine(g);

        // String --> Index
        this.synsets = new String[v1];
        for (int i = 0; i < tem.length; i++) {
            String[] s1 = tem[i].split(",");
            String[] s2 = s1[1].split("\\s+");
            int s = Integer.parseInt(s1[0]);

            for (String value : s2) {
                if (!st.contains(value)) {
                    Bag<Integer> tem1 = new Bag<>();
                    tem1.add(s);
                    st.put(value, tem1);
                } else st.get(value).add(s);
            }

            this.synsets[i] = s1[1];
        }
        // ---------------


        // constract tow in1
        // String --> Index
//        while (!in1.isEmpty()) {
//            String[] s = in1.readLine().split(",");
//            int i = Integer.parseInt(s[0]);
//            String[] s1 = s[1].split("\\s+");
//            for (String value : s1) {
//                if (!st.contains(value)) {
//                    HW.PartII.Week4.Bag<Integer> tem = new HW.PartII.Week4.Bag<>();
//                    tem.add(i);
//                    st.put(value, tem);
//                } else st.get(value).add(i);
//            }
//            v1++;
//        }
//
//        in1 = new In(synsets);
//        this.synsets = new String[v1];
//
//        while (!in1.isEmpty()) {
//            String[] s = in1.readLine().split(",");
//            int i = Integer.parseInt(s[0]);
//            this.synsets[i] = s[1];
//        }
//
//        g = new Digraph(v1);
//        while (!in2.isEmpty()) {
//            String[] s = in2.readLine().split(",");
//            int v = Integer.parseInt(s[0]);
//            for (int i = 1; i < s.length; i++) {
//                int w = Integer.parseInt(s[i]);
//                g.addEdge(v, w);
//            }
//        }
    }

//    private void isDAGandHasRoot() {
//        boolean[] marked = new boolean[v];
//        boolean[] stack = new boolean[v];
//
//        boolean hasRoot = dfs(0, marked, stack, false);
//        if (!hasRoot) throw new IllegalArgumentException();
//    }

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
        return st;
    }

    // is the word homework.a HW.PartII.Week1.WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) throw new IllegalArgumentException();
        return st.contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new IllegalArgumentException();

        Bag<Integer> a = st.get(nounA);
        Bag<Integer> b = st.get(nounB);

        return sapMine.length(a, b);
//
//        int[] distanceToA = new int[v];
//        boolean[] markedA = new boolean[v];
//
//        Queue<Integer> queue = new Queue<>();
//        for (int v : homework.a) {
//            distanceToA[v] = 0;
//            markedA[v] = true;
//            queue.enqueue(v);
//        }
//
//        while (!queue.isEmpty()) {
//            int v = queue.dequeue();
//
//            for (int w : g.adj(v)) {
//                if (!markedA[w]) {
//                    distanceToA[w] = distanceToA[v] + 1;
//                    markedA[w] = true;
//                    queue.enqueue(w);
//                }
//            }
//        }
//
//        int distance = v;
//        int[] distanceToB = new int[v];
//        boolean[] markedB = new boolean[v];
//
//        for (int v : b) {
//            distanceToB[v] = 0;
//            markedB[v] = true;
//            queue.enqueue(v);
//
//            if (markedA[v] && distanceToA[v] < distance) distance = distanceToA[v];
//        }
//
//        while (!queue.isEmpty()) {
//            int v = queue.dequeue();
//
//            for (int w : g.adj(v)) {
//                if (!markedB[w]) {
//                    distanceToB[w] = distanceToB[v] + 1;
//                    markedB[w] = true;
//                    queue.enqueue(w);
//                }
//                if (markedA[w] && distanceToA[w] + distanceToB[w] < distance)
//                    distance = distanceToA[w] + distanceToB[w];
//            }
//        }
//
//        return distance != v ? distance : -1;
    }

    // homework.a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in homework.a shortest ancestral path (defined below)
    public String sapMine(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new IllegalArgumentException();

        Bag<Integer> a = st.get(nounA);
        Bag<Integer> b = st.get(nounB);

        int ancestor = sapMine.ancestor(a, b);
        return ancestor != -1 ? synsets[ancestor] : null;

//        int[] distanceToA = new int[v];
//        boolean[] markedA = new boolean[v];
//
//        Queue<Integer> queue = new Queue<>();
//        for (int v : homework.a) {
//            distanceToA[v] = 0;
//            markedA[v] = true;
//            queue.enqueue(v);
//        }
//
//        while (!queue.isEmpty()) {
//            int v = queue.dequeue();
//
//            for (int w : g.adj(v)) {
//                if (!markedA[w]) {
//                    distanceToA[w] = distanceToA[v] + 1;
//                    markedA[w] = true;
//                    queue.enqueue(w);
//                }
//            }
//        }
//
//        int distance = v;
//        int ancestral = -1;
//        int[] distanceToB = new int[v];
//        boolean[] markedB = new boolean[v];
//
//        for (int v : b) {
//            distanceToB[v] = 0;
//            markedB[v] = true;
//            queue.enqueue(v);
//
//            if (markedA[v] && distanceToA[v] < distance) {
//                distance = distanceToA[v];
//                ancestral = v;
//            }
//        }
//
//        while (!queue.isEmpty()) {
//            int v = queue.dequeue();
//
//            for (int w : g.adj(v)) {
//                if (!markedB[w]) {
//                    distanceToB[w] = distanceToB[v] + 1;
//                    markedB[w] = true;
//                    queue.enqueue(w);
//                }
//
//                if (markedA[w] && distanceToA[w] + distanceToB[w] < distance) {
//                    distance = distanceToA[w] + distanceToB[w];
//                    ancestral = w;
//                }
//            }
//        }
//
//        return synsets[ancestral];
    }

//    public Iterable<Integer> FindsapMinePath(String nounA, String nounB) {
//        if (nounA == null || nounB == null) throw new IllegalArgumentException();
//
//        HW.PartII.Week4.Bag<Integer> homework.a = st.get(nounA);
//        HW.PartII.Week4.Bag<Integer> b = st.get(nounB);
//
//        int[] distanceTo = new int[v];
//        boolean[] marked = new boolean[v];
//        int[] edgeTo = new int[v];
//
//        Queue<Integer> queue = new Queue<>();
//        for (int v : homework.a) {
//            distanceTo[v] = 0;
//            marked[v] = true;
//            edgeTo[v] = v;
//            queue.enqueue(v);
//        }
//
//        while (!queue.isEmpty()) {
//            int v = queue.dequeue();
//
//            for (int w : g.adj(v)) {
//                if (!marked[w]) {
//                    distanceTo[w] = distanceTo[v]++;
//                    marked[v] = true;
//                    edgeTo[w] = v;
//                    queue.enqueue(w);
//                }
//            }
//        }
//
//        int distance = v;
//        int ancestral = v;
//        int subAncestral = v + 1;
//
//        for (int v : b) {
//            if (!marked[v]) {
//                distanceTo[v] = 1;
//                marked[v] = true;
//                edgeTo[v] = v;
//                queue.enqueue(v);
//            } else if (distanceTo[v] < distance) {
//                distance = distanceTo[v];
//                ancestral = v;
//            }
//        }
//
//        while (!queue.isEmpty()) {
//            int v = queue.dequeue();
//
//            for (int w : g.adj(v)) {
//                if (!marked[w]) {
//                    distanceTo[w] = distanceTo[v]++;
//                    marked[v] = true;
//                    edgeTo[w] = v;
//                    queue.enqueue(w);
//                } else if (distanceTo[w] == 0) {
//                    if (distanceTo[v] < distance) {
//                        distance = distanceTo[v];
//                        subAncestral = v + 1;
//                    }
//                } else if (distanceTo[w] + distanceTo[v] < distance) {
//                    distance = distanceTo[w] + distanceTo[v];
//                    // v --> w
//                    ancestral = w;
//                    subAncestral = v;
//                }
//            }
//        }
//
//        Stack<Integer> stack = new Stack<>();
//        if (subAncestral == v + 1) {
//
//            int v = ancestral;
//            while (v != edgeTo[v]) {
//                stack.push(v);
//                v = edgeTo[v];
//            }
//            stack.push(v);
//        } else {
//            Stack<Integer> tem = new Stack<>();
//            int w = subAncestral;
//            while (w != edgeTo[w]) {
//                tem.push(w);
//                w = edgeTo[w];
//            }
//
//            while (!tem.isEmpty()) stack.push(tem.pop());
//
//            int v = ancestral;
//            while (v != edgeTo[v]) {
//                stack.push(v);
//                v = edgeTo[v];
//            }
//            stack.push(v);
//        }
//
//        return stack;
//    }

    // do unit testing of this class
    public static void main(String[] args) {
        String filename1 = "HW_Project\\wordnet\\synsets6.txt";
        String filename2 = "HW_Project\\wordnet\\hypernyms6InvalidTwoRoots.txt";

        WordNetMine a = new WordNetMine(filename1, filename2);

        String nounA = "a";
        String nounB = "j";

        StdOut.println(a.distance(nounA, nounB));
        StdOut.println(a.sapMine(nounA, nounB));
    }
}
