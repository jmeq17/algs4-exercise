package utils;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;


public class Stack<Item> implements Iterable<Item> {
    private Node first;
    private int N;

    public Stack() {

    }

    // ------------------------
    // E10342
    // Method 1. It's not good.
//    public Stack(Stack<Item> s) {
//        Stack<Item> temp = new Stack<>();
//        int n = s.size();
//        for (int i = 0; i < n; i++) {
//            temp.push(s.pop());
//        }
//        for (int i = 0; i < n; i++) {
//            Item item = temp.pop();
//            this.push(item);
//            s.push(item);
//        }
//        this.N = n;
//    }
    // Method 2. Recursive
//    public Stack(Stack<Item> s) {
//        N = s.size();
//        first = new Node(s.first);
//    }
    // Method 3. Nonrecursive.
    public Stack(Stack<Item> s) {
        N = s.size();
        if (N != 0) {
            first = new Node(s.first);
            for (Node temp = first; temp.next != null; temp = temp.next)
                temp.next = new Node(temp.next);
        }
    }

    private class Node {
        Item item;
        Node next;


        private Node() {

        }

        // E10342. Method 2.
//        private Node(Node x) {
//            item = x.item;
//            if (x.next != null) this.next = new Node(x.next);
//        }

        // E10342. Method 3.
        private Node(Node x) {
            item = x.item;
            next = x.next;
        }
    }

    // E10307
    public Item peek() {
        return first.item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    //  iterator
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // E10312, 静态方法与实例方法有所修改，但两个方法都能实现一样的目的
    public Stack<Item> copy() {
        Stack<Item> copy = new Stack<>();
        Stack<Item> temp = new Stack<>();

        /*
            while (!s.isEmpty()) {
                temp.push(this.pop());
            }
            while (!temp.isEmpty()) {
                copy.push(temp.pop());
            }
         */

        // 下边的实现更好
        for (Item a : this) {
            temp.push(a);
        }
        for (Item a : temp) {
            copy.push(a);
        }

        return copy;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }


    // main
    public static void main(String[] args) {
        Stack<String> s = new Stack<>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                s.push(item);
            } else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}
