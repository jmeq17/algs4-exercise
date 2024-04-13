package ch2.topic2;

/*
Improvements. Run empirical studies to evaluate the effectiveness of each of the
three improvements to mergesort that are described in the text (see Exercise 2.2.11).
Also, compare the performance of the merge implementation given in the text with the
merge described in Exercise 2.2.10. In particular, empirically determine the best value
of the parameter that decides when to switch to insertion sort for small subarrays.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import utils.SortCompare;
import utils.CreateArrayUtils;

public class E20223Improvements {
    public static void main(String[] args) {
        String alg1 = "Merge";
        String alg2 = "E20211ImprovementOne";
        String alg3 = "E20211ImprovementTwo";
        String alg4 = "E20211ImprovementThree";
        String alg5 = "E20211ImprovementsForMerge";
        String alg6 = "E20210FastMerge";

        int N = 10000;
        int T = 1000;

        double t1 = SortCompare.timeRandomInput(alg1, N, T);
        double t2 = SortCompare.timeRandomInput(alg2, N, T);
        double t3 = SortCompare.timeRandomInput(alg3, N, T);
        double t4 = SortCompare.timeRandomInput(alg4, N, T);
        double t5 = SortCompare.timeRandomInput(alg5, N, T);
        double t6 = SortCompare.timeRandomInput(alg6, N, T);

        StdOut.printf("%s sort 用时为 %.4f\n", alg1, t1);
        StdOut.printf("%s sort 用时为 %.4f\n", alg2, t2);
        StdOut.printf("%s sort 用时为 %.4f\n", alg3, t3);
        StdOut.printf("%s sort 用时为 %.4f\n", alg4, t4);
        StdOut.printf("%s sort 用时为 %.4f\n", alg5, t5);
        StdOut.printf("%s sort 用时为 %.4f\n", alg6, t6);

        StdOut.printf("%s is %.1f times faster than %s.\n", alg2, t1 / t2, alg1);
        StdOut.printf("%s is %.1f times faster than %s.\n", alg3, t1 / t3, alg1);
        StdOut.printf("%s is %.1f times faster than %s.\n", alg4, t1 / t4, alg1);
        StdOut.printf("%s is %.1f times faster than %s.\n", alg5, t1 / t5, alg1);
        StdOut.printf("%s is %.1f times faster than %s.\n", alg6, t1 / t6, alg1);

        StdOut.println();

        Comparable[] a = CreateArrayUtils.RandomComDoubleArray(100000);
        double min = 1;
        int cutoff = 0;
        for (int i = 2; i < 30; i++) {
            Stopwatch timer = new Stopwatch();
            E20211ImprovementOne.sort(a, i);
            double time = timer.elapsedTime();

            if (time < min) {
                min = time;
                cutoff = i;
            }
//            StdOut.printf("Cutoff = %d, time = %.4f.\n", i, timer.elapsedTime());
        }
        StdOut.printf("The best cutoff is: %d.\n", cutoff);
    }
}
