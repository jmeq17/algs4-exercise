package homework.partI.week3;

import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPointsRAW {
    private final LineSegment[] lines;

    // finds all line segments containing 4 or more points
    public FastCollinearPointsRAW(Point[] points) {
        if (isIllegal(points)) throw new IllegalArgumentException();

        Queue<LineSegment> queue = new Queue<>();

        Arrays.sort(points);
        for (int i = 0; i < points.length; i++) {
            Point[] tem = new Point[points.length - i - 1];
            for (int j = i + 1, k = 0; j < points.length; j++) {
                tem[k++] = points[j];
            }
            Comparator com = points[i].slopeOrder();
            Arrays.sort(tem, com);

            int sum = 0;
            for (int l = 0; l < tem.length; l++) {
                if (l + 1 != tem.length && points[i].slopeTo(tem[l]) == points[i].slopeTo(tem[l + 1]))
                    sum++;
                else {
                    if (sum >= 2)
                        queue.enqueue(new LineSegment(points[i], tem[l]));
                    sum = 0;
                }
            }
        }

        // 会导致重复
        lines = new LineSegment[queue.size()];
        int i = 0;
        for (LineSegment line : queue) lines[i++] = line;
    }

    private boolean isIllegal(Point[] points) {
        if (points == null) return true;
        for (int i = 0; i < points.length; i++)
            for (int j = i + 1; j < points.length; j++)
                if (points[i] == null || points[j] == null || points[i] == points[j])
                    return true;
        return false;
    }

    // the number of line segments
    public int numberOfSegments() {
        return lines.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return lines;
    }
}
