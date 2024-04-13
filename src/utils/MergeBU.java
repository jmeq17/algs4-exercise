package utils;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class MergeBU {
    private static Comparable[] aux;

    private static int count;

    // E20206--------------
    public static void draw() {
        StdDraw.point(aux.length, count);
        count = 0;
    }
    // -----------------

    // 排序代码
    public static void sort(Comparable[] a) {
        int n = a.length;
        aux = new Comparable[n];
        for (int sz = 1; sz < n; sz += sz) {
            for (int lo = 0; lo < n - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
            }
        }
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];


            // -----------------
            count += 2;
            // -----------------
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }


        // -----------------
        count += 2;
        // -----------------
    }

    // 比较
    // 如果 v 小于 w，返回 true
    private static boolean less(Comparable v, Comparable w) {
        // -----------------
        count += 2;
        // -----------------

        return v.compareTo(w) < 0;
    }

    // debug
    private static void show(Comparable[] a) {
        for (Comparable comparable : a) {
            StdOut.print(comparable + " ");
        }
        StdOut.println();
    }

    // 是否完成排序
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }


    // client
    public static void main(String[] args) {
        String[] a = new In().readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
