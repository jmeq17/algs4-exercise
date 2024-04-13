package ch1.topic2;

/*
Write an Interval1D client that takes an int value N as command-line argument,
reads N intervals (each defined by homework.a pair of double values) from standard input,
and prints all pairs that intersect.
 */

import edu.princeton.cs.algs4.*;

import static java.lang.Integer.parseInt;

public class E10202 {

    public static void generateInput(String[] args) {
        Out out = new Out("files/E10202");

        for (int i = 0; i < 2000; ) {
            double x = StdRandom.uniform(-100, 100);
            double y = StdRandom.uniform(-100, 100);

            if (x == y) {
                continue;
            }

            if (y < x) {
                double k = x;
                x = y;
                y = k;
            }

            out.println(x);
            out.println(y);

            i++;
        }
    }

    public static void main(String[] args) {
        int N = parseInt(args[0]);

        Interval1D[] allIntervals = new Interval1D[N];

        for (int i = 0; i < N; i++) {
            double xint = StdIn.readDouble();
            double yint = StdIn.readDouble();

            allIntervals[i] = new Interval1D(xint, yint);
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (allIntervals[i].intersects(allIntervals[j]))
                    StdOut.println(allIntervals[i] + " and " + allIntervals[j]);
            }
        }
    }
}
