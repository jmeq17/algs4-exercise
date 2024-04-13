package exercise.ch2.topic3;

/*
Median-of-5 partitioning. Implement homework.a quicksort based on partitioning on the
median of homework.a random sample of five items from the subarray. Put the items of the sample
at the appropriate ends of the array so that only the median participates in partitioning.
Run doubling tests to determine the effectiveness of the change, in comparison both
to the standard algorithm and to median-of-3 partitioning (see the previous exercise).
Extra credit : Devise homework.a median-of-5 algorithm that uses fewer than seven compares on
any input.
 */

import utils.CreateArrayUtils;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import utils.SortCompare;

public class E20319MedianOfFive {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;

//        Comparable v = findPivot(homework.a, lo, hi);
        findPivot2(a, lo, hi);
        Comparable v = a[lo];

        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable k = a[i];
        a[i] = a[j];
        a[j] = k;
    }

    private static Comparable findPivot(Comparable[] a, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        int lomid = lo + (mid - lo) / 2;
        int himid = mid + (hi - mid) / 2;

        // 等于 6 次的比较方法
        if (less(a[lomid], a[lo])) exch(a, lo, lomid);
        if (less(a[himid], a[mid])) exch(a, mid, himid);
        // 第三次比较找出一个元素大于另外三个元素，肯定不是中位数，排除
        if (less(a[himid], a[lomid])) {
            if (less(a[hi], a[lo])) exch(a, lo, hi);
            // 第五次比较找出另一个元素大于三个元素，肯定不是中位数，排除
            if (less(a[hi], a[himid])) {
                // 第六次比较在剩下的三个元素中最大的就是中位数
                if (less(a[mid], a[hi])) return a[hi];
                else return a[mid];
            } else {
                if (less(a[lo], a[himid])) return a[himid];
                else return a[lo];
            }
        } else {
            if (less(a[hi], a[mid])) exch(a, mid, hi);
            if (less(a[lomid], a[hi])) {
                if (less(a[lomid], a[mid])) return a[mid];
                else return a[lomid];
            } else {
                if (less(a[lo], a[hi])) return a[hi];
                else return a[lo];
            }
        }

        // MergeSort 的比较次数小于等于7
        // 略
    }

    private static void findPivot2(Comparable[] a, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        int lomid = lo + (mid - lo) / 2;
        int himid = mid + (hi - mid) / 2;
        int middle;

        // 等于 6 次的比较方法
        if (less(a[lomid], a[lo])) exch(a, lo, lomid);
        if (less(a[himid], a[mid])) exch(a, mid, himid);
        // 第三次比较找出一个元素大于另外三个元素，肯定不是中位数，排除
        if (less(a[himid], a[lomid])) {
            if (less(a[hi], a[lo])) exch(a, lo, hi);
            // 第五次比较找出另一个元素大于三个元素，肯定不是中位数，排除
            if (less(a[hi], a[himid])) {
                // 第六次比较在剩下的三个元素中最大的就是中位数
                if (less(a[mid], a[hi])) middle = hi;
                else middle = mid;
            } else {
                if (less(a[lo], a[himid])) middle = himid;
                else middle = lo;
            }
        } else {
            if (less(a[hi], a[mid])) exch(a, mid, hi);
            if (less(a[lomid], a[hi])) {
                if (less(a[lomid], a[mid])) middle = mid;
                else middle = lomid;
            } else {
                if (less(a[lo], a[hi])) middle = hi;
                else middle = lo;
            }
        }

        exch(a, lo, middle);

        // MergeSort 的比较次数小于等于7
        // 略
    }

    public static void main(String[] args) {
        StdOut.println("size\t|\ttime for E20319\t|\ttime for Quick");
        int scale = 1000000;
        for (int N = 250; ; N *= 2) {
            Comparable[] a = CreateArrayUtils.RandomComIntArray(N, scale);
            double e20319 = SortCompare.time("E20319MedianOfFive", a);
            double quick = SortCompare.time("Quick", a);
            StdOut.println(N + "\t|\t" + e20319 + "\t|\t" + quick);
        }
    }
}
