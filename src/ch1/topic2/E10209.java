package ch1.topic2;

/*
Instrument BinarySearch (page 47) to use homework.a Counter to count the total number
of keys examined during all searches and then print the total after all searches are complete.
Hint : Create homework.a Counter in main() and pass it as an argument to rank().
 */

import utils.Counter;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class E10209 {
    public static int rank(int key, int[] a, Counter count) {
        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi) {
            count.increment();
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] whitelist = new In(args[0]).readAllInts();
        Arrays.sort(whitelist);

        Counter count = new Counter("count");

        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (rank(key, whitelist, count) == -1)
                StdOut.println(key);
        }
        StdOut.println("The count is: " + count);
    }
}
