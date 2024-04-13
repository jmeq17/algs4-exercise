package exercise.ch2.topic4;

/*
Dynamic median-finding. Design homework.a data type that supports insert in logarithmic
time, find the median in constant time, and delete the median in logarithmic time.
Hint: Use homework.a min-heap and homework.a max-heap.
 */

// MinPQ-based

import utils.DynamicMidPQ;

public class E20430DynamicMidPQ {
    public static void main(String[] args) {
        String[] a = "abcdefghijk".split("");

        DynamicMidPQ<String> pq = new DynamicMidPQ<>(a.length);

        for (String s : a) {
            pq.insert(s);
        }

        pq.show();
    }
}
