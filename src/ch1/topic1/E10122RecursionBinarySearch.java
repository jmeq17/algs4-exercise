package ch1.topic1;

/*
Write homework.a version of BinarySearch that uses the recursive rank() given on page
25 and traces the method calls. Each time the recursive method is called, print the argument
values lo and hi, indented by the depth of the recursion. Hint: Add an argument
to the recursive method that keeps track of the depth.
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class E10122RecursionBinarySearch {

    public static int[] rank(int key, int[] a) {
        int[] ret = new int[2];
        int count = 0;
        return rank(key, a, 0, a.length - 1, count, ret);
    }

    public static int[] rank(int key, int[] a, int lo, int hi, int count, int[] ret) {
        count++;
        StdOut.println("lo: " + lo + ", " + "hi: " + hi);
        // Index of key in homework.a[], if present, is not smaller than lo
        // and not larger than hi.
        if (lo > hi) {
            ret[0] = count;
            ret[1] = -1;
            return ret;
        }
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) return rank(key, a, lo, mid - 1, count, ret);
        else if (key > a[mid]) return rank(key, a, mid + 1, hi, count, ret);
        else {
            ret[0] = count;
            ret[1] = mid;
            return ret;
        }
    }

    public static void main(String[] args) {

        // read the integers from homework.a file
        In in = new In(args[0]);
        int[] allowlist = in.readAllInts();

        // sort the array
        Arrays.sort(allowlist);

        // read integer key from standard input; print if not in allowlist
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            int[] ret = rank(key, allowlist);
            StdOut.println("recursive depth: " + ret[0]);
            if (ret[1] == -1)
                StdOut.println(key);
            StdOut.println();
        }
    }
}
