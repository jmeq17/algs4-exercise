package utils;

// Stable MinPQ.

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class StableMinPQ<Key extends Comparable<Key>> {
    private final Key[] pq;
    private final int[] order;
    private int N;

    public StableMinPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
        order = new int[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key key) {
        pq[++N] = key;
        order[N] = N;
        swim(N);
    }

    public Key delMin() {
        assert N != 0;

        Key key = pq[1];
        exch(1, N);
        pq[N--] = null;
        sink(1);
        return key;
    }

    public void show() {
        while (!isEmpty()) {
            StdOut.println("order " + order[1] + ", " + delMin());
        }
    }

    private void exch(int i, int j) {
        Key k = pq[i];
        pq[i] = pq[j];
        pq[j] = k;

        int v = order[i];
        order[i] = order[j];
        order[j] = v;
    }

    private void swim(int i) {
        while (i > 1 && pq[i].compareTo(pq[i / 2]) < 0) {
            exch(i, i / 2);
            i = i / 2;
        }
    }

    private void sink(int i) {
        while (2 * i <= N) {
            int j = 2 * i;
            if (j < N) {
                int k = pq[j].compareTo(pq[j + 1]);
                if (k > 0) j++;
                else if (k == 0 && order[j + 1] < order[j]) j++;
            }
            int v = pq[i].compareTo(pq[j]);
            if (v < 0) break;
            if (v == 0 && order[i] < order[j]) break;
            exch(i, j);
            i = j;
        }
    }


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
