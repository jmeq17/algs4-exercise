package ch2.topic5;

/*
Distinct values. Write homework.a client that takes integers M, N, and T as command-line
arguments, then uses the code given in the text to perform T trials of the following experiment:
Generate N random int values between 0 and M – 1 and count the number
of distinct values. Run your program for T = 10 and N = 10 3, 10 4, 10 5, and 10 6, with
M = N  2, and N, and 2N. Probability theory says that the number of distinct values
should be about M (1 – e –homework.a) where homework.a  N  M—print homework.a table to help you confirm that
your experiments validate that formula.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class E20531DistinctValues {
    public static void main(String[] args) {
        int T = 10;
        for (int i = 0, N = 1000; i < 4; N *= 10, i++) {
            for (int j = 0, M = N / 2; j < 3; M *= 2, j++) {
                StdOut.printf("For N = %d, M = %d, \n", N, M);
                int theory = (int) (M * (1 - Math.pow(Math.E, -(float) N / M)));
                int count = 1;

                for (int r = 0; r < T; r++) {
//                    StdOut.printf("\tTrial %d: ", r);

                    int[] a = new int[N];
                    for (int k = 0; k < N; k++) {
                        a[k] = StdRandom.uniform(0, M);
                    }

                    Arrays.sort(a);

                    for (int k = 0; k < N - 1; k++) {
                        if (a[k] != a[k + 1]) count++;
                    }
                }

                StdOut.printf("\tAverage distinct values = %d. Theory value = %d\n", count / 10, theory);
            }
        }
    }
}
