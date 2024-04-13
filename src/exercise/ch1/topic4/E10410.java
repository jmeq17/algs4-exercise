package exercise.ch1.topic4;

/*
Modify binary search so that it always returns the element with the smallest
index that matches the search element (and still guarantees logarithmic running time).
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class E10410 {
    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else if (mid - 1 < 0 || a[mid - 1] < key) return mid;
            else hi = mid - 1;
//            else return mid;
        }
        return -1;
    }

    // DoublingTest
    public static double timeTrial(int N) {
        int[] a = new int[N];
        int key = StdRandom.uniform(-1000000, 1000000);
        int MAX = 1000000;
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Arrays.sort(a);
        Stopwatch timer = new Stopwatch();

        rank(key, a);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        StdOut.println("problem size\ttime\tratio");
        double timeOld = timeTrial(125);
        for (int N = 250; true; N *= 2) {
            double timeNew = timeTrial(N);
            StdOut.printf("%6d\t%7.1f\t%5.1f\n", N, timeNew, timeNew / timeOld);
            timeOld = timeNew;
        }


        // 实现性测试
//        int[] homework.a = {0, 4, 4, 4, 5, 6, 7, 7, 8, 9};
//        StdOut.println(rank(4, homework.a));
//        StdOut.println(rank(7, homework.a));
    }
}
