package exercise.ch1.topic1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
Dice simulation. The following code computes the exact probability distribution for the sum of two dice:

    int SIDES = 6;
    double[] dist = new double[2*SIDES+1];
    for (int i = 1; i <= SIDES; i++)
        for (int j = 1; j <= SIDES; j++)
            dist[i+j] += 1.0;

    for (int k = 2; k <= 2*SIDES; k++)
        dist[k] /= 36.0;

The value dist[i] is the probability that the dice sum to k. Run experiments to validate
this calculation simulating N dice throws, keeping track of the frequencies of occurrence
of each value when you compute the sum of two random integers between 1
and 6. How large does N have to be before your empirical results match the exact results
to three decimal places?
 */

public class E10135 {

    // generate homework.a array of probability
    public static double[] genDist() {
        int SIDES = 6;
        double[] dist = new double[2 * SIDES + 1];
        for (int i = 1; i <= SIDES; i++) for (int j = 1; j <= SIDES; j++) dist[i + j] += 1.0;

        for (int k = 2; k <= 2 * SIDES; k++) dist[k] /= 36.0;

        return dist;
    }

    // track of frequencies of occurrence of each value
    public static int[] trackDisk(int N) {
        int[] frequencies = new int[13];

        for (int i = 1; i <= N; i++) {
            frequencies[StdRandom.uniform(1, 7) + StdRandom.uniform(1, 7)]++;
        }
        return frequencies;
    }

    public static void main(String[] args) {
        double[] disk = genDist();
        int N = 1000000;
        int[] track = trackDisk(N);

        int len = track.length;
        StdOut.println("num\tfrequence\tprobability\tdecrease");
        for (int i = 2; i < len; i++) {
            StdOut.println(i + "\t" + track[i] / (double) N + "\t" + disk[i] + "\t" + (track[i] / (double) N - disk[i]));
        }
    }
}
