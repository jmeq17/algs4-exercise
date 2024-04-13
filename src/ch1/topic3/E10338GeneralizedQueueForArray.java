package ch1.topic3;

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
 **/

import edu.princeton.cs.algs4.StdOut;

public class E10338GeneralizedQueueForArray<Item> {
    private Item[] a;
    private int N;

    public E10338GeneralizedQueueForArray(int cap) {
        a = (Item[]) new Object[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Item x) {
        a[N++] = x;
    }

    public Item delete(int k) {
        if (isEmpty()) throw new IllegalStateException("The queue is empty.");
        Item item = a[k - 1];
        for (int i = k - 1; i < N - 1; i++) {
            a[i] = a[i + 1];
        }
        a[--N] = null;
        return item;
    }

    public static void main(String[] args) {
        E10338GeneralizedQueueForArray<String> s = new E10338GeneralizedQueueForArray<>(10);

        String[] input = "abcdefghij".split("");

        for (String i : input) s.insert(i);

        StdOut.println(s.delete(3));
    }
}
