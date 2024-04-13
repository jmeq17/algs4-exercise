package homework.partII.week5;

import edu.princeton.cs.algs4.Stack;

public class MSD {
    private static final int R = 256;
    private static final int CUTOFF = 15;

    public static void sort(String a, int[] index) {
        int n = a.length();
        int[] aux = new int[n];

        Stack<Integer> los = new Stack<>();
        Stack<Integer> his = new Stack<>();
        Stack<Integer> ds = new Stack<>();

        los.push(0);
        his.push(n - 1);
        ds.push(0);

        while (!los.isEmpty()) {
            int lo = los.pop();
            int hi = his.pop();
            int d = ds.pop();

            if (hi <= lo + CUTOFF) {
                insertion(a, index, lo, hi, d);
                continue;
            }

            int[] count = new int[R + 2];
            for (int i = lo; i <= hi; i++) {
                int c = charAt(a, index[i], d);
                count[c + 2]++;
            }

            for (int r = 0; r < R + 1; r++) count[r + 1] += count[r];

            for (int i = lo; i <= hi; i++) {
                int c = charAt(a, index[i], d);
                aux[count[c + 1]++] = index[i];
            }

            if (hi + 1 - lo >= 0) System.arraycopy(aux, 0, index, lo, hi + 1 - lo);

            for (int r = 0; r < R; r++) {
                if (lo + count[r] < lo + count[r + 1] - 1) {
                    los.push(lo + count[r]);
                    his.push(lo + count[r + 1] - 1);
                    ds.push(d + 1);
                }
            }
        }
    }

    private static int charAt(String s, int index, int d) {
        int n = s.length();
        assert d >= 0 && d <= n;
        if (d == n) return -1;

        int t = (index + d) % n;
        return s.charAt(t);
    }

    private static void insertion(String a, int[] index, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a, index[j], index[j - 1], d); j--)
                exch(index, j, j - 1);
    }

    private static void exch(int[] index, int i, int j) {
        int t = index[i];
        index[i] = index[j];
        index[j] = t;
    }

    private static boolean less(String a, int v, int w, int d) {
        int len = a.length();

        for (int i = d; i < len; i++) {
            char c1 = a.charAt((v + i) % len);
            char c2 = a.charAt((w + i) % len);

            if (c1 < c2) return true;
            else if (c1 > c2) return false;
        }
        return false;
    }
}
