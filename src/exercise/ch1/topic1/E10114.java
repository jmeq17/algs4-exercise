package exercise.ch1.topic1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class E10114 {
    public static int lg(int N) {
        int n = 1;
        int count = 0;
        while (n * 2 <= N) {
            n = n * 2;
            count++;
        }
        return count;
    }

    public static int lg2(int N) {
        int count = 0;
        while (N >= 2) {
            count++;
            N /= 2;
        }
        return count;
    }

    public static void main(String[] args) {
        StdOut.print("Please input an int: ");
        int N = StdIn.readInt();

        StdOut.println("exact value: " + Math.log(N) / Math.log(2));
        StdOut.println("lg(): " + lg(N));
        StdOut.println("lg2(): " + lg(N));
    }
}
