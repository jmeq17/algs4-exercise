package utils;

import edu.princeton.cs.algs4.In;

import static java.lang.Integer.parseInt;

public class Date implements Comparable<Date> {
    private final int day;
    private final int month;
    private final int year;

    public Date(int d, int m, int y) {
        day = d;
        month = m;
        year = y;
    }

    // Answer of E10219, s is the form of "day/month/year".
    // E10316 also uses the code.
    public Date(String s) {
        String[] date = s.split("/");
        day = parseInt(date[0]);
        month = parseInt(date[1]);
        year = parseInt(date[2]);
    }

    public int day() {
        return day;
    }

    public int month() {
        return month;
    }

    public int year() {
        return year;
    }

    // 实现 Comparable interface 用于排序
    public int compareTo(Date that) {
        if (this.year > that.year) return +1;
        if (this.year < that.year) return -1;
        if (this.month > that.month) return +1;
        if (this.month < that.month) return -1;
        return Integer.compare(this.day, that.day);
    }

    public boolean equals(Object x) {
        if (this == x) return true;
        if (x == null) return false;
        // Return the class of object.
        if (this.getClass() != x.getClass()) return false;
        Date that = (Date) x;
        if (this.day != that.day) return false;
        if (this.month != that.month) return false;
        return this.year == that.year;
    }

    public String toString() {
        return month + "/" + day + "/" + year;
    }

    // E10316
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
    // ------------------------------
}
