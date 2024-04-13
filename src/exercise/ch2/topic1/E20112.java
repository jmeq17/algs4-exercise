package exercise.ch2.topic1;

/*
Instrument shellsort to print the number of compares divided by the array size
for each increment. Write homework.a test client that tests the hypothesis that this number is homework.a
small constant, by sorting arrays of random Double values, using array sizes that are
increasing powers of 10, starting at 100.
 */

import edu.princeton.cs.algs4.StdOut;
import utils.SortCompare;

public class E20112 {

    public static double timeTrial(String alg1, int N, int T) {
        return SortCompare.timeRandomInput(alg1, N, T);
    }

    public static void main(String[] args) {
        // 排序算法
        String alg1 = "Shell";
        // 运行次数
        int T = 1;
        // 初始数组
        int N = 100;

        StdOut.printf("For %d, \n", N);
        timeTrial(alg1, N, T);

        for (N *= 10; true; N *= 10) {
            StdOut.printf("For %d, \n", N);
            timeTrial(alg1, N, T);
        }
    }
}
