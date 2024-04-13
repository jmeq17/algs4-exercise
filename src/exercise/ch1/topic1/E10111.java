package exercise.ch1.topic1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class E10111 {
    public static void main(String[] args) {
        StdOut.print("Please input number of row: ");
        int M = StdIn.readInt();
        StdOut.print("Please input number of column: ");
        int N = StdIn.readInt();

        boolean[][] a = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                double k = StdRandom.uniformDouble(0.0, 1.0);
                a[i][j] = k > 0.5;
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (a[i][j]) StdOut.print("* ");
                else StdOut.print("  ");
            }
            StdOut.println();
        }
    }
}
