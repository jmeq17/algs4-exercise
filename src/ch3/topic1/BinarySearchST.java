package ch3.topic1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import utils.VisualAccumulator;
import utils.Queue;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private final Key[] keys;
    private final Value[] vals;
    private int N;

    // test
//    private int countPut;
    //

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public void put(Key key, Value val) {
        // test
//        countPut = 0;
        //

        int i = rank(key);

        // test
//        if (i < N) countPut++;
        //

        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
//            return countPut;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;

        // test
//        return countPut;
        //
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        return null;
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

    public boolean contains(Key key) {
        return get(key) != null;
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
            StdOut.println("Key: " + keys[i] + ", value: " + vals[i] + ".");
        }
    }

    // returns the number of keys smaller than homework.a given key
    private int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            int cmp = key.compareTo(keys[mid]);

            // test
//            countPut++;
            //

            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = lo + 1;
            else return mid;
        }
        return lo;
    }

    // recursion implemention
    private int rank(Key key, int lo, int hi) {
        if (hi < lo) return lo;
        int mid = (hi + lo) / 2;
        int cmp = key.compareTo(keys[mid]);

        if (cmp < 0) return rank(key, lo, mid - 1);
        else if (cmp > 0) return rank(key, mid + 1, hi);
        else return mid;
    }

    public static void draw(int length) {
        In in = new In("files/tale");
        BinarySearchST<String, Integer> st = new BinarySearchST<>(6000);

        StdDraw.setCanvasSize(700, 300);
        StdDraw.setXscale(-800, 14350);
        StdDraw.setYscale(-600, 5737);
        StdDraw.line(-800, 0, 14350, 0);
        StdDraw.line(0, -600, 0, 5737);
        StdDraw.text(600, 5537, "5737");
        StdDraw.text(13750, -200, "14350");
        StdDraw.text(-200, -200, "0");
        StdDraw.setPenRadius(.005);
        VisualAccumulator visual = new VisualAccumulator();

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() < length) continue;
//            visual.addDataValue(st.put(word, 1));
            st.put(word, 1);
        }
    }


    public static void main(String[] args) {

    }
}
