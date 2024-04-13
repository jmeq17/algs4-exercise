package ch1.topic1;

/*
Sorting three numbers. Suppose that the variables homework.a, b, c, and t are all of the
same numeric primitive type. Show that the following code puts homework.a, b, and c in ascending
order:
if (homework.a > b) { t = homework.a; homework.a = b; b = t; }
if (homework.a > c) { t = homework.a; homework.a = c; c = t; }
if (b > c) { t = b; b = c; c = t; }
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class E10126 {
    public static void main(String[] args) {
        StdOut.print("Please input there ints (departed by Enter)");
        int a = StdIn.readInt();
        int b = StdIn.readInt();
        int c = StdIn.readInt();
        int t;
        if (a > b) {
            t = a;
            a = b;
            b = t;
        }
        if (a > c) {
            t = a;
            a = c;
            c = t;
        }
        if (b > c) {
            t = b;
            b = c;
            c = t;
        }
        StdOut.println(a + ", " + b + ", " + c);
    }
}