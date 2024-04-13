package utils;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class UFQuickFind {
    private final int[] id;
    private int count;

    // Time Test.
//    private int times;
    // ---------------------

    public UFQuickFind(int N) {
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
        return id[p];
    }

    public void union(int p, int q) {
        int N = id.length;
        int signP = id[p];
        int signQ = id[q];

        // Time Test.
//        times += 2;
        // ---------------------

        if (signP == signQ) return;

        // Time Test.
//        times += 10;
        // ---------------------

        for (int i = 0; i < N; i++) {
            if (id[i] == signP) {
                id[i] = signQ;

                // Time Test.
//                times += 1;
                // ---------------------
            }
        }

        // Time Test.
//        StdOut.println("times:" + times);
//        times = 0;
        // ---------------------

        count--;
    }


    // DoublingTest
    private static void Test(int[][] a, int N) {
        UFQuickFind uf = new UFQuickFind(N);

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
//        UFQuickFind uf = new UFQuickFind(N);
//        while (!StdIn.isEmpty()) {
//            int p = StdIn.readInt();
//            int q = StdIn.readInt();
//            if (uf.connected(p, q)) continue;
//            uf.union(p, q);
//            StdOut.println(p + " " + q);
//        }
//        StdOut.println(uf.count() + " components");


        // DoublingTest
        double timeOld = timeTrial(125);
        StdOut.println("problem size\ttime\tratio");
        for (int N = 250; true; N += N) {
            double timeNew = timeTrial(N);
            StdOut.printf("%6d\t%7.1f\t%5.1f\n", N, timeNew, timeNew / timeOld);
            timeOld = timeNew;
        }
    }
}
