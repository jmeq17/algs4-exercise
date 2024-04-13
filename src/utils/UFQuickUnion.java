package utils;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class UFQuickUnion {
    private final int[] id;
    private int count;

    private int times;

    public UFQuickUnion(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
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

            times += 2;
        }

        times += 1;
        return p;
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        id[pRoot] = qRoot;

        times += 1;
        StdOut.println("times:" + times);
        times = 0;

        count--;
    }


    // DoublingTest
    private static void Test(int[][] a, int N) {
        UFQuickUnion uf = new UFQuickUnion(N);

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


    public static void main(String[] args) {
        // 实现性测试
//        int N = StdIn.readInt();
//        UFQuickUnion uf = new UFQuickUnion(N);
//        while (!StdIn.isEmpty()) {
//            int p = StdIn.readInt();
//            int q = StdIn.readInt();
//            if (uf.connected(p, q)) continue;
//            uf.union(p, q);
//        }
//        StdOut.println(uf.count() + " components");


        // DoublingTest
        double timeOld = timeTrial(5);
        StdOut.println("problem size\ttime\tratio");
        for (int N = 250; true; N += N) {
            double timeNew = timeTrial(N);
            StdOut.printf("%6d\t%7.1f\t%5.1f\n", N, timeNew, timeNew / timeOld);
            timeOld = timeNew;
        }
    }
}
