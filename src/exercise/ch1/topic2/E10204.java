package exercise.ch1.topic2;

/*
What does the following code fragment print?
    String string1 = "hello";
    String string2 = string1;
    string1 = "world";
    StdOut.println(string1);
    StdOut.println(string2);
 */

import edu.princeton.cs.algs4.StdOut;

public class E10204 {
    public static void main(String[] args) {
        String string1 = "hello";
        String string2 = string1;
        string1 = "world";
        StdOut.println(string1);
        StdOut.println(string2);
    }
}
