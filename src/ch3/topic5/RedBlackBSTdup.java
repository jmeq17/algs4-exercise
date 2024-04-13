package ch3.topic5;

import edu.princeton.cs.algs4.StdOut;
import utils.Bag;
import utils.Queue;

public class RedBlackBSTdup<Key extends Comparable<Key>, Value> {
    private Node root;
    private final boolean RED = true;
    private final boolean BLACK = false;

    private class Node {
        Key key;
        Bag<Value> val;
        int N;
        Node left, right;
        boolean color;

        Node(Key key, Bag<Value> val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            Bag<Value> bag = new Bag<>();
            bag.add(val);
            return new Node(key, bag, 1, RED);
        }

        int cmp = key.compareTo(x.key);

        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val.add(val);

        return balance(x);
    }

    public Iterable<Value> get(Key key) {
        return get(root, key);
    }

    private Iterable<Value> get(Node x, Key key) {
        if (x == null) return null;
        int cmp = x.key.compareTo(key);

        if (cmp > 0) return get(x.left, key);
        else if (cmp < 0) return get(x.right, key);
        else return x.val;
    }

    // Another get() returning any value associated to given key
    // 与 E30509 一样，不再写了

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }

    public int size(Key lo, Key hi) {
        int ilo = rank(lo);
        int ihi = rank(hi);
        return ihi - ilo + 1;
    }

    public boolean contains(Key key) {
        return contains(root, key);
    }

    private boolean contains(Node x, Key key) {
        if (x == null) return false;

        int cmp = key.compareTo(x.key);

        if (cmp < 0) return contains(x.left, key);
        if (cmp > 0) return contains(x.right, key);
        return true;
    }

    public Key min() {
        if (root == null) return null;
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() {
        if (root == null) return null;
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);

        if (cmp < 0) return floor(x.left, key);
        if (cmp == 0) return x;

        Node t = floor(x.right, key);
        if (t == null) return x;
        return t;
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);

        if (cmp > 0) return ceiling(x.right, key);
        if (cmp == 0) return x;

        Node t = ceiling(x.left, key);
        if (t == null) return x;
        return t;
    }

    public Key select(int rank) {
        if (rank < 0 || rank >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        }
        return select(root, rank);
    }

    private Key select(Node x, int rank) {
        int i = size(x.left);

        if (rank < i) return select(x.left, rank);
        else if (rank > i) return select(x.right, rank - i - 1);
        else return x.key;
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null) return 0;

        int cmp = key.compareTo(x.key);

        if (cmp == 0) return size(x.left);
        if (cmp < 0) return rank(x.left, key);
        return rank(x.right, key) + size(x.left) + 1;
    }

    public void deleteMin() {
        assert root != null;

        if (!isRED(root.left) && !isRED(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (root != null) root.color = BLACK;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return null;

        if (!isRED(x.left) && !isRED(x.left.left)) x = moveRedLeft(x);

        x.left = deleteMin(x.left);

        return balance(x);
    }

    public void deleteMax() {
        assert root != null;

        if (!isRED(root.left)) {
            root.color = RED;
        }

        root = deleteMax(root);
        if (root != null) root.color = BLACK;
    }

    private Node deleteMax(Node x) {
        if (isRED(x.left)) x = rotateRight(x);
        if (x.right == null) return null;

        if (!isRED(x.right) && !isRED(x.right.left)) x = moveRedRight(x);

        x.right = deleteMax(x.right);

        return balance(x);
    }

    public void delete(Key key) {
        assert key != null;
        assert contains(key);

        if (!isRED(root.left)) root.color = RED;

        root = delete(root, key);
        if (root != null) root.color = BLACK;
    }

    private Node delete(Node x, Key key) {
        if (key.compareTo(x.key) < 0) {
            if (!isRED(x.left) && !isRED(x.left.left)) x = moveRedLeft(x);

            x.left = delete(x.left, key);
        } else {
            if (isRED(x.left)) x = rotateRight(x);

            if (key.compareTo(x.key) == 0 && (x.right == null)) return null;

            if (!isRED(x.right) && !isRED(x.right.left)) x = moveRedRight(x);

            if (key.compareTo(x.key) == 0) {
                Node t = min(x.right);
                x.key = t.key;
                x.val = t.val;

                x.right = deleteMin(x.right);
            } else x.right = delete(x.right, key);
        }
        return balance(x);
    }

    private Node moveRedLeft(Node x) {
        assert (x != null);
        assert isRED(x) && !isRED(x.left) && !isRED(x.left.left);

        flipColors(x);
        if (isRED(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);

            flipColors(x);
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        assert (x != null);
        assert isRED(x) && !isRED(x.right) && !isRED(x.right.left);

        flipColors(x);
        if (isRED(x.left.left)) {
            x = rotateRight(x);

            flipColors(x);
        }
        return x;
    }

    private Node balance(Node x) {
        assert (x != null);

        if (isRED(x.right) && !isRED(x.left)) x = rotateLeft(x);
        if (isRED(x.left) && isRED(x.left.left)) x = rotateRight(x);
        if (isRED(x.left) && isRED(x.right)) flipColors(x);

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Iterable keys() {
        return keys(min(), max());
    }

    public Iterable keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;

        int ilo = lo.compareTo(x.key);
        int ihi = hi.compareTo(x.key);

        if (ilo < 0) keys(x.left, queue, lo, hi);
        if (ilo <= 0 && ihi >= 0) queue.enqueue(x.key);
        if (ihi > 0) keys(x.right, queue, lo, hi);
    }

    private boolean isRED(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node x) {
        Node t = x.right;
        x.right = t.left;
        t.left = x;
        t.color = x.color;
        x.color = RED;
        t.N = x.N;
        x.N = size(x.right) + size(x.left) + 1;
        return t;
    }

    private Node rotateRight(Node x) {
        Node t = x.left;
        x.left = t.right;
        t.right = x;
        t.color = x.color;
        x.color = RED;
        t.N = x.N;
        x.N = size(x.right) + size(x.left) + 1;
        return t;
    }

    private void flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
    }

    public void print() {
        print(root);
    }

    private void print(Node x) {
        if (x == null) return;

        print(x.left);
        StdOut.print(x.key + " ");
        print(x.right);
    }

    public static void main(String[] args) {
        String[] s = "abcdefghijk".split("");
        RedBlackBSTdup<String, Integer> st = new RedBlackBSTdup<>();

        for (String i : s) st.put(i, 1);

//        Iterable<String> homework.a = st.keys("c", "g");
//
//        for (String i : homework.a) StdOut.println(i);

        Iterable<Integer> t = st.get("a");
        for (Integer j : t) StdOut.println(j);
    }
}
