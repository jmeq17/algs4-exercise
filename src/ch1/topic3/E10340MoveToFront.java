package ch1.topic3;

/**
 * Move-to-front. Read in homework.a sequence of characters from standard input and
 * maintain the characters in homework.a linked list with no duplicates. When you read in homework.a previously
 * unseen character, insert it at the front of the list. When you read in homework.a duplicate
 * character, delete it from the list and reinsert it at the beginning. Name your program
 * MoveToFront: it implements the well-known move-to-front strategy, which is useful for
 * caching, data compression, and many other applications where items that have been
 * recently accessed are more likely to be reaccessed.
 */

import edu.princeton.cs.algs4.StdOut;

public class E10340MoveToFront<Item> {
    private Node first;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Item item) {
        if (!isEmpty()) scan(item);
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    private void scan(Item item) {
        if (first.item.equals(item)) {
            first = first.next;
            N--;
        } else {
            for (Node current = first; current.next != null; current = current.next) {
                if (current.next.item.equals(item)) {
                    current.next = current.next.next;
                    N--;
                    break;
                }
            }
        }
    }


    public static void main(String[] args) {
        E10340MoveToFront<String> s = new E10340MoveToFront<>();
        String[] input = "aabbccddee".split("");

        for (String i : input) s.insert(i);

        StdOut.println();
    }
}
