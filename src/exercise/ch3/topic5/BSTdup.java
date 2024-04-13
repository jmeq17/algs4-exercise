package exercise.ch3.topic5;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import utils.Bag;

/**
 * The BSTdup class represents an ordered symbol table of generic key-value pairs. It derives from the standard balanced search tree implements with these modification: this class allows duplicate keys in the table. by associated values to one key.
 * This class implements size, isEmpty, put, get, contains and delete operations of symbol table. It alse supports operations of ordered symbol table such as min, max, deleteMin, deleteMax, floor, ceiling, rank, select. It supplies homework.a keys method to return an iterator which can return all keys sequentially in the table.
 * The calss is an assignment of 3.5.9 in Algorithms 4th by Robert.
 * The author is Jinkai Ruan.
 *
 * @param <Key>   the generic type of keys in this symbol table
 * @param <Value> the generic type of values in this symbol table
 */

public class BSTdup<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        Key key;
        Bag<Value> val;
        Node left, right;
        int N;

        Node(Key key, Bag val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

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

    /**
     * The get method returns an Iterable<Value> of value type containing all values associated with homework.a given key.
     *
     * @param key The generic type of keys in the symbol table.
     * @return return an Iterable<Value> of value type.
     */
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
    public Value getAngKey(Key key) {
        return getAngKey(root, key);
    }

    // 目标是返回任意一个值，这里没有保持随机性，因为 x.val 的第一个值也是任意的值之一
    private Value getAngKey(Node x, Key key) {
        if (x == null) return null;
        int cmp = x.key.compareTo(key);

        if (cmp > 0) return getAngKey(x.left, key);
        else if (cmp < 0) return getAngKey(x.right, key);
        else for (Value val : x.val) return val;

        // 其实程序必然只会执行到上边一条语句，但编译器会不通过，需要下边一条用于保护
        return null;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            Bag<Value> t = new Bag();
            t.add(val);
            return new Node(key, t, 1);
        }

        int cmp = x.key.compareTo(key);

        if (cmp < 0) x.right = put(x.right, key, val);
        else if (cmp > 0) x.left = put(x.left, key, val);
        else x.val.add(val);

        x.N = size(x.right) + size(x.left) + 1;
        return x;
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

        int cmp = x.key.compareTo(key);

        if (cmp == 0) return x;
        if (cmp > 0) return floor(x.left, key);

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

        int cmp = x.key.compareTo(key);

        if (cmp == 0) return x;
        if (cmp < 0) return ceiling(x.right, key);

        Node t = ceiling(x.left, key);
        if (t != null) return t;
        return x;
    }

    public Key select(int k) {
        // Assume k >= 0.
        Node x = select(root, k);
        if (x == null) return null;
        return x.key;
    }

    private Node select(Node x, int k) {
        if (x == null) return null;

        int i = size(x.left);

        if (i == k) return x;
        if (i > k) return select(x.left, k);
        return select(x.right, k - i - 1);
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null) return 0;

        int i = x.key.compareTo(key);

        if (i == 0) return size(x.left);
        if (i > 0) return rank(x.left, key);
        return rank(x.right, key) + size(x.left) + 1;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x == null) return null;
        if (x.left == null) return x.right;

        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
//        x.N--;
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x == null) return null;
        if (x.right == null) return x.left;

        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
//        x.N--;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;

        int cmp = x.key.compareTo(key);

        if (cmp > 0) x.left = delete(x.left, key);
        else if (cmp < 0) x.right = delete(x.right, key);
        else {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;

            Node t = x;
            x = min(t.right);

            x.right = deleteMin(t.right);   // 这里值得学习
            x.left = t.left;    // 这里顺序不能乱
        }
        x.N = size(x.left) + size(x.right) + 1;
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
        BSTdup<String, Integer> st = new BSTdup<>();

        st.put("C", 21);
        st.put("A", 22);
        st.put("E", 23);
        st.put("W", 24);
        st.put("B", 25);
        st.put("T", 26);
        st.put("Q", 27);
        st.put("Q", 67);
        st.put("W", 41);
        st.put("W", 51);

        Iterable<Integer> a = st.get("W");
        for (Integer i : a) StdOut.print(i + " ");
        StdOut.println();
        StdOut.println(st.getAngKey("A"));

        StdOut.println();

        StdOut.println(st.floor("S"));
        StdOut.println(st.ceiling("F"));
        StdOut.println(st.select(5));

        StdOut.println(st.rank("E"));
        StdOut.println("min: " + st.min());
        st.deleteMin();
        StdOut.println("min: " + st.min());
        StdOut.println("max: " + st.max());
        st.deleteMax();
        StdOut.println("max: " + st.max());

        st.delete("B");

        StdOut.println();
        st.print();

        Iterable<String> i1 = st.keys();
        Iterable<String> i2 = st.keys("E", "T");

        for (String s : i1) StdOut.print(s + " ");
        StdOut.println();
        for (String s : i2) StdOut.print(s + " ");

        StdOut.println();
    }
}
