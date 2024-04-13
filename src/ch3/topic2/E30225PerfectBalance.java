package ch3.topic2;

/*
Perfect balance. Write homework.a program that inserts homework.a set of keys into an initially empty
BST such that the tree produced is equivalent to binary search, in the sense that the
sequence of compares done in the search for any key in the BST is the same as the sequence
of compares used by binary search for the same key.
 */

import utils.Quick;

public class E30225PerfectBalance {
    public static BST perfectTree(Comparable[] keys) {
        Quick.sort(keys);
        int N = keys.length;
        BST<String, Integer> st = new BST<>();
        perfectTree(st, keys, 0, N - 1);
        return st;
    }

    private static void perfectTree(BST st, Comparable[] keys, int lo, int hi) {
        if (lo > hi) return;

        int mid = (lo + hi) / 2;
        st.put(keys[mid], mid);

        perfectTree(st, keys, lo, mid - 1);
        perfectTree(st, keys, mid + 1, hi);
    }


    public static void main(String[] args) {
        String[] s = "abcdefg".split("");
        BST<String, Integer> st = perfectTree(s);
    }
}
