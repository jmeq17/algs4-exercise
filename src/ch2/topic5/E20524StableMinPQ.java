package ch2.topic5;

/*
Stable priority queue. Develop homework.a stable priority-queue implementation (which
returns duplicate keys in the same order in which they were inserted).
 */

import edu.princeton.cs.algs4.StdRandom;
import utils.StableMinPQ;

public class E20524StableMinPQ {
    public static void main(String[] args) {
        int n = 20, i = 0;
        StableMinPQ pq = new StableMinPQ(n);
        while (i++ < n) {
            int v = StdRandom.uniform(1, 5);
            pq.insert(v);
        }
        pq.show();
    }
}
