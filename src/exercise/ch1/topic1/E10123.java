package exercise.ch1.topic1;

/*
Add to the BinarySearch test client the ability to respond to homework.a second argument:
+ to print numbers from standard input that are not in the whitelist, - to print
numbers that are in the whitelist.
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class E10123 {

    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);
        if (args[1].equals("+")) {
            while (!StdIn.isEmpty()) {
                int key = StdIn.readInt();
                if (rank(key, whitelist) == -1)
                    StdOut.println(key);
            }
        } else if (args[1].equals("-")) {
            while (!StdIn.isEmpty()) {
                int key = StdIn.readInt();
                if (rank(key, whitelist) != -1)
                    StdOut.println(key);
            }
        }
    }
}
