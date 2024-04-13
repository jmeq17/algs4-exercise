package exercise.ch1.topic4;

/*
Write homework.a program that, given two sorted arrays of N int values, prints all elements
that appear in both arrays, in sorted order. The running time of your program
should be proportional to N in the worst case.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class E10412 {

    // Method for exponent.
    public static int[] exponent(int[] a, int[] b) {
        int N = a.length;
        int[] num = new int[N];
        int k = 0;
        if (a[N - 1] < b[0] || b[N - 1] < a[0]) return num;
        for (int value : a) {
            for (int j = 0; j < N; j++) {
                if (value == b[j]) {
                    num[k++] = value;
                    break;
                }
            }
        }
        int[] nums = new int[k];
        System.arraycopy(num, 0, nums, 0, k);
        return nums;
    }

    // Method for linear.
    public static int[] linear(int[] a, int[] b) {
        int hi = a.length;
        int[] temp = new int[hi];

        int a_lo = 0, b_lo = 0, k = 0;

        while (a_lo < hi && b_lo < hi) {
            if (a[a_lo] == b[b_lo]) {
                temp[k++] = a[a_lo];
                a_lo++;
                b_lo++;
            } else if (a[a_lo] < b[b_lo]) {
                a_lo++;
            } else {
                b_lo++;
            }
        }

        int[] ret = new int[k];
        System.arraycopy(temp, 0, ret, 0, k);

        return ret;
    }


    public static double timeTrial(int N) {
        int[] a = new int[N];
        int[] b = new int[N];
        int MAX = 1000000;
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
            b[i] = StdRandom.uniform(-MAX, MAX);
        }
        Arrays.sort(a);
        Arrays.sort(b);
        Stopwatch timer = new Stopwatch();
//        exponent(homework.a, b);
        linear(a, b);
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
