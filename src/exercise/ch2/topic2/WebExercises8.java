package exercise.ch2.topic2;

/*
Merging two arrays of different lengths. Given two sorted arrays homework.a[] and b[] of sizes M and N where M ≥ N,
devise an algorithm to merge them into homework.a new sorted array c[] using ~ N lg M compares.

Hint: use binary search.

Note: there is homework.a lower bound of Omega(N log (1 + M/N)) compares. This follows because there are M+N choose N
possible merged outcomes. A decision tree argument shows that this requires at least lg (M+N choose N) compares.
We note that n choose r >= (n/r)^r.
 */

import edu.princeton.cs.algs4.StdOut;
import utils.Merge;

public class WebExercises8 {
    public static void mergeTwoSortedArrays(Comparable[] a, Comparable[] b, Comparable[] c) {
        int M = a.length;
        int N = b.length;

        int i = 0, j = 0;

        for (int k = 0; k < M + N; k++) {
            if (i > M - 1) c[k] = b[j++];
            else if (j > N - 1) c[k] = a[i++];
            else if (less(a[i], b[j])) c[k] = a[i++];
            else c[k] = b[j++];
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    public static void main(String[] args) {
        Integer[] a = {3, 5, 2, 76, 45, 97, 4, 21};
        Integer[] b = {7, 6, 8, 0, 56, 34, 133, 987, 11};

        Merge.sort(a);
        Merge.sort(b);

        Integer[] c = new Integer[a.length + b.length];

        mergeTwoSortedArrays(a, b, c);
        for (Integer integer : c) {
            StdOut.print(integer + " ");
        }
    }
}
