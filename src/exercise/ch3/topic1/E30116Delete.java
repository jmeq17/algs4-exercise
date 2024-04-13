package exercise.ch3.topic1;

/*
Implement the delete() method for BinarySearchST
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import utils.BinarySearchST;

public class E30116Delete {
    public static void main(String[] args) {
        In in = new In("files/tinyTale");
        BinarySearchST<String, Integer> st = new BinarySearchST<>(5);
        for (int i = 0; i < 5; i++) {
            String word = in.readString();
            st.put(word, StdRandom.uniform(0, 10));
        }
        st.show();
        StdOut.println("==================");
        st.delete("of");
        st.show();
        StdOut.println("==================");
        st.delete("on");
        st.show();
    }
}
