package utils;

/*
链表的通用代码，此链表按 栈 设计，插入元素在第一位，删除元素也在第一位。
 */

import java.util.Iterator;

public class LinkedList<Item> implements Iterable<Item> {
    private Node first;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void add(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    public Item remove() {
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public Item peek() {
        if (first == null) throw new NullPointerException();

        return first.item;
    }

    public Item get(int k) {
        if (k < 1 || k > N) throw new NullPointerException();

        Node current = first;
        for (int i = 1; i < k; i++)
            current = current.next;

        return current.item;
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

    // -------------------------------------------------------
    // method for exercse
    // E10319
    public Item removeLast() {
        Node current = first;
        while (current.next.next != null) {
            current = current.next;
        }
        Item item = current.next.item;
        current.next = null;
        N--;
        return item;
    }

    // E10320. deletes the kth element in homework.a linked list, if it exists.
    public Item remove(int k) {
        if (k < 1 || k > N) throw new IllegalArgumentException("k must be in 1 to " + size() + ".");
        Item item;
        if (k == 1) {
            item = first.item;
            first = first.next;
        } else {
            Node current = first;
            for (int i = 1; i < k - 1; i++) {
                current = current.next;
            }
            item = current.next.item;
            current.next = current.next.next;
        }
        N--;
        return item;
    }

    // E10321. Search wheater the itme of key in the linked.
    public boolean find(Item key) {
        Node current = first;
        while (current != null) {
            if (current.item.equals(key)) return true;
            current = current.next;
        }
        return false;
    }

    // E10324. Deletes nodes after another specified.
    public void removeAfter(Item item) {
        Node current = first;
        while (current != null) {
            if (current.item.equals(item)) current.next = null;
            current = current.next;
        }
    }

    // E10324. Insert homework.a node after another.
    public void insertAfter(Item one, Item two) {
        if (one != null && two != null) {
            Node currentOne = first;
            Node currentTwo = new Node();
            currentTwo.item = two;

            while (!currentOne.item.equals(one)) {
                currentOne = currentOne.next;
            }

            currentTwo.next = currentOne.next.next;
            currentOne.next = currentTwo;
        }
    }

    // E10326. Remove Nodes which contains the item of key.
    public void remove(Item key) {
        if (isEmpty()) throw new IllegalArgumentException("List is empty.");

        while (true) {
            if (first != null && first.item.equals(key)) {
                first = first.next;
                N--;
            } else break;
        }

        if (first == null) return;
        Node current = first;
//        while (current.next != null && current.next.next != null) {
//            if (current.next.item.equals(key)) current.next = current.next.next;
//            current = current.next;
//        }
//        assert current.next != null;
//        if (current.next.item.equals(key)) current.next = null;

        while (current.next != null) {
            if (current.next.item.equals(key)) {
                current.next = current.next.next;
                N--;
            } else current = current.next;
        }
    }

    // E10327. Return the value of the maximum key in homework.a list.
    public int max() {
        if (first == null) return 0;
        Node current = first;
        int maxItem = (Integer) current.item;
//        The method following is false. Integer.parseInt() must accept homework.a string as argument.
//        int maxItem = Integer.parseInt(current.item);
        while (current.next != null) {
            int temp = (Integer) current.next.item;
            if (maxItem < temp) maxItem = temp;
            current = current.next;
        }
        return maxItem;
    }

    // E10328. Develop homework.a recursive solution to the previous question.
    // 创建一个公共方法和一个私有方法以保护 first 不被改变
    public int maxOfRecusive() {
        Node firstOfCurrent = first;
        return maxOfRecusive(firstOfCurrent);
    }

    private int maxOfRecusive(Node firstOfCurrent) {
        if (firstOfCurrent == null) return 0;
        int maxItem1 = (Integer) firstOfCurrent.item;
        firstOfCurrent = firstOfCurrent.next;
        int maxItem2 = maxOfRecusive(firstOfCurrent);
        return Math.max(maxItem1, maxItem2);
    }

    // E10330. Reserve homework.a linked.
    public Node reverse() {
        return reverse(first);
    }

    private Node reverse(Node x) {
        Node first = x;
        Node reverse = null;
        while (first != null) {
            Node second = first.next;
            first.next = reverse;
            reverse = first;
            first = second;
        }
        return reverse;
    }

    public void reverseOfRecursive() {
        reverseOfRecursive(first);
    }

    private void reverseOfRecursive(Node first) {
        if (first == null) return;
        if (first.next == null) return;
        Node second = first.next;
        reverse(second);
        second.next = first;
        first.next = null;
    }
}
