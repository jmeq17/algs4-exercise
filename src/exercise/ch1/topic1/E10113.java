package exercise.ch1.topic1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class E10113 {
    public static void main(String[] args) {

        StdOut.print("Please input number of row: ");
        int M = StdIn.readInt();
        StdOut.print("Please input number of column: ");
        int N = StdIn.readInt();

        String[][] arr = new String[M][N];
        String[][] arr2 = new String[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = "" + i + j;
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                arr2[i][j] = arr[M - i - 1][N - j - 1];
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                StdOut.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                StdOut.print(arr2[i][j] + " ");
            }
            System.out.println();
        }
    }
}