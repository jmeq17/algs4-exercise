package homework.partI.week3;

public class LineSegment {
    private final Point p;
    private final Point q;

    // constructs the line segment between points p and q
    public LineSegment(Point p, Point q) {
        if (p == null || q == null) {
            throw new IllegalArgumentException("argument to HW.PartI.Week3.LineSegment constructor is null");
        }
        if (p.equals(q)) {
            throw new IllegalArgumentException("both arguments to HW.PartI.Week3.LineSegment constructor are the same point: " + p);
        }
        this.p = p;
        this.q = q;
    }

    // draws this line segment
    public void draw() {
        p.drawTo(q);
    }

    // string representation
    public String toString() {
        return p + " -> " + q;
    }
}