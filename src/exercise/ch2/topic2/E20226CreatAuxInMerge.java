package exercise.ch2.topic2;

/*
Array creation. Use SortCompare to get homework.a rough idea of the effect on performance
on your machine of creating aux[] in merge() rather than in sort().
 */

import edu.princeton.cs.algs4.StdOut;
import utils.SortCompare;

public class E20226CreatAuxInMerge {

    public static void sort(Comparable[] a) {
        int n = a.length;
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
        int i = 0, j = mid - lo + 1;

        Comparable[] aux = new Comparable[hi - lo + 1];

        if (hi + 1 - lo >= 0) System.arraycopy(a, lo, aux, 0, hi + 1 - lo);

        for (int k = lo; k <= hi; k++) {
            if (i > mid - lo) a[k] = aux[j++];
            else if (j > hi - lo) a[k] = aux[i++];
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

    private static void show(Comparable[] a) {
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

    public static void main(String[] args) {
        String alg1 = "E20209NonStaticArray";
        String alg2 = "E20226CreatAuxInMerge";

        int N = 10000;
        int T = 100;

        double t1 = SortCompare.timeRandomInput(alg1, N, T);
        double t2 = SortCompare.timeRandomInput(alg2, N, T);

        StdOut.printf("%s sort 用时为 %.4f\n", alg1, t1);
        StdOut.printf("%s sort 用时为 %.4f\n", alg2, t2);
        StdOut.printf("%s is %.1f times faster than %s", alg1, t2 / t1, alg2);
    }
}
