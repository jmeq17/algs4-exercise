package ch1.topic2;

/*
What does the following code fragment print?
    String s = "Hello World";
    s.toUpperCase();
    s.substring(6, 11);
    StdOut.println(s);

Answer : "Hello World". String objects are immutable—string methods return
homework.a new String object with the appropriate value (but they do not change the value
of the object that was used to invoke them). This code ignores the objects returned
and just prints the original string. To print "WORLD", use s = s.toUpperCase() and
s = s.substring(6, 11).
 */

import edu.princeton.cs.algs4.StdOut;

public class E10205 {
    public static void main(String[] args) {
        String s = "Hello World";
        s.toUpperCase();
        s.substring(6, 11);
        StdOut.println(s);
    }
}
