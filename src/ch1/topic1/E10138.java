package ch1.topic1;

/*
Binary search versus brute-force search. Write homework.a program BruteForceSearch
that uses the brute-force search method given on page 48 and compare its running time
on your computer with that of BinarySearch for largeW.txt and largeT.txt.
 */

// The tow files are so big. So we only search one number.

import edu.princeton.cs.algs4.*;

import java.util.Arrays;

public class E10138 {

    public static int BruteForceSearch(int key, int[] a) {
        for (int i = 0; i < a.length; i++)
            if (a[i] == key) return i;
        return -1;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] whitelist = in.readAllInts();

        In binary = new In(args[1]);
        In brute = new In(args[2]);

        // BruteForceSearch
        Stopwatch time2 = new Stopwatch();
        while (!brute.isEmpty()) {
            int key = brute.readInt();
            if (BruteForceSearch(key, whitelist) == -1) StdOut.println(key);
        }
        double timer2 = time2.elapsedTime();

        StdOut.println();

        // BinarySearch
        Arrays.sort(whitelist);
        Stopwatch time1 = new Stopwatch();
        while (!binary.isEmpty()) {
            int key = binary.readInt();
            if (BinarySearch.rank(key, whitelist) == -1) StdOut.println(key);
        }
        double timer1 = time1.elapsedTime();

        StdOut.println();
        StdOut.println("BruteForceSearch: " + timer2 + ", " + "BinarySearch: " + timer1);

        // -----------------
        // The tow files are so big. So we only search one number.
        // -----------------

//        int key = 11592252;
//        // BruteForceSearch
//        Stopwatch time3 = new Stopwatch();
//        BruteForceSearch(key, whitelist);
//        double timer3 = time3.elapsedTime();
//
//        StdOut.println();
//
//        // BinarySearch
//        Arrays.sort(whitelist);
//        Stopwatch time4 = new Stopwatch();
//        BinarySearch.rank(key, whitelist);
//        double timer4 = time4.elapsedTime();
//
//        StdOut.println();
//        StdOut.println("BruteForceSearch: " + timer3 + ", " + "BinarySearch: " + timer4);
    }
}
