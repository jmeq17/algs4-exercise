package ch3.topic4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import utils.VisualAccumulator;
import utils.Queue;
import ch3.topic1.SequentialSearchST;

public class SeparateChainingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;
    private static final int[] primes = {31, 61, 127, 251, 509, 1021, 2039, 4093, 8191, 16381, 32749};

    // The length of st[].
    private int M;
    // The number of keys.
    private int N;
    private SequentialSearchST<Key, Value>[] st;

    // E30418
    private int size;
    // ----------------------

    public SeparateChainingHashST() {
//        this(997);
        this(INIT_CAPACITY);
    }

    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    // E30418
    public SeparateChainingHashST(int M, int size) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }
        this.size = size;
    }

    private int hashE30418(Key x) {
        int t = x.hashCode() & 0x7fffffff;
        int lgM = (int) (Math.log(M) / Math.log(2));
        if (lgM < 26) t = t % primes[lgM + 5];
        return t % M;
    }

    public void putE30418(Key key, Value val) {
        if (N / M >= size) resize(2 * M);
        if (N >= 8 * M) resize(2 * M);

        int i = hashE30418(key);
        if (!st[i].contains(key)) N++;
        st[i].put(key, val);
    }
    // ----------------------------------

//    private int hash(Key key) {
//        return (key.hashCode() & 0x7fffffff) % M;
//    }

    private int hash(Key key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h & (M - 1);
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private void resize(int cap) {
        SequentialSearchST<Key, Value>[] a = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[cap];
        for (int i = 0; i < cap; i++) a[i] = new SequentialSearchST<>();

        int k = M;
        this.M = cap;
        for (int i = 0; i < k; i++)
            for (Key key : st[i].keys())
                a[hash(key)].put(key, st[i].get(key));

        this.st = a;
    }

    private void resizeOfBook(int cap) {
        SeparateChainingHashST<Key, Value> t = new SeparateChainingHashST<>(cap);

        for (int i = 0; i < M; i++)
            for (Key key : st[i].keys())
                t.put(key, st[i].get(key));

        this.st = t.st;
        this.M = t.M;
        this.N = t.N;
    }

    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        if (N >= 8 * M) resize(2 * M);

        int i = hash(key);
        if (!st[i].contains(key)) N++;
        st[i].put(key, val);
    }

    // FrequencyCounter Test
    public int putTest(Key key, Value val) {
        int count = 0;
        if (N >= 8 * M) count += resizeOfBookTest(2 * M);

        int i = hash(key);
        if (!st[i].contains(key)) N++;
        count += st[i].putTest(key, val);

        return count;
    }

    private int resizeOfBookTest(int cap) {
        SeparateChainingHashST<Key, Value> t = new SeparateChainingHashST<>(cap);
        int count = 0;

        for (int i = 0; i < M; i++)
            for (Key key : st[i].keys())
                count += t.putTest(key, st[i].get(key));

        this.st = t.st;
        this.M = t.M;
        this.N = t.N;

        return count;
    }

    private int resizeTest(int cap) {
        SequentialSearchST<Key, Value>[] a = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[cap];
        for (int i = 0; i < cap; i++) a[i] = new SequentialSearchST<>();
        int count = 0;

        int k = M;
        M = cap;
        for (int i = 0; i < k; i++)
            for (Key key : st[i])
                count += a[hash(key)].putTest(key, st[i].get(key));

        st = a;
        return count;
    }
    // -------------------------

    // E30405
    public void delete(Key key) {
        N -= st[hash(key)].size();
        st[hash(key)].delete(key);
        N += st[hash(key)].size();
    }
    // -------------------------

    // E30419
    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<>();

        for (int i = 0; i < M; i++) {
            Iterable<Key> keys = st[i].keys();
            for (Key j : keys) {
                q.enqueue(j);
            }
        }
        return q;
    }
    // -------------------------

    public void show() {
        Iterable<Key> q = keys();
        for (Key k : q) StdOut.print(k + " ");
    }

    public static void draw(int length) {
        In in = new In("files/tale");
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();

        StdDraw.setCanvasSize(700, 300);
        StdDraw.setXscale(-750, 14350);
        StdDraw.setYscale(-3, 15);
        StdDraw.line(-1000, -1, 14350, -1);
        StdDraw.line(0, -4, 0, 10);
        StdDraw.text(300, 9, "10");
        StdDraw.text(13750, -2, "14350");
        StdDraw.text(-200, 0, "0");
        StdDraw.setPenRadius(.005);
        VisualAccumulator visual = new VisualAccumulator();

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() < length) continue;
            visual.addDataValue(st.putTest(word, 1));
        }
    }

    public static void main(String[] args) {

    }
}
