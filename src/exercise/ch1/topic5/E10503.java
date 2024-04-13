package exercise.ch1.topic5;

/*
Do Exercise 1.5.1, but use weighted quick-union (page 228).
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class E10503 {
    public static void main(String[] args) {
        int N = 10;
        UFWeightQuickUnion uf = new UFWeightQuickUnion(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            uf.union(p, q);
        }
        StdOut.println();
        StdOut.println(uf.count() + " components");
    }
}
