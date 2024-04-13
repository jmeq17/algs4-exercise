package homework.partI.week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[8];
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();

        if (n == a.length) resizingArray(2 * n);
        a[n++] = item;
    }

    // remove and return homework.a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        if (n < a.length / 4) resizingArray(a.length / 2);
        int i = StdRandom.uniform(0, n);
        Item item = a[i];
        exch(i, n - 1);
        a[--n] = null;
        return item;
    }

    // return homework.a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        return a[StdRandom.uniform(0, n)];
    }

    private void exch(int i, int j) {
        Item k = a[i];
        a[i] = a[j];
        a[j] = k;
    }

    private void resizingArray(int max) {
        Item[] copy = (Item[]) new Object[max];
        if (n >= 0) System.arraycopy(a, 0, copy, 0, n);
        a = copy;
    }

    public Iterator<Item> iterator() {
//        StdRandom.shuffle(homework.a);
        Item[] tem = (Item[]) new Object[n];
        StdRandom.shuffle(tem);
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        int i = 0;
        int temN;
        Item[] tem;

        public RandomIterator() {
            temN = n;
            tem = (Item[]) new Object[temN];
            System.arraycopy(a, 0, tem, 0, temN);
            StdRandom.shuffle(tem);
        }

        public boolean hasNext() {
            return i < temN;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            return tem[i++];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> a = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (!s.equals("-")) {
                a.enqueue(s);
            } else
                StdOut.println(a.dequeue());
        }

        StdOut.println();

        while (!a.isEmpty()) StdOut.println(a.dequeue());
    }
}
