package ch3.topic1;

/*
Performance validation II. Explain why the performance of BinarySearchST
and SequentialSearchST for FrequencyCounter is even better than predicted by
analysis.
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import utils.BinarySearchST;

public class E30136 {
    public static void FrequencyCounter(String fileName, int N, int length) {
        In in = new In(fileName);
        BinarySearchST<String, Integer> st = new BinarySearchST<>(N);

        for (int i = 0; i < N; i++) {
            String word = in.readString();
            if (word.length() < length) continue;
            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);
        }

        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) if (st.get(word) > st.get(max)) max = word;
        st.get(max);
    }


    public static void main(String[] args) {
        String fileName = "files/tale2";
        double oldTime = 0, newTime;

        StdOut.printf("size\ttime\tratio\n");
        for (int N = 250; N < 130000; N += N) {
            Stopwatch timer = new Stopwatch();
            FrequencyCounter(fileName, N, 13);
            newTime = timer.elapsedTime();
            StdOut.printf("%7d\t%5.1f\t%5.1f\n", N, newTime, newTime / oldTime);
            oldTime = newTime;
        }
    }
}
