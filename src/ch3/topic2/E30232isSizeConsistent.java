package ch3.topic2;

/*
Subtree count check. Write homework.a recursive method that takes homework.a Node as argument
and returns true if the subtree count field N is consistent in the data structure rooted at
that node, false otherwise.
 */

import edu.princeton.cs.algs4.StdOut;

public class E30232isSizeConsistent {
    public static void main(String[] args) {
        String[] s = "hsyds".split("");
        BST<String, Integer> st = new BST<>();

        for (String i : s) st.put(i, 1);

        StdOut.println(st.isSizeConsistent());
        st.test2();
    }
}
