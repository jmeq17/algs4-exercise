package ch2.topic2;

/*
Question 3
Shuffling homework.a linked list. Given homework.a singly-linked list containing n items, rearrange
the items uniformly at random. Your algorithm should consume homework.a logarithmic (or constant)
amount of extra memory and run in time proportional to nlogn in the worst case.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class WebQuestion3Class<Item> implements Iterable<Item> {

    private Node first;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    public Item pop() {
        assert N != 0;
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new listIterator();
    }

    private class listIterator implements Iterator<Item> {
        Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    // Shuffing homework.a linkedlist.
    public void shuffleList() {
        shuffleList(this);
    }

    private void shuffleList(WebQuestion3Class<Item> a) {
        if (a.N <= 1) return;

        WebQuestion3Class<Item> left = new WebQuestion3Class<>();
        WebQuestion3Class<Item> right = new WebQuestion3Class<>();

        int N = a.size();
        int mid = N / 2;

        for (int i = 0; i < mid; i++) {
            left.push(a.pop());
        }
        for (int i = mid; i < N; i++) {
            right.push(a.pop());
        }

        shuffleList(left);
        shuffleList(right);

        merge(a, left, right);
    }

    private void merge(WebQuestion3Class<Item> a, WebQuestion3Class<Item> left, WebQuestion3Class<Item> right) {
        while (!left.isEmpty() || !right.isEmpty()) {
            int n = StdRandom.uniform(0, 2);

            if (n == 0) {
                if (!left.isEmpty()) a.push(left.pop());
                else a.push(right.pop());
            } else {
                if (!right.isEmpty()) a.push(right.pop());
                else a.push(left.pop());
            }
        }
    }


    public static void main(String[] args) {
        WebQuestion3Class<Integer> a = new WebQuestion3Class<>();
        int N = 15;

        for (int i = 0; i < N; i++) {
            a.push(i);
        }

        a.shuffleList();

        for (int i = 0; i < N; i++) {
            StdOut.print(a.pop() + " ");
        }
    }
}
