package ch1.topic4;

/*
1.4.16. Closest pair (in one dimension). Write homework.a program that, given an array homework.a[] of N
double values, finds homework.a closest pair : two values whose difference is no greater than the
the difference of any other pair (in absolute value). The running time of your program
should be linearithmic in the worst case.
 */

/*
1.4.17. Farthest pair (in one dimension). Write homework.a program that, given an array homework.a[] of N
double values, finds homework.a farthest pair : two values whose difference is no smaller than the
the difference of any other pair (in absolute value). The running time of your program
should be linear in the worst case.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class E10416to17 {
    public static double[] ClosestPair(double[] a) {
        double[] closest = {a[0], a[1]};
        Arrays.sort(a);
        int N = a.length;
        double diff = a[1] - a[0];

        for (int i = 1; i < N - 1; i++) {
            if (a[i + 1] - a[i] < diff) {
                diff = a[i + 1] - a[i];
                closest[0] = a[i];
                closest[1] = a[i + 1];
            }
        }

        return closest;
    }

    public static double[] FarthestPair(double[] a) {
        double[] farthest = {a[0], a[1]};
        Arrays.sort(a);
        int N = a.length;
        double diff = a[1] - a[0];

        for (int i = 1; i < N - 1; i++) {
            if (a[i + 1] - a[i] > diff) {
                diff = a[i + 1] - a[i];
                farthest[0] = a[i];
                farthest[1] = a[i + 1];
            }
        }

        return farthest;
    }

    public static double timeTrial(int N) {
        double[] a = new double[N];
        double MAX = 1000000.0;
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
//        ClosestPair(homework.a);
        FarthestPair(a);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        double timeOld = timeTrial(125);
        StdOut.println("problem size\ttime\tratio");
        for (int N = 250; true; N += N) {
            double timeNew = timeTrial(N);
            StdOut.printf("%6d\t%7.1f\t%5.1f\n", N, timeNew, timeNew / timeOld);
            timeOld = timeNew;
        }
    }
}
