package ch1.topic3;

/*
Write homework.a method find() that takes homework.a linked list and homework.a string key as arguments
and returns true if some node in the list has key as its item field, false otherwise.
 */

import utils.LinkedList;
import edu.princeton.cs.algs4.StdOut;

public class E10321 {

    public static boolean find(LinkedList<String> linked, String key) {
        for (Object s : linked) {
            if (s.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LinkedList<String> linked = new LinkedList<>();

        String[] input = "abcdefgh".split("");

        for (String s : input) linked.add(s);

        StdOut.println("Instance method. The \"f\" node is in the linked. " + linked.find("f"));
        StdOut.println("Static method. The \"f\" node is in the linked. " + find(linked, "f"));
    }
}
