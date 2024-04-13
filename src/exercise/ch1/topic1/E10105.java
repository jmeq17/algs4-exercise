package exercise.ch1.topic1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class E10105 {
    public static void main(String[] args) {
        double x = StdIn.readDouble();
        double y = StdIn.readDouble();
        if (x > 0 && x < 1 && y < 1 && y > 0) StdOut.println("true");
        else StdOut.println("false");
    }
}
