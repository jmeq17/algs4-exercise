package exercise.ch2.topic3;

/*
Fast 3-way partitioning. (J. Bentley and D. McIlroy) Implement an entropyoptimal
sort based on keeping items with equal keys at both the left and right ends
of the subarray. Maintain indices p and q such that homework.a[lo..p-1] and homework.a[q+1..hi] are
all equal to homework.a[lo], an index i such that homework.a[p..i-1] are all less than homework.a[lo], and
an index j such that homework.a[j+1..q] are all greater than homework.a[lo]. Add to the inner
partitioning loop code to swap homework.a[i] with homework.a[p] (and increment p) if it is equal
to v and to swap homework.a[j] with homework.a[q] (and decrement q) if it is equal to v before the
usual comparisons of homework.a[i] and homework.a[j] with v. After the partitioning loop has
terminated, add code to swap the items with equal keys into position.

Note : This code complements the code given in the text, in the sense that it
does extra swaps for keys equal to the partitioning item’s key, while the code
in the text does extra swaps for keys that are not equal to the partitioning
item’s key.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import utils.SortCompare;

public class E20322FastThreePartition {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
//        sort2(homework.a, 0, homework.a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int i = lo, j = hi + 1, p = lo + 1, q = hi;
        Comparable v = a[lo];

        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;

            if (i == j) {
                exch(a, i, p++);
                break;
            }

            if (i > j) break;

            exch(a, i, j);

            if (eq(a[i], v)) exch(a, i, p++);
            if (eq(a[j], v)) exch(a, j, q--);
        }

        for (int k = lo; k < p; ) exch(a, k++, j--);
        for (int k = hi; k > q; ) exch(a, k--, i++);

        sort(a, lo, j);
        sort(a, i, hi);
    }

    private static void sort2(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int i = lo, j = hi + 1, p = lo + 1, q = hi;
        Comparable v = a[lo];

        while (true) {
            while (true) {
                int ival = a[++i].compareTo(v);
                if (i > j || i >= hi) break;
                if (ival == 0) exch(a, i, p++);
                else if (ival > 0) break;
            }
            while (true) {
                int jval = a[--j].compareTo(v);
                if (i > j || j <= lo) break;
                if (jval == 0) exch(a, j, q--);
                else if (jval < 0) break;
            }

            if (i >= j) break;

            exch(a, i, j);
        }
        for (int k = lo; k < p; ) exch(a, k++, j--);
        for (int k = hi; k > q; ) exch(a, k--, i++);

        sort2(a, lo, j);
        sort2(a, i, hi);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static boolean eq(Comparable v, Comparable w) {
        return v.compareTo(w) == 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void show(Comparable[] a) {
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


    public static double timeTrial(String alg1, int N, int T) {
        return SortCompare.timeRandomInput(alg1, N, T);
    }

    public static void main(String[] args) {
////        Comparable[] homework.a = ToolsForArray.RandomComIntArray(1000, 20);
//        Integer[] homework.a = {5, 3, 1, 4, 4, 5, 1, 5, 7, 6, 5, 0};
//        sort(homework.a);
//        assert isSorted(homework.a);
//        show(homework.a);


        int T = 1, N = 128;

        double timeOld = SortCompare.timeRandomInput("E20322FastThreePartition", N, T), timeNew;
        StdOut.println("size\t|\ttime\t|\tratio");

        for (N += N; true; N += N) {
            timeNew = SortCompare.timeRandomInput("E20322FastThreePartition", N, T);
            StdOut.printf("%d\t|\t%.3f\t|\t%.3f\n", N, timeNew, timeNew / timeOld);
            timeOld = timeNew;
        }
    }
}
