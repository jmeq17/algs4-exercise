package homework.partI.week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {
    private final LineSegment[] lines;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (isIllegal(points)) throw new IllegalArgumentException();

        Queue<LineSegment> queue = new Queue<>();

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) && points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])) {
                            Point[] tem = {points[i], points[j], points[k], points[l]};
                            Arrays.sort(tem);
                            queue.enqueue(new LineSegment(tem[0], tem[3]));
                        }
                    }
                }
            }
        }
        lines = new LineSegment[queue.size()];
        int i = 0;
        for (LineSegment line : queue) lines[i++] = line;
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


        for (int i = 0; i < 100; i++) {
            BruteCollinearPoints collinear = new BruteCollinearPoints(points);
            LineSegment[] a = collinear.segments();
            StdOut.println(collinear.numberOfSegments());
            for (LineSegment segment : a) {
                StdOut.println(segment);
//                segment.draw();
            }
            StdOut.println();
        }
//        StdDraw.show();
    }
}
