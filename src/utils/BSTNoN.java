package utils;

/*
A BST implementation that omits rank() and select() and does not use homework.a count field in Node.
 */

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class BSTNoN<Key extends Comparable<Key>, Value> {
    private Node root;
    private int N;

    private class Node {
        Key key;
        Value val;
        Node left, right;

        Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        N++;
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val);

        int cmt = x.key.compareTo(key);

        if (cmt < 0) x.right = put(x.right, key, val);
        else if (cmt > 0) x.left = put(x.left, key, val);
        else x.val = val;

        return x;
    }

    // Exercises
    // ----------------------------


    // E30206
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;

        int hleft = height(x.left);
        int hright = height(x.right);

        if (hleft > hright) return hleft + 1;
        return hright + 1;
    }
    // ------------------------


    // E30207
    public int avgConpares() {
        return allHeight(root) / N + 1;
    }

    private int allHeight(Node x) {
        if (x == null) return 0;

        int ileft = allHeight(x.left);
        int iright = allHeight(x.right);

        return ileft + iright + height(x);
    }
    // --------------------------


    // --------------------------------
    // --------------------------------


    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return N;
    }

    public boolean contains(Key key) {
        return contains(root, key);
    }

    private boolean contains(Node x, Key key) {
        if (x == null) return false;

        int cmt = key.compareTo(x.key);

        if (cmt < 0) return contains(x.left, key);
        if (cmt > 0) return contains(x.right, key);
        return true;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;

        int cmt = x.key.compareTo(key);
        if (cmt > 0) return get(x.left, key);
        else if (cmt < 0) return get(x.right, key);
        else return x.val;
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

        int cmt = x.key.compareTo(key);

        if (cmt == 0) return x;
        if (cmt > 0) return floor(x.left, key);

        Node t = floor(x.right, key);
        if (t != null) return t;
        return x;
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;

        int cmt = x.key.compareTo(key);

        if (cmt == 0) return x;
        if (cmt < 0) return ceiling(x.right, key);

        Node t = ceiling(x.left, key);
        if (t != null) return t;
        return x;
    }

    public void deleteMin() {
        root = deleteMin(root);
        N--;
    }

    private Node deleteMin(Node x) {
        if (x == null) return null;
        if (x.left == null) return x.right;

        x.left = deleteMin(x.left);
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
        N--;
    }

    private Node deleteMax(Node x) {
        if (x == null) return null;
        if (x.right == null) return x.left;

        x.right = deleteMax(x.right);
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
        N--;
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;

        int cmt = x.key.compareTo(key);

        if (cmt > 0) x.left = delete(x.left, key);
        else if (cmt < 0) x.right = delete(x.right, key);
        else {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;

            Node t = x;
            x = min(t.right);
            x.left = t.left;
            x.right = deleteMin(t.right);
        }
        return x;
    }

    public void print() {
        print(root);
    }

    private void print(Node x) {
        if (x == null) return;
        print(x.left);
        StdOut.println(x.key);
        print(x.right);
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }


    public static void main(String[] args) {

    }
}
