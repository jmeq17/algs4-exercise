package ch2.topic3;

/*
Write homework.a program to compute the exact value of C_N, and compare the exact value
with the approximation 2N ln N, for N = 100, 1,000, and 10,000.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import utils.CreateArrayUtils;

public class E20306 {
    private static int compare;

    public static double computeCN(int N) {
        // CN ~ 2 (N  1)(1/3  1/4  . . .  1/(N  1) )   This is wrong.
        double right = 0;
        for (double i = 1; i <= N + 1; i++) right += 1.0 / i;
        return 2 * (N + 1) * right;
    }

    public static void sort(Comparable[] a) {
        compare = 0;
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) ;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static boolean less(Comparable v, Comparable w) {
        compare++;
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static int compareNum() {
        return compare;
    }


    public static void main(String[] args) {
        int[] size = {100, 1000, 10000};
        int MAX = 1000000;
        for (int N : size) {
            Comparable[] a = CreateArrayUtils.RandomComIntArray(N, MAX);
            sort(a);
            int exactCn = compareNum();
            double approxCn = 2 * N * Math.log(N);
            StdOut.printf("For N = %d, the exact value is %d, and the approximate value is %.3f.\n", N, exactCn, approxCn);
        }
    }
}
