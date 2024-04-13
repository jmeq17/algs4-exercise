package homework.partI.week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> q = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            q.enqueue(s);
        }

        Iterator<String> iter = q.iterator();
        for (int i = 0; i < k; i++)
            StdOut.println(iter.next());
    }
}
