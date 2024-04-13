package homework.partI.week1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONDIDENCE_95 = 1.96;

    private final double[] x;
    private final int T;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("n and trials must both larger than 0.");
        x = new double[trials];
        T = trials;

        for (int i = 0; i < T; i++) {
            Percolation a = new Percolation(n);

            while (!a.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (!a.isOpen(row, col)) a.open(row, col);
            }

            x[i] = (double) a.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(x);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(x);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONDIDENCE_95 * stddev() / Math.sqrt(T));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONDIDENCE_95 * stddev() / Math.sqrt(T));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats a = new PercolationStats(n, T);

        double mean = a.mean();
        double stddev = a.stddev();
        double confidenceLo = a.confidenceLo();
        double confidenceHi = a.confidenceHi();

        StdOut.printf("%-23s = %f\n", "mean", mean);
        StdOut.printf("%-23s = %f\n", "stddev", stddev);
        StdOut.printf("%-22s = [%f, %f]", "95% confidence interval", confidenceLo, confidenceHi);
    }
}
