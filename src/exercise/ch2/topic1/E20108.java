package exercise.ch2.topic1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import utils.Insertion;


public class E20108 {
    public static double timeTrial(String alg, int N, int T) {
        return timeRandomInput(alg, N, T);
    }

    private static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        String[] a = new String[N];

        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++) {
                if (StdRandom.uniform(2, 5) == 2) a[i] = "A";
                else if (StdRandom.uniform(2, 5) == 3) a[i] = "B";
                else a[i] = "C";
            }

            Stopwatch timer = new Stopwatch();
            Insertion.sort(a);
            total += timer.elapsedTime();
        }
        return total;
    }


    public static void main(String[] args) {
        String alg = "Insertion";
        // 运行次数
        int T = 100;
        // 初始数组
        int N = 125;

        double timeOld = timeTrial(alg, N, T);
        StdOut.println("size\t\ttime\t\tratio");

        for (N += N; true; N += N) {
            double timeNew = timeTrial(alg, N, T);
            StdOut.printf("%d\t\t%.1f\t\t%.1f\n", N, timeNew, timeNew / timeOld);
            timeOld = timeNew;
        }
    }
}
