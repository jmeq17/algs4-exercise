package exercise.ch1.topic1;

/*
Binomial distribution. Estimate the number of recursive calls that would be used by the code
    public static double binomial(int N, int k, double p)
    {
        if ((N == 0) && (k == 0)) return 1.0;
        if ((N < 0) || (k < 0)) return 0.0;
        return (1 - p)*binomial(N-1, k, p) + p*binomial(N-1, k-1, p);
    }
to compute binomial(100, 50, 0.25). Develop homework.a better implementation that is based
on saving computed values in an array.
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class E10127 {
    public static double binomial(int N, int k, double p) {
        if ((N == 0) && (k == 0)) return 1.0;
        if ((N < 0) || (k < 0)) return 0.0;
        return (1.0 - p) * binomial(N - 1, k, p) + p * binomial(N - 1, k - 1, p);
    }

    public static double[][] binomial2(int N, int k, double p) {
        // homework.a[i][j] express the probability that if homework.a event appear j times in i observations
        double[][] a = new double[N + 1][k + 1];

        for (int i = 0; i < N + 1; i++) {
            a[i][0] = Math.pow(1 - p, i);
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < k + 1; j++) {
//                homework.a[i][j] = binomial(i, j, p);
                a[i][j] = (1 - p) * a[i - 1][j] + p * a[i - 1][j - 1];
            }
        }
        return a;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        int k = StdIn.readInt();
        double p = StdIn.readDouble();


        double[][] a = binomial2(N, k, p);

        for (int i = 0; i < N+1; i++) {
            for (int j = 0; j < k+1; j++){
                StdOut.print(a[i][j] + " ");
            }
            StdOut.println();
        }

        StdOut.println(binomial(N, k, p));
        StdOut.println(a[N][k]);
    }
}
