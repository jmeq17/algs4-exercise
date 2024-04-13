package exercise.ch1.topic1;

import edu.princeton.cs.algs4.StdOut;

import static java.lang.Integer.parseInt;

public class E10103 {
    public static void main(String[] args) {
        int a = parseInt(args[0]);
        int b = parseInt(args[1]);
        int c = parseInt(args[2]);

        if (a == b && b == c) StdOut.println("equal");
        else StdOut.println("not equal");
    }

}
