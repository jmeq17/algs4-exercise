package ch3.topic4;

import edu.princeton.cs.algs4.StdOut;

public class E30421 {
    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
        String[] s = "abcderty".split("");

        for (String i : s) st.put(i, 1);

        st.show();
        StdOut.println("\n" + st.avgCostForMiss() + ", Expected: 3.");
    }
}
