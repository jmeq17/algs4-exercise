package ch3.topic4;

import edu.princeton.cs.algs4.StdOut;
import utils.LinearProbingHashST;

public class E30417 {
    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
        int val = 0;
        String[] s = "SEARCHEXAMPLE".split("");

        for (String i : s) st.put(i, val++);
        st.show();

        StdOut.println();
        st.delete("C");
        st.show();
    }
}
