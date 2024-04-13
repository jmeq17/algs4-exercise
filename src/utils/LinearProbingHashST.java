package utils;

import edu.princeton.cs.algs4.StdOut;

public class LinearProbingHashST<Key, Value> {
    private Key[] keys;
    private Value[] vals;
    private int M = 17;
    private int N;

    public LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    public LinearProbingHashST(int M) {
        this.M = M;
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int cap) {
        int t = M;
        M = cap;
        Key[] a = (Key[]) new Object[cap];
        Value[] b = (Value[]) new Object[cap];

        for (int i = 0; i < t; i++) {
            if (keys[i] != null) {
                int j = hash(keys[i]);
                while (a[j] != null) j = (j + 1) % M;

                a[j] = keys[i];
                b[j] = vals[i];
            }
        }

        keys = a;
        vals = b;
    }

    private void resizeOfBook(int cap) {
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<>(cap);

        for (int i = 0; i < M; i++)
            if (keys[i] != null)
                t.put(keys[i], vals[i]);
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    public void put(Key key, Value val) {
        if (N >= M / 2) resize(2 * M);
        int k = hash(key);

        while (keys[k] != null) {
            if (key.equals(keys[k])) {
                vals[k] = val;
                return;
            }
            k = (k + 1) % M;
        }
        keys[k] = key;
        vals[k] = val;
        N++;
    }

    public Value get(Key key) {
        int k = hash(key);

        while (keys[k] != null) {
            if (key.equals(keys[k])) return vals[k];
            k = (k + 1) % M;
        }
        return null;
    }

    public void delete(Key key) {
        if (!contains(key)) return;

        int k = hash(key);

        while (!key.equals(keys[k]))
            k = (k + 1) % M;

        keys[k] = null;
        vals[k] = null;
        N--;
        k = (k + 1) % M;

        while (keys[k] != null) {
            Key oldKey = keys[k];
            Value oldVal = vals[k];
            keys[k] = null;
            vals[k] = null;
            N--;
            put(oldKey, oldVal);
            k = (k + 1) % M;
        }

        if (N > 0 && N == M / 8) resize(M / 2);
    }

    // My method1
    public void delete1(Key key) {
        int k = hash(key);

        while (keys[k] != null) {
            if (key.equals(keys[k])) break;
            k = (k + 1) % M;
        }
        if (keys[k] == null) return;

        keys[k] = null;
        vals[k] = null;
        N--;
        k = (k + 1) % M;

        while (keys[k] != null) {
            Key oldKey = keys[k];
            Value oldVal = vals[k];
            keys[k] = null;
            vals[k] = null;
            N--;
            put(oldKey, oldVal);
            k = (k + 1) % M;
        }

        if (N > 0 && N == M / 8) resize(M / 2);
    }

    // My method2
    public void delete2(Key key) {
        int k = hash(key);

        while (keys[k] != null) {
            if (key.equals(keys[k])) {
                keys[k] = null;
                vals[k] = null;
                N--;
                k = (k + 1) % M;

                while (keys[k] != null) {
                    Key oldKey = keys[k];
                    Value oldVal = vals[k];
                    keys[k] = null;
                    vals[k] = null;
                    N--;
                    put(oldKey, oldVal);
                    k = (k + 1) % M;
                }

                if (N > 0 && N == M / 8) resize(M / 2);

                return;
            }
            k = (k + 1) % M;
        }
    }

    public boolean contains(Key key) {
        int k = hash(key);
        while (keys[k] != null) {
            if (key.equals(keys[k])) return true;
            k = (k + 1) % M;
        }
        return false;
    }

    // E30419
    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<>();

        for (int i = 0; i < M; i++) {
            if (keys[i] != null) q.enqueue(keys[i]);
        }
        return q;
    }
    // --------------------------------

    public void show() {
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                StdOut.print(keys[i] + " ");
            } else StdOut.print("- ");
        }
    }
}
