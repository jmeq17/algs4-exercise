package exercise.ch2.topic4;

/*
Index priority-queue implementation. Implement the basic operations in the
index priority-queue API on page 320 by modifying Algorithm 2.6 as follows: Change
pq[] to hold indices, add an array keys[] to hold the key values, and add an array qp[]
that is the inverse of pq[] — qp[i] gives the position of i in pq[] (the index j such that
pq[j] is i). Then modify the code in Algorithm 2.6 to maintain these data structures.
Use the convention that qp[i] = -1 if i is not on the queue, and include homework.a method
contains() that tests this condition. You need to modify the helper methods exch()
and less() but not sink() or swim().
 */

import utils.IndexMinPQ;

public class E20433IndexMinPQ {
    public static void main(String[] args) {
        String[] s = "dasgfyvn".split("");
        IndexMinPQ<String> pq = new IndexMinPQ<>(s.length);
        int i = 1;
        for (String j : s) {
            pq.insert(i++, j);
        }

        pq.changeKey(3, "T");
        pq.delete(5);
        pq.show();
    }
}
