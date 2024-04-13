package ch1.topic1;

/*
Empirical shuffle check. Run computational experiments to check that our
shuffling code on page 32 works as advertised. Write homework.a program ShuffleTest that takes
command-line arguments M and N, does N shuffles of an array of size M that is initialized
with homework.a[i] = i before each shuffle, and prints an M-by-M table such that row i
gives the number of times i wound up in position j for all j. All entries in the array
should be close to N/M.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class E10136 {

    public static void shuffle(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) { // Exchange homework.a[i] with random element in homework.a[i..N-1]
            int r = i + StdRandom.uniform(N - i);
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void initilizeArray(int[] a) {
        int M = a.length;
        for (int i = 0; i < M; i++) {
            a[i] = i;
        }
    }

    public static void changeMxM(int[] a, int[][] b) {
        int M = a.length;
        for (int i = 0; i < M; i++) {
            b[a[i]][i]++;
        }
    }

    public static void main(String[] args) {
        int N = 10000000;
        int M = 10;

        int[] a = new int[M];
        int[][] b = new int[M][M];

        for (int i = 0; i < N; i++) {
            initilizeArray(a);
            shuffle(a);
            changeMxM(a, b);
        }

        for(int i = 0; i < M; i++){
            for(int j = 0; j < M; j++){
                StdOut.print(b[i][j] + "\t");
            }
            StdOut.println();
        }
    }
}
