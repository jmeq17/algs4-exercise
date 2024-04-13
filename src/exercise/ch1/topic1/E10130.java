package exercise.ch1.topic1;

/*
Array exercise. Write homework.a code fragment that creates an N-by-N boolean array
homework.a[][] such that homework.a[i][j] is true if i and j are relatively prime (have no common factors),
and false otherwise.
 */

/*
The code fragment of Euclid's algorithm can be used.
 */

import edu.princeton.cs.algs4.StdOut;

public class E10130 {

    public static boolean isPrime(int i, int j) {
        if (i == 0 || j == 0) return false;
        return gcd(i, j) == 1;
    }

    public static int gcd(int i, int j) {
        if (j == 0) return i;
        else return gcd(j, i % j);
    }

    public static void main(String[] args) {
        boolean[][] a = new boolean[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                a[i][j] = isPrime(i, j);
                StdOut.print(a[i][j] + "\t");
            }
            StdOut.println();
        }
    }
}
