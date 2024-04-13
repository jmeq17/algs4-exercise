package homework.partI.week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node left;
    private Node right;
    private int n;

    private class Node {
        Item item;
        Node next;
        Node pre;
    }

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node oldleft = left;
        left = new Node();
        left.item = item;
        if (isEmpty()) {
            right = left;
        } else {
            left.next = oldleft;
            oldleft.pre = left;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node oldright = right;
        right = new Node();
        right.item = item;
        if (isEmpty()) {
            left = right;
        } else {
            oldright.next = right;
            right.pre = oldright;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();

        Item item = left.item;
        if (n == 1) {
            left = null;
            right = null;
        } else {
            left.next.pre = null;
            left = left.next;
        }
        n--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();

        Item item = right.item;
        if (n == 1) {
            right = null;
            left = null;
        } else {
            right.pre.next = null;
            right = right.pre;
        }
        n--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new IteratorDeque();
    }

    private class IteratorDeque implements Iterator<Item> {
        Node current = left;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<String> a = new Deque<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            int i = StdRandom.uniform(0, 2);
            if (!s.equals("-")) {
                if (i == 0)
                    a.addFirst(s);
                else a.addLast(s);
            } else {
                StdOut.println(a.removeFirst());
            }
        }

        StdOut.println();

        a.addFirst("left");
        a.addLast("right");
        StdOut.println(a.removeFirst());
        StdOut.println(a.removeLast());

        while (!a.isEmpty()) StdOut.println(a.removeFirst());
    }
}