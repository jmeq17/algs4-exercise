package ch2.topic3;

/*
Best case. Write homework.a program that produces homework.a best-case array (with no duplicates)
for sort() in Algorithm 2.5: an array of N items with distinct keys having the property
that every partition will produce subarrays that differ in size by at most 1 (the same
subarray sizes that would happen for an array of N equal keys). (For the purposes of this
exercise, ignore the initial shuffle.)
 */

import edu.princeton.cs.algs4.StdOut;

public class E20316BestCase {
    public static void produceBestCaseArray(Comparable[] a) {
        produceBestCaseArray(a, 0, (a.length - 1) / 2, a.length - 1);
    }

    public static void produceBestCaseArray(Comparable[] a, int lo, int mid, int hi) {
        if (hi <= lo) return;

        produceBestCaseArray(a, lo, (lo + mid - 1) / 2, mid - 1);
        produceBestCaseArray(a, mid + 1, (hi + mid + 1) / 2, hi);
        exch(a, lo, mid);
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


    public static void main(String[] args) {
        Comparable[] sortedArray = "abcdefghijklmnopqrstuvwxyz".split("");

        produceBestCaseArray(sortedArray);
        show(sortedArray);
    }
}
