package utils;

// The nonrecursive implemention of BST.

public class NonrecursiveBST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        Key key;
        Value val;
        int N;
        Node left, right;

        Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public void put(Key key, Value val) {
        if (root == null) {
            root = new Node(key, val, 1);
            return;
        }

        Node x = root;
        Node t = null;

        while (x != null) {
            t = x;

            int cmt = key.compareTo(x.key);

            if (cmt < 0) {
                x.N++;
                x = x.left;
            } else if (cmt > 0) {
                x.N++;
                x = x.right;
            } else {
                x.val = val;
                return;
            }
        }

        x = new Node(key, val, 1);

        int cmt = key.compareTo(t.key);

        if (cmt < 0) t.left = x;
        else t.right = x;
    }

    public Value get(Key key) {
        Node x = root;

        while (x != null) {
            int cmt = key.compareTo(x.key);

            if (cmt < 0) x = x.left;
            else if (cmt > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }

    public Key min() {
        Node x = root;
        Node t = x;

        while (x != null) {
            t = x;
            x = x.left;
        }

        if (t == null) return null;
        return t.key;
    }

    public Key max() {
        Node x = root;
        Node t = x;

        while (x != null) {
            t = x;
            x = x.right;
        }

        if (t == null) return null;
        return t.key;
    }

    public Key floor(Key key) {
        if (root == null) return null;
        Node x = root;

        while (true) {
            int cmt = key.compareTo(x.key);

            if (cmt < 0) x = x.left;
            else if (cmt == 0) return key;
            else {
                if (x.right == null || x.right.key.compareTo(key) > 0) return x.key;
                else x = x.right;
            }
        }
    }

    public Key ceiling(Key key) {
        if (root == null) return null;
        Node x = root;

        while (true) {
            int cmt = key.compareTo(x.key);

            if (cmt > 0) x = x.right;
            else if (cmt == 0) return key;
            else {
                if (x.left == null || x.left.key.compareTo(key) > 0) return x.key;
                else x = x.left;
            }
        }
    }

    public int rank(Key key) {
        // Assume that key is exiting.
        Node x = root;
        int rank = 0;

        while (true) {
            if (x == null) return rank;
            int i = key.compareTo(x.key);

            if (i == 0) return size(x.left) + rank;
            else if (i < 0) x = x.left;
            else {
                rank += size(x.left) + 1;
                x = x.right;
            }
        }
    }

    public Key select(int k) {
        Node x = root;

        while (true) {
            if (x.left == null) {
                if (k == 0) return x.key;
                else {
                    x = x.right;
                    k = k - 1;
                    continue;
                }
            }

            if (k < x.left.N) x = x.left;
            else if (k == x.left.N) return x.key;
            else {
                x = x.right;
                k = k - x.left.N - 1;
            }
        }
    }

    public Iterable<Key> keys() {
        Stack<Node> stack = new Stack<>();
        Queue<Key> queue = new Queue<>();

        Node x = root;

        while (x != null || !stack.isEmpty()) {
            if (x != null) {
                stack.push(x);
                x = x.left;
            } else {
                x = stack.pop();
                queue.enqueue(x.key);
                x = x.right;
            }
        }

        return queue;
    }
}
