package exercise.ch1.topic1;

/*
Bad shuffling. Suppose that you choose homework.a random integer between 0 and N-1
in our shuffling code instead of one between i and N-1. Show that the resulting order is
not equally likely to be one of the N! possibilities. Run the test of the previous exercise
for this version.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class E10137 {

    public static void badShuffle(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) { // Exchange homework.a[i] with random element in homework.a[i..N-1]
            int r = StdRandom.uniform(N);
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void main(String[] args) {
        int N = 10000000;
        int M = 10;

        int[] a = new int[M];
        int[][] b = new int[M][M];

        for (int i = 0; i < N; i++) {
            E10136.initilizeArray(a);
            badShuffle(a);
            E10136.changeMxM(a, b);
        }

        for(int i = 0; i < M; i++){
            for(int j = 0; j < M; j++){
                StdOut.print(b[i][j] + "\t");
            }
            StdOut.println();
        }
    }
}
