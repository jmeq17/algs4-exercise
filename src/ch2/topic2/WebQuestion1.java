package ch2.topic2;

/*
Question 1
Merging with smaller auxiliary array. Suppose that the subarray homework.a[0] to homework.a[n−1] is sorted and the subarray
homework.a[n] to homework.a[2∗n−1] is sorted. How can you merge the two subarrays so that homework.a[0] to homework.a[2∗n−1] is sorted using
an auxiliary array of length n (instead of 2n)?
 */

import edu.princeton.cs.algs4.StdOut;

public class WebQuestion1 {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void show(Comparable[] a) {
        for (Comparable comparable : a) {
            StdOut.print(comparable + " ");
        }
        StdOut.println();
    }

    public static void mergePub(Comparable[] a, int lo, int mid, int hi) {
        Comparable[] aux = new Comparable[mid + 1];
        int i = lo, j = mid + 1;

        for (int k = lo; k <= mid; k++) aux[k] = a[k];

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = a[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(a[j], aux[i])) {
                a[k] = a[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }


    public static void main(String[] args) {
        Integer[] a = {1, 3, 5, 7, 9, 6, 8, 10, 12};
        mergePub(a, 0, (a.length - 1) / 2, a.length - 1);
        show(a);
    }
}
