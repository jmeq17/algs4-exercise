package ch2.topic2;

/*
Inversions. Develop and implement homework.a linearithmic algorithm for computing
the number of inversions in homework.a given array (the number of exchanges that would be
performed by insertion sort for that array—see Section 2.1). This quantity is related
to the Kendall tau distance; see Section 2.5.
 */

import edu.princeton.cs.algs4.StdOut;

public class E20219Inversions {
    private static Comparable[] aux;
    private static int count;

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
        int i = lo, j = mid + 1;
        if (hi + 1 - lo >= 0) System.arraycopy(a, lo, aux, lo, hi + 1 - lo);

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
                count += (mid - i + 1);
            } else a[k] = aux[i++];
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void numOfInversion() {
        StdOut.println(count);
        count = 0;
    }


    public static void main(String[] args) {
        Integer[] a = {5, 4, 3, 2, 1};
        sort(a);
        numOfInversion();
    }
}