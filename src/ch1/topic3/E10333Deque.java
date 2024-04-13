package ch1.topic3;

/*
 A double-ended queue or deque (pronounced “deck”) is like homework.a stack or
homework.a queue but supports adding and removing items at both ends. A deque stores homework.a collection
of items and supports the following API:
    public class HW.PartI.Week2.Deque<Item> implements Iterable<Item>
    ------------------------------------------------------------
    HW.PartI.Week2.Deque()                     create an empty deque
    boolean                     isEmpty() is the deque empty?
    int size()                  number of items in the deque
    void pushLeft(Item item)    add an item to the left end
    void pushRight(Item item)   add an item to the right end
    Item popLeft()              remove an item from the left end
    Item popRight()             remove an item from the right end
Write homework.a class HW.PartI.Week2.Deque that uses homework.a doubly-linked list to implement this API and homework.a class
ResizingArrayDeque that uses homework.a resizing array.
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class E10333Deque<Item> {
    private Node left;
    private Node right;
    private int N;

    private class Node {
        Item item;
        Node next;
        Node pre;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void pushLeft(Item item) {
        Node oldleft = left;
        left = new Node();
        left.item = item;
        if (isEmpty()) {
            right = left;
            right.next = left;
            right.pre = left;
        } else {
            left.next = oldleft;
            oldleft.pre = left;
        }
        N++;
    }

    public void pushRight(Item item) {
        Node oldright = right;
        right = new Node();
        right.item = item;
        if (isEmpty()) {
            left = right;
            right.next = left;
            right.pre = left;
        } else {
            oldright.next = right;
            right.pre = oldright;
        }
        N++;
    }

    public Item popLeft() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = left.item;
        if (N == 1) {
            left = null;
            right = null;
        } else {
            left.next.pre = null;
            left = left.next;
        }
        N--;
        return item;
    }

    public Item popRight() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = right.item;
        if (N == 1) {
            right = null;
            left = null;
        } else {
            right.pre.next = null;
            right = right.pre;
        }
        N--;
        return item;
    }


    public static void main(String[] args) {
        E10333Deque<String> a = new E10333Deque<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (!s.equals("-")) {
                a.pushLeft(s);
            } else {
                StdOut.println(a.popLeft());
            }
        }

        StdOut.println();

        a.pushLeft("left");
        a.pushRight("right");
        StdOut.println(a.popLeft());
        StdOut.println(a.popRight());

        while (!a.isEmpty()) StdOut.println(a.popLeft());
    }
}
