package ch2.topic3;

/*
Find the expected number of subarrays of size 0, 1, and 2 when quicksort is used
to sort an array of N items with distinct keys. If you are mathematically inclined, do the
math; if not, run some experiments to develop hypotheses.
 */

import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdRandom;
import utils.CreateArrayUtils;

public class E20307 {
    public static int num0;
    public static int num1;
    public static int num2;

    public static void sort(Comparable[] a) {
        num0 = 0;
        num1 = 0;
        num2 = 0;
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        if (j - 1 < lo) num0++;
        else if (j - 1 - lo == 0) num1++;
        else if (j - 1 - lo == 1) num2++;
        if (hi < j + 1) num0++;
        else if (hi - j - 1 == 0) num1++;
        else if (hi - j - 1 == 1) num2++;
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }


    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) ;
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
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    public static int[] returnNums() {
        return new int[]{num0, num1, num2};
    }

    public static void Caculation(Out out, int count) {
        int MAX = 1000000;
        int size = StdRandom.uniformInt(1000000);

        out.println("数组长度为：" + size);
        out.println(count + "：");

        Comparable[] a = CreateArrayUtils.RandomComIntArray(size, MAX);
        E20307.sort(a);

        int[] nums = E20307.returnNums();
        out.println("0数组数量为：" + nums[0] + "\n" + "1数组数量为：" + nums[1] + "\n" + "2数组数量为：" + nums[2]);
        out.println("homework.a.length/nums0 = " + (float) a.length / nums[0]);
        out.println("homework.a.length/nums1 = " + (float) a.length / nums[1]);
        out.println("homework.a.length/nums2 = " + (float) a.length / nums[2]);

        out.println();
    }

    public static void main(String[] args) {
        Out out = new Out("./files/E20307.txt");
        for (int i = 0; i < 100; i++) {
            Caculation(out, i);
        }
        out.close();
    }
}
