package ch2.topic5;

/*
Implement homework.a method String[] dedup(String[] homework.a) that returns the objects in
homework.a[] in sorted order, with duplicates removed.
 */

import edu.princeton.cs.algs4.StdOut;
import utils.Quick;

public class E20504 {
    public static String[] dedup(String[] a) {
        Quick.sort(a);

        int N = a.length, n = N;
        for (int i = 0; i < N - 1; i++) {
            if (a[i].equals(a[i + 1])) {
                n--;
            }
        }

        String[] distinct = new String[n];

        distinct[0] = a[0];
        for (int i = 1, j = 1; i < N; i++) {
            if (!a[i].equals(a[i - 1]))
                distinct[j++] = a[i];
        }
        return distinct;
    }

    public static void main(String[] args) {
        String[] a = "avabodcrvarrzjojbo".split("");

        String[] dis = dedup(a);
        for (String i : dis) {
            StdOut.print(i + " ");
        }
    }
}
