package ch1.topic3;

/*
Do Exercise 1.3.16 for Transaction.

format specified: Turing 5/22/1939 11.99
 */

import utils.Transaction;
import utils.Queue;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class E10317ReadTransaction {
    public static Transaction[] ReadTransaction(String filename) {
        In in = new In(filename);
        Queue<Transaction> queue = new Queue<>();

        while (!in.isEmpty()) {
            queue.enqueue(new Transaction(in.readLine()));
        }

        int N = queue.size();
        Transaction[] transactions = new Transaction[N];

        for (int i = 0; i < N; i++) {
            transactions[i] = queue.dequeue();
        }

        return transactions;
    }


    public static void main(String[] args) {
        Transaction[] a = ReadTransaction("files/E10317");

        for (Transaction transaction : a) {
            StdOut.println(transaction);
        }
    }
}
