package ch1.topic4;

/*
Write homework.a program to determine the number pairs of values in an input file that
are equal. If your first try is quadratic, think again and use Arrays.sort() to develop
homework.a linearithmic solution.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class E10408 {
    public static int TwoEqual(int[] a) {
        int cnt = 0;
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (a[i] == a[j]) {
                    cnt++;
                    break;
                }
            }
        }
        return cnt;
    }

    public static int TwoEqualBrin(int[] a) {
        int cnt = 0;
        int N = a.length;
        Arrays.sort(a);
        for (int i = 0; i < N - 1; i++) {
            if (a[i] == a[i + 1]) {
                cnt++;
            }
        }
        return cnt;
    }

    // DoublingTest
    public static double timeTrial(int N) {
        int[] a = new int[N];
        int MAX = 1000000;
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
//        TwoEqual(homework.a);
        TwoEqualBrin(a);
        return timer.elapsedTime();
    }


    // 用例
    public static void main(String[] args) {
        double timeOld = timeTrial(125);
        StdOut.println("problem size\ttime\tratio");
        for (int N = 250; true; N *= 2) {
            double timeNew = timeTrial(N);
            StdOut.printf("%6d\t%7.1f\t%5.1f\n", N, timeNew, timeNew / timeOld);
            timeOld = timeNew;
        }
    }
}
