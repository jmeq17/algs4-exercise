package homework.partI.week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    private final LineSegment[] lines;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (isIllegal(points)) throw new IllegalArgumentException();

        // does not mutate the constructor argument.
        points = points.clone();
        Arrays.sort(points);

        /**
         * Three implement methods
         */
        // One
//        Queue<HW.PartI.Week3.Point> queue = new Queue<>();
//
//        for (int i = 0; i < points.length; i++) {
//            HW.PartI.Week3.Point[] tem = new HW.PartI.Week3.Point[points.length - i - 1];
//            for (int j = i + 1; j < points.length; j++) tem[j - i - 1] = points[j];
//
//            Comparator<HW.PartI.Week3.Point> com = points[i].slopeOrder();
//            Arrays.sort(tem, com);
//
//            int sum = 0;
//            for (int k = 0; k < tem.length; k++) {
//                if (k + 1 != tem.length && points[i].slopeTo(tem[k]) == points[i].slopeTo(tem[k + 1]))
//                    sum++;
//                else {
//                    if (sum >= 2) {
//                        queue.enqueue(tem[k]);
//                        queue.enqueue(points[i]);
//                    }
//                    sum = 0;
//                }
//            }
//        }
//
//        int i = 0;
//        HW.PartI.Week3.Point[] tem = new HW.PartI.Week3.Point[queue.size()];
//        for (HW.PartI.Week3.Point point : queue) tem[i++] = point;
//        int N = tem.length / 2;
//
//        for (i = 0; i < tem.length; i += 2) {
//            if (tem[i] != null) {
//                for (int j = i + 2; j < tem.length; j += 2) {
//                    if (tem[i] == tem[j]) {
//                        if (tem[i + 1].slopeTo(tem[i]) == tem[j + 1].slopeTo(tem[j])) {
//                            tem[j + 1] = null;
//                            tem[j] = null;
//                            N--;
//                        }
//                    }
//                }
//            }
//        }
//
//        lines = new HW.PartI.Week3.LineSegment[N];
//        int j = 0;
//        for (i = 0; i < tem.length; i += 2)
//            if (tem[i] != null)
//                lines[j++] = new HW.PartI.Week3.LineSegment(tem[i + 1], tem[i]);
        // ---------------------------------------
        // ---------------------------------------

        // Two
//        Queue<HW.PartI.Week3.LineSegment> queue = new Queue<>();
//
//        for (int i = 0; i < points.length; i++) {
//            HW.PartI.Week3.Point[] tem = new HW.PartI.Week3.Point[points.length - i - 1];
//            for (int j = i + 1; j < points.length; j++) tem[j - i - 1] = points[j];
//
//            Comparator<HW.PartI.Week3.Point> com = points[i].slopeOrder();
//            Arrays.sort(tem, com);
//
//            int sum = 0;
//            for (int k = 0; k < tem.length; k++) {
//                if (k + 1 != tem.length && points[i].slopeTo(tem[k]) == points[i].slopeTo(tem[k + 1]))
//                    sum++;
//                else {
//                    if (sum >= 2) {
//                        HW.PartI.Week3.Point[] tem2 = new HW.PartI.Week3.Point[sum + 1];
//                        for (int j = 0; j < sum + 1; j++) {
//                            tem2[j] = tem[k - j];
//                        }
//                        Arrays.sort(tem2);
//                        if (points[i].compareTo(tem2[0]) < 0)
//                            queue.enqueue(new HW.PartI.Week3.LineSegment(points[i], tem[k]));
//                    }
//                    sum = 0;
//                }
//            }
//        }
//
//        lines = new HW.PartI.Week3.LineSegment[queue.size()];
//        int i = 0;
//        for (HW.PartI.Week3.LineSegment line : queue) lines[i++] = line;
        // ---------------------------------------
        // ---------------------------------------

        // Three
        Queue<LineSegment> queue = new Queue<>();

        for (int i = 0; i < points.length; i++) {
            Point[] tem = new Point[points.length - 1];
            for (int j = 0; j < points.length - 1; j++) {
                if (j < i) tem[j] = points[j];
                else tem[j] = points[j + 1];
            }

            Comparator<Point> com = points[i].slopeOrder();
            Arrays.sort(tem, com);

            int sum = 0;
            for (int k = 0; k < tem.length; k++) {
                if (k + 1 != tem.length && points[i].slopeTo(tem[k]) == points[i].slopeTo(tem[k + 1]))
                    sum++;
                else {
                    if (sum >= 2) {
                        Point[] tem2 = new Point[sum + 1];
                        for (int j = 0; j < sum + 1; j++) {
                            tem2[j] = tem[k - j];
                        }
                        Arrays.sort(tem2);
                        if (points[i].compareTo(tem2[0]) < 0)
                            queue.enqueue(new LineSegment(points[i], tem[k]));
                    }
                    sum = 0;
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        LineSegment[] a = collinear.segments();
        for (LineSegment segment : a) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
//
//        StdOut.println();
//
//        homework.a[3] = new HW.PartI.Week3.LineSegment(new HW.PartI.Week3.Point(4, 5), new HW.PartI.Week3.Point(77, 0));
//        HW.PartI.Week3.LineSegment[] b = collinear.segments();
//
//        for (HW.PartI.Week3.LineSegment segment : b) {
//            StdOut.println(segment);
//            segment.draw();
//        }
    }
}
