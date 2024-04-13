package exercise.ch1.topic5;

/*
Do Exercise 1.5.1, but use quick-union (page 224). In addition, draw the forest of
trees represented by the id[] array after each input pair is processed.
 */

import utils.UFQuickUnion;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class E10502 {
    public static void main(String[] args) {
        int N = 10;
        UFQuickUnion uf = new UFQuickUnion(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            uf.union(p, q);
        }
        StdOut.println();
        StdOut.println(uf.count() + " components");
    }
}
