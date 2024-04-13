package exercise.ch3.topic5;

import utils.BinarySearchST;

public class BinarySearchSETWrap<Key extends Comparable<Key>> {
    private static final int INIT_CAPACITY = 7;

    private BinarySearchST<Key, Object> st;
    private int length;

    public BinarySearchSETWrap() {
        this(INIT_CAPACITY);
    }

    public BinarySearchSETWrap(int N) {
        st = new BinarySearchST(N);
        this.length = N;
    }

    private void resize(int cap) {
        BinarySearchST<Key, Object> t = new BinarySearchST(cap);
        for (Key k : st.keys()) t.put(k, null);
        st = t;
        length = t.size();
    }

    public int size() {
        return st.size();
    }

    public boolean isEmpty() {
        return st.isEmpty();
    }

    public void add(Key key) {
        if (st.size() >= length / 2) resize(2 * length);
        st.put(key, null);
    }

    public void delete(Key key) {
        if (st.size() >= length / 8) resize(length / 2);
        st.delete(key);
    }

    public boolean contains(Key key) {
        if (isEmpty()) return false;
        return st.select(st.rank(key)).compareTo(key) == 0;
    }

    public String toString() {
        return st.toString();
    }

    public Iterable<Key> keys() {
        return st.keys();
    }
}
