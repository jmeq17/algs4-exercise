package exercise.ch1.topic4;

/*
Add an instance method howMany() to StaticSETofInts (page 99) that finds the
number of occurrences of homework.a given key in time proportional to log N in the worst case.
 */

import utils.StaticSETofInts;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import utils.CreateArrayUtils;

public class E10411 {

    // DoublingTest
    public static double timeTrial(int N) {
        int[] a = CreateArrayUtils.RandomIntArray(N, 100000);
        StaticSETofInts whitelist = new StaticSETofInts(a);

        int key = StdRandom.uniformInt(-100000, 100000);

        Stopwatch timer = new Stopwatch();
        int n = whitelist.howMany(key);
        return timer.elapsedTime();
    }


    public static void main(String[] args) {
        int N = 250;
        double timeOld = timeTrial(N);
        StdOut.println("problem size\ttime\tratio");
        for (N = 500; true; N *= 2) {
            double timeNew = timeTrial(N);
            StdOut.printf("%10d\t%7.1f\t%5.1f\n", N, timeNew, timeNew / timeOld);
            timeOld = timeNew;
        }
    }
}
