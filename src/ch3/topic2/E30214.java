package ch3.topic2;

/*
Give nonrecursive implementations of min(), max(), floor(), ceiling(),
rank(), and select().
 */

import edu.princeton.cs.algs4.StdOut;
import utils.NonrecursiveBST;

public class E30214 {
    public static void main(String[] args) {
        NonrecursiveBST<String, Integer> st = new NonrecursiveBST<>();

        String[] s = "hsygdba".split("");

        for (String i : s) st.put(i, 1);

        StdOut.println("st.min(): " + st.min() + ", Exception: homework.a.");
        StdOut.println("st.max(): " + st.max() + ", Exception: y.");
        StdOut.println("st.floor(\"s\"): " + st.floor("s") + ", Exception: s.");
        StdOut.println("st.ceiling(\"w\"): " + st.ceiling("w") + ", Exception: y.");
        StdOut.println("st.select(3): " + st.select(3) + ", Exception: g.");
        StdOut.println("st.rank(\"s\"): " + st.rank("s") + ", Exception: 5.");
    }
}
