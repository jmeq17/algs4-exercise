package exercise.ch1.topic3;

import utils.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Rene Argento on 8/23/16.
 */

public class Demo {

    private static boolean canAPermutationBeGenerated(String[] permutation) {
        Stack<Integer> stack = new Stack<>();
        int current = 0;

        for (String value : permutation) {
            int integerValue = Integer.parseInt(value);

            if (stack.isEmpty() || integerValue > stack.peek()) {
                while (current < integerValue) {
                    stack.push(current);
                    current++;
                }

                current++;
            } else if (integerValue == stack.peek()) {
                stack.pop();
            } else {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        StdOut.println("Can homework.a permutation be generated?");
        StdOut.print("Input: 4 3 2 1 0 9 8 7 6 5 - ");
        StdOut.println(canAPermutationBeGenerated("4 3 2 1 0 9 8 7 6 5".split(" ")) + " Expected: true");
        StdOut.print("Input: 4 6 8 7 5 3 2 9 0 1 - ");
        StdOut.println(canAPermutationBeGenerated("4 6 8 7 5 3 2 9 0 1".split(" ")) + " Expected: false");
        StdOut.print("Input: 2 5 6 7 4 8 9 3 1 0 - ");
        StdOut.println(canAPermutationBeGenerated("2 5 6 7 4 8 9 3 1 0".split(" ")) + " Expected: true");
        StdOut.print("Input: 4 3 2 1 0 5 6 7 8 9 - ");
        StdOut.println(canAPermutationBeGenerated("4 3 2 1 0 5 6 7 8 9".split(" ")) + " Expected: true");
        StdOut.print("Input: 1 2 3 4 5 6 9 8 7 0 - ");
        StdOut.println(canAPermutationBeGenerated("1 2 3 4 5 6 9 8 7 0".split(" ")) + " Expected: true");
        StdOut.print("Input: 0 4 6 5 3 8 1 7 2 9 - ");
        StdOut.println(canAPermutationBeGenerated("0 4 6 5 3 8 1 7 2 9".split(" ")) + " Expected: false");
        StdOut.print("Input: 1 4 7 9 8 6 5 3 0 2 - ");
        StdOut.println(canAPermutationBeGenerated("1 4 7 9 8 6 5 3 0 2".split(" ")) + " Expected: false");
        StdOut.print("Input: 2 1 4 3 6 5 8 7 9 0 - ");
        StdOut.println(canAPermutationBeGenerated("2 1 4 3 6 5 8 7 9 0".split(" ")) + " Expected: true");
        StdOut.print("Input: 4 3 2 1 0 5 9 7 8 6 - ");
        StdOut.println(canAPermutationBeGenerated("4 3 2 1 0 5 9 7 8 6".split(" ")) + " Expected: false");
    }

}