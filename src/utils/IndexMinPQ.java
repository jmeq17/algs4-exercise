package utils;

import edu.princeton.cs.algs4.StdOut;

public class IndexMinPQ<Key extends Comparable<Key>> {
    private final int max;
    private int N;
    private final int[] pq;
    private final int[] qp;
    private final Key[] keys;

    public IndexMinPQ(int maxN) {
        this.max = maxN;
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0; i < maxN + 1; i++) {
            qp[i] = -1;
        }
    }

    public void insert(int i, Key key) {
        if (i < 0 || i > max) throw new IllegalArgumentException();
        if (contains(i)) throw new IllegalArgumentException();
        keys[i] = key;
        pq[++N] = i;
        qp[i] = N;
        swim(N);
    }

    public int delMin() {
        int minIndex = pq[1];
        exch(1, N--);
        keys[minIndex] = null;
        qp[minIndex] = -1;
        sink(1);
        return minIndex;
    }

    public void changeKey(int i, Key key) {
        keys[i] = key;

        swim(qp[i]);
        sink(qp[i]);

        // This is false:
//        exch(qp[i], N);
//        swim(N);
    }

    public boolean contains(int i) {
        return qp[i] != -1;
    }

    public void delete(int i) {
        int index = qp[i];
        exch(index, N--);
        qp[i] = -1;
        keys[i] = null;
        sink(index);
        swim(index);
    }

    public Key minKey() {
        return keys[pq[1]];
    }

    public int minIndex() {
        return pq[1];
    }

    public Key keyOf(int i) {
        return keys[i];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void show() {
        while (!isEmpty()) {
            StdOut.print(minKey() + " ");
            delMin();
        }
    }

    // ---------------------------------------------------------------------------------
    // Private Methods
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

    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void exch(int i, int j) {
        int k = pq[i];
        pq[i] = pq[j];
        pq[j] = k;

        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }


    public static void main(String[] args) {
        String[] a = "dhDASksKS".split("");

        IndexMinPQ<String> pq = new IndexMinPQ<>(a.length);

        int i = 1;
        for (String s : a) {
            pq.insert(i++, s);
        }

        pq.changeKey(4, "z");
        StdOut.println("Contain 3: " + pq.contains(3));
        StdOut.println("maxKey is: " + pq.minKey());
        StdOut.println("maxIndex is: " + pq.minIndex());
        StdOut.println("Key of 3 is: " + pq.keyOf(2) + ", Expected: h");
        pq.delete(1);
        pq.show();
    }
}
