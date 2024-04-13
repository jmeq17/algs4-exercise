package ch1.topic4;

/*
Faster 3-sum. As homework.a warmup, develop an implementation TwoSumFaster that
uses homework.a linear algorithm to count the pairs that sum to zero after the array is sorted (instead
of the binary-search-based linearithmic algorithm). Then apply homework.a similar idea to
develop homework.a quadratic algorithm for the 3-sum problem.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class E10415 {

    public static int SumTwoFaster(int[] a) {
        Arrays.sort(a);
        int hi = a.length - 1;
        if (a[hi] <= 0 || a[0] >= 0) return 0;
        int cnt = 0, lo = 0;

        while (lo < hi) {
            if (a[lo] + a[hi] < 0) {
                lo++;
                continue;
            }

            if (a[lo] + a[hi] > 0) {
                hi--;
                continue;
            }

            cnt++;
            lo++;
            hi--;
        }
        return cnt;
    }

    public static int SumThreeFaster(int[] a) {
        Arrays.sort(a);
        int cnt = 0;
        int N = a.length, hi = a.length - 1;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                int key = -(a[i] + a[j]);
                if (a[hi] <= key || a[j + 1] >= key) continue;

                int lo = j + 1;
                while (lo < hi) {
                    if (a[lo] + a[hi] < key) {
                        lo++;
                        continue;
                    }

                    if (a[lo] + a[hi] > key) {
                        hi--;
                        continue;
                    }

                    cnt++;
                    lo++;
                    hi--;
                }
            }
        }
        return cnt;
    }

    public static int SumThreeFaster2(int[] a) {
        Arrays.sort(a);
        int cnt = 0;

        for (int i = 0; i < a.length; i++) {
            for (int lo = i + 1, hi = a.length - 1; lo < hi; ) {
                if (a[i] + a[lo] + a[hi] > 0)
                    hi--;
                else if (a[i] + a[lo] + a[hi] < 0)
                    lo++;
                else {
                    lo++;
                    hi--;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static int SumThreeFaster3(int[] a) {
        Arrays.sort(a);
        int cnt = 0, lo = 0, se = lo + 1, hi = a.length - 1;

        while (lo < hi) {
            while (se < hi && a[se] + a[lo] + a[hi] == 0) {
                cnt++;
                se++;
                hi--;
            }
            while (se < hi && a[lo] + a[se] + a[hi] > 0) {
                hi--;
            }
            while (se < hi && a[lo] + a[se] + a[hi] < 0) {
                se++;
            }

            if (se >= hi) {
                lo++;
                se = lo + 1;
                hi = a.length - 1;
            }
        }
        return cnt;
    }

    public static double timeTrial(int N) {
        int[] a = new int[N];
        int MAX = 1000000;
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
//        SumTwoFaster(homework.a);
//        SumThreeFaster(homework.a);
//        SumThreeFaster2(homework.a);
        SumThreeFaster3(a);
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
