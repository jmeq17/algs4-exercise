package exercise.ch2.topic1;

/*
Insertion sort without exchanges. Develop an implementation of insertion sort
that moves larger elements to the right one position with one array access per entry,
rather than using exch(). Use SortCompare to evaluate the effectiveness of doing so.
 */

import edu.princeton.cs.algs4.StdOut;
import utils.SortCompare;

public class E20125InsertionsSortWithoutExchanges {

    public static void sort(Comparable[] a) {
        int N = a.length;

        for (int i = 1; i < N; i++) {
            Comparable item = a[i];
            int j;

            for (j = i; j > 0 && item.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = item;
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }


    public static void main(String[] args) {
        int N = 10000;
        int T = 100;

        String alg1 = "E20125InsertionsSortWithoutExchanges";
        String alg2 = "Insertion";

        double t1 = SortCompare.timeRandomInput(alg1, N, T);
        double t2 = SortCompare.timeRandomInput(alg2, N, T);

        StdOut.printf("%s sort 用时为 %.4f\n", alg1, t1);
        StdOut.printf("%s sort 用时为 %.4f\n", alg2, t2);
        StdOut.printf("%s is %.1f times faster than %s", alg1, t2 / t1, alg2);
    }
}
