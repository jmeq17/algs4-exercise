package ch3.topic2;

/*
Develop homework.a BST implementation that omits rank() and select() and does not
use homework.a count field in Node.

Answer: src3.BSTNoN
 */

import edu.princeton.cs.algs4.StdOut;
import utils.BSTNoN;

public class E30212 {
    public static void main(String[] args) {
        BSTNoN<String, Integer> st = new BSTNoN<>();

        st.put("A", 1);
        st.put("H", 2);

        StdOut.println(st.size());
    }
}
