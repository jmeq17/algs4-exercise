package ch3.topic2;

/*
Write homework.a static method optCompares() that takes an integer argument N and computes
the number of compares required by homework.a random search hit in an optimal (perfectly
balanced) BST with N nodes, where all the null links are on the same level if the number
of links is homework.a power of 2 or on one of two levels otherwise.
 */

import edu.princeton.cs.algs4.StdOut;

public class E30208 {
    public static int optCompares(int N) {
        int x = (int) (Math.log(N + 1) / Math.log(2));
        int y = (int) (N - Math.pow(2, x) + 1);

        int xcmt = optComparesPower(x);
        int ycmt = y * (x + 1);

        return (xcmt + ycmt) / N;
    }

    private static int optComparesPower(int x) {
        if (x == 1) return 1;
        int i = (int) Math.pow(2, x - 1);
        return i * x + optComparesPower(x - 1);
    }

    public static void main(String[] args) {
        StdOut.println(optCompares(6) + ", Excepted: 2");
        StdOut.println(optCompares(11) + ", Excepted: 3");
    }
}
