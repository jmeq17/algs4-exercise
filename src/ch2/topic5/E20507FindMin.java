package ch2.topic5;

import edu.princeton.cs.algs4.StdOut;
import utils.CreateArrayUtils;

public class E20507FindMin {
    private static int count = 0;

    public static Comparable select(Comparable[] a) {
        Comparable min = select(a, a.length - 1);
        StdOut.printf("For N = %d, the number of compare is = %d, 2N = %d.\n", a.length, count, 2 * a.length);
        count = 0;
        return min;
    }

    private static Comparable select(Comparable[] a, int hi) {
        if (hi <= 0) return a[0];

        int p = partition(a, hi);
        if (p > 0) return select(a, p - 1);
        return a[p];
    }

    private static int partition(Comparable[] a, int hi) {
        int i = 0, j = hi + 1;
        Comparable v = a[0];

        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) ;

            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, 0, j);
        return j;
    }

    private static boolean less(Comparable i, Comparable j) {
        count++;
        return i.compareTo(j) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable k = a[i];
        a[i] = a[j];
        a[j] = k;
    }


    public static void main(String[] args) {
        for (int N = 10000, i = 0; i < 5; N *= 10, i++) {
            Comparable[] a = CreateArrayUtils.RandomComDoubleArray(N);
            select(a);
        }
    }
}
