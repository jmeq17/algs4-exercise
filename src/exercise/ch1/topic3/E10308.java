package exercise.ch1.topic3;

/*
Give the contents and size of the array for DoublingStackOfStrings with the
input
    it was - the best - of times - - - it was - the - -
 */

import utils.ResizingStackOfString;
import edu.princeton.cs.algs4.StdOut;

public class E10308 {

    public static void main(String[] args) {
        ResizingStackOfString<String> a = new ResizingStackOfString<>(8);

        String[] input = "it was - the best - of times - - - it was - the - -".split("\\s+");
        int N = input.length;

        for (int i = 0; i < N; i++) {
            if (!input[i].equals("-")) a.push(input[i]);
            else if (!a.isEmpty()) a.pop();
        }

        // output
        StdOut.println("(" + a.size() + " left on stack)");
        while (!a.isEmpty()) StdOut.print(a.pop() + " ");
        StdOut.println();
        System.out.println("size of the array: " + a.arraySize());
    }
}
