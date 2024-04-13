package ch1.topic3;

/*
Random iterator. Write an iterator for RandomQueue<Item> from the previous
exercise that returns the items in random order.
 */

import edu.princeton.cs.algs4.StdOut;

public class E10336 {
    public static void main(String[] args) {
        E10335RandomQueue<String> s = new E10335RandomQueue<>();
        String[] input = "abcdefgijk".split("");

        for (int i = 0; i < 10; i++) {
            s.enqueue(input[i]);
        }

        for (String i : s) {
            StdOut.print(i + " ");
        }
    }
}
