package ch1.topic3;

/*
Give homework.a code fragment that removes the last node in homework.a linked list whose first node is first.
 */

import utils.LinkedList;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class E10319 {

    public static Object delLast(LinkedList linked) {
        Iterator iterator = linked.iterator();

        Object s = null;
        while (iterator.hasNext()) {
            s = iterator.next();
        }

        return s;
    }

    public static void main(String[] args) {
        LinkedList<String> linked = new LinkedList<>();

        String[] input = "abcdefgh".split("");

        for (String s : input) linked.add(s);

        StdOut.println("Instance method. The last node is: " + linked.removeLast() + ", Expection: homework.a");
        StdOut.println("Static method. The last node is: " + delLast(linked) + ", Expection: homework.a");
    }
}
