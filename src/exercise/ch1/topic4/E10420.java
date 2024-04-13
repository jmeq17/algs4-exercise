package exercise.ch1.topic4;

/*
Bitonic search. An array is bitonic if it is comprised of an increasing sequence
of integers followed immediately by homework.a decreasing sequence of integers. Write homework.a program
that, given homework.a bitonic array of N distinct int values, determines whether homework.a given integer is
in the array. Your program should use ~3 lg N compares in the worst case. Extra credit:
use only ~2 lg N compares in the worst case.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class E10420 {

    public static boolean isExist(int[] a, int key) {
        int hi = a.length - 1;
        if (a[0] > key && a[a.length - 1] > key) return false;
        int peek = FindPeek(a);
        if (a[peek] < key) return false;

        int lo = 0;
        if (BinarySearch(a, key, lo, peek) != -1) return true;
        return BinarySearch(hi, peek, a, key) != -1;
    }

    // Find the INDEX of peek.
    private static int FindPeek(int[] a) {
        int lo = 0, hi = a.length - 1;
        return FindPeek(a, lo, hi);
    }

    private static int FindPeek(int[] a, int lo, int hi) {
        if (hi - lo < 2) return -1;
        int mid = (hi + lo) / 2;
        if (a[mid] > a[mid - 1] && a[mid] > a[mid + 1]) return mid;
        if (a[mid - 1] > a[mid + 1]) return FindPeek(a, lo, mid);
        return FindPeek(a, mid, hi);
    }

    private static int BinarySearch(int[] a, int key, int lo, int hi) {
        if (hi < lo) return -1;

        int mid = (hi + lo) / 2;

        if (a[mid] < key) return BinarySearch(a, key, mid + 1, hi);
        if (a[mid] > key) return BinarySearch(a, key, lo, mid - 1);
        return mid;
    }

    private static int BinarySearch(int hi, int lo, int[] a, int key) {
        if (hi < lo) return -1;

        int mid = (hi + lo) / 2;

        if (a[mid] > key) return BinarySearch(a, key, mid + 1, hi);
        if (a[mid] < key) return BinarySearch(a, key, lo, mid - 1);
        return mid;
    }

    // 倍率测试
    public static double timeTrial(int N) {
        int[] a = new int[N];
        int MAX = 1000000;
        int mid = StdRandom.uniform(0, N);

        int[] temp1 = new int[mid];
        int[] temp2 = new int[N - mid];
        for (int i = 0; i < mid; i++) {
            temp1[i] = StdRandom.uniform(-MAX, MAX);
        }
        for (int i = 0; i < N - mid; i++) {
            temp2[i] = StdRandom.uniform(-MAX, MAX);
        }
        Arrays.sort(temp1);
        Arrays.sort(temp2);
        if (mid >= 0) System.arraycopy(temp1, 0, a, 0, mid);
        for (int i = mid; i < N; i++) {
            a[i] = temp2[(N - mid) - (i - mid) - 1];
        }

        int key = StdRandom.uniform(-MAX, MAX);
        Stopwatch timer = new Stopwatch();
        isExist(a, key);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        // 实现性测试
//        int[] homework.a = {1, 3, 5, 7, 9, 13, 15, 14, 12, 8, 6, 4, 2};
//        StdOut.println(isExist(homework.a, 11));

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
