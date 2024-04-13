package ch2.topic2;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import utils.Merge;
import utils.MergeBU;

public class E20206 {
    public static void sort(String s, int N) {
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();
        }

        if (s.equals("Merge")) {
            Merge.sort(a);
            Merge.draw();
        }
        if (s.equals("MergeBU")) {
            MergeBU.sort(a);
            MergeBU.draw();
        }
    }

    public static void main(String[] args) {
        StdDraw.setXscale(-10, 520);
        StdDraw.setYscale(-10, 6 * 520 * Math.log(520) / Math.log(2));
        StdDraw.setPenRadius(.01);
        StdOut.println("Array size\t\tCount\tUpperBound");

        for (int N = 1; N <= 512; N++) {
            StdDraw.setPenColor(255, 0, 0);
            sort("Merge", N);

            StdDraw.setPenColor(0, 255, 0);
            sort("MergeBU", N);

            StdDraw.setPenColor(0, 0, 0);
            StdDraw.point(N, 6 * N * (Math.log10(N) / Math.log10(2)));
        }

        var y = 2880 * Math.log10(480) / Math.log10(2);

        StdDraw.line(50, y, 100, y);
        StdDraw.text(160, y, "UpperBound");

        StdDraw.setPenColor(255, 0, 0);
        StdDraw.line(50, y - 1000, 100, y - 1000);
        StdDraw.text(140, y - 1000, "Merge");

        StdDraw.setPenColor(0, 255, 0);
        StdDraw.line(50, y - 2000, 100, y - 2000);
        StdDraw.text(150, y - 2000, "MergeBU");
    }
}
