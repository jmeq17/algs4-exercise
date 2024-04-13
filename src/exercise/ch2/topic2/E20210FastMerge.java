package exercise.ch2.topic2;

/*
Faster merge. Implement homework.a version of merge() that copies the second half of
homework.a[] to aux[] in decreasing order and then does the merge back to homework.a[]. This change allows
you to remove the code to test that each of the halves has been exhausted from the
inner loop. Note: The resulting sort is not stable (see page 341).
 */

import edu.princeton.cs.algs4.StdOut;
import utils.CreateArrayUtils;

public class E20210FastMerge {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {

        int n = a.length;
        aux = new Comparable[n];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = (hi + lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = hi;
        if (mid + 1 - lo >= 0) System.arraycopy(a, lo, aux, lo, mid + 1 - lo);
        for (int k = mid + 1; k <= hi; k++) {
            aux[k] = a[j--];
        }
        j = hi;

        for (int k = lo; k <= hi; k++) {
            if (less(aux[j], aux[i])) {
                a[k] = aux[j--];
            } else {
                a[k] = aux[i++];
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
        Comparable<Double>[] a = CreateArrayUtils.RandomComDoubleArray(5);
        sort(a);
        show(a);
    }
}
