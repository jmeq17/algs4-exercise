package ch1.topic3;

/*
Give the output printed by java Stack for the input
    it was - the best - of times - - - it was - the - -
 */

import utils.Stack;
import edu.princeton.cs.algs4.StdOut;

public class E10302 {
    public static void main(String[] args) {
        Stack<String> s = new Stack<>();

        String[] input = "it was - the best - of times - - - it was - the - -".split("\\s+");

        for (String value : input) {
            if (!value.equals("-")) {
                s.push(value);
            } else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}
