package ch3.topic2;

/*
Give nonrecursive implementations of get() and put() for BST.
Partial solution : Here is an implementation of get():
    public Value get(Key key)
    {
        Node x = root;
        while (x != null)
        {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x.val;
            else if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
        }
        return null;
    }
The implementation of put() is more complicated because of the need to save homework.a pointer
to the parent node to link in the new node at the bottom. Also, you need homework.a separate
pass to check whether the key is already in the table because of the need to update the
counts. Since there are many more searches than inserts in performance-critical implementations,
using this code for get() is justified; the corresponding change for put()
might not be noticed.
 */

import edu.princeton.cs.algs4.StdOut;
import utils.NonrecursiveBST;

public class E30213 {
    public static void main(String[] args) {
        NonrecursiveBST<String, Integer> st = new NonrecursiveBST<>();

        String[] s = "yhdneg".split("");

        for (String i : s) st.put(i, 1);

        StdOut.println(st.get("e"));
    }
}
