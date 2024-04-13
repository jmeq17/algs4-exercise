package utils;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class MinPQ<Key extends Comparable<Key>> {
    private int N = 0;
    private Key[] pq = (Key[]) new Comparable[4];

    public MinPQ() {
    }

    public MinPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public void insert(Key k) {
        if (N == pq.length - 1) resizing(2 * (pq.length - 1));
        pq[++N] = k;
        swim(N);
    }

    public Key delMin() {
        if (N == (pq.length - 1) / 4) resizing((pq.length - 1) / 2);
        Key min = pq[1];
        exch(1, N);
        pq[N--] = null;
        sink(1);
        return min;
    }

    public Key peek() {
        return pq[1];
    }

    // E20415
    public boolean isMinPQ() {
        for (int i = 1; i <= N / 2; i++) {
            int j = 2 * i;
            if (less(j, i)) {
                return false;
            }
            if (2 * i < N && less(j + 1, i)) {
                return false;
            }
        }
        return true;
    }
    // ---------------

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key k = pq[i];
        pq[i] = pq[j];
        pq[j] = k;
    }

    private void swim(int i) {
        while (i > 1 && less(i, i / 2)) {
            exch(i, i / 2);
            i = i / 2;
        }
    }

    private void sink(int i) {
        while (2 * i <= N) {
            int j = 2 * i;
            if (j < N && less(j + 1, j)) j++;
            if (!less(j, i)) break;
            exch(i, j);
            i = j;
        }
    }

    private void resizing(int sz) {
        Key[] temp = (Key[]) new Comparable[sz];
        if (N >= 0) System.arraycopy(pq, 1, temp, 1, N);
        pq = temp;
    }

    public void show() {
        while (!isEmpty()) {
            StdOut.print(delMin() + " ");
        }
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        MinPQ<String> a = new MinPQ<>(12);
        while (!in.isEmpty()) {
            String s = in.readString();
            if (!s.equals("-")) {
                a.insert(s);
            } else StdOut.print(a.delMin() + " ");
        }
        StdOut.println();
        while (!a.isEmpty()) StdOut.print(a.delMin() + " ");
    }
}
