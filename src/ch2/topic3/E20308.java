package ch2.topic3;

/*
About how many compares will Quick.sort() make when sorting an array of
N items that are all equal?
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import utils.CreateArrayUtils;

public class E20308 {
    public static int N;

    public static int sort(Comparable[] a) {
        N = 0;
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        return N;
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
        N++;
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    public static void main(String[] args) {
        int T = 10;
        for (int i = 0; i < T; i++) {
            int N = StdRandom.uniformInt(1000000);
            int value = StdRandom.uniformInt(1000000);
            Comparable[] a = CreateArrayUtils.EqualComArray(N, value);
            int count = sort(a);
            StdOut.println("Aaary size N = " + N);
            StdOut.println("N lg(N) = " + (int) (N * Math.log(N) / Math.log(2)));
            StdOut.println("Compara numbers = " + count);
            StdOut.println();
        }
    }
}
