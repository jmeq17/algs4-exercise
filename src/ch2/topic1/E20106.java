package ch2.topic1;

import edu.princeton.cs.algs4.StdOut;
import utils.SortCompare;

public class E20106 {
    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];

        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++)
                a[i] = 0.5;
            total += SortCompare.time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = "Selection";
        String alg2 = "Insertion";

        double t1 = timeRandomInput(alg1, 10000, 100);
        double t2 = timeRandomInput(alg2, 10000, 100);

        StdOut.println("Selection: " + t1);
        StdOut.println("Insertion: " + t2);
        StdOut.printf("%s is %.1f times faster than %s\n", alg2, t1 / t2, alg1);
    }
}
