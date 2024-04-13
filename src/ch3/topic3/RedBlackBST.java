package ch3.topic3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import utils.VisualAccumulator;
import utils.Queue;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private Node root;
    private final boolean RED = true;
    private final boolean BLACK = false;

    // Visual Test
//    private int count;
    // ---------------------

    private class Node {
        Key key;
        Value val;
        int N;
        Node left, right;
        boolean color;

        Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    public void put(Key key, Value val) {
        // Visual Test
//        count = 0;
        // ---------------------

        root = put(root, key, val);
        root.color = BLACK;

        // Visual Test
//        return count;
        // ---------------------
    }

    private Node put(Node x, Key key, Value val) {
        // insert at bottom (and color it red)
        if (x == null) return new Node(key, val, 1, RED);

        int cmp = key.compareTo(x.key);

        // Visual Tets
//        count++;
        // ---------------------

        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;

        if (isRED(x.right) && !isRED(x.left)) x = rotateLeft(x);
//        if (isRED(x.left) && isRED(x.left.left)) x = rotateRight(x);
        else if (isRED(x.left) && isRED(x.left.left)) x = rotateRight(x);
        if (isRED(x.left) && isRED(x.right)) flipColors(x);

        x.N = size(x.left) + size(x.right) + 1;

        return x;
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

        int cmt = key.compareTo(x.key);

        if (cmt < 0) return floor(x.left, key);
        if (cmt == 0) return x;

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

        int cmt = key.compareTo(x.key);

        if (cmt > 0) return ceiling(x.right, key);
        if (cmt == 0) return x;

        Node t = ceiling(x.left, key);
        if (t == null) return x;
        return t;
    }

    public Key select(int rank) {
        Node x = select(root, rank);
        if (x == null) return null;
        return x.key;
    }

    private Node select(Node x, int rank) {
        if (x == null) return null;

        int i = size(x.left);

        if (rank == i) return x;
        if (rank < i) select(x.left, rank);
        return select(x.right, rank - i - 1);
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null) return -1;

        int cmt = key.compareTo(x.key);

        if (cmt == 0) return size(x.left);
        if (cmt < 0) return rank(x.left, key);
        return rank(x.right, key) + size(x.left) + 1;
    }


    // E30339 - E30341
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

        // 这里是普通的节点判断语句
        // 这个这个 if 判断失败，说明：
        //  - x 节点是某个 3 或 4-节点的 root
        //  - x 的左子节点不是 2-节点
        // 以上哪种情况出现都可以将 x 直接传递至 x.left
        if (!isRED(x.left) && !isRED(x.left.left)) {
            x.color = BLACK;
            x.left.color = RED;
            x.right.color = RED;

            // 这个 if 判断 x 的左子节点的兄弟节点是不是 3-节点
            // 如果不是不需要特殊处理，如果是则需要操作一番
            if (isRED(x.right.left)) {
                x.right = rotateRight(x.right);
                x = rotateLeft(x);

                x.color = RED;
                x.left.color = BLACK;
                x.right.color = BLACK;
            }
        }

        x.left = deleteMin(x.left);

        // 向上传递分解临时 4-节点
        if (isRED(x.right) && !isRED(x.left)) x = rotateLeft(x);
        else if (isRED(x.left) && isRED(x.left.left)) x = rotateRight(x);
        if (isRED(x.left) && isRED(x.right)) flipColors(x);

        x.N = size(x.left) + size(x.right) + 1;

        return x;

        // 书上的做法有额外定义两个函数来使代码简洁
        // if (x.left == null) return null;

        // if (!isRED(x.left) && !isRED(x.left.left))
        //     x = moveRedLeft(x);

        // x.left = deleteMin(x.left);

        // return balance(x);
    }

    public void deleteMax() {
        assert root != null;

        if (!isRED(root.left)) {
            root.color = RED;
        }

        root = deleteMax(root);
        if (root != null) root.color = BLACK;
    }

    // 这样不行，如果 root 是 3-节点，不对其进行转换，下边的改变颜色代码会导致其失去平衡
//    private Node deleteMax(Node x) {
//        if (x.right == null) return null;
//
//        if (!isRED(x.right.left)) {
//            x.color = BLACK;
//            x.left.color = RED;
//            x.right.color = RED;
//
//            if (isRED(x.left.left)) {
//                x = rotateRight(x);
//
//                x.color = RED;
//                x.left.color = BLACK;
//                x.right.color = BLACK;
//            }
//        } else x.right = rotateRight(x.right);
//
//        x.right = deleteMax(x.right);
//
//        if (isRED(x.right) && !isRED(x.left)) x = rotateLeft(x);
//        else if (isRED(x.left) && isRED(x.left.left)) x = rotateRight(x);
//        if (isRED(x.left) && isRED(x.right)) flipColors(x);
//
//        x.N = size(x.left) + size(x.right) + 1;
//
//        return x;
//    }

    private Node deleteMax(Node x) {
        if (isRED(x.left)) x = rotateRight(x);
        if (x.right == null) return null;

        if (!isRED(x.right) && !isRED(x.right.left)) {
            x.color = BLACK;
            x.left.color = RED;
            x.right.color = RED;

            if (isRED(x.left.left)) {
                x = rotateRight(x);

                x.color = RED;
                x.left.color = BLACK;
                x.right.color = BLACK;
            }
        }

        x.right = deleteMax(x.right);

        if (isRED(x.right) && !isRED(x.left)) x = rotateLeft(x);
        else if (isRED(x.left) && isRED(x.left.left)) x = rotateRight(x);
        if (isRED(x.left) && isRED(x.right)) flipColors(x);

        x.N = size(x.left) + size(x.right) + 1;

        return x;

        // 书上的做法
//        if (isRED(x.left)) x = rotateRight(x);
//
//        if (x.right == null) return null;
//
//        if (!isRED(x.right) && !isRED(x.right.left)) x = moveRedRight(x);
//
//        x.right = deleteMax(x.right);
//
//        return balance(x);
    }

    public void delete(Key key) {
        assert key != null;
        assert contains(key);

        if (!isRED(root.left)) root.color = RED;

        root = delete(root, key);
        if (root != null) root.color = BLACK;
    }

//    private Node delete(Node x, Key key) {
//        if (key.compareTo(x.key) < 0) {
//            if (!isRED(x.left) && !isRED(x.left.left)) {
//                x.color = BLACK;
//                x.left.color = RED;
//                x.right.color = RED;
//
//                if (isRED(x.right.left)) {
//                    x.right = rotateRight(x.right);
//                    x = rotateLeft(x);
//
//                    x.color = RED;
//                    x.left.color = BLACK;
//                    x.right.color = BLACK;
//                }
//            }
//            x.left = delete(x.left, key);
//        } else if (key.compareTo(x.key) > 0) {
//            if (isRED(x.left)) {
//                x = rotateRight(x).right;
//                x.right = delete(x.right, key);
//            }
//
//
////            if (key.compareTo(x.key) == 0 && (x.right == null))
////                return null;
//
//            else {
//                if (!isRED(x.right) && !isRED(x.right.left)) {
//                    x.color = BLACK;
//                    x.left.color = RED;
//                    x.right.color = RED;
//
//                    if (isRED(x.left.left)) {
//                        x = rotateRight(x);
//
//                        x.color = RED;
//                        x.left.color = BLACK;
//                        x.right.color = BLACK;
//                    }
//
//
//                    if (key.compareTo(x.key) == 0) {
//                        Node t = min(x.right);
//                        x.key = t.key;
//                        x.val = t.val;
//                        x.right = deleteMin(x.right);
//                    } else x.right = delete(x.right, key);
//                } else x.right = delete(x.right, key);
//            }
//        } else {
//            if (x.right == null) return null;
//
//            if (!isRED(x.right) && !isRED(x.right.left)) {
//                x.color = BLACK;
//                x.left.color = RED;
//                x.right.color = RED;
//
//                if (isRED(x.left.left)) {
//                    x = rotateRight(x);
//
//                    x.color = RED;
//                    x.left.color = BLACK;
//                    x.right.color = BLACK;
//                }
//            }
//
//            Node t = min(x.right.right);
//            x.right.key = t.key;
//            x.right.val = t.val;
//            x.right.right = deleteMin(x.right.right);
//        }
//
//        if (isRED(x.right) && !isRED(x.left)) x = rotateLeft(x);
//        else if (isRED(x.left) && isRED(x.left.left)) x = rotateRight(x);
//        if (isRED(x.left) && isRED(x.right)) flipColors(x);
//
//        x.N = size(x.left) + size(x.right) + 1;
//        return x;
//    }

    private Node delete(Node x, Key key) {
        if (key.compareTo(x.key) < 0) {
            if (!isRED(x.left) && !isRED(x.left.left)) {
                x.color = BLACK;
                x.left.color = RED;
                x.right.color = RED;

                if (isRED(x.right.left)) {
                    x.right = rotateRight(x.right);
                    x = rotateLeft(x);

                    x.color = BLACK;
                    x.left.color = RED;
                    x.right.color = RED;
                }
            }
            x.left = delete(x.left, key);
        } else {
            if (isRED(x.left)) x = rotateRight(x);

                // 进入这条语句说明 x 是 2-节点
            else if (key.compareTo(x.key) == 0 && (x.right == null))
                return null;

            if (!isRED(x.right) && !isRED(x.right.left)) {
                x.color = BLACK;
                x.left.color = RED;
                x.right.color = RED;

                if (isRED(x.left.left)) {
                    x = rotateRight(x);

                    x.color = RED;
                    x.left.color = BLACK;
                    x.right.color = BLACK;

                    // 其实如果进入这个循环，会导致 x.left 变为新的 x，key显然大于它，不用再进行相等性比较了，但代码上不好实现，
                    // 想到的最好办法就是下边的，但不能直接复制语句而删除原语句，因为如果删除而没有进入 if 大循环的话将不会执行这些
                    // 这在执行时确实省了一些步骤，但代码过于冗余
                    x.right = delete(x.right, key);
//                }
                } else if (key.compareTo(x.key) == 0) {
                    Node t = min(x.right);
                    x.key = t.key;
                    x.val = t.val;
                    x.right = deleteMin(x.right);
                } else x.right = delete(x.right, key);
            } else if (key.compareTo(x.key) == 0) {
                Node t = min(x.right);
                x.key = t.key;
                x.val = t.val;
                x.right = deleteMin(x.right);
            } else x.right = delete(x.right, key);
        }

        if (isRED(x.right) && !isRED(x.left)) x = rotateLeft(x);
        else if (isRED(x.left) && isRED(x.left.left)) x = rotateRight(x);
        if (isRED(x.left) && isRED(x.right)) flipColors(x);

        x.N = size(x.left) + size(x.right) + 1;
        return x;

        // 书上的做法
//        if (key.compareTo(x.key) < 0) {
//            if (!isRED(x.left) && !isRED(x.left.left)) x = moveRedLeft(x);
//
//            x.left = delete(x.left, key);
//        } else {
//            if (isRED(x.left)) x = rotateRight(x);
//
//            if (key.compareTo(x.key) == 0 && (x.right == null)) return null;
//
//            if (!isRED(x.right) && !isRED(x.right.left)) x = moveRedRight(x);
//
//            if (key.compareTo(x.key) == 0) {
//                Node t = min(x.right);
//                x.key = t.key;
//                x.val = t.val;
//
//                x.right = deleteMin(x.right);
//            } else x.right = delete(x.right, key);
//        }
//        return balance(x);
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
    // ------------------------------------


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

    public void print() {
        print(root);
    }

    private void print(Node x) {
        if (x == null) return;

        print(x.left);
        StdOut.print(x.key + " ");
        print(x.right);
    }

    // Exercise
    // E30333
    public boolean isRedBlackBST() {
        return is23(root) && isBalanced();
    }

    private boolean is23(Node x) {
        if (x.right != null && x.right.color == RED) return false;
        if (x.left == null || x.left.left == null) return true;
        if (x.left.color == RED && x.left.left.color == RED) return false;

        return is23(x.left) && is23(x.right);
    }

    private boolean isBalanced() {
        Node x = root;
        int count = 0;
        while (x != null) {
            if (x.color == BLACK) count++;
            x = x.left;
        }
        return isBalanced(root, count);
    }

    private boolean isBalanced(Node x, int count) {
        if (x == null) return count == 0;
        if (x.color != RED) count--;
        return isBalanced(x.left, count) && isBalanced(x.right, count);
    }
    // -------------------------

    private boolean isRED(Node x) {
        if (x == null) return false;
//        return x.color;
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
//        x.color = RED;
//        x.left.color = BLACK;
//        x.right.color = BLACK;

        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
    }


    public static void draw(int length) {
        In in = new In("files/tale");
        RedBlackBST<String, Integer> st = new RedBlackBST<>();

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
        // Visual Test
//        draw(8);


        // keys Test
        String[] s = "abcdefghijk".split("");
        RedBlackBST<String, Integer> st = new RedBlackBST<>();

        for (String i : s) st.put(i, 1);

        Iterable<String> a = st.keys("c", "g");

        for (String i : a) StdOut.println(i);

    }
}
