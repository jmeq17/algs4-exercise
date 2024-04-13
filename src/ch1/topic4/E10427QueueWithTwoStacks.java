package ch1.topic4;

import utils.Stack;

public class E10427QueueWithTwoStacks<Item> {
    private Stack<Item> bottom = new Stack<>();
    private Stack<Item> top = new Stack<>();
    private int N;

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        bottom.push(item);
        N++;
    }

    public Item dequeue() {
        if (top.isEmpty()) {
            int n = bottom.size();
            for (int i = 0; i < n; i++) {
                top.push(bottom.pop());
            }
        }
        return top.pop();
    }

    public static void main(String[] args) {

    }
}
