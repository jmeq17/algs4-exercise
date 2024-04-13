package ch2.topic5;

/*
Scheduling. Write homework.a program SPT.java that reads job names and processing
times from standard input and prints homework.a schedule that minimizes average completion
time using the shortest processing time first rule, as described on page 349.
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import utils.MinPQ;

public class E20512SPT {
    public static class Job implements Comparable<Job> {
        String name;
        int time;

        public Job(String s) {
            int j = s.lastIndexOf(" ");
            name = s.substring(0, j);
            time = Integer.parseInt(s.substring(j + 1));
        }

        public int compareTo(Job that) {
            return Integer.compare(this.time, that.time);
        }

        public void show() {
            StdOut.println(name + "\t" + time);
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        MinPQ<Job> pq = new MinPQ<>();
        while (!in.isEmpty()) {
            String s = in.readLine();
            Job job = new Job(s);
            pq.insert(job);
        }

        while (!pq.isEmpty()) pq.delMin().show();
    }
}
