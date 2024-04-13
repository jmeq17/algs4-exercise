package ch3.topic4;

import edu.princeton.cs.algs4.StdOut;
import utils.SequentialSearchHashST;

public class E30403 {
    public static void main(String[] args) {
        SequentialSearchHashST<String, Integer> st = new SequentialSearchHashST<>();
        String[] s = "sufficiently short to enable efficient search through".split("");

        for (String value : s) {
            st.put(value, 1);
        }

        st.delete("e");
        Iterable<String> it = st.keys();
        for (String i : it) StdOut.print(i + " ");

        StdOut.println();

        st.deleteGtsize(4);
        it = st.keys();
        for (String i : it) StdOut.print(i + " ");
    }
}
