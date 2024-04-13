package homework.partI.week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
    private static final boolean RED = true;
    private static final boolean LEFT = true;
    private Rect root;

    private static class Rect {
        Point2D p;
        int n;
        RectHV r;
        Rect left, right;
        boolean color;
        double distTo;

        Rect(Point2D p, int n, boolean color, RectHV r, double varX, double varY, boolean isLEFT) {
            this.p = p;
            this.n = n;
            this.color = color;
            if (color) {
                if (isLEFT) this.r = new RectHV(r.xmin(), r.ymin(), r.xmax(), varY);
                else this.r = new RectHV(r.xmin(), varY, r.xmax(), r.ymax());
            } else {
                if (isLEFT) this.r = new RectHV(r.xmin(), r.ymin(), varX, r.ymax());
                else this.r = new RectHV(varX, r.ymin(), r.xmax(), r.ymax());
            }
        }
    }

    public KdTree() {

    }

    // is the set empty?
    public boolean isEmpty() {
        return root == null;
    }

    // number of points in the set
    public int size() {
        if (root == null) return 0;
        return root.n;
    }

    private int size(Rect x) {
        if (x == null) return 0;
        return x.n;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (contains(p)) return;

        if (root == null) {
            RectHV tem = new RectHV(0, 0, 1, 1);
            root = new Rect(p, 1, RED, tem, 0, 1, LEFT);
            return;
        }
        root = insert(root, p, RED, null, 0, 0, LEFT);
    }

    private Rect insert(Rect k, Point2D p, boolean color, RectHV r, double varX, double varY, boolean orient) {
        if (k == null) return new Rect(p, 1, color, r, varX, varY, orient);

        double cmp;
        if (k.color) cmp = p.x() - k.p.x();
        else cmp = p.y() - k.p.y();

        if (cmp < 0) k.left = insert(k.left, p, !k.color, k.r, k.p.x(), k.p.y(), LEFT);
        else if (cmp > 0) k.right = insert(k.right, p, !k.color, k.r, k.p.x(), k.p.y(), !LEFT);
        else {
            if (k.color) {
                if (p.y() - k.p.y() < 0) k.left = insert(k.left, p, false, k.r, k.p.x(), k.p.y(), LEFT);
                else k.right = insert(k.right, p, false, k.r, k.p.x(), k.p.y(), !LEFT);
            } else {
                if (p.x() - k.p.x() < 0) k.left = insert(k.left, p, true, k.r, k.p.x(), k.p.y(), LEFT);
                else k.right = insert(k.right, p, true, k.r, k.p.x(), k.p.y(), !LEFT);
            }
        }

        k.n = size(k.right) + size(k.left) + 1;
        return k;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (root == null) return false;

        double cmp;
        Rect current = root;

        while (current != null) {
            if (current.color) cmp = p.x() - current.p.x();
            else cmp = p.y() - current.p.y();

            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else {
                if (p.x() == current.p.x() && p.y() == current.p.y()) return true;
                if (current.color) {
                    if (p.y() - current.p.y() < 0) current = current.left;
                    else current = current.right;
                } else {
                    if (p.x() - current.p.x() < 0) current = current.left;
                    else current = current.right;
                }
            }
        }
        return false;
    }

    // draw all points to standard draw
    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(-0.1, 1.1);
        StdDraw.setYscale(-0.1, 1.1);
        StdDraw.line(0, 0, 0, 1);
        StdDraw.line(0, 1, 1, 1);
        StdDraw.line(1, 1, 1, 0);
        StdDraw.line(1, 0, 0, 0);
        draw(root, 0, 1, 0, 1);
    }

    private void draw(Rect k, double xmin, double xmax, double ymin, double ymax) {
        if (k == null) return;

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.02);
        k.p.draw();
        StdDraw.setPenRadius(0.003);
        if (k.color) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(k.p.x(), ymin, k.p.x(), ymax);
            draw(k.left, xmin, k.p.x(), ymin, ymax);
            draw(k.right, k.p.x(), xmax, ymin, ymax);
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(xmin, k.p.y(), xmax, k.p.y());
            draw(k.left, xmin, xmax, ymin, k.p.y());
            draw(k.right, xmin, xmax, k.p.y(), ymax);
        }
    }

//    public void draw() {
//        draw(root);
//    }
//
//    private void draw(Rect k) {
//        if (k == null) return;
//
//        k.p.draw();
//        draw(k.left);
//        draw(k.right);
//    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        Queue<Point2D> queue = new Queue<>();
        range(root, rect, queue);
        return queue;
    }

    private void range(Rect k, RectHV rect, Queue<Point2D> queue) {
        if (k == null) return;

        if (rect.contains(k.p)) queue.enqueue(k.p);

        if (k.left != null && rect.intersects(k.left.r)) range(k.left, rect, queue);
        if (k.right != null && rect.intersects(k.right.r)) range(k.right, rect, queue);
    }

    // homework.a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (root == null) return null;
        if (!root.r.contains(p)) throw new IllegalArgumentException();

        root.distTo = p.distanceSquaredTo(root.p);
        return nearest(root, p, root).p;
    }

    private Rect nearest(Rect k, Point2D p, Rect shortestRect) {
        if (k == null) return shortestRect;

        double dist = p.distanceSquaredTo(k.p);
        if (dist < shortestRect.distTo) {
            shortestRect = k;
            shortestRect.distTo = dist;
        }

        // If the value of r.left or r.right is null, the corresponding value of distLeft or distRight just large than sqrt(2).
        double distLeft, distRight;
        if (k.left != null) distLeft = k.left.r.distanceSquaredTo(p);
        else distLeft = 2;
        if (k.right != null) distRight = k.right.r.distanceSquaredTo(p);
        else distRight = 2;

        if (distLeft < distRight) {
            if (distLeft < shortestRect.distTo) {
                shortestRect = nearest(k.left, p, shortestRect);
                if (distRight < shortestRect.distTo) shortestRect = nearest(k.right, p, shortestRect);
            }
        } else {
            if (distRight < shortestRect.distTo) {
                shortestRect = nearest(k.right, p, shortestRect);
                if (distLeft < shortestRect.distTo) shortestRect = nearest(k.left, p, shortestRect);
            }
        }
        return shortestRect;
    }
}
