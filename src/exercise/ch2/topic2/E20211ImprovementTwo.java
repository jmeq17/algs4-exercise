package exercise.ch2.topic2;

/*
Improvements. Test whether the array is already in order.
 */

import edu.princeton.cs.algs4.StdOut;
import utils.CreateArrayUtils;

public class E20211ImprovementTwo {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = a.clone();
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi == lo) return;

        int mid = (hi + lo) / 2;

        sort(a, lo, mid);
        sort(a, mid + 1, hi);

        // Improve Two.
        if (less(a[mid + 1], a[mid])) return;

        merge(a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        if (hi + 1 - lo >= 0) System.arraycopy(a, lo, aux, lo, hi + 1 - lo);

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    public static void insertionSort(Comparable[] a, int lo, int hi) {
        int N = hi - lo + 1;

        for (int i = lo + 1; i < N; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
        }
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
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
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
        Comparable[] a = CreateArrayUtils.RandomComDoubleArray(1000);
        sort(a);
        show(a);
    }
}
