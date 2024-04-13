package ch2.topic1;

/*
Shellsort is subquadratic. Use SortCompare to compare shellsort with insertion
sort and selection sort on your computer. Use array sizes that are increasing powers of
2, starting at 128.
 */

import edu.princeton.cs.algs4.StdOut;
import utils.SortCompare;

public class E20127 {

    public static double[] timeTrial(String alg1, String alg2, String alg3, int N, int T) {
        double[] times = new double[3];

        double t1 = SortCompare.timeRandomInput(alg1, N, T);
        double t2 = SortCompare.timeRandomInput(alg2, N, T);
        double t3 = SortCompare.timeRandomInput(alg3, N, T);

        times[0] = t1;
        times[1] = t2;
        times[2] = t3;
        return times;
    }

    public static void main(String[] args) {
        // 第一个排序算法
        String alg1 = args[0];
        // 第二个排序算法
        String alg2 = args[1];
        // 第二个排序算法
        String alg3 = args[2];
        // 运行次数
        int T = Integer.parseInt(args[3]);
        // 初始数组
        int N = 128;

        double[] times = timeTrial(alg1, alg2, alg3, N, T);
        StdOut.printf("For %d, \t '%s' is \t %.1f \t times faster than '%s',\n", N, alg1, times[1] / times[0], alg2);
        StdOut.printf("\t\t\t\t\tand \t %.1f \t times faster than '%s'\n", times[2] / times[0], alg3);
        StdOut.println();

        for (N += N; true; N += N) {
            times = timeTrial(alg1, alg2, alg3, N, T);
            StdOut.printf("For %d, \t '%s' is \t %.1f \t times faster than '%s',\n", N, alg1, times[1] / times[0], alg2);
            StdOut.printf("\t\t\t\t\tand \t %.1f \t times faster than '%s'\n", times[2] / times[0], alg3);
            StdOut.println();
        }
    }
}
