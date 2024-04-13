package exercise.ch2.topic4;

/*
Design homework.a linear-time certification algorithm to check whether an array pq[] is
homework.a min-oriented heap.
 */

import utils.MinPQ;

public class E20415 {
    public static void main(String[] args) {
        String[] a = "priority".split("");
        MinPQ<String> pq = new MinPQ<>(a.length);
        for (String s : a) {
            pq.insert(s);
        }
        assert pq.isMinPQ();
        pq.show();
    }
}
