package ch3.topic5;

// Based on LinearProbingHashST, maintaining sets of keys of primitive int.

import edu.princeton.cs.algs4.StdOut;
import utils.Queue;

public class HashSTint<Value> {
    private static final int INIT_CAPACITY = 17;

    private int[] keys;
    private Value[] vals;
    private int isZero = -1;
    private int M;
    private int N;

    public HashSTint() {
        this(INIT_CAPACITY);
    }

    public HashSTint(int M) {
        this.M = M;
        keys = new int[M];
        vals = (Value[]) new Object[M];
    }

    private int hash(int key) {
        return (key & 0x7fffffff) % M;
    }

    public boolean contains(int key) {
        int k = hash(key);
        while (keys[k] != 0 || isZero == k) {
            if (key == keys[k]) return true;
            k = (k + 1) % M;
        }
        return false;
    }

    private void resize(int cap) {
        int t = M;
        int isZeroA = -1;
        M = cap;
        int[] a = new int[cap];
        Value[] b = (Value[]) new Object[cap];

        for (int i = 0; i < t; i++) {
            if (keys[i] != 0 || isZero == i) {
                int j = hash(keys[i]);
                if (a[j] != 0 || isZeroA == j) j = (j + 1) % M;
                a[j] = keys[i];
                b[j] = vals[i];
                if (keys[i] == 0) isZeroA = j;
            }
        }

        keys = a;
        vals = b;
        isZero = isZeroA;
    }

    private void resizeOfBook(int cap) {
        HashSTint<Value> t = new HashSTint<>(cap);

        for (int i = 0; i < M; i++)
            if (keys[i] != 0 || isZero == i)
                t.put(keys[i], vals[i]);
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    public void put(int key, Value val) {
        if (N >= M / 2) resize(2 * M);
        int k = hash(key);

        while (keys[k] != 0 || isZero == k) {
            if (key == keys[k]) {
                vals[k] = val;
                return;
            }
            k = (k + 1) % M;
        }
        keys[k] = key;
        vals[k] = val;
        if (key == 0) isZero = k;
        N++;
    }

    public Value get(int key) {
        int k = hash(key);

        while (keys[k] != 0 || isZero == k) {
            if (key == keys[k]) return vals[k];
            k = (k + 1) % M;
        }
        return null;
    }

    public void delete(int key) {
        if (!contains(key)) return;

        int k = hash(key);

        while (key != keys[k])
            k = (k + 1) % M;

        if (k == isZero) isZero = -1;
        else keys[k] = 0;
        vals[k] = null;
        N--;
        k = (k + 1) % M;

        while (keys[k] != 0 || isZero == k) {
            int oldKey = keys[k];
            Value oldVal = vals[k];
            if (k == isZero) isZero = -1;
            else keys[k] = 0;
            vals[k] = null;
            N--;
            put(oldKey, oldVal);
            k = (k + 1) % M;
        }

        if (N > 0 && N == M / 8) resize(M / 2);
    }

    public Iterable<Integer> keys() {
        Queue<Integer> q = new Queue<>();

        for (int i = 0; i < M; i++) {
            if (keys[i] != 0 || isZero == i) q.enqueue(keys[i]);
        }
        return q;
    }

    public void show() {
        for (int i = 0; i < M; i++) {
            if (keys[i] != 0 || isZero == i) {
                StdOut.print(keys[i] + " ");
            } else StdOut.print("- ");
        }
    }
}
