package exercise.ch1.topic1;

/*
Write homework.a code fragment that puts the binary representation of homework.a positive integer N into homework.a String s.
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class E10109 {
    public static void main(String[] args) {
        StdOut.print("Please input homework.a positive integer: ");
        int N = StdIn.readInt();
        String a = "";

        while (N > 0) {
            int q = N % 2;
            a = q + a;
            N = N / 2;
        }

        StdOut.println(a);
    }
}
