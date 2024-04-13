package exercise.ch1.topic2;

/*
Write homework.a Point2D client that takes an integer value N from the command line,
generates N random points in the unit square, and computes the distance separating
the closest pair of points.
 */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import static java.lang.Integer.parseInt;

public class E10201 {
    public static void main(String[] args) {
        int N = parseInt(args[0]);
        Point2D[] p = new Point2D[N];

        for (int i = 0; i < N; i++) {
            Point2D point = new Point2D(StdRandom.uniform(), StdRandom.uniform());
            p[i] = point;
            point.draw();
        }

        double minDistance = p[0].distanceTo(p[1]);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double i1 = p[i].distanceTo(p[j]);
                if (i1 < minDistance) minDistance = i1;
            }
        }

        StdOut.println(minDistance);
    }
}
