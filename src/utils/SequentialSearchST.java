package utils;

// ST implemention in an unordered linked list

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class SequentialSearchST<Key, Value> implements Iterable<Key> {
    private Node first;
    private int N;

    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        N++;
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key))
                return x.val;
        return null;
    }

    public void delete(Key key) {
        if (key.equals(first.key)) {
            first = first.next;
            N--;
            return;
        }
        for (Node x = first; x.next != null; x = x.next)
            if (key.equals(x.next.key)) {
                x.next = x.next.next;
                N--;
                break;
            }
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    // 简单的 keys 实现迭代器
    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<>();
        for (Node current = first; current != null; current = current.next) {
            q.enqueue(current.key);
        }
        return q;
    }

    public void show() {
        for (Node current = first; current != null; current = current.next) {
            StdOut.println("Key: " + current.key + ", Value: " + current.val + ".");
        }
    }

    public Iterator<Key> iterator() {
        return new keysIterator();
    }

    private class keysIterator implements Iterator<Key> {
        Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Key next() {
            Key item = current.key;
            current = current.next;
            return item;
        }
    }


    public static void main(String[] args) {

    }
}
