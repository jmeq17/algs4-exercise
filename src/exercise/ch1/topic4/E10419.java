package exercise.ch1.topic4;

/*
Local minimum of homework.a matrix. Given an N-by-N array homework.a[] of N 2 distinct integers,
design an algorithm that finds homework.a local minimum: an entry homework.a[i][j] that is strictly less
than its neighbors. Internal entries have 4 neighbors; entries on an edge have 3 neighbors;
entries on homework.a corner have 2 neighbors. The running time of your program should
be proportional to N in the worst case, which means that you cannot afford to examine
all N 2 entries.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class E10419 {

    public static int[] FindValley(int[][] a) {
        int lo_x = 0, lo_y = 0, hi_x = a.length - 1, hi_y = a[1].length - 1;
        int[] index = {-1, -1};
        return FindValley(a, lo_x, lo_y, hi_x, hi_y, index);
    }

    private static int[] FindValley(int[][] a, int lo_x, int lo_y, int hi_x, int hi_y, int[] index) {
        if (hi_x - lo_x < 2 || hi_y - lo_y < 2) return index;
        int mid_x = (hi_x + lo_x) / 2, mid_y = (hi_y + lo_y) / 2;
        if (a[mid_x][mid_y] < a[mid_x][mid_y - 1] && a[mid_x][mid_y] < a[mid_x][mid_y + 1] &&
                a[mid_x][mid_y] < a[mid_x - 1][mid_y] && a[mid_x][mid_y] < a[mid_x + 1][mid_y]) {
            index[0] = mid_x;
            index[1] = mid_y;
            return index;
        }
        if (a[mid_x - 1][mid_y] < a[mid_x + 1][mid_y] && a[mid_x][mid_y - 1] < a[mid_x][mid_y + 1])
            return FindValley(a, lo_x, lo_y, mid_x, mid_y, index);
        if (a[mid_x - 1][mid_y] > a[mid_x + 1][mid_y] && a[mid_x][mid_y - 1] < a[mid_x][mid_y + 1])
            return FindValley(a, mid_x, lo_y, hi_x, mid_y, index);
        if (a[mid_x - 1][mid_y] < a[mid_x + 1][mid_y] && a[mid_x][mid_y - 1] > a[mid_x][mid_y + 1])
            return FindValley(a, lo_x, mid_y, mid_x, hi_y, index);
        return FindValley(a, mid_x, mid_y, hi_x, hi_y, index);
    }


    public static double timeTrial(int N) {
        int[][] a = new int[N][N];
        int MAX = 1000000;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = StdRandom.uniform(-MAX, MAX);
            }
        }
        Stopwatch timer = new Stopwatch();
        FindValley(a);
        return timer.elapsedTime();
    }


    public static void main(String[] args) {
        // 实现性用例
//        int[][] homework.a = {{5, 90, 3, 10},
//                {4, 1, -7, 15},
//                {7, -1, -8, 19},
//                {12, 8, 13, 99}};
//
//        int[] index = FindValley(homework.a);
//        StdOut.println(homework.a[index[0]][index[1]]);


        // 倍率测试
        double timeOld = timeTrial(125);
        StdOut.println("problem size\ttime\tratio");
        for (int N = 250; true; N += N) {
            double timeNew = timeTrial(N);
            StdOut.printf("%6d\t%7.1f\t%5.1f\n", N, timeNew, timeNew / timeOld);
            timeOld = timeNew;
        }
    }
}
