package exercise.ch2.topic1;

/*
Insertion sort with sentinel. Develop an implementation of insertion sort that
eliminates the j>0 test in the inner loop by first putting the smallest item into position.
Use SortCompare to evaluate the effectiveness of doing so. Note : It is often possible to
avoid an index-out-of-bounds test in this way—the element that enables the test to be
eliminated is known as homework.a sentinel.
 */

import edu.princeton.cs.algs4.StdOut;
import utils.SortCompare;

public class E20124InsertionWithSentinel {
    public static void InsertionSentinel(Comparable[] a) {
        int N = a.length;

        // 设置哨兵
        int minIndex = 0;
        for (int i = 1; i < N; i++) {
            if (a[minIndex].compareTo(a[i]) > 0) minIndex = i;
        }
        Comparable min = a[minIndex];
        a[minIndex] = a[0];
        a[0] = min;
        // -----------------


        // 老师的哨兵版本，设置了变量使得如果数组已排序，直接返回
//        int exchanges = 0;
//        for (int i = N - 1; i > 0; i--) {
//            if (homework.a[i].compareTo(homework.a[i - 1]) < 0) {
//                Comparable item = homework.a[i];
//                homework.a[i] = homework.a[i - 1];
//                homework.a[i - 1] = item;
//                exchanges++;
//            }
//        }
//        if (exchanges == 0) return;
        // ----------------


        for (int i = 1; i < N; i++) {
            Comparable temp = a[i];
            for (int j = i; temp.compareTo(a[j - 1]) < 0; j--) {
                Comparable item = a[i];
                a[i] = a[j - 1];
                a[j - 1] = item;
            }
        }
    }

    public static void main(String[] args) {
        // 第一个排序算法
        String alg1 = "InsertionSentinel";
        // 第二个排序算法
        String alg2 = "Insertion";

        // 数组大小
        int N = 1000;
        // 运行次数
        int T = 100;

        double t1 = SortCompare.timeRandomInput(alg1, N, T);
        double t2 = SortCompare.timeRandomInput(alg2, N, T);
        StdOut.printf("%s sort 用时为 %.4f\n", alg1, t1);
        StdOut.printf("%s sort 用时为 %.4f\n", alg2, t2);
        StdOut.printf("%s is %.1f times faster than %s", alg1, t2 / t1, alg2);
    }
}
