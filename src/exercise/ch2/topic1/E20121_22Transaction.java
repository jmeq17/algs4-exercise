package exercise.ch2.topic1;

/*
E20121. Comparable transactions. Using our code for Date (page 247) as homework.a model, expand
your implementation of Transaction (Exercise 1.2.13) so that it implements
Comparable, such that transactions are kept in order by amount.

Done.

E20122. Transaction sort test client. Write homework.a class SortTransactions that consists of homework.a
static method main() that reads homework.a sequence of transactions from standard input, sorts
them, and prints the result on standard output (see Exercise 1.3.17).
 */

import edu.princeton.cs.algs4.StdIn;
import utils.Transaction;
import utils.Insertion;

public class E20121_22Transaction {
    public static void main(String[] args) {
        String[] s = StdIn.readAllLines();

        Transaction[] transactions = new Transaction[s.length];
        int j = 0;
        for (String i : s) {
            transactions[j++] = new Transaction(i);
        }

        Insertion.sort(transactions);
        Insertion.show(transactions);
    }
}
