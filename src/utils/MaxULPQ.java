package utils;

// This is homework.a implemention of unordered linked-list for priority queue.

import edu.princeton.cs.algs4.StdOut;

public class MaxULPQ<Key extends Comparable<Key>> {
    private Node first;
    private int N;

    private class Node {
        Key key;
        Node next;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key key) {
        Node oldfirst = first;
        first = new Node();
        first.key = key;
        first.next = oldfirst;
        N++;
    }

    public Key delMax() {
        Key maxKey = findMax();
        if (maxKey.compareTo(first.key) == 0) {
            first = first.next;
            N--;
            return maxKey;
        }
        for (Node current = first; current.next != null; current = current.next) {
            if (maxKey.compareTo(current.next.key) == 0) {
                current.next = current.next.next;
                break;
            }
        }
        N--;
        return maxKey;
    }

    private Key findMax() {
        assert !isEmpty();
        Key maxKey = first.key;
        for (Node current = first.next; current != null; current = current.next) {
            if (less(maxKey, current.key)) maxKey = current.key;
        }
        return maxKey;
    }

    private boolean less(Key i, Key j) {
        return i.compareTo(j) < 0;
    }

    public void show() {
        while (!isEmpty()) {
            StdOut.print(delMax() + " ");
        }
    }


    public static void main(String[] args) {
        String[] a = "priority".split("");
        MaxULPQ<String> pq = new MaxULPQ<>();
        for (String s : a) {
            pq.insert(s);
        }

        StdOut.println("Max key is: " + pq.delMax() + ", Expected: y.");
        pq.show();
    }
}
