package ch1.topic1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class E10115 {
    public static int[] histogram(int[] a, int M) {
        int[] b = new int[M];
        for (int i = 0; i < M; i++) {
            b[i] = countTimes(a, i);
        }
        return b;
    }

    public static int countTimes(int[] a, int i) {
        int count = 0;
        for (int j : a) {
            if (i == j) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        StdOut.print("Please input the length of array homework.a: ");
        int N = StdIn.readInt();
        StdOut.print("Please input the length of array b: ");
        int M = StdIn.readInt();

        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(1, 10);
        }

        int[] b = histogram(a, M);
        for (int i = 0; i < M; i++) {
            StdOut.print(b[i] + " ");
        }
    }
}
