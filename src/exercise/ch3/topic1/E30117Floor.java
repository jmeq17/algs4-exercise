package exercise.ch3.topic1;

/*
Implement the floor() method for BinarySearchST.
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import utils.BinarySearchST;

public class E30117Floor {
    public static void main(String[] args) {
        In in = new In("files/tinyTale");
        BinarySearchST<String, Integer> st = new BinarySearchST<>(10);
        for (int i = 0; i < 10; i++) {
            String word = in.readString();
            st.put(word, StdRandom.uniform(0, 10));
        }
        st.show();
        StdOut.println("==================");
        StdOut.println(st.floor("times") + ", Expected: times.");
        StdOut.println("==================");
        StdOut.println(st.floor("tomes") + ", Expected: times.");
    }
}
