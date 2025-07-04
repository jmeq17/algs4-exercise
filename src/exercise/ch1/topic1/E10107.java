package exercise.ch1.topic1;

import edu.princeton.cs.algs4.StdOut;

public class E10107 {
    public static void main(String[] args) {
        double t = 9.0;
        while (Math.abs(t - 9.0 / t) > .001) t = (9.0 / t + t) / 2.0;
        StdOut.printf("homework.a. " + "%.5f\n", t);

        // ---

        int sum = 0;
        for (int i = 1; i < 1000; i++)
            for (int j = 0; j < i; j++) sum++;
        StdOut.println("b. " + sum);

        // ---

        int sum2 = 0;
        for (int i = 1; i < 1000; i *= 2)
            for (int j = 0; j < 1000; j++)
                sum2++;
        StdOut.println("c. " + sum2);
    }
}
