package ch2.topic1;

/*
Implement homework.a version of shellsort that keeps the increment sequence in an array,
rather than computing it.
 */

import edu.princeton.cs.algs4.StdOut;

public class E20111ShellSortForArray {
    // 排序代码
    public static void sort(Comparable[] a) {

        int N = a.length;
        int[] seq = new int[N];
        int i = 0, h = 1;
        while (h < N / 3) {
            seq[i++] = h;
            h = 3 * h + 1;
        }
        seq[i] = h;

        for (; i >= 0; i--) {
            int t = seq[i];
            h = t;
            while (t < N) {
                for (int k = t; k >= h && less(a[k], a[k - h]); k -= h)
                    exch(a, k, k - h);
                t++;
            }
        }
    }

    // 比较
    // 如果 v 小于 w，返回 true
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // 交换
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    // debug
    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
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
        String[] a = "DHASKJDHASKJ".split("");
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
