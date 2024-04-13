package ch2.topic3;

/*
Nuts and bolts. (G. J. E. Rawlins) You have homework.a mixed pile of N nuts and N bolts
and need to quickly find the corresponding pairs of nuts and bolts. Each nut matches
exactly one bolt, and each bolt matches exactly one nut. By fitting homework.a nut and bolt together,
you can see which is bigger, but it is not possible to directly compare two nuts or
two bolts. Give an efficient method for solving the problem.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import utils.CreateArrayUtils;

public class E20315NutsAndBolts {
    // Source: https://www.geeksforgeeks.org/nuts-bolts-problem-lock-key-problem/
    private static void matchPair(Comparable[] nuts, Comparable[] bolts) {
        StdRandom.shuffle(nuts);
        StdRandom.shuffle(bolts);
        matchPair(nuts, bolts, 0, nuts.length - 1);
    }

    private static void matchPair(Comparable[] nuts, Comparable[] bolts, int lo, int hi) {
        if (lo < hi) {
            int pivot = partition(nuts, lo, hi, bolts[lo]);
            partition(bolts, lo, hi, nuts[pivot]);
            matchPair(nuts, bolts, lo, pivot - 1);
            matchPair(nuts, bolts, pivot + 1, hi);
        }
    }

    private static int partition(Comparable[] a, int lo, int hi, Comparable pivot) {
        int i = lo;

        // Return the partition index of an array based on the pivot
        // element of other array.
        for (int j = lo; j < hi; j++) {
            int temp = a[j].compareTo(pivot);
            if (temp < 0) {
                exch(a, i, j);
                i++;
            } else if (temp == 0) {
                exch(a, j, hi);
                j--;
            }
        }
        exch(a, i, hi);
        return i;
    }

    // ------------------------

    public static void sort(Comparable[] nuts, Comparable[] bolts) {
        StdRandom.shuffle(nuts);
        StdRandom.shuffle(bolts);

        sort(nuts, bolts, 0, nuts.length - 1, 0);
    }

    private static void sort(Comparable[] nuts, Comparable[] bolts, int lo, int hi, int pivot) {
        if (hi <= lo) return;
        int j = partition(nuts, bolts, lo, hi, pivot);
        partition(bolts, nuts, lo, hi, j);
        sort(bolts, nuts, lo, j - 1, lo);
        sort(bolts, nuts, j + 1, hi, j + 1);
    }

    private static int partition(Comparable[] nuts, Comparable[] bolts, int lo, int hi, int pivot) {
        int i = lo, j = lo;
        Comparable v = nuts[pivot];

        while (i < hi) {
            int temp = bolts[i].compareTo(v);
            if (temp < 0) exch(bolts, i, j++);
            else if (temp == 0) {
                exch(bolts, i, hi);
                i--;
            }
            i++;
        }
        exch(bolts, hi, j);
        return j;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void show(Comparable[] nuts, Comparable[] bolts) {
        for (Comparable a : nuts) StdOut.printf("%.5f ", a);
        StdOut.println();
        for (Comparable a : bolts) StdOut.printf("%.5f ", a);
    }


    public static void main(String[] args) {
        int N = 10;
        Comparable[] nuts = CreateArrayUtils.RandomComDoubleArray(N);
        Comparable[] bolts = new Comparable[N];
        int i = 0;
        for (Comparable a : nuts) bolts[i++] = a;

        sort(nuts, bolts);
        show(nuts, bolts);
    }
}
