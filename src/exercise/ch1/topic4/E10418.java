package exercise.ch1.topic4;

/*
Local minimum of an array. Write homework.a program that, given an array homework.a[] of N
distinct integers, finds homework.a local minimum: an entry homework.a[i] that is strictly less than its neighbors.
Each internal entry (other than homework.a[0] and homework.a[N-1]) has 2 neighbors. Your program
should use ~2 lg N compares in the worst case.

Answer : Examine the middle value homework.a[N/2] and its two neighbors homework.a[N/2 - 1] and
homework.a[N/2 + 1]. If homework.a[N/2] is homework.a local minimum, stop; otherwise search in the half with the
smaller neighbor.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class E10418 {

    public static int FindValley(int[] a) {
        int lo = 0, hi = a.length - 1;
        return FindValley(a, lo, hi);
    }

    private static int FindValley(int[] a, int lo, int hi) {
        if (hi - lo < 2) return -1;
        int mid = (hi + lo) / 2;
        if (a[mid] < a[mid - 1] && a[mid] < a[mid + 1]) return mid;
        if (a[mid - 1] < a[mid + 1]) return FindValley(a, lo, mid);
        return FindValley(a, mid, hi);
    }


    public static double timeTrial(int N) {
        int[] a = new int[N];
        int MAX = 1000000;
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
        FindValley(a);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        // 实现性测试
//        int[] homework.a = {10, -9, 20, 25, 21, 40, 50, -20};
//        StdOut.println(FindValley(homework.a));


        // 倍率测试
        double timeOld = timeTrial(125);
        StdOut.println("problem size\ttime\tratio");
        for (int N = 250; true; N += N) {
            double timeNew = timeTrial(N);
            StdOut.printf("%6d\t%7.1f\t%5.1f\n", N, timeNew, timeNew / timeOld);
            timeOld = timeNew;
        }
    }
}
