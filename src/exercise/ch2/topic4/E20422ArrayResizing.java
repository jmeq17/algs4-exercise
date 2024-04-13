package exercise.ch2.topic4;

/*
Array resizing. Add array resizing to MaxPQ, and prove bounds like those of
Proposition Q for array accesses, in an amortized sense.
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import utils.MaxPQResizing;

public class E20422ArrayResizing {
    public static void main(String[] args) {
        In in = new In(args[0]);
        MaxPQResizing<String> a = new MaxPQResizing<>();
        while (!in.isEmpty()) {
            String s = in.readString();
            if (!s.equals("-")) {
                a.insert(s);
            } else StdOut.print(a.delMax() + " ");

        }
        StdOut.println();
        while (!a.isEmpty()) StdOut.print(a.delMax() + " ");
    }
}
