package ch1.topic3;

/*
Random queue. A random queue stores homework.a collection of items and supports the
following API:
    public class RandomQueue<Item>
    ---------------------------------------
    RandomQueue()               create an empty random queue
    boolean isEmpty()           is the queue empty?
    void enqueue(Item item)     add an item
    Item dequeue()              remove and return homework.a random item (sample without replacement)
    Item sample()               return homework.a random item, but do not remove (sample with replacement)
Write homework.a class RandomQueue that implements this API. Hint : Use an array representation
(with resizing). To remove an item, swap one at homework.a random position (indexed 0 through
N-1) with the one at the last position (index N-1). Then delete and return the last object,
as in ResizingArrayStack. Write homework.a client that deals bridge hands (13 cards each)
using RandomQueue<Card>.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class E10335RandomQueue<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[8];
    private int N;

    public E10335RandomQueue() {

    }

    public E10335RandomQueue(int cap) {
        a = (Item[]) new Object[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void enqueue(Item item) {
        if (N == a.length) resizingArray(2 * N);
        a[N++] = item;
    }

    public Item dequeue() {
        if (N < a.length / 4) resizingArray(a.length / 2);
        int i = StdRandom.uniform(0, N);
        Item item = a[i];
        exch(i, N - 1);
        a[--N] = null;
        return item;
    }

    public Item sample() {
        return a[StdRandom.uniform(0, N)];
    }

    private void exch(int i, int j) {
        Item k = a[i];
        a[i] = a[j];
        a[j] = k;
    }

    private void resizingArray(int max) {
        Item[] copy = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            copy[i] = a[i];
        }
        a = copy;
    }

    // E10366. Iterator.
    public Iterator<Item> iterator() {
        StdRandom.shuffle(a);
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        int i = 0;

        public boolean hasNext() {
            return i < N;
        }

        public void remove() {

        }

        public Item next() {
            while (a[i] == null) {
                i++;
                N++;
            }
            return a[i++];
        }
    }


    // ---------
    // test method
    public static void spreadCard(String[] str, E10335RandomQueue<String> s) {
        for (int j = 0; j < 13; j++) {
            str[j] = s.dequeue();
        }
    }

    public static void main(String[] args) {
        E10335RandomQueue<String> s = new E10335RandomQueue<>(52);

        String[] num = "1 2 3 4 5 6 7 8 9 10 J Q K".split("\\s+");

        for (int i = 0; i < 13; i++) {
            s.enqueue("♥" + num[i]);
            s.enqueue("♠" + num[i]);
            s.enqueue("♦" + num[i]);
            s.enqueue("♣" + num[i]);
        }

        String[] a = new String[13];
        String[] b = new String[13];
        String[] c = new String[13];
        String[] d = new String[13];

        spreadCard(a, s);
        spreadCard(b, s);
        spreadCard(c, s);
        spreadCard(d, s);

        for (String i : a) {
            StdOut.print(i + " ");
        }
        StdOut.println();
        for (String i : b) {
            StdOut.print(i + " ");
        }
        StdOut.println();
        for (String i : c) {
            StdOut.print(i + " ");
        }
        StdOut.println();
        for (String i : d) {
            StdOut.print(i + " ");
        }
    }
}
