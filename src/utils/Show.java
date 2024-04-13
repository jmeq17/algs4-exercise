package utils;

import edu.princeton.cs.algs4.StdOut;

public class Show {
    // Show homework.a int[] array.
    public static void show(int[] a) {
        for (int j : a) {
            StdOut.print(j + " ");
        }
        StdOut.println();
    }

    // Show homework.a Comparable[] array.
    public static <T> void show(Comparable<T>[] a) {
        for (Comparable<T> j : a) {
            StdOut.print(j + " ");
        }
        StdOut.println();
    }
}
