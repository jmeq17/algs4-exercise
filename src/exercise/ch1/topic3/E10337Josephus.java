package exercise.ch1.topic3;

/******************************************************************************
 *  Compilation:  javac Josephus.java
 *  Execution:    java Josephus n m
 *  Dependencies: Queue.java
 *
 *  Solves the Josephus problem.
 *
 *  % java Josephus 2 7
 *  1 3 5 0 4 2 6
 *
 ******************************************************************************/

import utils.Queue;
import edu.princeton.cs.algs4.StdOut;

public class E10337Josephus {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        // initialize the queue
        Queue<Integer> queue = new Queue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);

        // 这个方法很棒
        while (!queue.isEmpty()) {
            for (int i = 0; i < m - 1; i++)
                queue.enqueue(queue.dequeue());
            StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println();
    }
}
