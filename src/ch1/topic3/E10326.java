package ch1.topic3;

/*
Write homework.a method remove() that takes homework.a linked list and homework.a string key as arguments
and removes all of the nodes in the list that have key as its item field.
 */

/*
修改链表，不能使用静态方法
 */

import utils.LinkedList;
import edu.princeton.cs.algs4.StdOut;

public class E10326 {
    public static void main(String[] args) {
        LinkedList<String> linked = new LinkedList<>();

        String[] input = "abcaefah".split("");

        for (String s : input) linked.add(s);
        String s = "a";

        StdOut.print("before remove: ");
        for (String item : linked) {
            StdOut.print(item + " ");
        }

        StdOut.println();
        linked.remove(s);

        StdOut.print("after remove: ");
        for (String item : linked) {
            StdOut.print(item + " ");
        }
    }
}
