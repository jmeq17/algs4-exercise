package exercise.ch3.topic5;

// E30501
// It is alse possible to use RedBlackBST as fundamental type.

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;

public class SET<Key extends Comparable<Key>> {
    private final ST<Key, Object> st;

    public SET() {
        st = new ST<>();
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

    public Key min() {
        return st.min();
    }

    public Key max() {
        return st.max();
    }

    public Key floor(Key key) {
        return st.floor(key);
    }

    public Key ceiling(Key key) {
        return st.ceiling(key);
    }

    public void deleteMin() {
        st.delete(min());
    }

    public void deleteMax() {
        st.delete(max());
    }

    public Iterable<Key> keys() {
        return st.keys();
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        Iterable<Key> ite = st.keys();
        for (Key k : ite) {
            if (k.compareTo(lo) == 0) {
                queue.enqueue(k);
                break;
            }
        }
        for (Key k : ite) {
            queue.enqueue(k);
            if (k.compareTo(hi) == 0)
                break;
        }
        return queue;
    }
}
