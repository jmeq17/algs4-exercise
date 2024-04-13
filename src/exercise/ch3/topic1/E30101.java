package exercise.ch3.topic1;

/*
Write homework.a client that creates homework.a symbol table mapping letter grades to numerical
scores, as in the table below, then reads from standard input homework.a list of letter grades and
computes and prints the GPA (the average of the numbers corresponding to the grades).

A+     A    A-   B+   B    B-   C+   C    C-   D    F
4.33 4.00 3.67 3.33 3.00 2.67 2.33 2.00 1.67 1.00 0.00
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import utils.SequentialSearchST;

public class E30101 {
    public static void main(String[] args) {
        SequentialSearchST<String, Double> st = new SequentialSearchST<>();

        In in = new In(args[0]);
        while (!in.isEmpty()) {
            String s = in.readString();
            Double d = in.readDouble();

            st.put(s, d);
        }

        Iterable<String> iterator = st.keys();

        double sum = 0;
        int n = 0;
        for (String i : iterator) {
            sum += st.get(i);
            n++;
        }

        StdOut.printf("The GPA is: %.2f.\n", sum / n);
    }
}
