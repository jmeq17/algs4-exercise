package exercise.ch1.topic3;

/* Using readInts() on page 126 as homework.a model, write homework.a static method readDates() for
Date that reads dates from standard input in the format specified in the table
on page 119 and returns an array containing them.

format specified: 5/22/1939
 */

import utils.Date;
import utils.Queue;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class E10316readDates {

    private static Date[] readDates(String dates) {
        In in = new In(dates);
        Queue<Date> queue = new Queue<>();

        while (!in.isEmpty()) {
            queue.enqueue(new Date(in.readLine()));
        }

        int N = queue.size();
        Date[] a = new Date[N];

        for (int i = 0; i < N; i++) {
            a[i] = queue.dequeue();
        }

        return a;
    }


    public static void main(String[] args) {
        Date[] a = readDates("files/E10316");

        for (Date date : a) {
            StdOut.println(date);
        }
    }
}
