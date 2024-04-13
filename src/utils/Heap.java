package utils;

import edu.princeton.cs.algs4.StdOut;

public class Heap {
    public static void sortSwim(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            swim(a, i);
        }
        sort(a);
    }

    public static void sortSink(Comparable[] a) {
        int N = a.length - 1;
        for (int i = N / 2; i >= 0; i--) {
            sink(a, i, N);
        }
        sort(a);
    }

    private static void sort(Comparable[] a) {
        int n = a.length - 1;
        while (n > 0) {
            exch(a, 0, n--);
            sink(a, 0, n);
        }
    }

    private static void swim(Comparable[] a, int i) {
        while (i > 0 && !less(a[i], a[(i - 1) / 2])) {
            exch(a, i, (i - 1) / 2);
            i = i / 2;
        }
    }

    private static void sink(Comparable[] a, int k, int N) {
        while (2 * k + 1 <= N) {
            int j = 2 * k + 1;
            if (j < N && less(a[j], a[j + 1])) j++;
            if (!less(a[k], a[j])) break;
            exch(a, k, j);
            k = j;
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable k = a[i];
        a[i] = a[j];
        a[j] = k;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
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

    public static void main(String[] args) {
        Integer[] a = {2, 3, 6, 2, 5, 1, 8, 9, 4, 5};
        sortSink(a);
//        sortSwim(homework.a);
        show(a);
    }
}
