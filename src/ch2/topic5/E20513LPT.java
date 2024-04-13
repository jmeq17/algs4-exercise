package ch2.topic5;

/*
Load balancing. Write homework.a program LPT.java that takes an integer M as homework.a command-
line argument, reads job names and processing times from standard input and
prints homework.a schedule assigning the jobs to M processors that approximately minimizes the
time when the last job completes using the longest processing time first rule, as described
on page 349.
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import utils.MaxPQResizing;
import utils.MinPQ;

public class E20513LPT {
    public static class Job implements Comparable<Job> {
        private final String name;
        private final int time;

        public Job(String s) {
            int j = s.lastIndexOf(" ");
            name = s.substring(0, j);
            time = Integer.parseInt(s.substring(j + 1));
        }

        // Return the reserve order.
        public int compareTo(Job that) {
            return Integer.compare(that.time, this.time);
        }

        public void show() {
            StdOut.println(name + "\t" + time);
        }

        public String toString() {
            return name + "\t" + time + "\n";
        }
    }

    public static class Processor implements Comparable<Processor> {
        private int allTime = 0;

        public void add(Job job) {
            allTime += job.time;
        }

        public int compareTo(Processor that) {
            return Integer.compare(this.allTime, that.allTime);
        }

        public void show() {
            StdOut.println(allTime);
        }
    }

    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        MinPQ<Processor> pq = new MinPQ<>(m);
        MaxPQResizing<Job> pqJobs = new MaxPQResizing<>();
        In in = new In(args[1]);

        for (int i = 0; i < m; i++) {
            pq.insert(new Processor());
        }

        while (!in.isEmpty()) {
            String s = in.readLine();
            Job job = new Job(s);
            pqJobs.insert(job);
        }

        while (!pqJobs.isEmpty()) {
            Processor min = pq.delMin();
            min.add(pqJobs.delMax());
            pq.insert(min);
        }

        while (!pq.isEmpty()) pq.delMin().show();
    }
}
