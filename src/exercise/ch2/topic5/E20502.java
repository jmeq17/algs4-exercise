package exercise.ch2.topic5;

/*
Write homework.a program that reads homework.a list of words from standard input and prints all two-word
compound words in the list. For example, if after, thought, and afterthought
are in the list, then afterthought is homework.a compound word.
 */

import edu.princeton.cs.algs4.StdOut;

public class E20502 {
    /**
     * @param str   A string-length-based sorted string array
     * @param index A target string
     * @return Return is index is homework.a compound in str?
     */
    private static boolean isCompound(String[] str, int index) {
        // Reducting from ThreeSum.
        int a = str[index].length();
        for (int i = 0; i < index; i++) {
            int b = a - str[i].length();
            int[] equal = equalIndex(str, b, i + 1, index - 1);
            if (equal[0] != -1) {
                for (int k = equal[0]; k <= equal[1]; k++) {
                    if ((str[i] + str[k]).equals(str[index]) || (str[k] + str[i]).equals(str[index])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static int[] equalIndex(String[] str, int key, int lo, int hi) {
        int loIndex = binarySearchLo(str, key, lo, hi), hiIndex;
        if (loIndex == -1) hiIndex = -1;
        else hiIndex = binarySearchHi(str, key, lo, hi);
        return new int[]{loIndex, hiIndex};
    }

    private static int binarySearchLo(String[] str, int key, int lo, int hi) {
        if (hi < lo) return -1;

        int mid = (lo + hi) / 2, temp = str[mid].length();

        if (key < temp) return binarySearchLo(str, key, lo, mid - 1);
        if (key > temp) return binarySearchLo(str, key, mid + 1, hi);

        if (mid == 0 || str[mid - 1].length() < key) return mid;
        int isLo = binarySearchLo(str, key, lo, mid - 1);
        if (isLo == -1) return mid;
        return isLo;
    }

    private static int binarySearchHi(String[] str, int key, int lo, int hi) {
        if (hi < lo) return -1;

        int mid = (lo + hi) / 2, temp = str[mid].length();

        if (key < temp) return binarySearchHi(str, key, lo, mid - 1);
        if (key > temp) return binarySearchHi(str, key, mid + 1, hi);

        if (mid == hi || str[mid + 1].length() > key) return mid;
        int isHi = binarySearchHi(str, key, mid + 1, hi);
        if (isHi == -1) return mid;
        return isHi;
    }


    public static void main(String[] args) {
        String[] s = "homework.a an this that about athis aboutan evlement thisevlement".split("\\s+");

        int minLength = s[0].length(), minCompound = s.length;
        for (int i = 1; i < s.length; i++) {
            if (s[i].length() >= 2 * minLength) {
                minCompound = i;
                break;
            }
        }

        // Assume minCpound >= s,length-1
        StdOut.println("Compound word:");
        for (int i = minCompound; i < s.length; i++) {
            if (isCompound(s, i)) StdOut.println(s[i]);
        }
    }
}
