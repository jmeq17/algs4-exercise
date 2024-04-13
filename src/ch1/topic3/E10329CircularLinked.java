package ch1.topic3;

/*
Write homework.a Queue implementation that uses homework.a circular linked list, which is the same
as homework.a linked list except that no links are null and the value of last.next is first whenever
the list is not empty. Keep only one Node instance variable (last).
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class E10329CircularLinked<Item> {
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public void enqueue(Item item) {
        if (isEmpty()) {
            // 这里也要 new 以下，不然 last 处于未赋值状态
            last = new Node();
            last.item = item;
            last.next = last;
        } else {
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.next = oldlast.next;
            oldlast.next = last;

        }
        N++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new IllegalStateException("The QUEUE is EMPTY.");
        if (size() == 1) {
            Item item = last.item;
            last = null;
            N--;
            return item;
        } else {
            Item item = last.next.item;
            last.next = last.next.next;
            N--;
            return item;
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }


    public static void main(String[] args) {
        E10329CircularLinked<Integer> integers = new E10329CircularLinked<>();

        for (int i = 0; i < 6; i++) integers.enqueue(StdRandom.uniform(100));

        while (!integers.isEmpty()) {
            StdOut.println(integers.dequeue());
        }
    }
}
