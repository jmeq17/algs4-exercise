package exercise.ch3.topic2;

/*
Write homework.a test client for BST that tests the implementations of min(), max(),
floor(), ceiling(), select(), rank(), delete(), deleteMin(), deleteMax(), and
keys() that are given in the text. Start with the standard indexing client given on page
370. Add code to take additional command-line arguments, as appropriate.
 */

import edu.princeton.cs.algs4.StdOut;

public class E30210 {
    public static void main(String[] args) {
        BST<String, Integer> st = new BST<>();

        String[] s = "GREATQUESTION".split("");

        for (int i = 0; i < s.length; i++) st.put(s[i], i);

        StdOut.println("min(): " + st.min() + ", Expected: A");
        StdOut.println("max(): " + st.max() + ", Expected: U");

        StdOut.println("floor(\"J\"): " + st.floor("J") + ", Expected: I");
        StdOut.println("ceiling(\"J\"): " + st.ceiling("J") + ", Expected: N");

        StdOut.println("select(5): " + st.select(5) + ", Expected: O");
        StdOut.println("rank(\"N\"): " + st.rank("N") + ", Expected: 4");

        st.delete("Q");
        StdOut.println("delete: Q, get(\"Q\"): " + st.get("Q") + ", Expected: null");

        st.deleteMin();
        st.deleteMax();
        StdOut.println("deleteMin, get(\"A\"): " + st.get("A") + ", Expected: null");
        StdOut.println("deleteMin, get(\"U\"): " + st.get("U") + ", Expected: null");
    }
}
