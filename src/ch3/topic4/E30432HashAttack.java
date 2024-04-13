package ch3.topic4;

/*
Hash attack. Find 2^N strings, each of length 2^N, that have the same hashCode()
value, supposing that the hashCode() implementation for String is the following:
public int hashCode(String s) {
    int hash = 0;
    for (int i = 0; i < s.length(); i++)
        hash = (hash * 31) + s.charAt(i);
    return hash;
}
 */

import edu.princeton.cs.algs4.StdOut;

public class E30432HashAttack {
    public static int hashCode(String s) {
        int hash = 0;
        for (int i = 0; i < s.length(); i++)
            hash = (hash * 31) + s.charAt(i);
        return hash;
    }

    public static void main(String[] args) {
        int n = 4;
        String s = "a";

        StdOut.println(hashCode("AaBB"));
        StdOut.println(hashCode("BB"));
    }
}
