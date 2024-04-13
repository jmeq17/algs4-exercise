package ch2.topic2;

/*
Suppose that Algorithm 2.4 is modified to skip the call on merge() whenever
homework.a[mid] <= homework.a[mid+1]. Prove that the number of compares used to mergesort homework.a sorted
array is linear.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class E20208AdvancedMerge {
    private static Comparable[] aux;

    public static double sort(Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        int n = a.length;
        aux = new Comparable[n];
        sort(a, 0, a.length - 1);
        return timer.elapsedTime();
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

        if (less(a[mid], a[j])) {
            return;
        }

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
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

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static double timeTrial(int N) {
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++) {
            a[i] = i;
        }
        return sort(a);
    }


    public static void main(String[] args) {
        int N = 10000;

        double timeOld = timeTrial(N);
        StdOut.println("Array size\t\t\ttime\t\t\tratio");

        for (N += N; true; N += N) {
            double timeNew = timeTrial(N);
            StdOut.printf("%d\t\t\t%.1f\t\t\t%.1f\n", N, timeNew, timeNew / timeOld);
        }
    }
}
