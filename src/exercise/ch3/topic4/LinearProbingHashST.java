package exercise.ch3.topic4;

import edu.princeton.cs.algs4.StdOut;
import utils.Queue;

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

    // E30421
    public int avgCostForMiss() {
//        int count = 0;
//        int k = 1;
//
//        for (int i = 0; i < M; i++) {
//            if (keys[i] != null) {
//                count += k;
//                k++;
//            } else k = 1;
//        }
//
//        return 1 + count / M;

        // 上边的实现在数组开头和结尾都有键时会计算失误
        // 下边的做法先扫描一遍数组，找到一个 null 键，从此处开始再扫描一遍
        // 共计扫描两边数组

        int count = 0;
        int k = 1, i;

        for (i = 0; i < M; i++) {
            if (keys[i] == null) break;
        }

        for (int j = i + 1; j % M != i; j = (j + 1) % M) {
            if (keys[j] != null) {
                count += k;
                k++;
            } else k = 1;
        }
        return (count / M) + 1;
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
