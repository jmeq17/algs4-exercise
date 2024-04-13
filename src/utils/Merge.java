package utils;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Merge {
    private static Comparable[] aux;

    // E20206--------------
    private static int count;

    public static void draw() {
        StdDraw.point(aux.length, count);
        count = 0;
    }
    // -----------------

    // E20207------------------
    public static int compreNum;

    public static int printCompare() {
        return compreNum;
    }
    // -----------------

    public static void sort(Comparable[] a) {
        int n = a.length;
        aux = new Comparable[n];
        sort(a, 0, n - 1);
    }

    // 排序代码
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = (hi + lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];

//             -----------------
//            count += 2;
//             -----------------
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }

            // -----------------
//            count += 2;
            // -----------------
        }
    }


    // 比较
    // 如果 v 小于 w，返回 true
    private static boolean less(Comparable v, Comparable w) {
        // -----------------
//        count += 2;
        // -----------------

        // -----------------
//        compreNum++;
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
