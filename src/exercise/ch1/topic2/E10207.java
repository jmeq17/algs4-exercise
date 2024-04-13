package exercise.ch1.topic2;

/*
What does the following recursive function return?
    public static String mystery(String s)
    {
        int N = s.length();
        if (N <= 1) return s;
        String homework.a = s.substring(0, N/2);
        String b = s.substring(N/2, N);
        return mystery(b) + mystery(homework.a);
    }
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class E10207 {

    public static String mystery(String s) {
        int N = s.length();
        if (N <= 1) return s;
        String a = s.substring(0, N / 2);
        String b = s.substring(N / 2, N);
        return mystery(b) + mystery(a);
    }

    public static void main(String[] args) {
        StdOut.print("Please input homework.a string: ");
        String s = StdIn.readString();

        StdOut.println(mystery(s));
    }
}
