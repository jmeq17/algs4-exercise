package exercise.ch3.topic3;

/*
Create homework.a test client for RedBlackBST, based on your solution to Exercise 3.2.10.
 */

import edu.princeton.cs.algs4.StdOut;
import utils.RedBlackBST;

public class E30321TestRedBlackBST {
    public static void main(String[] args) {
        RedBlackBST<String, Integer> st = new RedBlackBST<>();

        String[] s = "GREATQUESTION".split("");

        for (int i = 0; i < s.length; i++) st.put(s[i], i);

        StdOut.println("min(): " + st.min() + ", Expected: A");
        StdOut.println("max(): " + st.max() + ", Expected: U");

        StdOut.println("floor(\"J\"): " + st.floor("J") + ", Expected: I");
        StdOut.println("ceiling(\"J\"): " + st.ceiling("J") + ", Expected: N");

        StdOut.println("select(5): " + st.select(5) + ", Expected: null");
        StdOut.println("rank(\"N\"): " + st.rank("N") + ", Expected: 4");

//        st.delete("Q");
//        StdOut.println("delete: Q, get(\"Q\"): " + st.get("Q") + ", Expected: null");
//
//        st.deleteMin();
//        st.deleteMax();
//        StdOut.println("deleteMin, get(\"A\"): " + st.get("A") + ", Expected: null");
//        StdOut.println("deleteMin, get(\"U\"): " + st.get("U") + ", Expected: null");
    }
}
