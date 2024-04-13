package exercise.ch1.topic3;

/*
What does the following code fragment print when N is 50? Give homework.a high-level
description of what it does when presented with homework.a positive integer N.
    Stack<Integer> stack = new Stack<Integer>();
    while (N > 0)
    {
        stack.push(N % 2);
        N = N / 2;
    }
    for (int d : stack) StdOut.print(d);
    StdOut.println();
Answer : Prints the binary representation of N (110010 when N is 50).
 */

import utils.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class E10305 {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        int N = StdIn.readInt();
        while (N > 0) {
            stack.push(N % 2);
            N = N / 2;
        }
        for (int d : stack) StdOut.print(d);
        StdOut.println();
    }
}
