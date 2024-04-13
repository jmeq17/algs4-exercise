package ch3.topic1;

/*
Small tables. Suppose that homework.a BinarySearchST client has S search operations
and N distinct keys. Give the order of growth of S such that the cost of building the table
is the same as the cost of all the searches.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import utils.Queue;

public class E30127<Key extends Comparable<Key>, Value> {
    private final Key[] keys;
    private final Value[] vals;
    private int N;

    public int build = 0;
    public int S = 0;

    public E30127(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public void put(Key key, Value val) {
        int i = rank(key);

        if (i < N) build++;

        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; j--) {
            build++;
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        return null;
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<>();
        for (int i = 0; i < N; i++) {
            q.enqueue(keys[i]);
        }
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
            StdOut.println("Key: " + keys[i] + ", value: " + vals[i] + ".");
        }
    }

    private int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            int cmp = key.compareTo(keys[mid]);

            build++;
            S++;

            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = lo + 1;
            else return mid;
        }
        return lo;
    }


    public static void main(String[] args) {
        int N = 100;
//        int S = (int) (N * N / 2 / (Math.log(N) / Math.log(2)));
        int S = (int) (N * N / (Math.log(N) / Math.log(2)));
        E30127<Double, Integer> st;
//        for (; true; N += N) {
        st = new E30127<>(N);

        for (int i = 0; i < N; i++) st.put(StdRandom.uniform(), i);
        StdOut.println("build: " + st.build);
        for (int i = 0; i < S; i++) st.get(StdRandom.uniform());
        StdOut.println("S: " + st.S);
        StdOut.println();
//        }
    }
}
