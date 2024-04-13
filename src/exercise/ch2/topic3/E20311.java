package exercise.ch2.topic3;

/*
Suppose that we scan over items with keys equal to the partitioning item’s key
instead of stopping the scans when we encounter them. Show that the running time
of this version of quicksort is quadratic for all arrays with just homework.a constant number of
distinct keys.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import utils.CreateArrayUtils;

public class E20311 {
    public static int count = 0;

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        StdOut.print(count + ", ");
        count = 0;
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
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static boolean less(Comparable v, Comparable w) {
        count++;
        return v.compareTo(w) <= 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    public static void main(String[] args) {
        double timeold = 1;
        StdOut.println("size\ttime\tratio");

        for (int N = 250, i = 0; i < 7; N = 2 * N, i++) {
            Comparable[] a = CreateArrayUtils.EqualComArray(N, 1);
            Stopwatch timer = new Stopwatch();
            StdOut.printf("for N = %d, actual compara number = ", N);
            sort(a);
            StdOut.printf("N*N-2 = %d\n", N * N - 1);
            double timenew = timer.elapsedTime();

            StdOut.printf("%d\t%.6f\t%.6f\n\n", N, timenew, timenew / timeold);
            timeold = timenew;
        }
    }
}
