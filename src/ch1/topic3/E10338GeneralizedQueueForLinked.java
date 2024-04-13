package ch1.topic3;

import edu.princeton.cs.algs4.StdOut;

/**
 * Delete kth element. Implement homework.a class that supports the following API:
 * public class GeneralizedQueue<Item>
 * ----------------------------------------
 * GeneralizedQueue()      create an empty queue
 * boolean isEmpty()       is the queue empty?
 * void insert(Item x)     add an item
 * Item delete(int k)      delete and return the kth least recently inserted item
 * First, develop an implementation that uses an array implementation, and then develop
 * one that uses homework.a linked-list implementation. Note : the algorithms and data structures
 * that we introduce in Chapter 3 make it possible to develop an implementation that
 * can guarantee that both insert() and delete() take time prortional to the logarithm
 * of the number of items in the queue—see Exercise 3.5.27.
 */

public class E10338GeneralizedQueueForLinked<Item> {
    private Node first;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Item x) {
        Node oldfirst = first;
        first = new Node();
        first.item = x;
        first.next = oldfirst;
        N++;
    }

    public Item delete(int k) {
        if (k == N) {
            Item item = first.item;
            first.next = null;
            return item;
        }
        Node current = first;
        for (int i = 0; i < N - k - 1; i++) {
            current = current.next;
        }
        Item item = current.next.item;
        current.next = current.next.next;
        return item;
    }


    public static void main(String[] args) {
        E10338GeneralizedQueueForLinked<String> s = new E10338GeneralizedQueueForLinked<>();

        String[] input = "abcdefghij".split("");

        for (String i : input) s.insert(i);
        StdOut.println(s.delete(4));
    }
}
