package exercise.ch1.topic1;

/*
Random matches. Write homework.a BinarySearch client that takes an int value T as
command-line argument and runs T trials of the following experiment for N = 1000, 10000,
100000, and 1000000: generate two arrays of N randomly generated positive six-digit int values,
and find the number of values that appear in both arrays. Print homework.a table giving the average
value of this quantity over the T trials for each value of N.
 */

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class E10139 {

    public static int[] randomArray(int N) {
        int[] a = new int[N];

        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(100000, 1000000);
        }
        return a;
    }

    public static int randomMatchs(int[] a, int[] b) {
        int N = a.length - 1;
        if (a[0] > b[N] || a[N] < b[0]) return 0;

        if (a[0] < b[0]) {
            return randomMatchs2(a, b);
        } else return randomMatchs2(b, a);
    }

    public static int randomMatchs2(int[] a, int[] b) {
        int N = a.length - 1;
        int num = 0;
        for (int i = 0; b[0] <= a[N] && i < N; i++) {
            if (BinarySearch.rank(b[i], a) != -1) num++;
        }
        return num;
    }

    public static void main(String[] args) {
        int T = parseInt(args[0]);
        int[][] table = new int[T][4];
        for (int i = 0; i < T; i++) {
            int j = 0;
            for (int N = 1000; N < 1000001; N *= 10) {
                int[] a = randomArray(N);
                int[] b = randomArray(N);

                Arrays.sort(a);
                Arrays.sort(b);

                table[i][j] = randomMatchs(a, b);
                StdOut.printf("%d: %d", N, table[i][j]);
                StdOut.print("\t\t");
                j++;
            }
            StdOut.println();
        }

        double[] average = new double[4];
        for (int i = 0; i < 4; i++) {
            int sum = 0;
            for (int j = 0; j < T; j++) {
                sum += table[j][i];
            }
            average[i] = (double) sum / (double) T;
        }
        StdOut.println("average value: ");
        StdOut.printf("1000: %.1f\t10000: %.1f\t100000: %.1f\t1000000: %.1f", average[0], average[1], average[2], average[3]);
    }
}
