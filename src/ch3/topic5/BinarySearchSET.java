package ch3.topic5;

import edu.princeton.cs.algs4.StdOut;
import utils.Queue;

public class BinarySearchSET<Key extends Comparable<Key>> {
    private final Key[] keys;
    private int N;

    public BinarySearchSET(int capacity) {
        keys = (Key[]) new Comparable[capacity];
    }

    public void put(Key key) {
        int i = rank(key);

        if (i < N && keys[i].compareTo(key) == 0) {
            return;
        }
        if (N - i >= 0) System.arraycopy(keys, i, keys, i + 1, N - i);
        keys[i] = key;
        N++;
    }

    public void delete(Key key) {
        int i = rank(key);
        if (i >= N || keys[i].compareTo(key) != 0) return;

        N--;
        if (N - i >= 0) System.arraycopy(keys, i + 1, keys, i, N - i);
        // This can omit.
        keys[N] = null;
    }
    // -----------------

    public void deleteMin() {
        N--;
        if (N >= 0) System.arraycopy(keys, 1, keys, 0, N);
        keys[N] = null;
    }

    public void deleteMax() {
        N--;
        keys[N] = null;
    }

    public boolean contains(Key key) {
        if (isEmpty()) return false;
        int i = rank(key);
        return i < N && keys[i].compareTo(key) == 0;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    public Key select(int k) {
        return keys[k];
    }

    // E30117
    public Key floor(Key key) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return keys[i];
        if (i > 0) return keys[i - 1];
        return null;
    }
    // ------------

    public Key ceiling(Key key) {
        int i = rank(key);
        if (i < N) return keys[i];
        return null;
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<>();
        for (int i = 0; i < N; i++) {
            q.enqueue(keys[i]);
        }
        return q;
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<>();
        for (int i = rank(lo); i < rank(hi); i++)
            q.enqueue(keys[i]);
        // 为何要加这么一句判断，会出现 hi 不在符号表中的用例吗？
        if (contains(hi))
            q.enqueue(keys[rank(hi)]);
        return q;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void show() {
        for (int i = 0; i < N; i++) {
            StdOut.println("Key: " + keys[i] + ".");
        }
    }

    private int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            int cmp = key.compareTo(keys[mid]);

            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = lo + 1;
            else return mid;
        }
        return lo;
    }

    private int rank(Key key, int lo, int hi) {
        if (hi < lo) return lo;
        int mid = (hi + lo) / 2;
        int cmp = key.compareTo(keys[mid]);

        if (cmp < 0) return rank(key, lo, mid - 1);
        else if (cmp > 0) return rank(key, mid + 1, hi);
        else return mid;
    }
}
