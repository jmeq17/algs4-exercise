package exercise.ch1.topic3;

import utils.Queue;
import utils.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Catenable queues, stacks, or steques. Add an extra operation catenation that (destructively)
 * concatenates two queues, stacks, or steques (see Exercise 1.3.32). Hint : Use
 * homework.a circular linked list, maintaining homework.a pointer to the last item.
 */

public class E10347Catenable {
    public static <Item> Queue<Item> catenable(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> output = new Queue<>();
        int n1 = q1.size();
        for (int i = 0; i < n1; i++) {
            output.enqueue(q1.dequeue());
        }
        int n2 = q2.size();
        for (int i = 0; i < n2; i++) {
            output.enqueue(q2.dequeue());
        }
        return output;
    }

    public static <Item> Stack<Item> catenable(Stack<Item> stack1, Stack<Item> stack2) {
        // 用 E10342 的复制栈方法可以简单点
        Stack<Item> temp = new Stack<>();
        int n1 = stack1.size();
        for (int i = 0; i < n1; i++) {
            temp.push(stack1.pop());
        }
        int n2 = stack2.size();
        for (int i = 0; i < n2; i++) {
            temp.push(stack2.pop());
        }
        Stack<Item> output = new Stack<>();
        int n3 = temp.size();
        for (int i = 0; i < n3; i++) {
            output.push(temp.pop());
        }
        return output;
    }

    public static <Item> E10332Steque<Item> catenable(E10332Steque<Item> steque1, E10332Steque<Item> steque2) {
        E10332Steque<Item> temp = new E10332Steque<>();
        int n1 = steque1.size();
        for (int i = 0; i < n1; i++) {
            temp.push(steque1.pop());
        }
        int n2 = steque2.size();
        for (int i = 0; i < n2; i++) {
            temp.push(steque2.pop());
        }
        E10332Steque<Item> output = new E10332Steque<>();
        int n3 = temp.size();
        for (int i = 0; i < n3; i++) {
            output.push(temp.pop());
        }
        return output;
    }

    public static void main(String[] args) {

        StdOut.println("Catenation of queues");
        testQueueCatenation();

        StdOut.println("Catenation of stacks");
        testStackCatenation();

        StdOut.println("Catenation of steques");
        testStequeCatenation();
    }

    private static void testQueueCatenation() {
        Queue<Integer> queue1 = new Queue<>();
        queue1.enqueue(0);
        queue1.enqueue(1);
        queue1.enqueue(2);
        queue1.enqueue(3);

        Queue<Integer> queue2 = new Queue<>();
        queue2.enqueue(7);
        queue2.enqueue(8);
        queue2.enqueue(9);

        Queue<Integer> output = catenable(queue1, queue2);


        StdOut.print("Result after catenation: ");
        for (int item : output) {
            StdOut.print(item + " ");
        }
        StdOut.println("\nExpected: 0 1 2 3 7 8 9");
    }

    private static void testStackCatenation() {
        Stack<Integer> stack1 = new Stack<>();
        stack1.push(0);
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);

        Stack<Integer> stack2 = new Stack<>();
        stack2.push(7);
        stack2.push(8);
        stack2.push(9);

        Stack<Integer> output = catenable(stack1, stack2);


        StdOut.print("Result after catenation: ");
        for (int item : output) {
            StdOut.print(item + " ");
        }
        StdOut.println("\nExpected: 9 8 7 3 2 1 0");
    }

    private static void testStequeCatenation() {
        E10332Steque<Integer> steque1 = new E10332Steque<>();
        steque1.enqueue(0);
        steque1.enqueue(1);
        steque1.enqueue(2);
        steque1.enqueue(3);

        E10332Steque<Integer> steque2 = new E10332Steque<>();
        steque2.push(7);
        steque2.push(8);
        steque2.push(9);

        E10332Steque<Integer> output = catenable(steque1, steque2);
        int N = output.size();
        StdOut.print("Result after catenation: ");
        for (int i = 0; i < N; i++) {
            StdOut.print(output.pop() + " ");
        }
        StdOut.println("\nExpected: 3 2 1 0 7 8 9");
    }
}
