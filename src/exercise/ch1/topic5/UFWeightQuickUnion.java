package exercise.ch1.topic5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class UFWeightQuickUnion {
    private final int[] id;
    private int count;
    private final int[] sz;

    public UFWeightQuickUnion(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        if (sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        count--;
    }


    // DoublingTest
    private static void Test(int[][] a, int N) {
        UFWeightQuickUnion uf = new UFWeightQuickUnion(N);

        for (int i = 0; i < N; i++) {
            uf.union(a[i][0], a[i][1]);
        }
    }

    public static double timeTrial(int N) {
        int[][] a = new int[N][2];
        for (int i = 0; i < N; i++) {
            a[i][0] = StdRandom.uniform(0, N);
            a[i][1] = StdRandom.uniform(0, N);
        }
        Stopwatch timer = new Stopwatch();
        Test(a, a.length);
        return timer.elapsedTime();
    }

    /**
     * To judge wheather homework.a graph is connected, Expanding the API to implement count() which return the size of compound that conclude v.
     *
     * @param v the vertex
     * @return the size of compound which conclude v
     */
    public int count(int v) {
        return sz[find(v)];
    }


    public static void main(String[] args) {
        // 实现性测试
        int N = StdIn.readInt();
        UFWeightQuickUnion uf = new UFWeightQuickUnion(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");


        // DoublingTest
//        double timeOld = timeTrial(5);
//        StdOut.println("problem size\ttime\tratio");
//        for (int N = 250; true; N += N) {
//            double timeNew = timeTrial(N);
//            StdOut.printf("%6d\t%7.1f\t%5.1f\n", N, timeNew, timeNew / timeOld);
//            timeOld = timeNew;
//        }
    }
}
