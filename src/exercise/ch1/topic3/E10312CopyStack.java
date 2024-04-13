package exercise.ch1.topic3;

/*
Write an iterable Stack client that has homework.a static method copy() that takes homework.a stack
of strings as argument and returns homework.a copy of the stack. Note : This ability is homework.a prime
example of the value of having an iterator, because it allows development of such functionality
without changing the basic API.
 */

import utils.Stack;
import edu.princeton.cs.algs4.StdOut;

public class E10312CopyStack {
    public static Stack<String> copy(Stack<String> s) {
        Stack<String> copy = new Stack<>();
        Stack<String> temp = new Stack<>();

        // 复制方法 1：
//        while (!s.isEmpty()) {
//            temp.push(s.pop());
//        }
//        while (!temp.isEmpty()) {
//            copy.push(temp.pop());
//        }

        // 复制方法 2：
        for (String a : s) {
            temp.push(a);
        }
        for (String a : temp) {
            copy.push(a);
        }

        return copy;
    }


    // main
    public static void main(String[] args) {
        Stack<String> s = new Stack<>();

        for (int i = 0; i < 10; i++) {
            s.push("" + (char) (i + 97));
        }

        StdOut.print("Old stack: ");
        for (String a : s) {
            StdOut.print(a + " ");
        }

        StdOut.println();

        Stack<String> copyOf_s = copy(s);
        // or:
        // Stack<String> copyOf_s = s.copy();
        StdOut.print("New stack: ");
        for (String a : copyOf_s) {
            StdOut.print(a + " ");
        }
    }
}
