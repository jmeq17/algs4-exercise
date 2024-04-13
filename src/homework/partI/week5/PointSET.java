package homework.partI.week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Queue;

public class PointSET {
    private final SET<Point2D> set;

    // construct an empty set of points
    public PointSET() {
        set = new SET<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return set.isEmpty();
    }

    // number of points in the set
    public int size() {
        return set.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (!set.contains(p)) set.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return set.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D point : set) {
            point.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        double xmin = rect.xmin();
        double xmax = rect.xmax();
        double ymin = rect.ymin();
        double ymax = rect.ymax();
        Queue<Point2D> queue = new Queue<>();
        for (Point2D point : set) {
            double x = point.x();
            double y = point.y();
            if (x >= xmin && x <= xmax && y >= ymin && y <= ymax) {
                queue.enqueue(point);
            }
        }
        return queue;
    }

    // homework.a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (set.isEmpty()) return null;

        Point2D nearest = new Point2D(1, 1);
        double disc = 2;
        for (Point2D point : set) {
            double tem = point.distanceSquaredTo(p);
            if (tem < disc) {
                nearest = point;
                disc = tem;
            }
        }
        return nearest;
    }
}
