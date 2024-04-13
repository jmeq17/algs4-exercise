package utils;

import exercise.ch2.classForExercise.InsertionX;
import exercise.ch2.topic1.E20124InsertionWithSentinel;
import exercise.ch2.topic1.E20125InsertionsSortWithoutExchanges;
import exercise.ch2.topic2.*;
import exercise.ch2.topic3.E20311;
import exercise.ch2.topic3.E20317Sentinels;
import exercise.ch2.topic3.E20319MedianOfFive;
import exercise.ch2.topic3.E20322FastThreePartition;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class SortCompare {
    public static double time(String alg, Comparable<Double>[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) Insertion.sort(a);
        if (alg.equals("Selection")) Selection.sort(a);
        if (alg.equals("Shell")) Shell.sort(a);
        if (alg.equals("Merge")) Merge.sort(a);
        if (alg.equals("MergeBU")) MergeBU.sort(a);
        if (alg.equals("Quick")) Quick.sort(a);
        if (alg.equals("E20317")) E20317Sentinels.sort(a);
        if (alg.equals("HeapSwim")) Heap.sortSwim(a);
        if (alg.equals("HeapSink")) Heap.sortSink(a);
        if (alg.equals("E20125InsertionsSortWithoutExchanges")) E20125InsertionsSortWithoutExchanges.sort(a);
        if (alg.equals("InsertionSentinel")) E20124InsertionWithSentinel.InsertionSentinel(a);
        if (alg.equals("InsertionX")) InsertionX.sort(a);
        if (alg.equals("E20211ImprovementsForMerge")) E20211ImprovementsForMerge.sort(a);
        if (alg.equals("E20210FastMerge")) E20210FastMerge.sort(a);
        if (alg.equals("E20211ImprovementOne")) E20211ImprovementOne.sort(a);
        if (alg.equals("E20211ImprovementTwo")) E20211ImprovementTwo.sort(a);
        if (alg.equals("E20211ImprovementThree")) E20211ImprovementThree.sort(a);
        if (alg.equals("E20209NonStaticArray")) E20209NonStaticArray.sort(a);
        if (alg.equals("E20226CreatAuxInMerge")) E20226CreatAuxInMerge.sort(a);
        if (alg.equals("E20311")) E20311.sort(a);
        if (alg.equals("E20319MedianOfFive")) E20319MedianOfFive.sort(a);
        if (alg.equals("E20322FastThreePartition")) E20322FastThreePartition.sort(a);
//        if (alg.equals("Demo")) Demo.sort(homework.a);
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];

        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++)
                a[i] = StdRandom.uniformDouble();
            total += time(alg, a);
        }
        return total;
    }


    // client
    public static void main(String[] args) {
        // 第一个排序算法
        String alg1 = "HeapSink";
        // 第二个排序算法
        String alg2 = "Merge";

        // 数组大小
        int N = 1000;
        // 运行次数
        int T = 100;

        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        StdOut.printf("%s sort 用时为 %.4f\n", alg1, t1);
        StdOut.printf("%s sort 用时为 %.4f\n", alg2, t2);
        StdOut.printf("%s is %.1f times faster than %s", alg1, t2 / t1, alg2);
    }
}
