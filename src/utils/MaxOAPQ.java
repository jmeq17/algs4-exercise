package utils;

// This is homework.a implemention of ordered array for priority queue.

import edu.princeton.cs.algs4.StdOut;

public class MaxOAPQ<Key extends Comparable<Key>> {
    private final Key[] pq;
    private int N;

    public MaxOAPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key key) {
        int index = binarySearch(key);
        if (N - index >= 0) System.arraycopy(pq, index, pq, index + 1, N - index);
        pq[index] = key;
        N++;
    }

    public Key delMax() {
        Key key = pq[--N];
        return key;
    }

    private int binarySearch(Key key) {
        int lo = 0, hi = N - 1;
        return binarySearch(key, lo, hi);
    }

    private int binarySearch(Key key, int lo, int hi) {
        if (hi < lo) {
            return lo;
        }
        if (hi == lo) {
            if (less(pq[lo], key)) return lo + 1;
            return lo;
        }

        int mid = (hi + lo) / 2;
        int v = pq[mid].compareTo(key);
        if (v < 0) return binarySearch(key, mid + 1, hi);
        if (v > 0) return binarySearch(key, lo, mid - 1);

        return mid;
    }

    private boolean less(Comparable i, Comparable j) {
        return i.compareTo(j) < 0;
    }

    public void show() {
        while (!isEmpty()) {
            StdOut.print(delMax() + " ");
        }
    }


    public static void main(String[] args) {
        String[] a = "priority".split("");
        MaxOAPQ<String> pq = new MaxOAPQ<>(a.length);
        for (String s : a) {
            pq.insert(s);
        }
        pq.show();
    }
}
