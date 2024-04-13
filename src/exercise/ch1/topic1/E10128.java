package exercise.ch1.topic1;

/*
Remove duplicates. Modify the test client in BinarySearch to remove any duplicate
keys in the whitelist after the sort.
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class E10128 {

    public static int[] rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else {
                StdOut.println(a[mid]);
                return delItems(a, mid);
            }
        }
        return a;
    }

    public static int[] delItems(int[] a, int key) {
        int[] newA = new int[a.length - 1];
        for (int i = 0; i < key; i++) {
            newA[i] = a[i];
        }
        for (int i = key + 1; i < a.length; i++) {
            newA[i-1] = a[i];
        }
        return newA;
    }

    public static void main(String[] args) {
        int[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            whitelist = rank(key, whitelist);
        }
    }
}
