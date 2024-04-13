package exercise.ch2.topic2;

import edu.princeton.cs.algs4.StdOut;

public class E20205 {
    public static void bottomUpMerge(Comparable[] a) {
        int n = a.length;
        for (int sz = 1; sz < n; sz += sz) {
            for (int lo = 0; lo < n - sz; lo += sz + sz) {
                merge(lo, Math.min(lo + sz + sz - 1, n - 1));
            }
        }
    }

    public static void topDownMerge(Comparable[] a) {
        topDownMerge(0, a.length - 1);
    }

    private static void topDownMerge(int lo, int hi) {
        if (hi <= lo) return;
        int mid = (hi + lo) / 2;
        topDownMerge(lo, mid);
        topDownMerge(mid + 1, hi);
        merge(lo, hi);
    }

    private static void merge(int lo, int hi) {
        StdOut.printf("{%d, %d}\n", lo, hi);
    }


    public static void main(String[] args) {
        Double[] a = new Double[39];
        for (int i = 0; i < 39; i++) {
            a[i] = i + 0.0;
        }
        StdOut.println("topDownMerge: ");
        topDownMerge(a);
        StdOut.println();
        StdOut.println("bottomUpMerge: ");
        bottomUpMerge(a);
    }
}
