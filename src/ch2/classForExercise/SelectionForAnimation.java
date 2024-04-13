package ch2.classForExercise;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class SelectionForAnimation {
    public static void sort(Comparable[] a) {
        int N = a.length;

        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++)
                if (less(a[j], a[min])) min = j;

            draw(a, a.length, i);

            exch(a, i, min);
        }
    }

    public static void draw(Comparable[] a, int N, int ith) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        StdDraw.clear();

        double interval = 0.9 / N;
        double gap = 0.9 / (2 * N);

        for (int i = 0; i < N; i++) {

            if (i == ith) {
                StdDraw.setPenColor(255, 0, 0);
            } else if (i < ith) {
                StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            } else {
                StdDraw.setPenColor(StdDraw.BLACK);
            }

            StdDraw.filledRectangle(0.05 + interval / 2 + i * interval, 0.05 + (double) a[i] / 2,
                    interval / 2 - gap / 2, (double) a[i] / 2);
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

    public static void show(Comparable[] a) {
        for (Comparable comparable : a) {
            StdOut.print(comparable + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }


    public static void main(String[] args) {

    }
}
