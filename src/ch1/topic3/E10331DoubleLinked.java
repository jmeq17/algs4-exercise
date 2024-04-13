package ch1.topic3;

/*
Implement homework.a nested class DoubleNode for building doubly-linked lists, where
each node contains homework.a reference to the item preceding it and the item following it in the
list (null if there is no such item). Then implement static methods for the following
tasks: insert at the beginning, insert at the end, remove from the beginning, remove
from the end, insert before homework.a given node, insert after homework.a given node, and remove homework.a given
node.
 */

import edu.princeton.cs.algs4.StdOut;

public class E10331DoubleLinked<Item> {

    private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node pre;
        Node next;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void enBegin(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) {
            last = first;
        } else {
            first.next = oldfirst;
            oldfirst.pre = first;
        }
        N++;
    }

    public void enEnd(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
            last.pre = oldlast;
        }
        N++;
    }

    public Item remBegim() {
        Item item = first.item;
        first.next.pre = null;
        first = first.next;
        N--;
        return item;
    }

    public Item remEnd() {
        Item item = last.item;
        last.pre.next = null;
        last = last.pre;
        N--;
        return item;
    }

    public void enBeforeGiven(Item olditem, Item newitem) {

        if (olditem == null) throw new IllegalArgumentException("olditem is null.");

        Node x = new Node();

        for (Node i = first; i != null; i = i.next) {
            if (i.item.equals(olditem)) {
                x = i;
                break;
            }
        }
        enBeforeGiven(x, newitem);
    }

    private void enBeforeGiven(Node x, Item item) {
        if (x == first) {
            enBegin(item);
        } else {
            Node node = new Node();
            node.item = item;
            x.pre.next = node;
            node.pre = x.pre;
            node.next = x;
            x.pre = node;
        }
        N++;
    }

    public void enAfterGiven(Item olditem, Item newitem) {

        if (olditem == null) throw new IllegalArgumentException("olditem is null.");

        Node x = new Node();

        for (Node i = first; i != null; i = i.next) {
            if (i.item.equals(olditem)) {
                x = i;
                break;
            }
        }
        enAfterGiven(x, newitem);
    }

    private void enAfterGiven(Node x, Item item) {
        if (x == last) {
            enEnd(item);
        } else {
            Node node = new Node();
            node.item = item;
            node.next = x.next;
            x.next.pre = node;
            node.pre = x;
            x.next = node;
        }
        N++;
    }

    public void remGiven(Item item) {
        Node x = new Node();

        for (Node i = first; i != null; i = i.next) {
            if (i.item.equals(item)) {
                x = i;
                break;
            }
        }
        remGiven(x);
    }

    private void remGiven(Node x) {
        if (x == first) {
            remBegim();
        } else if (x == last) {
            remEnd();
        } else {
            x.pre.next = x.next;
            x.next.pre = x.pre;
        }
        N--;
    }


    public static void main(String[] args) {
        E10331DoubleLinked<Integer> integers = new E10331DoubleLinked<>();

        int[] a = {0, 1, 2, 4, 5, 7, 8, 8, 9};

        for (int i : a) integers.enBegin(i);

        integers.enBegin(0);
        integers.enEnd(9);
        integers.enBeforeGiven(2, 3);
        integers.enAfterGiven(7, 6);
        integers.remBegim();
        integers.remEnd();
        integers.remGiven(8);

        StdOut.println();
    }
}
