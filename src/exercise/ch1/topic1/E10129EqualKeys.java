package exercise.ch1.topic1;

/*
Equal keys. Add to BinarySearch homework.a static method rank() that takes homework.a key and
homework.a sorted array of int values (some of which may be equal) as arguments and returns the
number of elements that are smaller than the key and homework.a similar method count() that
returns the number of elements equal to the key.

Note : If i and j are the values returned by rank(key, homework.a) and count(key, homework.a) respectively,
then homework.a[i..i+j-1] are the values in the array that are equal to key.
 */

import edu.princeton.cs.algs4.StdOut;

public class E10129EqualKeys {
    public static int rank(int key, int[] a) {
        return ranklo(key, a, 0, a.length - 1);
    }

    public static int count(int key, int[] a) {
        int equlo = ranklo(key, a, 0, a.length - 1);
        int equhi;
        if (equlo == -1) equhi = -1;
        else equhi = rankhi(key, a, equlo, a.length - 1);
        return equhi - equlo + 1;
    }

    private static int ranklo(int key, int[] a, int lo, int hi) {
        if (hi < lo) return -1;

        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) return ranklo(key, a, lo, mid - 1);
        if (key > a[mid]) return ranklo(key, a, mid + 1, hi);

        if (mid == 0 || a[mid - 1] < key) return mid;
        int v = ranklo(key, a, lo, mid - 1);
        if (v == -1) return mid;
        return v;
    }

    private static int rankhi(int key, int[] a, int lo, int hi) {
        if (hi < lo) return -1;

        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) return rankhi(key, a, lo, mid - 1);
        if (key > a[mid]) return rankhi(key, a, mid + 1, hi);

        if (mid == hi || a[mid + 1] > key) return mid;
        int v = rankhi(key, a, mid + 1, hi);
        if (v == -1) return mid;

        return v;
    }

    public static boolean verify(int key, int[] a) {
        int i = rank(key, a);
        int j = count(key, a);

        for (int k = i; k < i + j - 1; k++) {
            if (a[k] != key) return false;
        }

        return true;
    }

    public static void main(String[] args) {

        int[] array = {9, 15, 19, 19, 23, 24, 29, 34, 38, 38, 39, 47, 49, 52, 77, 78, 82, 84, 93};

        for (int i : array) {
            StdOut.println("Rank: " + rank(i, array));
            StdOut.println("Count: " + count(i, array));
            StdOut.println("Verification: " + verify(i, array));
            StdOut.println();
        }
    }
}
