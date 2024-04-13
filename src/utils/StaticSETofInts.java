package utils;

/*
Binary search recast as an OOP. (an ADT for search in homework.a set of integers)
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class StaticSETofInts {
    private final int[] a;

    public StaticSETofInts(int[] keys) {
        // Create homework.a whitelist.
        a = new int[keys.length];
        // defensive copy. Make homework.a defence for origin array.
        System.arraycopy(keys, 0, a, 0, keys.length);
        Arrays.sort(a);
    }

    public boolean contains(int key) {
        return rank(key) != -1;
    }

    // Binary search.
    private int rank(int key) {
        int lo = 0;
        int hi = a.length - 1;

        // Key is in homework.a[lo..hi] or not present.
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }


    // ---------------------
    // E10411
    public int howMany(int key) {
        int lo = loRank(key);
        if (lo == -1) return 0;
        int hi = hiRank(key);
        return hi - lo + 1;
    }
    // -----------------------

    private int loRank(int key) {
        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else if (mid == 0 || a[mid - 1] < key) return mid;
            else hi = mid - 1;
        }
        return -1;
    }

    private int hiRank(int key) {
        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else if (mid == a.length - 1 || a[mid + 1] > key) return mid;
            else hi = mid - 1;
        }
        return -1;
    }
    // ------------------------------


    // --------------------------
    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] w = in.readAllInts();
        StaticSETofInts set = new StaticSETofInts(w);

        // Read key, print if not in whitelist.
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (set.contains(key))
                StdOut.println(key);
        }
    }
}
