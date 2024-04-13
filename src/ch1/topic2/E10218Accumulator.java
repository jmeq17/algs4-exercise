package ch1.topic2;

/*
Variance for accumulator. Validate that the following code, which adds the
methods var() and stddev() to Accumulator, computes both the mean and variance
of the numbers presented as arguments to addDataValue():
    public class Accumulator
    {
        private double m = 0.0;
        private double s = 0.0;
        private int N = 0;
        public void addDataValue(double x)
        {
            N++;
            double delta = x - m;
            m += delta / N;
            s += (double) (N - 1) / N * delta * delta;
        }
        public double mean()
        { return m; }
        public double var()
        { return s/(N - 1); }
        public double stddev()
        { return Math.sqrt(this.var()); }
    }
This implementation is less susceptible to roundoff error than the straightforward implementation
based on saving the sum of the squares of the numbers.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class E10218Accumulator {
    private double m = 0.0;
    private double s = 0.0;
    private int N = 0;

    public void addDataValue(double x) {
        N++;
        double delta = x - m;
        m += delta / N;
        s += (double) (N - 1) / N * delta * delta;
    }

    public double mean() {
        return m;
    }

    public double var() {
        return s / (N - 1);
    }

    public double stddev() {
        return Math.sqrt(this.var());
    }


    public static void main(String[] args) {
        double[] a = new double[100];
        double sum = 0.0;
        for (int i  = 0; i < 100; i++) {
            a[i] = StdRandom.uniform(-100.0, 234.0);
            sum += a[i];
        }

        StdOut.println("Standard output: ");
        StdOut.println("mean: " + sum / a.length);

        E10218Accumulator add = new E10218Accumulator();
        for(int i = 0; i < 100;i++){
            add.addDataValue(a[i]);
        }

        StdOut.println("Test: ");
        StdOut.println("mean: " + add.mean());
        StdOut.println("variance: " + add.var());
        StdOut.println("Stddev: " + add.stddev());
    }
}
