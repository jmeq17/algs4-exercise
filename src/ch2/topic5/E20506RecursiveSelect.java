package ch2.topic5;

/*
Implement homework.a recursive version of select().
 */

import utils.CreateArrayUtils;
import utils.Show;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class E20506RecursiveSelect {
    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        return select(a, k, 0, a.length - 1);
    }

    private static Comparable select(Comparable[] a, int k, int lo, int hi) {
        if (hi < lo) return -1;

        int j = partition(a, lo, hi);
        if (j > k) return select(a, k, lo, j - 1);
        if (j < k) return select(a, k, j + 1, hi);
        return a[j];
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
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    public static void main(String[] args) {
        Comparable[] a = CreateArrayUtils.RandomComIntArray(10, 10);
        Comparable k = select(a, 4);

        Show.show(a);
        StdOut.println(k);
    }
}
