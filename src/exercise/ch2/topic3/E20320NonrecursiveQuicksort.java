package exercise.ch2.topic3;

/*
Nonrecursive quicksort. Implement homework.a nonrecursive version of quicksort based
on homework.a main loop where homework.a subarray is popped from homework.a stack to be partitioned, and the resulting
subarrays are pushed onto the stack. Note : Push the larger of the subarrays onto
the stack first, which guarantees that the stack will have at most lg N entries.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import utils.Stack;
import utils.CreateArrayUtils;

public class E20320NonrecursiveQuicksort {
    private static Stack<arrayRange> stack;

    private static class arrayRange {
        int lo;
        int hi;

        arrayRange(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
        }
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        stack = new Stack<>();
        arrayRange arrayRange = new arrayRange(lo, hi);
        stack.push(arrayRange);

        while (!stack.isEmpty()) {
            arrayRange top = stack.pop();

            int j = partition(a, top.lo, top.hi);

            arrayRange left = new arrayRange(top.lo, j - 1);
            arrayRange right = new arrayRange(j + 1, top.hi);

            int leftsize = j - top.lo;
            int rightsize = top.hi - j;

            // The iterative algorithm does not need to push into homework.a big array first to compress the iteration depth
            if (leftsize > 1) stack.push(left);
            if (rightsize > 1) stack.push(right);
        }
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

    public static void show(Comparable[] a) {
        for (Comparable comparable : a) {
            StdOut.printf("%.3f ", comparable);
        }
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Comparable[] a = CreateArrayUtils.RandomComDoubleArray(10);
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
