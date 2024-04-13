package utils;

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Point2D {
    private final double x;
    private final double y;
    private final double r;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
        this.r = Math.sqrt(x * x + y * x);
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double r() {
        return r;
    }

    public double theta() {
        return Math.atan2(y, x);
    }

    public double thetaTo(Point2D that) {
        return Math.atan2(y - that.y, x - that.x);
    }

    public double distanceTo(Point2D that) {
        return Math.sqrt((this.x - that.x) * (this.x - that.x) + (this.y - that.y) * (this.y - that.y));
    }

    public void show() {
        StdOut.println(x + ", " + y);
    }

    public void draw() {

    }

    public static class X_Order implements Comparator<Point2D> {
        public int compare(Point2D v, Point2D w) {
            return Double.compare(v.x, w.x);
        }
    }

    public static class Y_Order implements Comparator<Point2D> {
        public int compare(Point2D v, Point2D w) {
            return Double.compare(v.y, w.y);
        }
    }

    public static class R_Order implements Comparator<Point2D> {
        public int compare(Point2D v, Point2D w) {
            return Double.compare(v.r, w.r);
        }
    }

    public Comparator<Point2D> Distance_Order() {
        return new Distance_Order();
    }

    public class Distance_Order implements Comparator<Point2D> {
        public int compare(Point2D a, Point2D b) {
            double distance1 = distanceTo(a);
            double distance2 = distanceTo(b);

            return Double.compare(distance1, distance2);
        }
    }

    public Comparator<Point2D> Polar_Order() {
        return new Polar_Order();
    }

    public class Polar_Order implements Comparator<Point2D> {
        public int compare(Point2D a, Point2D b) {
            double polar1 = Math.atan2(a.y - y, a.x - x);
            double polar2 = Math.atan2(b.y - y, b.x - x);

            return Double.compare(polar1, polar2);
        }
    }
}
