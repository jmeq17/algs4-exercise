package ch2.topic4;

/*
Provide priority-queue implementations that support insert and remove the
maximum, one for each of the following underlying data structures: unordered array,
ordered array, unordered linked list, and ordered linked list. Give homework.a table of the worstcase
bounds for each operation for each of your four implementations.
 */

import edu.princeton.cs.algs4.StdOut;
import utils.MaxOAPQ;
import utils.MaxOLPQ;
import utils.MaxUAPQ;
import utils.MaxULPQ;

public class E20403 {
    public static void main(String[] args) {
        String[] a = "priority".split("");

        // -------------------

        // Unorder array:
        StdOut.println("Unordered Array:");
        MaxUAPQ<String> uapq = new MaxUAPQ<>(a.length);
        for (String s : a) {
            uapq.insert(s);
        }

        StdOut.println("Max key is: " + uapq.delMax() + ", Expected: y.");
        uapq.show();
        StdOut.println("\n**********************\n");

        // Ordered array:
        StdOut.println("Ordered Array:");
        MaxOAPQ<String> oapq = new MaxOAPQ<>(a.length);
        for (String s : a) {
            oapq.insert(s);
        }

        StdOut.println("Max key is: " + oapq.delMax() + ", Expected: y.");
        oapq.show();
        StdOut.println("\n**********************\n");

        // Unordered linked-list:
        StdOut.println("Unordered linked-list: ");
        MaxULPQ<String> ulpq = new MaxULPQ<>();
        for (String s : a) {
            ulpq.insert(s);
        }

        StdOut.println("Max key is: " + ulpq.delMax() + ", Expected: y.");
        ulpq.show();
        StdOut.println("\n**********************\n");

        // Unordered linked-list:
        StdOut.println("Ordered linked-list: ");
        MaxOLPQ<String> olpq = new MaxOLPQ<>();
        for (String s : a) {
            olpq.insert(s);
        }

        StdOut.println("Max key is: " + olpq.delMax() + ", Expected: y.");
        olpq.show();
        StdOut.println("\n**********************\n");
    }
}
