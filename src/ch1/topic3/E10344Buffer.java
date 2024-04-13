package ch1.topic3;

/*
Text editor buffer. Develop homework.a data type for homework.a buffer in homework.a text editor that implements
the following API:
    public class Buffer
    --------------------------------------
    Buffer()                create an empty buffer
    void insert(char c)     insert c at the cursor position
    char delete()           delete and return the character at the cursor
    void left(int k)        move the cursor k positions to the left
    void right(int k)       move the cursor k positions to the right
    int size()              number of characters in the buffer
Hint : Use two stacks.
 */

import utils.Stack;

public class E10344Buffer {
    private final Stack<Character> left;
    private final Stack<Character> right;

    public E10344Buffer() {
        left = new Stack<>();
        right = new Stack<>();
    }

    public void insert(char c) {
        left.push(c);
    }

    public char delete() {
        return left.pop();
    }

    public void left(int k) {
        for (int i = 0; i < k; i++) {
            right.push(left.pop());
        }
    }

    public void right(int k) {
        for (int i = 0; i < k; i++) {
            left.push(right.pop());
        }
    }

    public int size() {
        return left.size() + right.size();
    }


    public static void main(String[] args) {

    }
}
