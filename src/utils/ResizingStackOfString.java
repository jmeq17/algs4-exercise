package utils;
/*
The second and third vision of FixedCapacityStackOfStrings.
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ResizingStackOfString<Item> {
    private Item[] a;
    private int N;

    public ResizingStackOfString(int cap) {
        a = (Item[]) new Object[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public int arraySize() {
        return a.length;
    }

    public void push(Item item) {
        if (N == a.length) resize(2 * N);
        a[N++] = item;
    }

    public Item pop() {
        Item item = a[--N];
        a[N] = null;    // Avoid loitering (see text).
        // 这样缩减数组后为半满，仍可进行多次 push pop 操作，这是不以 homework.a.length / 2 作为判断的原因
        if (N > 0 && N == a.length / 4) resize(a.length / 2);
        return item;
    }

    public void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) temp[i] = a[i];
        a = temp;
    }

    public static void main(String[] args) {
        ResizingStackOfString<String> s = new ResizingStackOfString<>(100);

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                s.push(item);
            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}
