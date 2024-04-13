package homework.partI.week3;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    // constructs the point (x, y)
    public Point(int x, int y) {
        if (x < 0 || x > 32767) throw new IllegalArgumentException();
        if (y < 0 || y > 32767) throw new IllegalArgumentException();

        this.x = x;
        this.y = y;
    }

    // draws this point
    public void draw() {
        StdDraw.point(x, y);
    }

    // draws the line segment from this point to that point
    public void drawTo(Point that) {
        StdDraw.line(x, y, that.x, that.y);
    }

    // string representation
    public String toString() {
        return "(" + x + ", " + y + ")";
//        return "x = " + x + ", y = " + y;
    }

    // compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y < that.y) return -1;
        if (this.y == that.y && this.x < that.x) return -1;
        if (this.y == that.y && this.x == that.x) return 0;
        return 1;
    }

    // the slope between this point and that point
    public double slopeTo(Point that) {
        if (this.x == that.x && this.y == that.y) return Double.NEGATIVE_INFINITY;
        if (this.x == that.x) return Double.POSITIVE_INFINITY;
        if (this.y == that.y) return 0.0;

        return (1.0 * this.y - that.y) / (this.x - that.x);
    }

    // compare two points by slopes they make with this point
    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {

            public int compare(Point o1, Point o2) {
                double slope1 = slopeTo(o1);
                double slope2 = slopeTo(o2);
                return Double.compare(slope1, slope2);
            }
        };
    }

    public static void main(String[] args) {
        Point p = new Point(7, 7);
        Point q = new Point(3, 6);
        Point r = new Point(4, 7);

        Comparator<Point> com = p.slopeOrder();
        StdOut.println(com.compare(q, r));
        StdOut.println(p.slopeTo(q));
        StdOut.println(p.slopeTo(r));
    }
}
