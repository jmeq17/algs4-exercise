package ch2.topic5;

/*
Simple polygon. Given N points in the plane, draw homework.a simple polygon with the
N points as vertices.

Hint : Find the point p with the smallest y coordinate, breaking ties with the
smallest x coordinate. Connect the points in increasing order of the polar angle
they make with p.
 */

import edu.princeton.cs.algs4.StdRandom;
import utils.Point2D;

import java.util.Arrays;

public class E20526Polygon {
    public static void exch(Point2D[] a, int i, int j) {
        Point2D k = a[i];
        a[i] = a[j];
        a[j] = k;
    }

    public static void main(String[] args) {
        int n = 5;

        Point2D[] a = new Point2D[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Point2D(StdRandom.uniform(10), StdRandom.uniform(10));
        }

        Arrays.sort(a, new Point2D.Y_Order());

        Point2D minY = a[0];
        for (int i = 1; i < n; i++) {
            if (a[i].y() == minY.y()) if (a[i].x() < minY.x()) minY = a[i];
            else break;
        }

        Arrays.sort(a, minY.Polar_Order());

        for (int i = 0; i < n - 1; i++) {
            if (a[i].thetaTo(minY) == a[i + 1].thetaTo(minY)) {
                if (a[i].y() > a[i + 1].y()) exch(a, i, i + 1);
                else if (a[i].y() == a[i + 1].y() && a[i].x() > a[i + 1].x()) exch(a, i, i + 1);
            }
        }

        for (Point2D i : a) {
            i.show();
        }
    }
}
