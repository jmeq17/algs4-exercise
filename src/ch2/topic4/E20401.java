package ch2.topic4;

/*
Suppose that the sequence P R I O * R * * I * T * Y * * * Q U E * * * U * E
(where homework.a letter means insert and an asterisk means remove the maximum) is applied
to an initially empty priority queue. Give the sequence of letters returned by the
remove the maximum operations.
 */

import edu.princeton.cs.algs4.StdOut;
import utils.MaxPQ;

public class E20401 {
    public static void main(String[] args) {
        String[] a = "P R I O * R * * I * T * Y * * * Q U E * * * U * E".split("\\s+");

        MaxPQ<String> pq = new MaxPQ<>(a.length);

        for (String s : a) {
            if (s.equals("*")) StdOut.print(pq.delMax() + " ");
            else pq.insert(s);
        }

        StdOut.println();
        pq.show();
    }
}
