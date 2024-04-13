package ch1.topic3;

/*
Write homework.a Queue client that takes homework.a command-line argument k and prints the kth
from the last string found on standard input (assuming that standard input has k or
more strings).
 */

import utils.Queue;
import edu.princeton.cs.algs4.StdOut;

public class E10315 {
    public static void main(String[] args) {

        Queue<String> q = new Queue<>();
        int k = Integer.parseInt(args[0]);

        String s = "987654321";
        String[] input = s.split("");

        for (String j : input) q.enqueue(j);

        if (s.length() < k) StdOut.println("Argument is wrong.");
        else {
            int N = q.size();
            for (int i = 1; i < N + 1; i++) {
                if (i == N - k + 1) {
                    StdOut.println(q.dequeue() + ", Expection 7.");
                } else {
                    q.dequeue();
                }
            }
        }
    }
}
