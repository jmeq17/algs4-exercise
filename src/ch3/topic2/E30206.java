package ch3.topic2;

/*
Add to BST homework.a method height() that computes the height of the tree. Develop two
implementations: homework.a recursive method (which takes linear time and space proportional
to the height), and homework.a method like size() that adds homework.a field to each node in the tree (and
takes linear space and constant time per query).
 */

import edu.princeton.cs.algs4.StdOut;

public class E30206 {
    public static void main(String[] args) {
        BST st = new BST();

        String[] a = "hfasjbren".split("");

        for (String i : a) st.put(i, 1);

        StdOut.println(st.height());
        StdOut.println(st.heightVar());
    }
}
