package exercise.ch1.topic3;

/*
A implement of ResizingArrayDeque that uses homework.a resizing array.
 */

import edu.princeton.cs.algs4.StdOut;

public class E10333ResizingArrayDeque<Item> {
    private Item[] a = (Item[]) new Object[8];
    private int Nr;
    private int Nl;

    public E10333ResizingArrayDeque() {
    }

    public E10333ResizingArrayDeque(int cap) {
        a = (Item[]) new Object[8];
    }

    public boolean isEmpty() {
        return Nr + Nl == 0;
    }

    public int size() {
        return Nr + Nl;
    }

    public void pushLeft(Item item) {
        if (Nr + Nl == a.length) resizingArray(2 * a.length);
        a[Nl++] = item;
    }

    public void pushRight(Item item) {
        if (Nr + Nl == a.length) resizingArray(2 * a.length);
        a[a.length - 1 - Nr++] = item;
    }

    public Item popLeft() {
        Item item = a[--Nl];
        if (Nl + Nr < a.length / 4) resizingArray(a.length / 2);
        a[Nl] = null;
        return item;
    }

    public Item popRight() {
        Item item = a[a.length - 1 - --Nr];
        if (Nl + Nr < a.length / 4) resizingArray(a.length / 2);
        a[a.length - 1 - Nr] = null;
        return item;
    }

    private void resizingArray(int n) {
        Item[] copy = (Item[]) new Object[n];
        for (int i = 0; i < Nl; i++) {
            copy[i] = a[i];
        }
        for (int i = 0; i < Nr; i++) {
            copy[copy.length - 1 - i] = a[a.length - 1 - i];
        }
        a = copy;
    }


    public static void main(String[] args) {
        E10333ResizingArrayDeque<String> s = new E10333ResizingArrayDeque<>();
        String[] input = "abcdefgijk".split("");

        for (int i = 0; i < 5; i++) {
            s.pushLeft(input[i]);
        }

        for (int i = 5; i < 10; i++) {
            s.pushRight(input[i]);
        }

        StdOut.println();
    }
}
