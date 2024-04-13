package utils;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BinarySearch {
    public static int binarySearch(Comparable[] a, Comparable key) {
        Arrays.sort(a);
        return indexOf(a, 0, a.length - 1, key);
    }

    private static int indexOf(Comparable[] a, int lo, int hi, Comparable key) {
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int tem = key.compareTo(a[mid]);

            if (tem < 0) hi = mid - 1;
            else if (tem > 0) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    // Tools.Recursion
    private static int Rank(Comparable[] a, int lo, int hi, Comparable key) {
        if (hi < lo) return -1;

        int mid = (lo + hi) / 2;
        int tem = key.compareTo(a[mid]);

        if (tem < 0) return Rank(a, lo, mid - 1, key);
        if (tem > 0) return Rank(a, mid + 1, hi, key);
        return mid;
    }


    public static void main(String[] args) {
        Comparable[] a = CreateArrayUtils.RandomComIntArray(20, 10);
        Arrays.sort(a);
        for (Comparable i : a) StdOut.print(i + " ");

        StdOut.print("\n" + binarySearch(a, 4));
    }
}
