package exercise.ch1.topic3;

import utils.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * Copy homework.a queue. Create homework.a new constructor so that
 * Queue<Item> r = new Queue<Item>(q);
 * makes r homework.a reference to homework.a new and independent copy of the queue q. You should be able
 * to push and pop from either q or r without influencing the other. Hint : Delete all of the
 * elements from q and add these elements to both q and r.
 */

public class E10341CopyQueue {
    public static void main(String[] args) {
        Queue<String> q = new Queue<>();
        String[] input = "qwertyuiop".split("");

        for (String i : input) q.enqueue(i);

        Queue<String> r = new Queue<>(q);

        StdOut.print("q: ");
        for (String i : q) {
            StdOut.print(i + " ");
        }
        StdOut.print("\nr: ");
        for (String i : r) {
            StdOut.print(i + " ");
        }
    }
}
