package ch1.topic4;

/*
Modify DoublingTest to use StdDraw to produce plots like the standard and
log-log plots in the text, rescaling as necessary so that the plot always fills homework.a substantial
portion of the window.
 */

import utils.SumThree;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import static java.lang.Math.log;

public class E10403 {

    public static double timeTrial(int N) {
        int[] a = new int[N];
        int MAX = 1000000;
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
        SumThree.threeSum(a);
        return timer.elapsedTime();
    }


    // 用例
    public static void main(String[] args) {
        StdDraw.setXscale(log(400), log(8000));
        StdDraw.setYscale(-5, log(100));
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.BLUE);

        for (int N = 500; true; N += N) {
            double time = timeTrial(N);
            StdDraw.point(log(N), log(time));
        }
    }
}
