package homework.partII.week5;

// n^2 time

public class MSD_Old {
    private static final int R = 256;
    private static final int CUTOFF = 15;

    public static void sort(String[] a, int[] index) {
        int n = a.length;
        String[] aux = new String[n];
        int[] auxIndex = new int[n];
        sort(a, 0, n - 1, 0, aux, index, auxIndex);
    }

    private static int charAt(String s, int d) {
        assert d >= 0 && d <= s.length();
        if (d == s.length()) return -1;
        return s.charAt(d);
    }

    private static void sort(String[] a, int lo, int hi, int d, String[] aux, int[] index, int[] auxIndex) {
        if (hi <= lo + CUTOFF) {
            insertion(a, index, lo, hi, d);
            return;
        }

        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            count[c + 2]++;
        }

        for (int r = 0; r < R + 1; r++) count[r + 1] += count[r];

        for (int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            aux[count[c + 1]] = a[i];
            auxIndex[count[c + 1]] = index[i];
            count[c + 1]++;
        }

        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo];
            index[i] = auxIndex[i - lo];
        }

        for (int r = 0; r < R; r++)
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1, aux, index, auxIndex);
    }

    private static void insertion(String[] a, int[] index, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j - 1], d); j--)
                exch(a, index, j, j - 1);
    }

    private static void exch(String[] a, int[] index, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;

        int t = index[i];
        index[i] = index[j];
        index[j] = t;
    }

    private static boolean less(String v, String w, int d) {
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) return true;
            if (v.charAt(i) > w.charAt(i)) return false;
        }
        return v.length() < w.length();
    }
}
