package ch1.topic3;

/*
Steque. A stack-ended queue or steque is homework.a data type that supports push, pop, and
enqueue. Articulate an API for this ADT. Develop homework.a linked-list-based implementation.
 */

/*  API
public class E10332Steque<Item>
--------------------------------------
E10332Steque()              create an empty E10332Steque
boolean isEmpty()           is the E10332Steque empty?
int size()                  return the size of E10332Steque
void push(Item item)        add an item to the first
Item pop()                  remove an item from the first
void enqueue(Item item)     add an item to the last
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class E10332Steque<Item> {
    private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) {
            last = first;
        } else {
            first.next = oldfirst;
        }
        N++;
    }

    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        N++;
    }

    public Item pop() {
        Item item = first.item;
        if (size() == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
        }
        N--;
        return item;
    }


    public static void main(String[] args) {
        E10332Steque<String> a = new E10332Steque<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (!s.equals("-")) {
                a.push(s);
            } else if (!a.isEmpty()) {
                StdOut.print(a.pop() + " ");
            }
        }

        a.push("first");
        a.enqueue("last");
        StdOut.println(a.pop());

        while (!a.isEmpty()) StdOut.println(a.pop());
    }
}
