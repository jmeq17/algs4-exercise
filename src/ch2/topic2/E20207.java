package ch2.topic2;

/*
Show that the number of compares used by mergesort is monotonically increasing
(C(N+1) > C(N) for all N > 0).
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import utils.Merge;

public class E20207 {
    public static void main(String[] args) {
        Double[] a;
        int oldCom = 0;
        for (int i = 1; i < 1000; i++) {
            a = new Double[i];
            for (int j = 0; j < i; j++) {
                a[j] = StdRandom.uniform();
            }

            Merge.sort(a);
            int newCom = Merge.printCompare();
            StdOut.printf("N = %d, number of compare: %d\n", i, newCom);

            if (newCom < oldCom) {
                StdOut.println("false");
                break;
            }

            oldCom = newCom;
        }
    }
}
