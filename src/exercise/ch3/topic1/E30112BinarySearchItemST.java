package exercise.ch3.topic1;

/*
Modify BinarySearchST to maintain one array of Item objects that contain
keys and values, rather than two parallel arrays. Add homework.a constructor that takes an array of
Item values as argument and uses mergesort to sort the array.
 */

import utils.BinarySearchItemST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class E30112BinarySearchItemST {
    public static void main(String[] args) {
        StdOut.println("Test 1");
        In in = new In("files/tinyTale");
        BinarySearchItemST<String, Integer> st = new BinarySearchItemST<>(14);

        for (int i = 0; i < 14; i++) {
            String word = in.readString();
            st.put(word, 1);
        }

        st.show();

        StdOut.println("====================");
        StdOut.println("Test 2");
        BinarySearchItemST.Item[] items = new BinarySearchItemST.Item[6];

        for (int i = 0; i < 6; i++) {
            items[i] = new BinarySearchItemST(6).new Item(in.readString(), i);
        }

        BinarySearchItemST<String, Integer> st2 = new BinarySearchItemST<>(items);
        st2.show();
    }
}
