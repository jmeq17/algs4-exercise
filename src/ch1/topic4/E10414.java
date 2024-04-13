package ch1.topic4;

/*
4-sum. Develop an algorithm for the 4-sum problem.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class E10414 {
    private static int SumFour(int[] a) {
        int n = a.length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        if (a[i] + a[j] + a[k] + a[l] == 0) {
                            cnt++;
                        }
                    }
                }
            }
        }
        return cnt;
    }

    private static int SumFourFast(int[] a) {
        int cnt = 0;

        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (binarySearch(a, -(a[i] + a[j] + a[k]), 0, a.length - 1) > k) cnt++;
//                    if (BinarySearch.indexOf(homework.a, -(homework.a[i] + homework.a[j] + homework.a[k])) > k) cnt++;
                }
            }
        }
        return cnt;
    }

    private static int binarySearch(int[] a, int key, int lo, int hi) {
        if (lo > hi) return -1;

        int mid = lo + (hi - lo) / 2;
        if (a[mid] > key) {
            return binarySearch(a, key, lo, mid - 1);
        } else if (a[mid] < key) {
            return binarySearch(a, key, mid + 1, hi);
        } else return mid;
    }

    public static double timeTrial(int N) {
        int[] a = new int[N];
        int MAX = 1000000;
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Arrays.sort(a);
        Stopwatch timer = new Stopwatch();
//        SumFour(homework.a);
        SumFourFast(a);
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
