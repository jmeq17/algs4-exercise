package homework.partI.week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class KdTreeFull {
    private static final boolean RED = true;
    private Node root;

    private static class Node {
        double x;
        double y;
        boolean color;
        int n;
        private Node right, left;
        double dist;

        Node(double x, double y, int n, boolean color) {
            this.x = x;
            this.y = y;
            this.n = n;
            this.color = color;
        }
    }

    public KdTreeFull() {

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

    private int size(Node x) {
        if (x == null) return 0;
        return x.n;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (contains(p)) return;

        double x = p.x();
        double y = p.y();
        root = insert(x, y, root, RED);
    }

    private Node insert(double x, double y, Node k, boolean color) {
        if (k == null) return new Node(x, y, 1, color);

        double cmp;
        if (k.color) cmp = x - k.x;
        else cmp = y - k.y;

        if (cmp < 0) k.left = insert(x, y, k.left, !k.color);
        else if (cmp > 0) k.right = insert(x, y, k.right, !k.color);
        else {
            if (k.color) {
                if (y - k.y < 0) k.left = insert(x, y, k.left, false);
                else k.right = insert(x, y, k.right, false);
            } else {
                if (x - k.x < 0) k.left = insert(x, y, k.left, true);
                else k.right = insert(x, y, k.right, true);
            }
        }

        k.n = size(k.right) + size(k.left) + 1;
        return k;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (root == null) return false;

        double x = p.x();
        double y = p.y();
        double cmp;
        Node current = root;

        while (current != null) {
            if (current.color) cmp = current.x - x;
            else cmp = current.y - y;

            if (cmp < 0) current = current.right;
            else if (cmp > 0) current = current.left;
            else {
                if (x == current.x && y == current.y) return true;
                if (current.color) {
                    if (current.y - y < 0) current = current.right;
                    else current = current.left;
                } else {
                    if (current.x - x < 0) current = current.right;
                    else current = current.left;
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

    private void draw(Node k, double xmin, double xmax, double ymin, double ymax) {
        if (k == null) return;

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.02);
        StdDraw.point(k.x, k.y);
        StdDraw.setPenRadius(.003);
        if (k.color) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(k.x, ymin, k.x, ymax);
            draw(k.left, xmin, k.x, ymin, ymax);
            draw(k.right, k.x, xmax, ymin, ymax);
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(xmin, k.y, xmax, k.y);
            draw(k.left, xmin, xmax, ymin, k.y);
            draw(k.right, xmin, xmax, k.y, ymax);
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        Queue<Point2D> queue = new Queue<>();
//        range(root, rect.xmin(), rect.xmax(), rect.ymin(), rect.ymax(), queue);
        range(root, rect, queue);
        return queue;
    }

//    private void range(Node k, double xmin, double xmax, double ymin, double ymax, Queue<Point2D> queue) {
//        if (k == null) return;
//
//        double x = k.x;
//        double y = k.y;
//        if (x >= xmin && x <= xmax && y >= ymin && y <= ymax)
//            queue.enqueue(new Point2D(k.x, k.y));
//
//        if (k.color) {
//            if (xmin <= x) range(k.left, xmin, xmax, ymin, ymax, queue);
//            if (xmax >= x) range(k.right, xmin, xmax, ymin, ymax, queue);
//        } else {
//            if (ymin <= y) range(k.left, xmin, xmax, ymin, ymax, queue);
//            if (ymax >= y) range(k.right, xmin, xmax, ymin, ymax, queue);
//        }
//    }

    private void range(Node k, RectHV rect, Queue<Point2D> queue) {
        if (k == null) return;

        Point2D tem = new Point2D(k.x, k.y);
        if (rect.contains(tem)) queue.enqueue(tem);

        if (k.color) {
            if (rect.xmin() <= k.x) range(k.left, rect, queue);
            if (rect.xmax() >= k.x) range(k.right, rect, queue);
        } else {
            if (rect.ymin() <= k.y) range(k.left, rect, queue);
            if (rect.ymax() >= k.y) range(k.right, rect, queue);
        }
    }

    // homework.a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (root == null) return null;

        root.dist = p.distanceTo(new Point2D(root.x, root.y));
        Node n = nearest(root, p, root);
        if (n != null) return new Point2D(n.x, n.y);
        return null;
    }

    private Node nearest(Node k, Point2D p, Node shortestNode) {
        if (k == null) return shortestNode;


        double x = p.x();
        double y = p.y();
        double disc = p.distanceTo(new Point2D(k.x, k.y));
        if (disc < shortestNode.dist) {
            shortestNode = k;
            shortestNode.dist = disc;
        }


//        double x = p.x();
//        double y = p.y();
//        double shortestDisc = p.distanceTo(new Point2D(shortestNode.x, shortestNode.y));
//        double disc = p.distanceTo(new Point2D(k.x, k.y));
//        if (disc < shortestDisc) {
//            shortestNode = k;
//            shortestNode.dist = shortestDisc;
//        }

        if (k.color) {
            if (x < k.x || x == k.x && y < k.y) {
                shortestNode = nearest(k.left, p, shortestNode);
                if (k.x - x < shortestNode.dist) shortestNode = nearest(k.right, p, shortestNode);
            } else if (x > k.x || x == k.x && y > k.y) {
                shortestNode = nearest(k.right, p, shortestNode);
                if (x - k.x < shortestNode.dist) shortestNode = nearest(k.left, p, shortestNode);
            } else return k;
        } else {
            if (y < k.y || y == k.y && x < k.x) {
                shortestNode = nearest(k.left, p, shortestNode);
                if (k.y - y < shortestNode.dist) shortestNode = nearest(k.right, p, shortestNode);
            } else if (y > k.y || y == k.y && x > k.x) {
                shortestNode = nearest(k.right, p, shortestNode);
                if (y - k.y < shortestNode.dist) shortestNode = nearest(k.left, p, shortestNode);
            } else return k;
        }
        return shortestNode;
    }

//    private Node nearest(Node k, Point2D p, Node shortestNode) {
//        if (k == null) return shortestNode;
//
//        double x = p.x();
//        double y = p.y();
//        double shortestDisc = p.distanceTo(new Point2D(shortestNode.x, shortestNode.y));
//        double disc = p.distanceTo(new Point2D(k.x, k.y));
//        if (disc < shortestDisc) {
//            shortestDisc = disc;
//            shortestNode = k;
//        }
//
//        if (k.color) {
//            if (x < k.x || x == k.x && y < k.y) {
//                shortestNode = nearest(k.left, p, shortestNode);
////                double t1 = p.distanceTo(new Point2D(tem.x, tem.y));
//
//                if (k.x - x < shortestDisc) {
//                    shortestNode = nearest(k.right, p, shortestNode);
////                    double t2 = p.distanceTo(new Point2D(tem2.x, tem2.y));
//
////                    if (t1 < t2) return tem;
////                    else return tem2;
//                }
////                else return tem;
//            } else if (x > k.x || x == k.x && y > k.y) {
//                shortestNode = nearest(k.right, p, shortestNode);
////                double t1 = p.distanceTo(new Point2D(tem.x, tem.y));
//
//                if (x - k.x < shortestDisc) {
//                    shortestNode = nearest(k.left, p, shortestNode);
////                    double t2 = p.distanceTo(new Point2D(tem2.x, tem2.y));
////
////                    if (t1 < t2) return tem;
////                    else return tem2;
//                }
////                else return tem;
//            } else return k;
//        } else {
//            if (y < k.y || y == k.y && x < k.x) {
//                shortestNode = nearest(k.left, p, shortestNode);
////                double t1 = p.distanceTo(new Point2D(tem.x, tem.y));
//
//                if (k.y - y < shortestDisc) {
//                    shortestNode = nearest(k.right, p, shortestNode);
////                    double t2 = p.distanceTo(new Point2D(tem2.x, tem2.y));
////
////                    if (t1 < t2) return tem;
////                    else return tem2;
//
//                }
////                else return tem;
//            } else if (y > k.y || y == k.y && x > k.x) {
//                shortestNode = nearest(k.right, p, shortestNode);
////                double t1 = p.distanceTo(new Point2D(tem.x, tem.y));
//
//                if (y - k.y < shortestDisc) {
//                    shortestNode = nearest(k.left, p, shortestNode);
////                    double t2 = p.distanceTo(new Point2D(tem2.x, tem2.y));
////
////                    if (t1 < t2) return tem;
////                    else return tem2;
//                }
////                else return tem;
//            } else return k;
//        }
//        return shortestNode;
//    }

//    private Node nearest(Node k, Point2D p, double shortestDisc, Node shortestNode) {
//        if (k == null) return null;
//
//        double x = p.x();
//        double y = p.y();
//        double disc = p.distanceTo(new Point2D(k.x, k.y));
//        if (disc < shortestDisc) {
//            shortestDisc = disc;
//            shortestNode = k;
//        }
//        Node tem;
//
//        if (k.color) {
//            if (x < k.x || x == k.x && y < k.y) {
//                tem = nearest(k.left, p, shortestDisc, shortestNode);
//                double t1 = p.distanceTo(new Point2D(tem.x, tem.y));
//
////                if (tem != null) {
////                    disc = p.distanceTo(new Point2D(tem.x, tem.y));
////                    if (disc < shortestDisc) {
////                        shortestDisc = disc;
////                        shortestNode = k;
////                    }
////                }
//
//                if (k.x - x < shortestDisc) {
//                    Node tem2 = nearest(k.right, p, shortestDisc, shortestNode);
//                    double t2 = p.distanceTo(new Point2D(tem2.x, tem2.y));
//
//                    if (t1 < t2) return tem;
//                    else return tem2;
//
////                    if (tem != null) {
////                        disc = p.distanceTo(new Point2D(tem.x, tem.y));
////                        if (disc < shortestDisc) shortestNode = tem;
////                    }
//                } else return tem;
//            } else if (x > k.x || x == k.x && y > k.y) {
//                tem = nearest(k.right, p, shortestDisc, shortestNode);
//                double t1 = p.distanceTo(new Point2D(tem.x, tem.y));
////                if (tem != null) {
////                    disc = p.distanceTo(new Point2D(tem.x, tem.y));
////                    if (disc < shortestDisc) {
////                        shortestDisc = disc;
////                        shortestNode = k;
////                    }
////                }
//
//                if (x - k.x < shortestDisc) {
//                    Node tem2 = nearest(k.left, p, shortestDisc, shortestNode);
//                    double t2 = p.distanceTo(new Point2D(tem2.x, tem2.y));
//
//                    if (t1 < t2) return tem;
//                    else return tem2;
//
//
////                    if (tem != null) {
////                        disc = p.distanceTo(new Point2D(tem.x, tem.y));
////                        if (disc < shortestDisc) shortestNode = tem;
////                    }
//                } else return tem;
//            } else return k;
//        } else {
//            if (y < k.y || y == k.y && x < k.x) {
//                tem = nearest(k.left, p, shortestDisc, shortestNode);
//                double t1 = p.distanceTo(new Point2D(tem.x, tem.y));
//
////                if (tem != null) {
////                    disc = p.distanceTo(new Point2D(tem.x, tem.y));
////                    if (disc < shortestDisc) {
////                        shortestDisc = disc;
////                        shortestNode = k;
////                    }
////                }
//
//                if (k.y - y < shortestDisc) {
//                    Node tem2 = nearest(k.right, p, shortestDisc, shortestNode);
//                    double t2 = p.distanceTo(new Point2D(tem2.x, tem2.y));
//
//                    if (t1 < t2) return tem;
//                    else return tem2;
//
////                    if (tem != null) {
////                        disc = p.distanceTo(new Point2D(tem.x, tem.y));
////                        if (disc < shortestDisc) shortestNode = tem;
////                    }
//                } else return tem;
//            } else if (y > k.y || y == k.y && x > k.x) {
//                tem = nearest(k.right, p, shortestDisc, shortestNode);
//                double t1 = p.distanceTo(new Point2D(tem.x, tem.y));
//
////                if (tem != null) {
////                    disc = p.distanceTo(new Point2D(tem.x, tem.y));
////                    if (disc < shortestDisc) {
////                        shortestDisc = disc;
////                        shortestNode = k;
////                    }
////                }
//
//                if (y - k.y < shortestDisc) {
//                    Node tem2 = nearest(k.left, p, shortestDisc, shortestNode);
//                    double t2 = p.distanceTo(new Point2D(tem2.x, tem2.y));
//
//                    if (t1 < t2) return tem;
//                    else return tem2;
//
////                    if (tem != null) {
////                        disc = p.distanceTo(new Point2D(tem.x, tem.y));
////                        if (disc < shortestDisc) shortestNode = tem;
////                    }
//                } else return tem;
//            } else return k;
//        }
////
////        return shortestNode;
//    }

    public static void main(String[] args) {
        In in = new In("HW_Project\\kdtree\\test.txt");
        KdTreeFull set = new KdTreeFull();

        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            set.insert(new Point2D(x, y));
        }

        set.draw();

//        StdOut.println(set.contains(new Point2D(0.046, 0.497)));

//        StdDraw.setPenColor(StdDraw.GREEN);
//        StdDraw.rectangle(0.78, 0.545, 0.06, 0.075);
//        RectHV rect = new RectHV(0.72, 0.47, 0.84, 0.62);
//        Iterable<Point2D> iter = set.range(rect);
//        for (Point2D point : iter)
//            StdOut.println(point);

        Point2D p = new Point2D(0.625, 0.9375);
        StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(StdDraw.MAGENTA);
        p.draw();
        set.nearest(p);
        StdOut.println(set.nearest(p));
    }
}
