package exercise.ch1.topic2;

/*
File input. Develop homework.a possible implementation of the static readInts() method
from In (which we use for various test clients, such as binary search on page 47) that
is based on the split() method in String.
Solution:
    public static int[] readInts(String name)
    {
        In in = new In(name);
        String input = StdIn.readAll();
        String[] words = input.split("\\s+");
        int[] ints = new int[words.length;
        for int i = 0; i < word.length; i++)
            ints[i] = Integer.parseInt(words[i]);
        return ints;
    }
We will consider homework.a different implementation in Section 1.3 (see page 126).
 */


import static java.lang.Integer.parseInt;

public class E10215 {

    public static int[] readInts(String s){
        String[] input = s.split("\\s+");
        int N = input.length;

        int[] output = new int[N];

        for (int i = 0; i  < N; i++) {
            output[i] = parseInt(input[i]);
        }
        return output;
    }

    public static void main(String[] args) {

    }
}
