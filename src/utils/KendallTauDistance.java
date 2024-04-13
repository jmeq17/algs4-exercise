package utils;

import edu.princeton.cs.algs4.StdOut;

public class KendallTauDistance {
    private static int[] aux_b;
    private static int[] aux;
    private static int inversions;

    // 如果不想改变 homework.a，可以再创建一个辅助数组克隆 homework.a
    private static int[] a;

    public static int distance(int[] a, int[] b) {
        int n = a.length;
        assert n == b.length;

        inversions = 0;

        aux = new int[n];
        aux_b = new int[n];
        for (int i = 0; i < n; i++) {
            aux_b[b[i]] = i;
        }

        // 改变 homework.a
//        distance(homework.a, 0, n - 1);
//        for (int i = 0; i < n; i++) {
//            assert homework.a[i] == b[i];
//        }

        // 不改变 homework.a 的做法
        KendallTauDistance.a = a.clone();
        distance(0, n - 1);
        for (int i = 0; i < n; i++) {
            assert KendallTauDistance.a[i] == b[i];
        }
        // ----------------

        return inversions;
    }

    // 改变 homework.a
    private static void distance(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = (hi + lo) / 2;
        distance(a, lo, mid);
        distance(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    private static void merge(int[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;

        if (hi + 1 - lo >= 0) System.arraycopy(a, lo, aux, lo, hi + 1 - lo);

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux_b[aux[i]] > aux_b[aux[j]]) {
                a[k] = aux[j++];
                inversions += (mid - i + 1);
            } else {
                a[k] = aux[i++];
            }
        }
    }

    // 不改变 homework.a
    private static void distance(int lo, int hi) {
        if (hi <= lo) return;
        int mid = (hi + lo) / 2;
        distance(lo, mid);
        distance(mid + 1, hi);
        merge(lo, mid, hi);
    }

    private static void merge(int lo, int mid, int hi) {
        int i = lo, j = mid + 1;

        if (hi + 1 - lo >= 0) System.arraycopy(a, lo, aux, lo, hi + 1 - lo);

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux_b[aux[i]] > aux_b[aux[j]]) {
                a[k] = aux[j++];
                inversions += (mid - i + 1);
            } else {
                a[k] = aux[i++];
            }
        }
    }


    public static void main(String[] args) {
        // two random permutation of size n
        int n = Integer.parseInt(args[0]);
        int[] a = KendallTau.permutation(n);
        int[] b = KendallTau.permutation(n);

        // print initial permutation
        for (int i = 0; i < n; i++)
            StdOut.println(a[i] + " " + b[i]);
        StdOut.println();

        // My code.
        StdOut.println("inversions = " + distance(a, b));
        // Algs4's code.
        StdOut.println("inversions of algs4 = " + KendallTau.distance(a, b));
    }
}
