package homework.partI.week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPointsSubmit2 {
    private final LineSegment[] lines;

    // finds all line segments containing 4 or more points
    public FastCollinearPointsSubmit2(Point[] points) {
        if (isIllegal(points)) throw new IllegalArgumentException();

        points = points.clone();
        Arrays.sort(points);

        Queue<Point> queue = new Queue<>();

        for (int i = 0; i < points.length; i++) {
            Point[] tem = new Point[points.length - i - 1];
            for (int j = i + 1; j < points.length; j++) tem[j - i - 1] = points[j];

            Comparator<Point> com = points[i].slopeOrder();
            Arrays.sort(tem, com);

            int sum = 0;
            for (int k = 0; k < tem.length; k++) {
                if (k + 1 != tem.length && points[i].slopeTo(tem[k]) == points[i].slopeTo(tem[k + 1]))
                    sum++;
                else {
                    if (sum >= 2) {
                        queue.enqueue(tem[k]);
                        queue.enqueue(points[i]);
                    }
                    sum = 0;
                }
            }
        }

        int i = 0;
        Point[] tem = new Point[queue.size()];
        for (Point point : queue) tem[i++] = point;
        int N = tem.length / 2;

        for (i = 0; i < tem.length; i += 2) {
            if (tem[i] != null) {
                for (int j = i + 2; j < tem.length; j += 2) {
                    if (tem[i] == tem[j]) {
                        if (tem[i + 1].slopeTo(tem[i]) == tem[j + 1].slopeTo(tem[j])) {
                            tem[j + 1] = null;
                            tem[j] = null;
                            N--;
                        }
                    }
                }
            }
        }

        lines = new LineSegment[N];
        int j = 0;
        for (i = 0; i < tem.length; i += 2)
            if (tem[i] != null)
                lines[j++] = new LineSegment(tem[i + 1], tem[i]);
    }

    private boolean isIllegal(Point[] points) {
        if (points == null) return true;
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) return true;
            for (int j = i + 1; j < points.length; j++)
                if (points[j] == null || points[i].slopeTo(points[j]) == Double.NEGATIVE_INFINITY)
                    return true;
        }
        return false;
    }

    // the number of line segments
    public int numberOfSegments() {
        return lines.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return lines.clone();
    }

    public static void main(String[] args) {

        // read the n points from homework.a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPointsSubmit2 collinear = new FastCollinearPointsSubmit2(points);
        LineSegment[] a = collinear.segments();
        for (LineSegment segment : a) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

        StdOut.println();

        a[3] = new LineSegment(new Point(4, 5), new Point(77, 0));
        LineSegment[] b = collinear.segments();

        for (LineSegment segment : b) {
            StdOut.println(segment);
            segment.draw();
        }
    }
}
