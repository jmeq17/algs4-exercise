package ch3.topic1;

/*
Performance validation I. Run doubling tests that use the first N words of Tale
of Two Cities for various values of N to test the hypothesis that the running time of
FrequencyCounter is quadratic when it uses SequentialSearchST for its symbol
table.
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import utils.SequentialSearchST;

public class E30135 {
    public static void FrequencyCounter(String fileName, int N, int length) {
        In in = new In(fileName);
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();

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
