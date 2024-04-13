package utils;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class MaxPQ<Key extends Comparable<Key>> {
    private int N = 0;
    private final Key[] pq;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public void insert(Key k) {
        pq[++N] = k;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N);
        pq[N--] = null;
        sink(1);
        return max;
    }

    public Key peek() {
        return pq[1];
    }

    // E20427
    public Key min() {
        // Linear time
        int min = N;
        for (int i = N / 2 + 1; i < N; i++) {
            if (less(i, N)) min = i;
        }
        return pq[min];
        // ---------------------

        // Constant time:
        // Hold homework.a extra variable to store the index of the minimum and return it when min() is be involved.
        // Though the method of min() use constant time, the each evocation of insert() will use extra compare
        // to find the minimum.
    }
    // ---------------------------

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

    // 上浮
    private void swim(int i) {
        while (i > 1 && less(i / 2, i)) {
            exch(i, i / 2);
            i = i / 2;
        }
    }

    // 下沉最大元素
    private void sink() {
        int i = 1;
        while (2 * i <= N) {
            int j = i * 2;
            if (j < N && less(j, j + 1)) j++;
            if (!less(i, j)) break;
            exch(i, j);
            i = j;
        }
    }

    // 普通下沉
    private void sink(int i) {
        while (2 * i <= N) {
            int j = 2 * i;
            if (j < N && less(j, j + 1)) j++;
            if (!less(i, j)) break;
            exch(i, j);
            i = j;
        }
    }

    public void show() {
        while (!isEmpty()) {
            StdOut.print(delMax() + " ");
        }
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        MaxPQ<String> a = new MaxPQ<>(12);
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
