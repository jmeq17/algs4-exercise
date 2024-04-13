package utils;

import edu.princeton.cs.algs4.StdOut;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private final Key[] keys;
    private final Value[] vals;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        return null;
    }

    public void put(Key key, Value val) {
        int i = rank(key);

        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    // E30116
    public void delete(Key key) {
        int i = rank(key);
        if (i >= N || keys[i].compareTo(key) != 0) return;

        N--;
        for (int j = i; j < N; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }
        // This can omit.
        keys[N] = null;
        vals[N] = null;
    }
    // -----------------

    public void deleteMin() {
        N--;
        for (int j = 0; j < N; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }
        keys[N] = null;
        vals[N] = null;
    }

    public void deleteMax() {
        N--;
        keys[N] = null;
        vals[N] = null;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
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

    // returns the number of keys smaller than homework.a given key
    public int rank(Key key) {
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

    // recursion implemention
    public int rank(Key key, int lo, int hi) {
        if (hi < lo) return lo;
        int mid = (hi + lo) / 2;
        int cmp = key.compareTo(keys[mid]);

        if (cmp < 0) return rank(key, lo, mid - 1);
        else if (cmp > 0) return rank(key, mid + 1, hi);
        else return mid;
    }

    public Key select(int k) {
        return keys[k];
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

    public void show() {
        for (int i = 0; i < N; i++) {
            StdOut.println("Key: " + keys[i] + ", value: " + vals[i] + ".");
        }
    }
}
