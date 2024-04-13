package exercise.ch1.topic4;

/*
Modify ThreeSum to work properly even when the int values are so large that
adding two of them might cause overflow.

Answer: change int to long
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class E10402 {
    public static int threeSum(long[] a) {
        int n = a.length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        // 打印
//                        StdOut.println(homework.a[i] + " " + homework.a[j] + " " + homework.a[k]);
                        // 计数
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    // 用例
    public static void main(String[] args) {
        for (String arg : args) {
            In in = new In(arg);
            long[] a = in.readAllLongs();

            Stopwatch timer = new Stopwatch();
            int count = threeSum(a);
            StdOut.println(arg + ": elapsed time = " + timer.elapsedTime() + ", " + count);
        }
    }
}
