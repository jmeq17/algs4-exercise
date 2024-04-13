package exercise.ch1.topic1;

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

public class E10122RecursionBinarySearch_2 {

    public static int rank(int key, int[] a) {
        int count = 0;
        return rank(key, a, 0, a.length - 1, ++count);
    }

    public static int rank(int key, int[] a, int lo, int hi, int count) {
        for (int i = 0; i < count; i++) {
            StdOut.print("\t");
        }
        StdOut.println("lo: " + lo + ", " + "hi: " + hi);

        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) return rank(key, a, lo, mid - 1, ++count);
        else if (key > a[mid]) return rank(key, a, mid + 1, hi, ++count);
        else return mid;
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
            if (rank(key, allowlist) == -1) StdOut.println(key);
            StdOut.println();
        }
    }
}
