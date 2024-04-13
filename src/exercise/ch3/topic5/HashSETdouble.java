package exercise.ch3.topic5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import utils.Queue;

public class HashSETdouble {
    private static final int INIT_CAPACITY = 17;

    private double[] keys;
    private int isZero = -1;
    private int M;
    private int N;

    public HashSETdouble() {
        this(INIT_CAPACITY);
    }

    public HashSETdouble(int M) {
        this.M = M;
        keys = new double[M];
    }

    private int hash(double key) {
        // The procedence of % is higher than &.
        return (((Double) key).hashCode() & 0x7fffffff) % M;
    }

    public boolean contains(double key) {
        int k = hash(key);
        while (keys[k] != 0.0 || isZero == k) {
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
        double[] a = new double[cap];

        // isZeroA 是保存 homework.a 中的键为 0 的索引数，如果与 isZero 混用，会出现这种情况：
        // keys 中实际为 0 的键重新散列入 homework.a 后，isZero 的值可能会被改变，然后有可能出现某一个索引为未初始化的 0，
        // 但该索引值与修改后的 isZero 是相等的，导致将其认为是某个为 0 的键而再次加入索引，虽然 homework.a 中不会出现重复的 0 键，
        // 但会增加程序的无效运行（其实也就多运行了一次循环）
        for (int i = 0; i < t; i++) {
            if (keys[i] != 0.0 || isZero == i) {
                int j = hash(keys[i]);
                while (a[j] != 0.0 || isZeroA == j) j = (j + 1) % M;

                a[j] = keys[i];
                if (keys[i] == 0.0) isZeroA = j;
            }
        }

        keys = a;
        isZero = isZeroA;
    }

    private void resizeOfBook(int cap) {
        HashSETdouble t = new HashSETdouble(cap);

        for (int i = 0; i < M; i++)
            if (keys[i] != 0.0 || isZero == i)
                t.put(keys[i]);
        keys = t.keys;
        M = t.M;
    }

    public void put(double key) {
        if (N >= M / 2) resize(2 * M);
        int k = hash(key);

        while (keys[k] != 0.0 || isZero == k) {
            if (key == keys[k]) return;
            k = (k + 1) % M;
        }
        keys[k] = key;
        if (key == 0.0) isZero = k;
        N++;
    }

    public void delete(int key) {
        if (!contains(key)) return;

        int k = hash(key);

        while (key != keys[k]) k = (k + 1) % M;

        if (k == isZero) isZero = -1;
        else keys[k] = 0.0;
        N--;
        k = (k + 1) % M;

        while (keys[k] != 0.0 || isZero == k) {
            double oldKey = keys[k];
            if (key == 0.0) isZero = -1;
            else keys[k] = 0.0;
            N--;
            put(oldKey);
            k = (k + 1) % M;
        }

        if (N > 0 && N <= M / 8) resize(M / 2);
    }

    public Iterable<Double> keys() {
        Queue<Double> q = new Queue<>();

        for (int i = 0; i < M; i++) {
            if (keys[i] != 0.0 || isZero == 1) q.enqueue(keys[i]);
        }
        return q;
    }

    public void show() {
        for (int i = 0; i < M; i++) {
            if (keys[i] != 0.0 || isZero == i) {
                StdOut.print(keys[i] + " ");
            } else StdOut.print("- ");
        }
    }

    public static void main(String[] args) {
        HashSETdouble st = new HashSETdouble();

        for (int i = 0; i < 1000; i++) {
            double j = StdRandom.uniformDouble(-100.0, 100.0);
            st.put(j);
        }

        st.show();
    }
}
