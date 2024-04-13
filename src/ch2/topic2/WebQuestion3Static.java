package ch2.topic2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import utils.LinkedList;

public class WebQuestion3Static {
    public static void rearrange(LinkedList<Comparable> a) {
        // rearrange(homework.a, 1, homework.a.size());
        rearrangeTwo(a);
    }

    // The first method for partitioning homework.a linked-list.
    private static void rearrange(LinkedList<Comparable> a, int lo, int hi) {
        if (hi == lo) return;

        int mid = (lo + hi) / 2;

        LinkedList<Comparable> left = new LinkedList<>();
        LinkedList<Comparable> right = new LinkedList<>();

        for (int i = lo; i < mid + 1; i++) {
            left.add(a.remove());
        }
        for (int i = mid + 1; i <= hi; i++) {
            right.add(a.remove());
        }

        rearrange(left, lo, mid);
        rearrange(right, mid + 1, hi);
        merge(a, left, right);
    }

    // The second method for partitioning homework.a linked-list.
    private static void rearrangeTwo(LinkedList<Comparable> a) {
        int N = a.size();
        if (N == 1) return;

        LinkedList<Comparable> left = new LinkedList<>();
        LinkedList<Comparable> right = new LinkedList<>();

        while (a.size() > 1) {
            left.add(a.remove());
            right.add(a.remove());
        }
        if (a.size() == 1) left.add(a.remove());

        rearrangeTwo(left);
        rearrangeTwo(right);
        merge(a, left, right);
    }

    private static void merge(LinkedList<Comparable> a, LinkedList<Comparable> left, LinkedList<Comparable> right) {
        while (!left.isEmpty() || !right.isEmpty()) {
            int n = StdRandom.uniform(0, 2);

            if (n == 0) {
                if (!left.isEmpty()) a.add(left.remove());
                else a.add(right.remove());
            } else {
                if (!right.isEmpty()) a.add(right.remove());
                else a.add(left.remove());
            }
        }
    }


    public static void main(String[] args) {
        LinkedList<Comparable> a = new LinkedList<>();
        for (int i = 0; i < 15; i++) {
            a.add(i);
        }

        rearrange(a);

        for (Comparable i : a) {
            StdOut.print(i + " ");
        }
    }
}
