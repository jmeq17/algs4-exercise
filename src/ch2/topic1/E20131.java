package ch2.topic1;

/*
Doubling test. Write homework.a client that performs homework.a doubling test for sort algorithms.
Start at N equal to 1000, and print N, the predicted number of seconds, the actual number
of seconds, and the ratio as N doubles. Use your program to validate that insertion
sort and selection sort are quadratic for random inputs, and formulate and test homework.a hypothesis
for shellsort.
 */

import edu.princeton.cs.algs4.StdOut;
import utils.SortCompare;

public class E20131 {
    public static double timeTrial(String alg1, int N, int T) {
        return SortCompare.timeRandomInput(alg1, N, T);
    }

    public static void main(String[] args) {
        // 第一个排序算法
//        String alg1 = "Selection";
        // 第二个排序算法
//        String alg1 = "Insertion";
        // 第二个排序算法
        String alg1 = "Shell";
        // 运行次数
        int T = 100;
        // 初始数组
        int N = 1000;

        double timeOld = timeTrial(alg1, N, T);
        StdOut.println("size\ttimeOfPredict\ttimeOfActual\tratio");

        for (N += N; true; N += N) {
            double timeNew = timeTrial(alg1, N, T);
            // Selection
//            StdOut.printf("%d\t%.1f\t\t%.1f\t\t%.1f\n", N, timeOld * 4, timeNew, timeNew / timeOld);
            // Insertion
//            StdOut.printf("%d\t%.1f\t\t%.1f\t\t%.1f\n", N, timeOld * 4, timeNew, timeNew / timeOld);
            // Shell
            StdOut.printf("%d\t%.1f\t\t%.1f\t\t%.1f\n", N, timeOld * 3 / 2, timeNew, timeNew / timeOld);
            timeOld = timeNew;
        }
    }
}
