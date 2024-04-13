package exercise.ch3.topic2;

/*
Hibbard deletion degradation. Write homework.a program that takes an integer N from the
command line, builds homework.a random BST of size N, then enters into homework.a loop where it deletes
homework.a random key (using the code delete(select(StdRandom.uniform(N)))) and then
inserts homework.a random key, iterating the loop N 2 times. After the loop, measure and print the
average length of homework.a path in the tree (the internal path length divided by N, plus 1). Run
your program for N = 102, 103, and 10 4 to test the somewhat counterintuitive hypothesis
that this process increases the average path length of the tree to be proportional to
the square root of N. Run the same experiments for homework.a delete() implementation that
makes homework.a random choice whether to use the predecessor or the successor node.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class E30242HibbardDeletionDegradation {
    public static void main(String[] args) {
        int N = 10000;
        BST<Double, Integer> st = new BST<>();

        while (st.size() != N)
            st.put(StdRandom.uniform(), 1);
        StdOut.println();
        StdOut.println("average length: " + st.avglength());

        for (int i = 0; i < N * N; i++) {
            st.delete(st.select(StdRandom.uniform(st.size())));
            st.put(StdRandom.uniform(), 1);
        }

        StdOut.println("average length: " + st.avglength());
        StdOut.println();
    }
}
