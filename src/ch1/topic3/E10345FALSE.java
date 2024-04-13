package ch1.topic3;

/*
Stack generability. Suppose that we have homework.a sequence of intermixed push and
pop operations as with our test stack client, where the integers 0, 1, ..., N-1 in that
order (push directives) are intermixed with N minus signs (pop directives). Devise an
algorithm that determines whether the intermixed sequence causes the stack to underflow.
(You may use only an amount of space independent of N—you cannot store the
integers in homework.a data structure.) Devise homework.a linear-time algorithm that determines whether homework.a
given permutation can be generated as output by our test client (depending on where
the pop directives occur).
 */

import utils.Stack;
import edu.princeton.cs.algs4.StdOut;

public class E10345FALSE {

    public static boolean arrayImplement(String[] s) {
        int arrayLength = 0;
        for (String i : s) {
            if (i.equals("-")) {
                arrayLength--;
                if (arrayLength < 0) return false;
            } else arrayLength++;
        }
        return true;
    }

    public static boolean stackImplement(String s) {
        Stack<Integer> stack = new Stack<>();
        int N = s.length();
        for (int i = 0; i < N; i++) {

        }
        return true;
    }


    public static void main(String[] args) {
        String[] test = "12-3--45--67--".split("");
        StdOut.println(arrayImplement(test));
    }
}
