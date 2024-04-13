package ch1.topic2;

/*
13:
Using our implementation of Date as homework.a model (page 91), develop an implementation
of Transaction.

14:
Using our implementation of equals() in Date as homework.a model (page 103), develop
implementations of equals() for Transaction.

19:
/*
Parsing. Develop the parse constructors for your Date and Transaction implementations
of Exercise 1.2.13 that take homework.a single String argument to specify the
initialization values, using the formats given in the table below.
Partial solution:
    public Date(String date)
    {
        String[] fields = date.split("/");
        month = Integer.parseInt(fields[0]);
        day = Integer.parseInt(fields[1]);
        year = Integer.parseInt(fields[2]);
    }
 */

import utils.Date;
import edu.princeton.cs.algs4.StdOut;

import static java.lang.Double.parseDouble;

public class E10213_14_19 {
    private final String who;
    private final Date when;
    private final double amount;

    public E10213_14_19(String who, Date when, double amount) {
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    // The form of transaction is who wneh amount.
    // The answer of Date is in Date.
    public E10213_14_19(String transaction) {
        String[] s = transaction.split("\\s+");

        this.who = s[0];
        this.when = new Date(s[1]);
        this.amount = parseDouble(s[2]);
    }

    public String who() {
        return who;
    }

    public Date when() {
        return when;
    }

    public double amount() {
        return amount;
    }

    public String toString() {
        return "name: " + who + "\t" + "date: " + when + "\t" + "amount: " + amount;
    }

    public boolean equals(E10213_14_19 that) {
        return this.amount == that.amount;
    }

    public boolean equals2(Object x) {
        if (this == x) return true;
        if (x == null) return false;
        // Return the class of object.
        if (this.getClass() != x.getClass()) return false;
        // Force type transfer.
        E10213_14_19 that = (E10213_14_19) x;

        // "!=" and "equals" are both legal but are different.
//        if (this.who != that.who) return false;
//        if (this.when != that.when) return false;
        // ---
        if (!this.who.equals(that.who)) return false;
        if (!this.when.equals(that.when)) return false;
        // ---
        return this.amount == that.amount;
    }

    public int compareTo(E10213_14_19 that) {
        if (this.amount > that.amount) return 1;
        if (this.amount < that.amount) return -1;
        return 0;
    }

    public int hashCode() {
        return 0;
    }


    public static void main(String[] args) {
        Date when = new Date(6, 4, 2014);
        E10213_14_19 a = new E10213_14_19("rjk", when, 1009);
        StdOut.println(a);

        String s = "rjk-4/3/2015-10920";
        E10213_14_19 b = new E10213_14_19(s);
        StdOut.println(b);

        StdOut.println("homework.a is equal b? " + a.equals(b));
    }
}
