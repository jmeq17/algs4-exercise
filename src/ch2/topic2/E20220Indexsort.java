package ch2.topic2;

/*
Index sort. Develop and implement homework.a version of mergesort that does not rearrange
the array, but returns an int[] array perm such that perm[i] is the index of the
i th smallest entry in the array.
 */

import utils.CreateArrayUtils;
import edu.princeton.cs.algs4.StdOut;

public class E20220Indexsort {

    public static int[] sort(Comparable[] a) {
        int n = a.length;
        int[] index = CreateArrayUtils.IntSeqArray(n);
        int[] aux = new int[n];
        return sort(a, index, aux, 0, n - 1);
    }

    private static int[] sort(Comparable[] a, int[] index, int[] aux, int lo, int hi) {
        if (hi <= lo) return index;
        int mid = (hi + lo) / 2;
        sort(a, index, aux, lo, mid);
        sort(a, index, aux, mid + 1, hi);
        merge(a, index, aux, lo, mid, hi);
        return index;
    }

    private static void merge(Comparable[] a, int[] index, int[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        if (hi + 1 - lo >= 0) System.arraycopy(index, lo, aux, lo, hi + 1 - lo);

        for (int k = lo; k <= hi; k++) {
            if (i > mid) index[k] = aux[j++];
            else if (j > hi) index[k] = aux[i++];
            else if (less(a[aux[j]], a[aux[i]])) {
                index[k] = aux[j++];
            } else {
                index[k] = aux[i++];
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
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
        Integer[] a = {3, 5, 2, 7, 1, 0, 9, 8};
        int[] index = sort(a);
        for (int i = 0; i < a.length; i++) {
            StdOut.print(index[i] + " ");
        }
        StdOut.println();
        StdOut.println("Answer: 5 4 2 0 1 3 7 6");
    }
}
