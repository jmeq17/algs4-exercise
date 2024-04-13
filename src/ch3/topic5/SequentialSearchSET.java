package ch3.topic5;

import utils.SequentialSearchST;

public class SequentialSearchSET<Key> {
    private final SequentialSearchST<Key, Object> st;

    public SequentialSearchSET() {
        st = new SequentialSearchST<>();
    }

    public int size() {
        return st.size();
    }

    public boolean isEmpty() {
        return st.isEmpty();
    }

    public void add(Key key) {
        st.put(key, null);
    }

    public void delete(Key key) {
        st.delete(key);
    }

    public boolean contains(Key key) {
        return st.contains(key);
    }

    public String toString() {
        return st.toString();
    }

    public Iterable<Key> keys() {
        return st.keys();
    }
}
