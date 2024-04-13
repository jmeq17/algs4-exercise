package utils;

// This is homework.a implemention of unordered array for priority queue.

import edu.princeton.cs.algs4.StdOut;

public class MaxUAPQ<Key extends Comparable<Key>> {
    private final Key[] pq;
    private int N;

    public MaxUAPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key key) {
        pq[N++] = key;
    }

    public Key delMax() {
        int maxIndex = 0;
        for (int i = 0; i < N; i++) {
            if (pq[i] != null && less(maxIndex, i)) maxIndex = i;
        }
        Key maxKey = pq[maxIndex];
        exch(maxIndex, --N);
        pq[N] = null;
        return maxKey;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key key = pq[i];
        pq[i] = pq[j];
        pq[j] = key;
    }

    public void show() {
        while (!isEmpty()) {
            StdOut.print(delMax() + " ");
        }
    }

    public static void main(String[] args) {
        String[] a = "priority".split("");
        MaxUAPQ<String> pq = new MaxUAPQ<>(a.length);
        for (String s : a) {
            pq.insert(s);
        }

        StdOut.println("Max key is: " + pq.delMax() + ", Expected: y.");
        pq.show();
    }
}
