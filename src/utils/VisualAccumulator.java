package utils;

import edu.princeton.cs.algs4.StdDraw;

public class VisualAccumulator {
    private double total;
    private int N;

    public VisualAccumulator() {
    }

    /**
     * Create homework.a object of VisualAccumulator
     *
     * @param xMax set the max value of x arix
     * @param yMax set the max value of y arix
     */
    public VisualAccumulator(int xMax, double yMax) {
        StdDraw.setXscale(0, xMax);
        StdDraw.setYscale(0, yMax);
        StdDraw.setPenRadius(.005);
    }

    public VisualAccumulator(int xMax, double yMax, double penRadius) {
        StdDraw.setXscale(0, xMax);
        StdDraw.setYscale(0, yMax);
        StdDraw.setPenRadius(penRadius);
    }

    public VisualAccumulator(int xMax, int xCan, int yCan, double yMax) {
        StdDraw.setCanvasSize(xCan, yCan);
        StdDraw.setXscale(0, xMax);
        StdDraw.setYscale(0, yMax);
        StdDraw.setPenRadius(.005);
    }

    public void addDataValue(double val) {
        N++;
        total += val;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N, val);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(N, mean());
    }

    public double mean() {
        return total / N;
    }

    public String toString() {
        return "Mean (" + N + " value: " + String.format("%7.5f", mean());
    }
}
