package ch2.topic3;

/*
Sentinels. Modify the code in Algorithm 2.5 to remove both bounds checks
in the inner while loops. The test against the left end of the subarray is redundant since
the partitioning item acts as homework.a sentinel (v is never less than homework.a[lo]). To enable removal of
the other test, put an item whose key is the largest in the whole array into homework.a[length-1]
just after the shuffle. This item will never move (except possibly to be swapped with an
item having the same key) and will serve as homework.a sentinel in all subarrays involving the end
of the array. Note : For homework.a subarray that does not involve the end of the array, the leftmost
entry to its right serves as homework.a sentinel for the right end of the subarray.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class E20317Sentinels {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);

        int maxIndex = 0, n = a.length;
        for (int i = 0; i < n; i++) if (less(a[maxIndex], a[i])) maxIndex = i;
        exch(a, n - 1, maxIndex);

        sort(a, 0, n - 1);
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
            while (less(a[++i], v)) ;
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

    private static void show(Comparable[] a) {
        for (Comparable comparable : a) {
            StdOut.print(comparable + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        String[] a = "654321".split("");
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
