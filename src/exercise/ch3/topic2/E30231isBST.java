package exercise.ch3.topic2;

/*
Certification. Write homework.a method isBST() that takes homework.a Node as argument and returns
true if the argument node is the root of homework.a binary search tree, false otherwise.
Hint : Write homework.a helper method that takes homework.a Node and two Keys as arguments and returns
true if the argument node is the root of homework.a binary search tree with all keys strictly between
the two argument keys, false otherwise.
 */

import edu.princeton.cs.algs4.StdOut;

public class E30231isBST {
    public static void main(String[] args) {
        String[] s = "hsyds".split("");
        BST<String, Integer> st = new BST<>();

        for (String i : s) st.put(i, 1);

        StdOut.println(st.isBST2());
        StdOut.println(st.isBST());
        st.test();
    }
}
