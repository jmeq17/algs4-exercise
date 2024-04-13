package utils;

/*
E20422:
Array resizing. Add array resizing to MaxPQ, and prove bounds like those of
Proposition Q for array accesses, in an amortized sense.
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class MaxPQResizing<Key extends Comparable<Key>> {
    private int N = 0;
    private Key[] pq;

    public MaxPQResizing() {
        pq = (Key[]) new Comparable[4];
    }

    private void resizing(int sz) {
        Key[] temp = (Key[]) new Comparable[sz];
        if (N >= 0) System.arraycopy(pq, 1, temp, 1, N);
        pq = temp;
    }

    public void insert(Key k) {
        if (N == pq.length - 1) resizing(2 * (pq.length - 1));
        pq[++N] = k;
        swim(N);
    }

    public Key delMax() {
        if (N == (pq.length - 1) / 4) resizing((pq.length - 1) / 2);
        Key max = pq[1];
        exch(1, N);
        pq[N--] = null;
        sink(1);
        return max;
    }

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
        while (i > 1 && less(i / 2, i)) {
            exch(i, i / 2);
            i = i / 2;
        }
    }

    private void sink(int i) {
        while (2 * i <= N) {
            int j = 2 * i;
            if (j < N && less(j, j + 1)) j++;
            if (!less(i, j)) break;
            exch(i, j);
            i = j;
        }
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        MaxPQResizing<String> a = new MaxPQResizing<>();
        while (!in.isEmpty()) {
            String s = in.readString();
            if (!s.equals("-")) {
                a.insert(s);
            } else StdOut.print(a.delMax() + " ");
        }
        StdOut.println();
        while (!a.isEmpty()) StdOut.print(a.delMax() + " ");
    }
}
