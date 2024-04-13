package exercise.ch1.topic1;

/*
Write homework.a recursive static method that computes the value of ln (N !)
 */

import edu.princeton.cs.algs4.StdOut;

public class E10120 {

    public static double lnFactorial(int N) {
        if (N == 1) return 0;
        return lnFactorial(N-1) + Math.log(N);
    }

    public static void main(String[] args) {
        StdOut.println(lnFactorial(20));
    }
}
