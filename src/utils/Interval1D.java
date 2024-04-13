package utils;

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Interval1D {
    private final double left;
    private final double right;

    public Interval1D(double left, double right) {
        if (left < right) {
            this.left = left;
            this.right = right;
        } else {
            this.left = right;
            this.right = left;
        }
    }

    public double left() {
        return left;
    }

    public double right() {
        return right;
    }

    public double length() {
        return right - left;
    }

    public boolean contains(double x) {
        return x >= left || x <= right;
    }

    public void show() {
        StdOut.println("[" + left + ", " + right + "], length = " + length());
    }

    public boolean intersects(Interval1D that) {
        return that.left - this.right <= 0 || this.left - that.right <= 0;
    }

    public static class Left_Order implements Comparator<Interval1D> {
        public int compare(Interval1D v, Interval1D w) {
            return Double.compare(v.left, w.left);
        }
    }

    public static class Right_Order implements Comparator<Interval1D> {
        public int compare(Interval1D v, Interval1D w) {
            return Double.compare(v.right, w.right);
        }
    }

    public static class Length_Order implements Comparator<Interval1D> {
        public int compare(Interval1D v, Interval1D w) {
            return Double.compare(v.length(), w.length());
        }
    }
}
