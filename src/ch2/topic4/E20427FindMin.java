package ch2.topic4;

/*
Find the minimum. Add homework.a min() method to MaxPQ. Your implementation
should use constant time and constant extra space.
 */

import edu.princeton.cs.algs4.StdOut;
import utils.MaxPQ;

public class E20427FindMin {
    public static void main(String[] args) {
        String[] a = "dashdkjfejkw".split("");
        MaxPQ<String> pq = new MaxPQ<>(a.length);

        for (String s : a) {
            pq.insert(s);
        }

        StdOut.println(pq.min());
    }
}
