package exercise.ch3.topic5;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Write homework.a program that takes homework.a string on standard input and an integer k as command-line
 * argument and puts on standard output homework.a sorted list of the k-grams (substrings
 * of length k) found in the string, each followed by its index in the string.
 */

public class E30515 {
    public static void main(String[] args) {
        String s = StdIn.readString();
        int k = StdIn.readInt();
        ST<String, Integer> st = new ST<>();

        for (int i = 0; i < s.length() - k + 1; i++) {
            String j = s.substring(i, i + k);
            st.put(j, i);
        }

        for (String i : st) StdOut.println(i + ", " + st.get(i));
    }
}
