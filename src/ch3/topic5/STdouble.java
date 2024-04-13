package ch3.topic5;

import edu.princeton.cs.algs4.StdOut;
import utils.Queue;

public class STdouble<Value> {
    private Node root;
    private final boolean RED = true;
    private final boolean BLACK = false;

    private class Node {
        double key;
        Value val;
        int N;
        Node left, right;
        boolean color;

        Node(double key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }

    public int size(double lo, double hi) {
        int ilo = rank(lo);
        int ihi = rank(hi);
        return ihi - ilo + 1;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(double key) {
        return contains(root, key);
    }

    private boolean contains(Node x, double key) {
        if (x == null) return false;

        double cmp = key - x.key;

        if (cmp < 0) return contains(x.left, key);
        if (cmp > 0) return contains(x.right, key);
        return true;
    }

    public void put(double key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node x, double key, Value val) {
        if (x == null) return new Node(key, val, 1, RED);

        double cmp = key - x.key;

        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;

        return balance(x);
    }

    public Value get(double key) {
        return get(root, key);
    }

    private Value get(Node x, double key) {
        if (x == null) return null;

        double cmp = x.key - key;
        if (cmp > 0) return get(x.left, key);
        else if (cmp < 0) return get(x.right, key);
        else return x.val;
    }

    public double min() {
        if (root == null) throw new NullPointerException("The symbol table is empty.");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public double max() {
        if (root == null) throw new NullPointerException("The symbol table is empty.");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    public double floor(double key) {
        Node x = floor(root, key);
        if (x == null)
            throw new IllegalArgumentException("The key is less than the smallest key of the symbol table.");
        return x.key;
    }

    private Node floor(Node x, double key) {
        if (x == null) return null;

        double cmp = key - x.key;

        if (cmp < 0) return floor(x.left, key);
        if (cmp == 0) return x;

        Node t = floor(x.right, key);
        if (t == null) return x;
        return t;
    }

    public double ceiling(double key) {
        Node x = ceiling(root, key);
        if (x == null)
            throw new IllegalArgumentException("The key is larger than the largest key of the symbol table.");
        return x.key;
    }

    private Node ceiling(Node x, double key) {
        if (x == null) return null;

        double cmp = key - x.key;

        if (cmp > 0) return ceiling(x.right, key);
        if (cmp == 0) return x;

        Node t = ceiling(x.left, key);
        if (t == null) return x;
        return t;
    }

    public double select(int rank) {
        if (rank < 0 || rank >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        }
        return select(root, rank);
    }

    private double select(Node x, int rank) {
        int i = size(x.left);

        if (rank < i) return select(x.left, rank);
        else if (rank > i) return select(x.right, rank - i - 1);
        else return x.key;
    }

    public int rank(double key) {
        return rank(root, key);
    }

    private int rank(Node x, double key) {
        if (x == null) return 0;

        double cmp = key - x.key;

        if (cmp == 0) return size(x.left);
        if (cmp < 0) return rank(x.left, key);
        return rank(x.right, key) + size(x.left) + 1;
    }

    public void deleteMin() {
        assert root != null;

        // 这个 if 语句是将 root 当作 3-节点对待（被指向的链接是红色就是三节点）
        // 后一个比较可以省略
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

    public void delete(double key) {
        assert contains(key);

        if (!isRED(root.left)) root.color = RED;

        root = delete(root, key);
        if (root != null) root.color = BLACK;
    }

    private Node delete(Node x, double key) {
        if (key - x.key < 0) {
            if (!isRED(x.left) && !isRED(x.left.left)) x = moveRedLeft(x);
            x.left = delete(x.left, key);
        } else {
            if (isRED(x.left)) x = rotateRight(x);
            else if (key - x.key == 0 && (x.right == null))
                return null;

            if (!isRED(x.right) && !isRED(x.right.left)) x = moveRedRight(x);

            if (key - x.key == 0) {
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

    public Iterable<Double> keys() {
        return keys(min(), max());
    }

    public Iterable<Double> keys(double lo, double hi) {
        Queue<Double> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Double> queue, double lo, double hi) {
        if (x == null) return;

        double ilo = lo - x.key;
        double ihi = hi - x.key;

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
        STdouble<String> st = new STdouble<>();
        double i = 0;

        for (String j : s) st.put(i++, j);

        Iterable<Double> a = st.keys(2, 6);

        for (Double j : a) StdOut.println(j);
    }
}
