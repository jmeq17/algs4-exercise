package utils;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BinarySearchItemST<Key extends Comparable<Key>, Value> {
    private final Item[] items;
    private int N;

    public class Item implements Comparable<Item> {
        Key key;
        Value val;

        public Item(Key key, Value val) {
            this.key = key;
            this.val = val;
        }

        public int compareTo(Item that) {
            return this.key.compareTo(that.key);
        }
    }

    public BinarySearchItemST(int maxN) {
        items = new BinarySearchItemST.Item[maxN];
    }

    public BinarySearchItemST(Item[] items) {
        Merge.sort(items);
        this.items = items;
        N = items.length;
    }

    public void put(Key key, Value val) {
        int i = rank(key);

        if (i < N && items[i].key.compareTo(key) == 0) {
            items[i].val = val;
            return;
        }
        if (N - i >= 0) System.arraycopy(items, i, items, i + 1, N - i);
        items[i] = new Item(key, val);
        N++;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && items[i].key.compareTo(key) == 0) return items[i].val;
        return null;
    }

    public void delete(Key key) {
        int i = rank(key);
        N--;
        if (N - i >= 0) System.arraycopy(items, i + 1, items, i, N - i);
        items[N] = null;
    }

    public void deleteMin() {
        N--;
        if (N >= 0) System.arraycopy(items, 1, items, 0, N);
        items[N] = null;
    }

    public void deleteMax() {
        N--;
        items[N] = null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Key min() {
        return items[0].key;
    }

    public Key max() {
        return items[N - 1].key;
    }

    public Key select(int k) {
        return items[k].key;
    }

    public Key floor(Key key) {
        int i = rank(key);
        if (i < N && items[i].key.compareTo(key) == 0) return items[i].key;
        if (i > 0) return items[i - 1].key;
        return null;
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        if (i < N) return items[i].key;
        return null;
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<>();
        for (int i = 0; i < N; i++) {
            q.enqueue(items[i].key);
        }
        return q;
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<>();
        for (int i = rank(lo); i < rank(hi); i++)
            q.enqueue(items[i].key);
        if (contains(hi))
            q.enqueue(items[rank(hi)].key);
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
            StdOut.println("key: " + items[i].key + ", value: " + items[i].val + ".");
        }
    }

    private int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            int cmp = key.compareTo(items[mid].key);

            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = lo + 1;
            else return mid;
        }
        return lo;
    }

    public static void draw(int length) {

    }


    public static void main(String[] args) {
        StdOut.println("Test 1");
        In in = new In("files/tinyTale");
        BinarySearchItemST<String, Integer> st = new BinarySearchItemST<>(14);

        for (int i = 0; i < 14; i++) {
            String word = in.readString();
            st.put(word, 1);
        }

        st.show();

        StdOut.println("====================");
        StdOut.println("Test 2");
        BinarySearchItemST.Item[] items = new BinarySearchItemST.Item[6];

        for (int i = 0; i < 6; i++) {
            items[i] = new BinarySearchItemST(6).new Item(in.readString(), i);
        }

        BinarySearchItemST<String, Integer> st2 = new BinarySearchItemST<>(items);
        st2.show();
    }
}
