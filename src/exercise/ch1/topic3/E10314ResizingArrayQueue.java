package exercise.ch1.topic3;

/*
Develop homework.a class ResizingArrayQueueOfStrings that implements the queue
abstraction with homework.a fixed-size array, and then extend your implementation to use array
resizing to remove the size restriction.
 */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class E10314ResizingArrayQueue<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 2;

    private Item[] q;
    private int N;

    private int first;
    private int last;

    public E10314ResizingArrayQueue() {
        q = (Item[]) new Object[INIT_CAPACITY];
        N = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int capacity) {
        assert capacity >= N;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            // 是不是不求模 q.length 也可以？但这样保险
            copy[i] = q[(first + i) % q.length];
        }
        q = copy;
        first = 0;
        last = N;
    }

    public void enqueue(Item item) {
        if (N == q.length) resize(2 * q.length);

        q[last++] = item;
        // wrap-around? Why
        // 因为省空间，当数组前边删了几个元素，而 last 到达末尾时（此时 N<homework.a.length，不够 resize），将再加入的元素放在数组前边保存
        if (last == q.length) last = 0;
        N++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = q[first];
        q[first] = null;    // to avoid loitering
        N--;
        first++;
        if (first == q.length) first = 0;
        if (N > 0 && N == q.length / 4) resize(q.length / 2);
        return item;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return q[first];
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < N;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[(i + first) % q.length];
            i++;
            return item;
        }
    }

    public static void main(String[] args) {
        E10314ResizingArrayQueue queue = new E10314ResizingArrayQueue();
        String[] input = "it was - the best - of times - - - it was - the - -".split("\\s+");

        for (String item : input) {
            if (!item.equals("-")) queue.enqueue(item);
            else if (!queue.isEmpty()) StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }
}
