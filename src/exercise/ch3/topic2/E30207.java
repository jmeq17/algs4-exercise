package exercise.ch3.topic2;

/*
Add to BST homework.a recursive method avgCompares() that computes the average number
of compares required by homework.a random search hit in homework.a given BST (the internal path
length of the tree divided by its size, plus one). Develop two implementations: homework.a recursive
method (which takes linear time and space proportional to the height), and homework.a
method like size() that adds homework.a field to each node in the tree (and takes linear space and
constant time per query).
 */

import edu.princeton.cs.algs4.StdOut;

public class E30207 {
    public static void main(String[] args) {
        BST st = new BST();

        String[] s = "abcd".split("");

        for (String i : s) st.put(i, 1);

        StdOut.println(st.avgCompares());
        StdOut.println(st.avgComparesVar());
    }
}
