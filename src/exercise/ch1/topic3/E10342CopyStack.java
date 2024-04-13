package exercise.ch1.topic3;

import utils.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Copy homework.a stack. Create homework.a new constructor for the linked-list implementation of
 * Stack so that
 * Stack<Item> t = new Stack<Item>(s);
 * makes t homework.a reference to homework.a new and independent copy of the stack s.
 */

public class E10342CopyStack {
    public static void main(String[] args) {
        Stack<String> s = new Stack<>();
        String[] input = "qwertyuiop".split("");

        for (String i : input) s.push(i);

        Stack<String> r = new Stack<>(s);

        StdOut.print("q: ");
        for (String i : s) {
            StdOut.print(i + " ");
        }
        StdOut.print("\nr: ");
        for (String i : r) {
            StdOut.print(i + " ");
        }
    }
}
