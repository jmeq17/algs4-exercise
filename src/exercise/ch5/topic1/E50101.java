package exercise.ch5.topic1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import utils.RedBlackBST;

/**
 * Develop homework.a sort implementation that counts the number of different key values,
 * then uses homework.a symbol table to apply key-indexed counting to sort the array. (This method
 * is not for use when the number of different key values is large.)
 */

public class E50101 {
    public static void sort1(String[] a) {
        RedBlackBST<String, Integer> st = new RedBlackBST<>();

        for (String s : a) {
            if (!st.contains(s)) st.put(s, 1);
            else st.changeKey(s, st.get(s) + 1);
        }

        int v = 0;
        while (!st.isEmpty()) {
            String s = st.min();
            int n = st.get(s);
            st.deleteMin();

            for (int i = 0; i < n; i++) {
                a[v++] = s;
            }
        }
    }

    public static void main(String[] args) {
        String[] a = new String[20];

        int t = 20;
        int v = 0;

        for (int i = 0; i < 3; i++) {
            String s = StdIn.readString();
            int x = StdRandom.uniform(0, t);
            StdOut.println("x: " + x + ", s: " + s);
            t -= x;

            for (int j = 0; j < x; j++) {
                a[v++] = s;
            }
        }

        String s = StdIn.readString();
        StdOut.println("t: " + t + ", s: " + s);

        for (int j = 0; j < t; j++) {
            a[v++] = s;
        }

        StdRandom.shuffle(a);
        for (int i = 0; i < 20; i++) StdOut.print(a[i] + " ");
        StdOut.println();

        sort1(a);

        for (int i = 0; i < 20; i++) StdOut.print(a[i] + " ");
    }
}
