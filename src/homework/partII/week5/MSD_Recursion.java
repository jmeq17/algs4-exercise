package homework.partII.week5;

/**
 * Using index of string to express string array rathan than directly using of string array.
 * This class is homework.a combination of HW.PartII.Week5.MSD and LSD, because that the comparation of character is from left to right but the length of all string is equeal.
 * So this is not necessary to maintain end character.
 */

public class MSD_Recursion {
    private static final int R = 256;
    private static final int CUTOFF = 15;

    public static void sort(String a, int[] index) {
        int n = a.length();
        int[] aux = new int[n];
//        sort(homework.a, 0, n - 1, 0, aux, index);
        sort1(a, 0, n - 1, 0, aux, index);
//        sort2(homework.a, 0, n - 1, 0, aux, index);
    }

    private static int charAt(String s, int index, int d) {
        int n = s.length();
        assert d >= 0 && d <= n;
        if (d == n) return -1;

        int t = (index + d) % n;
        return s.charAt(t);
    }

    // invalid
    private static void sort(String a, int lo, int hi, int d, int[] aux, int[] index) {
        if (hi <= lo + CUTOFF) {
            insertion(a, index, lo, hi, d);
            return;
        }

//        if (lo >= hi) return;

//        if (d == 17 && hi == 16)
//            System.out.println();

        int[] count = new int[R + 1];
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a, index[i], d);
            count[c + 1]++;
        }

        for (int r = 0; r < R; r++) count[r + 1] += count[r];

        for (int i = lo; i <= hi; i++) {
            int c = charAt(a, index[i], d);
//            if (c == -1)
//                System.out.println();
            aux[count[c]++] = index[i];
        }

        if (hi + 1 - lo >= 0) System.arraycopy(aux, 0, index, lo, hi + 1 - lo);

        for (int r = 0; r < R; r++)
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1, aux, index);
    }

    // The sort implementation of version 2 combination the feature of HW.PartII.Week5.MSD and LSD, but will stick when reach the end of string
    // There are three solution:

    // Method 1, modify back to HW.PartII.Week5.MSD, discard LSD
    private static void sort1(String a, int lo, int hi, int d, int[] aux, int[] index) {
        if (hi <= lo + CUTOFF) {
            insertion(a, index, lo, hi, d);
            return;
        }

//        if (lo >= hi) return;

//        if (d == 17 && hi == 16)
//            System.out.println();

        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a, index[i], d);
            count[c + 2]++;
        }

        for (int r = 0; r < R + 1; r++) count[r + 1] += count[r];

        for (int i = lo; i <= hi; i++) {
            int c = charAt(a, index[i], d);
//            if (c == -1)
//                System.out.println();
            aux[count[c + 1]++] = index[i];
        }

        if (hi + 1 - lo >= 0) System.arraycopy(aux, 0, index, lo, hi + 1 - lo);

        for (int r = 0; r < R; r++)
            sort1(a, lo + count[r], lo + count[r + 1] - 1, d + 1, aux, index);
    }

    // Method 2, add extra detection addition to avoid program processing when reach the end of string
    private static void sort2(String a, int lo, int hi, int d, int[] aux, int[] index) {
        if (hi <= lo + CUTOFF) {
            insertion(a, index, lo, hi, d);
            return;
        }

        if (d == a.length() || hi <= lo) return;

//        if (d == 17 && hi == 16)
//            System.out.println();

        int[] count = new int[R + 1];
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a, index[i], d);
            count[c + 1]++;
        }

        for (int r = 0; r < R; r++) count[r + 1] += count[r];

        for (int i = lo; i <= hi; i++) {
            int c = charAt(a, index[i], d);
//            if (c == -1)
//                System.out.println();
            aux[count[c]++] = index[i];
        }

        if (hi + 1 - lo >= 0) System.arraycopy(aux, 0, index, lo, hi + 1 - lo);

        for (int r = 0; r < R; r++)
            sort2(a, lo + count[r], lo + count[r + 1] - 1, d + 1, aux, index);
    }

    // Method 3, like LSD, using iteration rather than recursion. In this implemention, the argument d is not needed.
    // This method is invalid, because the digits in the low position while upset the order of the high position.
    public static void sort3(String a, int[] index) {
        int n = a.length();
        int[] aux = new int[n];

        for (int d = n - 1; d >= 0; d--) {
            int[] count = new int[R + 1];
            for (int i = 0; i < n; i++) count[a.charAt((i + d) % n) + 1]++;

            for (int r = 0; r < R; r++) count[r + 1] += count[r];

            for (int i = 0; i < n; i++) aux[count[a.charAt((index[i] + d) % n)]++] = index[i];

            System.arraycopy(aux, 0, index, 0, n);
        }

//        insertion(homework.a, index, 0, n - 1, Math.max(n - CUTOFF, 0));
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

    public static void main(String[] args) {
        String a = "ABRACADABRA!";
        int[] index = new int[a.length()];

        for (int i = 0; i < a.length(); i++) index[i] = i;

        sort(a, index);
        System.out.println();
    }
}
