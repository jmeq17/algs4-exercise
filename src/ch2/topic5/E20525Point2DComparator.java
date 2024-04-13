package ch2.topic5;

/*
Points in the plane. Write three static comparators for the Point2D data type
of page 77, one that compares points by their x coordinate, one that compares them by
their y coordinate, and one that compares them by their distance from the origin. Write
two non-static comparators for the Point2D data type, one that compares them by
their distance to homework.a specified point and one that compares them by their polar angle with
respect to homework.a specified point.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import utils.Point2D;

import java.util.Arrays;

public class E20525Point2DComparator {
    public static Point2D[] genPoint2DArray(int n) {
        Point2D[] a = new Point2D[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Point2D(StdRandom.uniform(0.0, 1.0), StdRandom.uniform(0.0, 1.0));
        }
        return a;
    }


    public static void main(String[] args) {
        int n = 10;
        Point2D[] a = genPoint2DArray(n);

        Arrays.sort(a, new Point2D.X_Order());
        for (Point2D i : a) {
            StdOut.println(i.x());
        }
        StdOut.println();

        Arrays.sort(a, new Point2D.Y_Order());
        for (Point2D i : a) {
            StdOut.println(i.y());
        }
        StdOut.println();

        Arrays.sort(a, new Point2D.R_Order());
        for (Point2D i : a) {
            StdOut.println(i.r());
        }
        StdOut.println();

        Point2D p = new Point2D(0.1, 0.2);

        Arrays.sort(a, p.Distance_Order());
        for (Point2D i : a) {
            StdOut.println(i.distanceTo(p));
        }
        StdOut.println();

        Arrays.sort(a, p.Polar_Order());
        for (Point2D i : a) {
            StdOut.println(i.thetaTo(p));
        }
        StdOut.println();
    }
}
