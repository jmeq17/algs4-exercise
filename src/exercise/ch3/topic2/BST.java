package exercise.ch3.topic2;

import edu.princeton.cs.algs4.*;
import utils.VisualAccumulator;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    // E30201
//    private int count;
    // --------------------------

    private class Node {
        Key key;
        Value val;
        Node left, right;
        int N;

        // E30206
        int height;
        // ----------------------

        // E30207
        int avgCompares;
        // ----------------------

        Node(Key key, Value val, int N, int height, int avgCompares) {
            this.key = key;
            this.val = val;
            this.N = N;

            // E30206
            this.height = height;
            // ----------------------

            // E30207
            this.avgCompares = avgCompares;
            // -------------------------
        }
    }


    public void put(Key key, Value val) {
        // E30201
//        count = 0;
        // --------------------------

        root = put(root, key, val);

        // E30201
//        return count;
        // --------------------------
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1, 0, 1);

        int cmt = x.key.compareTo(key);

        // E30201
//        count++;
        // --------------------------

        if (cmt < 0) x.right = put(x.right, key, val);
        else if (cmt > 0) x.left = put(x.left, key, val);
        else x.val = val;

        x.N = size(x.right) + size(x.left) + 1;

        // E30206
//        x.height = Math.max(height(x.left), height(x.right)) + 1;
        x.height = Math.max(heightVar(x.left), heightVar(x.right)) + 1;
        // ---------------------------

        // EE30207
        // This is wrong.
//        x.avgCompares = allHeight(x) / x.N + 1;

        x.avgCompares = internalPath(x) / x.N + 1;
        // ---------------------------

        return x;
    }


    // ----------------------------
    // Exercises
    // ----------------------------


    // E30206
    public int height() {
        return height(root);
    }

    // 计算节点 x 的高度
    private int height(Node x) {
        if (x == null) return -1;

        int hleft = height(x.left);
        int hright = height(x.right);

        if (hleft > hright) return hleft + 1;
        return hright + 1;

//        return 1 + Math.max(height(x.left), height(x.right));
    }

    // Instance variable
    public int heightVar() {
        return heightVar(root);
    }

    private int heightVar(Node x) {
        if (x == null) return 0;
        return x.height;
    }
    // ------------------------


    // E30207
    // 计算根节点的平均路径
    public int avgCompares() {
        return internalPath(root) / root.N + 1;
    }

    // 计算以 x 为根节点的二叉树的内部路径长度
    // 这里计算的是所有节点的高度和，不是内部路径长度
    private int allHeight(Node x) {
        if (x == null) return 0;

        int ileft = allHeight(x.left);
        int iright = allHeight(x.right);

        return ileft + iright + x.height;
    }

    // 这里计算内部路径长度
    public int internalPath() {
        return internalPath(root);
    }

    private int internalPath(Node x) {
        int paths = 0, depth = 1, k;
        Queue<Node> queue = new Queue<>();
        queue.enqueue(x);

        while (!queue.isEmpty()) {
            k = queue.size();

            for (int i = 0; i < k; i++) {
                x = queue.dequeue();

                if (x.left != null) {
                    paths += depth;
                    queue.enqueue(x.left);
                }
                if (x.right != null) {
                    paths += depth;
                    queue.enqueue(x.right);
                }
            }
            depth++;
        }
        return paths;
    }


    // Instance variable
    public int avgComparesVar() {
        return avgComparesVar(root);
    }

    private int avgComparesVar(Node x) {
        if (x == null) return 0;
        return x.avgCompares;
    }
    // --------------------------


    // E30231
    public boolean isBST2() {
        return isOrder(root);
    }

    private boolean isOrder(Node x) {
        if (x == null) return true;

        if (x.left != null && x.left.key.compareTo(x.key) > 0) return false;
        if (x.right != null && x.right.key.compareTo(x.key) < 0) return false;

        return isOrder(x.left) && isOrder(x.right);
    }

    public boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node x, Key lo, Key hi) {
        if (x == null) return true;

        if (lo != null && lo.compareTo(x.key) > 0) return false;
        if (hi != null && hi.compareTo(x.key) < 0) return false;

        return isBST(x.left, lo, x.key) && isBST(x.right, x.key, hi);
    }

    public void test() {
        root = new Node((Key) "F", (Value) "1", 1, 0, 1);
        Node left = new Node((Key) "H", (Value) "1", 1, 0, 1);
        Node right = new Node((Key) "K", (Value) "1", 1, 0, 1);

        root.left = left;
        root.right = right;

        StdOut.println("isBST: " + isBST());
        StdOut.println("isBST2: " + isBST2());
    }
    // --------------------------


    // E30232
    public boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;

        if (size(x) != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.right) && isSizeConsistent(x.left);
    }

    public void test2() {
        root = new Node((Key) "F", (Value) "1", 1, 0, 1);
        Node left = new Node((Key) "H", (Value) "1", 1, 0, 1);
        Node right = new Node((Key) "K", (Value) "1", 1, 0, 1);

        root.left = left;
        root.right = right;

        StdOut.println("isSizeConsistent: " + isSizeConsistent());
    }
    // --------------------------------


    // E30233
    public boolean check() {
        return isRankConsistent(root) && isSelectConsistent(root);
    }

    private boolean isRankConsistent(Node x) {
        for (int i = 0; i < size(); i++) {
            if (i != rank(select(i))) return false;
        }
        return true;
    }

    private boolean isSelectConsistent(Node x) {
        if (x == null) return true;

//        if (x.key.compareTo(select(rank(x.key))) != 0) return false;
//        return isSelectConsistent(x.left) && isSelectConsistent(x.right);

        // Another method:
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }
    // --------------------------------


    // E30242
    public int avglength() {
//        return avgCompares();
        return root.avgCompares;
    }


    // --------------------------------
    // --------------------------------


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
//        x.N = size(x.left) + size(x.right) + 1;
        x.N--;
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x == null) return null;
        if (x.right == null) return x.left;

        x.right = deleteMax(x.right);
//        x.N = size(x.left) + size(x.right) + 1;
        x.N--;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
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

            x.right = deleteMin(t.right);   // 这里值得学习
            x.left = t.left;    // 注意顺序
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

    public static void draw(int length) {
        In in = new In("files/tale");
        BST<String, Integer> st = new BST<>();

        StdDraw.setCanvasSize(700, 300);
        StdDraw.setXscale(-200, 14350);
        StdDraw.setYscale(-2, 25);
        StdDraw.line(-200, 0, 14350, 0);
        StdDraw.line(0, -2, 0, 20);
        StdDraw.text(300, 19, "20");
        StdDraw.text(13750, -1, "14350");
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
        StdOut.println("Test 1");
        In in = new In("files/Tale");
        BST<String, Integer> st = new BST<>();

        for (int i = 0; i < 100000; i++) {
            String word = in.readString();
            st.put(word, 1);
        }


        int k = StdRandom.uniform(100);
        String s = st.select(k);
        st.delete(s);


//        for (int i = 0; i < 100; i++) {
//            int k = StdRandom.uniform(100);
//            String s = st.select(k);
//            st.delete(s);
//            StdOut.println(i);
//            st.put(s, 1);
//        }
    }
}
