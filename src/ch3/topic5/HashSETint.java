package ch3.topic5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import utils.Queue;

public class HashSETint {
    private static final int INIT_CAPACITY = 17;

    private int[] keys;
    private int isZero = -1;
    private int M;
    private int N;

    public HashSETint() {
        this(INIT_CAPACITY);
    }

    public HashSETint(int M) {
        this.M = M;
        keys = new int[M];
    }

    private int hash(int key) {
        // The procedence of % is higher than &.
        return (key & 0x7fffffff) % M;
    }

    public boolean contains(int key) {
        int k = hash(key);
        while (keys[k] != 0 || isZero == k) {
            if (key == keys[k]) return true;
            k = (k + 1) % M;
        }
        return false;
    }

    public int size() {
        return N;
    }

    private void resize(int cap) {
        int t = M;
        int isZeroA = -1;
        M = cap;
        int[] a = new int[cap];

        // isZeroA 是保存 homework.a 中的键为 0 的索引数，如果与 isZero 混用，会出现这种情况：
        // keys 中实际为 0 的键重新散列入 homework.a 后，isZero 的值可能会被改变，然后有可能出现某一个索引为未初始化的 0，
        // 但该索引值与修改后的 isZero 是相等的，导致将其认为是某个为 0 的键而再次加入索引，虽然 homework.a 中不会出现重复的 0 键，
        // 但会增加程序的无效运行（其实也就多运行了一次循环）
        for (int i = 0; i < t; i++) {
            if (keys[i] != 0 || isZero == i) {
                int j = hash(keys[i]);
                while (a[j] != 0 || isZeroA == j) j = (j + 1) % M;

                a[j] = keys[i];
                if (keys[i] == 0) isZeroA = j;
            }
        }

        keys = a;
        isZero = isZeroA;
    }

    private void resizeOfBook(int cap) {
        HashSETint t = new HashSETint(cap);

        for (int i = 0; i < M; i++)
            if (keys[i] != 0 || isZero == i)
                t.put(keys[i]);
        keys = t.keys;
        M = t.M;
    }

    public void put(int key) {
        if (N >= M / 2) resize(2 * M);
        int k = hash(key);

        while (keys[k] != 0 || isZero == k) {
            if (key == keys[k]) return;
            k = (k + 1) % M;
        }
        keys[k] = key;
        if (key == 0) isZero = k;
        N++;
    }

    public void delete(int key) {
        if (!contains(key)) return;

        int k = hash(key);

        while (key != keys[k]) k = (k + 1) % M;

        if (k == isZero) isZero = -1;
        else keys[k] = 0;
        N--;
        k = (k + 1) % M;

        while (keys[k] != 0 || isZero == k) {
            int oldKey = keys[k];
            if (key == 0) isZero = -1;
            else keys[k] = 0;
            N--;
            put(oldKey);
            k = (k + 1) % M;
        }

        if (N > 0 && N <= M / 8) resize(M / 2);
    }

    public Iterable<Integer> keys() {
        Queue<Integer> q = new Queue<>();

        for (int i = 0; i < M; i++) {
            if (keys[i] != 0 || isZero == i) q.enqueue(keys[i]);
        }
        return q;
    }

    public void show() {
        for (int i = 0; i < M; i++) {
            if (keys[i] != 0 || isZero == i) {
                StdOut.print(keys[i] + " ");
            } else StdOut.print("- ");
        }
    }

    public static void main(String[] args) {
        HashSETint st = new HashSETint();

        for (int i = 0; i < 1000; i++) {
            int j = StdRandom.uniformInt(-100, 100);
            st.put(j);
        }

        st.show();
    }
}
