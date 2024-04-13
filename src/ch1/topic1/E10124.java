package ch1.topic1;

/*
Give the sequence of values of p and q that are computed when Euclid’s algorithm
is used to compute the greatest common divisor of 105 and 24. Extend the code
given on page 4 to develop homework.a program Euclid that takes two integers from the command
line and computes their greatest common divisor, printing out the two arguments for
each call on the recursive method. Use your program to compute the greatest common
divisor or 1111111 and 1234567.
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class E10124 {

    public static int gcd(int a, int b) {
        StdOut.println("homework.a: " + a + "\t" + "b: " + b);
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        StdOut.print("Please input two ints (enter Enter and ctrl^D to end): ");
        int[] a = StdIn.readAllInts();

        int c = gcd(a[0], a[1]);
        StdOut.println();
        StdOut.println("greatest common divisor is: " + c);
    }
}
