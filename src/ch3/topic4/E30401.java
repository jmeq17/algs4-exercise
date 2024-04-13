package ch3.topic4;

/*
Insert the keys E A S Y Q U T I O N in that order into an initially empty table
of M = 5 lists, using separate chaining. Use the hash function 11 * k % M to transform
the kth letter of the alphabet into homework.a table index.
 */

import edu.princeton.cs.algs4.StdOut;

public class E30401 {
    public static void main(String[] args) {
        String[] s = "E A S Y Q U T I O N".split("\\s+");

        int M = 5, a = 'A';
        for (String i : s) {
            int k = i.charAt(0) - a + 1;
            StdOut.println(i + "'s hash code is: " + 11 * k % M);
        }
    }
}
