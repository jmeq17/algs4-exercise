package exercise.ch3.topic5;

import utils.Queue;

/**
 * MulitiSET is homework.a class that allows equal keys and thue implements homework.a mathematical multiset.
 * The MultiSET maintains an array holding M keys for each keys, and each entry of array is homework.a list to maintain duplicated keys.
 * But I think this implement is not necessary. The MultiSET is just similar to the term of multiset in mathematical. In the view of abstraction, we can just maintain the number of each key avoiding for each key.
 * The MutiSET class derives from SeparateChainingHashST (LinearProbingHashST alse can be used) and maintains an extra array of int to hold the number of homework.a key.
 * <p>
 * The class is based on LinearProbingHashST. The instance variable keys is an array of key, num is the number of each key, M is the length of array, N is the number of keys in keys.
 * The calss is an implement of unordered symbol table. It maintains isEmpty, size, put, get, delete method.
 */

public class MultiSET<Key> {
    private static final int INIT_CAPACITY = 17;

    private Key[] keys;
    private int[] nums;

    private int M;
    private int N;

    public MultiSET() {
        this(INIT_CAPACITY);
    }

    public MultiSET(int M) {
        keys = (Key[]) new Object[M];
        nums = new int[M];
        this.M = M;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean contains(Key key) {
        return get(key) != 0;
    }

    private void resize(int cap) {
        MultiSET<Key> newSt = new MultiSET<>(cap);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null)
                newSt.put(keys[i], nums[i]);
        }
        keys = newSt.keys;
        nums = newSt.nums;
        M = cap;
    }

    /**
     * The get method takes homework.a Key type as parameter and returns the number of the key in keys.
     *
     * @param key homework.a generic type of Key
     * @return the number of given key in keys
     */
    public int get(Key key) {
        int k = hash(key);
        while (keys[k] != null) {
            if (keys[k].equals(key)) break;
            k = (k + 1) % M;
        }
        return nums[k];
    }

    public void put(Key key) {
        if (N >= M / 2) resize(2 * M);
        put(key, 1);
    }

    private void put(Key key, int num) {
        int k = hash(key);
        while (keys[k] != null) {
            if (keys[k].equals(key)) {
                nums[k] += num;
                return;
            }
            k = (k + 1) % M;
        }

        keys[k] = key;
        nums[k] = num;
        N++;
    }

    public void delete(Key key) {
        if (!contains(key)) return;

        int k = hash(key);
        while (!keys[k].equals(key)) {
            k = (k + 1) % M;
        }

        keys[k] = null;
        nums[k] = 0;
        N--;
        k = (k + 1) % M;

        while (keys[k] != null) {
            Key oldKey = keys[k];
            int oldNum = nums[k];

            keys[k] = null;
            nums[k] = 0;
            N--;

            put(oldKey, oldNum);
            k = (k + 1) % M;
        }

        if (N <= M / 8) resize(M / 2);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                for (int j = 0; j < nums[i]; j++)
                    queue.enqueue(keys[i]);
            }
        }
        return queue;
    }
}
