package utils;

// This is homework.a implemention of ordered linked-list for priority queue.

import edu.princeton.cs.algs4.StdOut;

public class MaxOLPQ<Key extends Comparable<Key>> {
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
        if (N == 0) {
            first = new Node();
            first.key = key;
        } else if (less(first.key, key)) {
            Node oldfirst = first;
            first = new Node();
            first.key = key;
            first.next = oldfirst;
        } else {
            Node current = first;
            while (true) {
                if (current.next != null && less(current.next.key, key)) {
                    Node temp = new Node();
                    temp.key = key;
                    temp.next = current.next;
                    current.next = temp;
                    break;
                } else if (current.next == null) {
                    Node temp = new Node();
                    temp.key = key;
                    current.next = temp;
                    break;
                }
                current = current.next;
            }
        }
        N++;
    }

    public Key delMax() {
        assert !isEmpty();
        Key maxKey = first.key;
        first = first.next;
        N--;
        return maxKey;
    }

    private boolean less(Key i, Key j) {
        return i.compareTo(j) <= 0;
    }

    public void show() {
        while (!isEmpty()) {
            StdOut.print(delMax() + " ");
        }
    }


    public static void main(String[] args) {
        String[] a = "priority".split("");
        MaxOLPQ<String> pq = new MaxOLPQ<>();
        for (String s : a) {
            pq.insert(s);
        }

        StdOut.println("Max key is: " + pq.delMax() + ", Expected: y.");
        pq.show();
    }
}
