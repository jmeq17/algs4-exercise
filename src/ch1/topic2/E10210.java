package ch1.topic2;

/*
Develop homework.a class VisualCounter that allows both increment and decrement
operations. Take two arguments N and max in the constructor, where N specifies the
maximum number of operations and max specifies the maximum absolute value for
the counter. As homework.a side effect, create homework.a plot showing the value of the counter each time its
tally changes.
 */

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class E10210 {
    private final int N;
    private final int max;
    private int count;

    private int numberOfOperations;

    public E10210(int N, int max) {
        this.N = N;
        this.max = Math.abs(max);

        StdDraw.setXscale(0, this.N + 1);
        StdDraw.setYscale(-this.max, this.max);
        StdDraw.setPenRadius(.005);
    }

    public void increment() {
        if (numberOfOperations < N && Math.abs(count) < max) {
            numberOfOperations++;
            count++;
            StdDraw.point(numberOfOperations, count);
        }
    }

    public void decrement() {
        if (numberOfOperations < N && Math.abs(count) <= max) {
            count--;
            numberOfOperations++;
            StdDraw.point(numberOfOperations, count);
        }
    }

    public static void main(String[] args) {
        StdOut.print("Please input N: ");
        int N = StdIn.readInt();
        StdOut.print("Please input max: ");
        int max = StdIn.readInt();

        E10210 demo = new E10210(N, max);

        for (int i = 0; i < N; i++) {
            if (StdRandom.uniform() < 0.5) demo.increment();
            else demo.decrement();
        }
    }
}
