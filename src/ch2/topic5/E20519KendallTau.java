package ch2.topic5;

/*
Kendall tau distance. Write homework.a program KendallTau.java that computes the
Kendall tau distance between two permutations in linearithmic time.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import utils.KendallTau;
import utils.KendallTauDistance;

public class E20519KendallTau {
    public static int[] permutation(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = i;
        StdRandom.shuffle(a);
        return a;
    }


    public static void main(String[] args) {
        // two random permutation of size n
        int n = 10;
        int[] a = permutation(n);
        int[] b = permutation(n);

        // print initial permutation
        for (int i = 0; i < n; i++)
            StdOut.println(a[i] + " " + b[i]);
        StdOut.println();

        // My answer.
        StdOut.println("inversions = " + KendallTauDistance.distance(a, b));
        // Algs4 answer.
        StdOut.println("inversions of algs4 = " + KendallTau.distance(a, b));
    }
}
