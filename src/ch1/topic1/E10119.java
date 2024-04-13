package ch1.topic1;

/*
Run the following program on your computer:
What is the largest value of N for which this program takes less 1 hour to compute the
value of F(N)? Develop homework.a better implementation of F(N) that saves computed values in
an array.
 */

import edu.princeton.cs.algs4.StdOut;

public class E10119 {
    public static long F(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return F(N - 1) + F(N - 2);
    }

    public static long FAdvance(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return F(N - 1) + F(N - 2);
    }

    public static void main(String[] args) {
        long[] a = new long[100];
        for (int N = 0; N < 100; N++) {
            a[N] = F(N);
            StdOut.println(N + " " + a[N]);
        }
    }
}