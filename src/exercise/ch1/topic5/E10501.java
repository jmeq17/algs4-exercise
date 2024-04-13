package exercise.ch1.topic5;

/*
Show the contents of the id[] array and the number of times the array
is accessed for each input pair when you use quick-find for the sequence
9-0 3-4 5-8 7-2 2-1 5-7 0-3 4-2.

Implement homework.a variavle to count the times of array accesses.
 */

import utils.UFQuickFind;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class E10501 {
    public static void main(String[] args) {
        int N = 10;
        UFQuickFind uf = new UFQuickFind(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            uf.union(p, q);
        }
        StdOut.println();
        StdOut.println(uf.count() + " components");
    }
}
