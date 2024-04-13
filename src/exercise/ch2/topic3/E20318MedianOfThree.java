package exercise.ch2.topic3;

/*
Median-of-3 partitioning. Add median-of-3 partitioning to quicksort, as described
in the text (see page 296). Run doubling tests to determine the effectiveness of
the change.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import utils.CreateArrayUtils;

public class E20318MedianOfThree {
    public static void sort(Comparable[] a) {
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
        Comparable v = findPivot(a, lo, hi);
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) ;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static Comparable findPivot(Comparable[] a, int lo, int hi) {
        if (hi - lo < 2) return a[lo];
        int mid = (lo + hi) / 2;
        if (less(a[mid], a[lo])) exch(a, lo, mid);
        if (less(a[mid], a[hi])) exch(a, mid, hi);
        if (less(a[mid], a[lo])) exch(a, mid, lo);
        return a[mid];
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    public static void main(String[] args) {
        int N = 128;

        double timesOld = 1.0, timesNew;
        for (N += N; ; N *= 2) {
            Comparable[] a = CreateArrayUtils.RandomComDoubleArray(N);
            Stopwatch timer = new Stopwatch();
            sort(a);
            timesNew = timer.elapsedTime();
            StdOut.printf("%d\t\t%.3f\t\t%.3f\n", N, timesNew, timesNew / timesOld);
            timesOld = timesNew;
        }
    }
}
