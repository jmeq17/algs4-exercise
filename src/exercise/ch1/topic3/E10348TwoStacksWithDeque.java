package exercise.ch1.topic3;

import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/**
 * Two stacks with homework.a deque. Implement two stacks with homework.a single deque so that each
 * operation takes homework.a constant number of deque operations (see Exercise 1.3.33).
 */

public class E10348TwoStacksWithDeque<Item> {
    private final E10333Deque<Item> stequ;
    private int N1;
    private int N2;

    public E10348TwoStacksWithDeque() {
        stequ = new E10333Deque<>();
    }

    public boolean isEmpty1() {
        return N1 == 0;
    }

    public boolean isEmpty2() {
        return N2 == 0;
    }

    public int size1() {
        return N1;
    }

    public int size2() {
        return N2;
    }

    public void pushStack1(Item item) {
        stequ.pushLeft(item);
        N1++;
    }

    public void pushStack2(Item item) {
        stequ.pushRight(item);
        N2++;
    }

    public Item popStack1() {
        if (isEmpty1()) throw new NoSuchElementException("Stack underflow");
        N1--;
        return stequ.popLeft();
    }

    public Item popStack2() {
        if (isEmpty2()) throw new NoSuchElementException("Stack underflow");
        N2--;
        return stequ.popRight();
    }


    public static void main(String[] args) {
        E10348TwoStacksWithDeque<String> test = new E10348TwoStacksWithDeque<>();

        String[] inputLeft = "left".split("");
        String[] inputRight = "right".split("");

        for (String i : inputLeft) {
            test.pushStack1(i);
        }
        for (String i : inputRight) {
            test.pushStack2(i);
        }

        StdOut.println("The size of Stack1 if: " + test.size1());
        StdOut.println("The size of Stack2 if: " + test.size2());
    }
}
