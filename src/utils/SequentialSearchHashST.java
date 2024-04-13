package utils;

public class SequentialSearchHashST<Key, Value> {
    private static final int INIT_CAPACITY = 5;

    private final Node[] st;
    private final int M;
    private int N;

    // There is no necessary to use generic.
    private static class Node<Key, Value> {
        Key key;
        Value val;
        Node next;
        int N;

        // E30403
        int size;
        // ----------------------

        public Node(Key key, Value val, Node next, int size) {
            this.key = key;
            this.val = val;
            this.next = next;
            this.N = size(next) + 1;

            this.size = size;
        }

        private int size(Node x) {
            if (x == null) return 0;
            return x.N;
        }
    }

    public SequentialSearchHashST() {
        this(INIT_CAPACITY);
    }

    public SequentialSearchHashST(int M) {
        st = new Node[5];
        this.M = M;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key, Value val) {
        int k = hash(key);

        for (Node x = st[k]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }

        st[k] = new Node(key, val, st[k], N);
        N++;
    }

    public Value get(Key key) {
        int k = hash(key);
        for (Node x = st[k]; x != null; x = x.next)
            if (key.equals(x.key))
                return (Value) x.val;
        return null;
    }

    public void delete(Key key) {
        assert contains(key);
        N--;
        int k = hash(key);
        if (key.equals(st[k].key)) {
            st[k] = st[k].next;
            return;
        }
        for (Node x = st[k]; x.next != null; x = x.next) {
            if (key.equals(x.next.key)) {
                x.next = x.next.next;
                x.N--;
                break;
            }
            x.N--;
        }
    }

    // E30403
    public void deleteGtsize(int k) {
        for (int i = 0; i < M; i++) {
            for (Node x = st[i]; x != null; x = x.next) {
                if (x.size > k) delete((Key) x.key);
            }
        }
    }
    // ----------------------------

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<>();
        for (int i = 0; i < M; i++) {
            for (Node current = st[i]; current != null; current = current.next) {
                q.enqueue((Key) current.key);
            }
        }
        return q;
    }
}
