package ch3.topic1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import utils.VisualAccumulator;
import utils.Queue;

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

    // FrequencyCounter Test
    public int putTest(Key key, Value val) {
        int count = 0;

        for (Node x = first; x != null; x = x.next) {

            count++;

            if (key.equals(x.key)) {
                x.val = val;

                return count;
            }
        }
        first = new Node(key, val, first);
        N++;

        return count;
    }
    // ----------------------

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

    public static void draw(int length) {
        In in = new In("files/tale");
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();

        StdDraw.setCanvasSize(700, 300);
        StdDraw.setXscale(-800, 14350);
        StdDraw.setYscale(-600, 5737);
        StdDraw.line(-800, 0, 14350, 0);
        StdDraw.line(0, -600, 0, 5737);
        StdDraw.text(600, 5537, "5737");
        StdDraw.text(13750, -200, "14350");
        StdDraw.text(-200, -200, "0");
        StdDraw.setPenRadius(.005);
        VisualAccumulator visual = new VisualAccumulator();

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() < length) continue;
            visual.addDataValue(st.putTest(word, 1));
        }
    }
}
