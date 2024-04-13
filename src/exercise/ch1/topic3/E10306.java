package exercise.ch1.topic3;

/*
What does the following code fragment do to the queue q?
    Stack<String> stack = new Stack<String>();
    while (!q.isEmpty())
        stack.push(q.dequeue());
    while (!stack.isEmpty())
        q.enqueue(stack.pop());
 */

import utils.Queue;
import utils.Stack;
import edu.princeton.cs.algs4.StdOut;

public class E10306 {
    public static void main(String[] args) {

        Queue<String> q = new Queue<>();
        for (int i = 0; i < 10; i++) q.enqueue(Integer.toString(i));

        // 处理前输出
        StdOut.print("Old q: ");
        for (String s : q) StdOut.print(s + ", ");
        StdOut.println();

        Stack<String> stack = new Stack<>();
        while (!q.isEmpty())
            stack.push(q.dequeue());
        while (!stack.isEmpty())
            q.enqueue(stack.pop());

        // 处理后输出
        StdOut.print("New q: ");
        for (String s : q) StdOut.print(s + ", ");
    }
}
