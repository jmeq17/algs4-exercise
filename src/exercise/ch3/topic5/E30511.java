package exercise.ch3.topic5;

import edu.princeton.cs.algs4.StdOut;

/**
 * Develop homework.a MultiSET class that is like SET, but allows equal keys and thus implements homework.a mathematical multiset.
 */

public class E30511 {
    public static void main(String[] args) {
        MultiSET<String> set = new MultiSET<>();
        String[] s = "keyvalue' + 'pair with that key in the input (not just the most recent, as in the associative-array".split("");

        for (String i : s) set.put(i);

        StdOut.println(set.get("h") + ", Excepted: 5.");
    }
}
