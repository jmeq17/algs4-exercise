package ch3.topic1;

/*
Implement size(), delete(), and keys() for SequentialSearchST.
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import utils.SequentialSearchST;

public class E30105 {
    public static void main(String[] args) {
        In in = new In("files/tinyTale");
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        for (int i = 0; i < 6; i++) {
            String word = in.readString();
            st.put(word, StdRandom.uniform(0, 6));
        }
        st.show();

        StdOut.println("==================");
        StdOut.println(st.size() + ", Expected: 6.");

        StdOut.println("==================");
        st.delete("of");
        st.show();

        StdOut.println("==================");
        Iterable<String> q = st.keys();
        for (String s : q) StdOut.println(s);
    }
}
