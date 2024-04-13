package exercise.ch1.topic2;

/*
Write an Interval2D client that takes command-line arguments N, min, and max
and generates N random 2D intervals whose width and height are uniformly distributed
between min and max in the unit square. Draw them on StdDraw and print the number
of pairs of intervals that intersect and the number of intervals that are contained in one
another.
 */

import edu.princeton.cs.algs4.*;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class E10203 {

    public static Interval1D generateInterval1D(double min, double max) {
        double x = StdRandom.uniform(min, max);
        double y = StdRandom.uniform(min, max);

        if (x == y) y += StdRandom.uniform(min, max);
        if (y < x) {
            double k = x;
            x = y;
            y = k;
        }
        return new Interval1D(x, y);
    }

    public static void drawInterval2D(Interval2D[] interval, double min, double max) {
        StdDraw.setCanvasSize(1024, 512);
        StdDraw.setPenRadius(.015);
        StdDraw.setScale(min, max);

        int N = interval.length;

        for (int i = 0; i < N; i++) {
            Interval1D xint = generateInterval1D(min, max);
            Interval1D yint = generateInterval1D(min, max);

            interval[i] = new Interval2D(xint, yint);
            interval[i].draw();
        }
    }

    public static void main(String[] args) {
        int N = parseInt(args[0]);
        double min = parseDouble(args[1]);
        double max = parseDouble(args[2]);

        Interval2D[] allIntervals = new Interval2D[N];

        drawInterval2D(allIntervals, min, max);

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (allIntervals[i].intersects(allIntervals[j])) {
                    count++;
//                    StdOut.println("intersect: " + allIntervals[i] + " and " + allIntervals[j]);
                }
                // cannot make up the second question.
            }
        }
        StdOut.println("Intersect: " + count);
    }
}
