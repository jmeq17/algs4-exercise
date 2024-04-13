package ch2.topic3;

/*
Give homework.a code fragment that sorts an array that is known to consist of items having
just two distinct keys.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class E20305 {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        int gt = a.length - 1;
        if (gt <= 0) return;
        int lt = 0, i = 1;

        Comparable v = a[0];
        while (i <= gt) {
            int mnt = a[i].compareTo(v);
            if (mnt < 0) exch(a, lt++, i++);
            else if (mnt > 0) exch(a, i, gt--);
            else i++;
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (Comparable comparable : a) {
            StdOut.print(comparable + " ");
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
        String[] a = "gfgfgfgfffgffgfgffffgggfffgfg".split("");
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
