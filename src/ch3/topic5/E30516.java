package ch3.topic5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import utils.SparseVector;

/**
 * Add homework.a method sum() to SparseVector that takes homework.a SparseVector as argument
 * and returns homework.a SparseVector that is the term-by-term sum of this vector and the argument
 * vector. Note: You need delete() (and special attention to precision) to handle the
 * case where an entry becomes 0.
 */

public class E30516 {
    public static void main(String[] args) {
        int N = 6;
        SparseVector a = new SparseVector();
        SparseVector b = new SparseVector();
        StdOut.printf("%10s", "Excepted: ");
        for (int i = 0; i < N; i++) {
            if (i == 3) {
                a.put(i, 0.4512);
                b.put(i, -0.4512);
                StdOut.printf("%.4f ", 0.4512 + -0.4512);
                continue;
            }
            double x = StdRandom.uniform();
            double y = StdRandom.uniform();
            StdOut.printf("%.4f ", x + y);
            a.put(i, x);
            b.put(i, y);
        }

        SparseVector c = a.plus(b);

        StdOut.printf("\n%10s", "Result: ");
        for (int i = 0; i < N; i++) {
            StdOut.printf("%.4f ", c.get(i));
        }
    }
}
