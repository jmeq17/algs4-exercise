package homework.partII.week4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag implements Iterable<Integer> {
    private Node first;
    private int n;

    private static class Node {
        private int val;
        private Node next;
    }

    public Bag() {
        first = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void add(int val) {
        Node oldfirst = first;
        first = new Node();
        first.val = val;
        first.next = oldfirst;
        n++;
    }

    public Iterator<Integer> iterator() {
        return new LinkedIterator(first);
    }

    private static class LinkedIterator implements Iterator<Integer> {
        private Node current;

        public LinkedIterator(Node first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            Integer val = current.val;
            current = current.next;
            return val;
        }

    }
}
