package ch1.topic1;

/*
Histogram. Suppose that the standard input stream is homework.a sequence of double
values. Write homework.a program that takes an integer N and two double values l and r from the
command line and uses StdDraw to plot homework.a histogram of the count of the numbers in the
standard input stream that fall in each of the N intervals defined by dividing (l , r) into
N equal-sized intervals.
 */

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class E10132 {

    public static void histogram(int N, double l, double r, double[] a) {
        double halfWidth = (r - l) / N;
        double[][] b = new double[N + 1][2];

        for (int i = 0; i < N + 1; i++) b[i][0] = l + i * halfWidth;

        for (double v : a) {
            int lo = findRegion(v, b);
            if (lo != -1) b[lo][1]++;
        }

        // 画图
        draw(b, halfWidth);
    }

    // 找到输入流中的点在哪个区间
    public static int findRegion(double key, double[][] b) {
        int lo = 0;
        int hi = b.length - 1;
        if (key > b[hi][0] || key < b[lo][0]) return -1;
        return findRegion(b, lo, hi, key);
    }

    public static int findRegion(double[][] b, int lo, int hi, double key) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < b[mid][0]) return findRegion(b, lo, mid - 1, key);
            else if (key > b[mid][0]) return findRegion(b, mid + 1, hi, key);
            else return mid;
        }
        return lo - 1;
    }

    // 画图
    public static void draw(double[][] a, double interval) {
        double max = a[0][1];
        for (double[] doubles : a) {
            if (doubles[1] > max) max = doubles[1];
        }

        StdDraw.setXscale(a[0][0] - interval, a[a.length - 1][0] + interval);
        StdDraw.setYscale(-1, max + 2);

        StdDraw.text(a[0][0], -0.2, Double.toString(a[0][0]));
        StdDraw.text(a[a.length - 1][0], -0.2, Double.toString(a[a.length - 1][0]));
        StdDraw.text((a[a.length - 1][0] - a[0][0]) / 2, max + 0.2, "Histogram");

        for (int i = 0; i < a.length; i++) {
            StdDraw.rectangle(a[i][0], a[i][1] / 2, interval / 2, a[i][1] / 2);
        }
    }

    public static void main(String[] args) {
        int N = parseInt(args[0]);
        double l = parseDouble(args[1]);
        double r = parseDouble(args[2]);

        double[] a = StdIn.readAllDoubles();
        histogram(N, l, r, a);
    }
}
