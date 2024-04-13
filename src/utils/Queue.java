package utils;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;

    // E10341
    public Queue(Queue<Item> q) {
        int n = q.size();
        for (int i = 0; i < n; i++) {
            Item item = q.dequeue();
            q.enqueue(item);
            this.enqueue(item);
        }
        this.N = n;
    }

    public Queue() {

    }

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
        // Or: N == 0.  // 考虑不周，这里不行，因为下边要通过 first == null 来判断 N 是否减一
        // Stack 的代码应该可以，但还是保持一致的好
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
//        last.next = null;   // 可省略
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        N--;
        return item;
    }

    //  iterator
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

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

    public Node reverse(Node x) {
        Node first = x;
        Node reverse = null;
        while (first != null) {
            Node second = first.next;
            first.next = reverse;
            reverse = first;
            first = second;
        }
        return reverse;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    public static void main(String[] args) {
        Queue<String> s = new Queue<>();

        // TEST 1
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                s.enqueue(item);
            } else if (!s.isEmpty()) StdOut.print(s.dequeue() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}