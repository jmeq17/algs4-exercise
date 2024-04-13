package ch1.topic3;

/*
Write homework.a function that takes the first Node in homework.a linked list as argument and (destructively)
reverses the list, returning the first Node in the result.
Iterative solution : To accomplish this task, we maintain references to three consecutive
nodes in the linked list, reverse, first, and second. At each iteration, we extract the
node first from the original linked list and insert it at the beginning of the reversed
list. We maintain the invariant that first is the first node of what’s left of the original
list, second is the second node of what’s left of the original list, and reverse is the first
node of the resulting reversed list.
    public Node reverse(Node x)
    {
        Node first = x;
        Node reverse = null;
        while (first != null)
        {
            Node second = first.next;
            first.next = reverse;
            reverse = first;
            first = second;
        }
        return reverse;
    }
When writing code involving linked lists, we must always be careful to properly handle
the exceptional cases (when the linked list is empty, when the list has only one or two
nodes) and the boundary cases (dealing with the first or last items). This is usually
much trickier than handling the normal cases.
 */

import utils.LinkedList;
import edu.princeton.cs.algs4.StdOut;

public class E10330_FALSE {
    public static void main(String[] args) {
        LinkedList<Integer> integers = new LinkedList<>();

        for (int i = 0; i < 5; i++) integers.add(i);

        StdOut.print("before delete: ");
        for (int item : integers) {
            StdOut.print(item + " ");
        }

        StdOut.println();
//        integers.reverse();
        integers.reverseOfRecursive();

        StdOut.print("after delete: ");
        for (int item : integers) {
            StdOut.print(item + " ");
        }
    }
}
