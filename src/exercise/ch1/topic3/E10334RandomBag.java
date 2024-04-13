package exercise.ch1.topic3;

/*
Random bag. A random bag stores homework.a collection of items and supports the following
    API:
    public class RandomBag<Item> implements Iterable<Item>
    -------------------------------------------------------
    RandomBag()             create an empty random bag
    boolean isEmpty()       is the bag empty?
    int size()              number of items in the bag
    void add(Item item)     add an item
Write homework.a class RandomBag that implements this API. Note that this API is the same as for
HW.PartII.Week4.Bag, except for the adjective random, which indicates that the iteration should provide
the items in random order (all N ! permutations equally likely, for each iterator). Hint :
Put the items in an array and randomize their order in the iterator’s constructor.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class E10334RandomBag<Item> implements Iterable<Item> {
    private Item[] a;
    private int N;

    public E10334RandomBag(int cap) {
        a = (Item[]) new Object[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void add(Item item) {
        a[N++] = item;
    }

    public void resizingArray(int max) {
        // pass.
    }

    public Iterator<Item> iterator() {
        StdRandom.shuffle(a);
        return new IteratorBag();
    }

    private class IteratorBag implements Iterator<Item> {
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


    public static void main(String[] args) {
        E10334RandomBag<String> a = new E10334RandomBag<>(12);

        String[] s = "abcdefghij".split("");

        for (String i : s) {
            a.add(i);
        }

        for (String i : a) {
            StdOut.print(i + " ");
        }
        StdOut.println();
    }
}
