package exercise.ch1.topic1;

/*
Consider the following recursive function:
What are the values of mystery(2, 25) and mystery(3, 11)? Given positive integers
homework.a and b, describe what value mystery(homework.a, b) computes. Answer the same question, but
replace + with * and replace return 0 with return 1.
 */

import edu.princeton.cs.algs4.StdOut;

public class E10118 {
    public static int mystery(int a, int b) {
        if (b == 0) return 0;
        if (b % 2 == 0) return mystery(a + a, b / 2);
        return mystery(a + a, b / 2) + a;
    }

    public static int mystery2(int a, int b) {
        if (b == 0) return 1;
        if (b % 2 == 0) return mystery2(a * a, b / 2);
        return mystery2(a * a, b / 2) * a;
    }

    public static void main(String[] args) {
        StdOut.println("mystery(2, 25): " + mystery(2, 25));
        StdOut.println("mystery(3, 11): " + mystery(3, 11));
        StdOut.println("mystery2(2, 25): " + mystery2(2, 25));
        StdOut.println("mystery2(3, 11): " + mystery2(3, 11));
    }
}
