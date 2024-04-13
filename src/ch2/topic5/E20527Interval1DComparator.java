package ch2.topic5;

/*
One-dimensional intervals. Write three comparators for the Interval1D data
type of page 77, one that compares intervals by their left endpoint, one that compares
intervals by their right endpoint, and one that compares intervals by their length.
 */

import utils.Interval1D;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class E20527Interval1DComparator {
    public static void main(String[] args) {
        int n = 5;
        Interval1D[] a = new Interval1D[n];

        for (int i = 0; i < n; i++) {
            a[i] = new Interval1D(StdRandom.uniform(0.0, 1.0), StdRandom.uniform(0.0, 1.0));
            a[i].show();
        }

        StdOut.println("Left:");
        Arrays.sort(a, new Interval1D.Left_Order());
        for (int i = 0; i < n; i++) {
            a[i].show();
        }

        StdOut.println("Right:");
        Arrays.sort(a, new Interval1D.Right_Order());
        for (int i = 0; i < n; i++) {
            a[i].show();
        }

        StdOut.println("Length:");
        Arrays.sort(a, new Interval1D.Length_Order());
        for (int i = 0; i < n; i++) {
            a[i].show();
        }
    }
}
