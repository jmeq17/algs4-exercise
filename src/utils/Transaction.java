package utils;

/*
The code derives from the exercise of E10213_14_19. Beacuse this class will be used frequency,
I get it homework.a named class.
 */

import edu.princeton.cs.algs4.StdOut;

import static java.lang.Double.parseDouble;

public class Transaction implements Comparable<Transaction> {
    private final String who;
    private final Date when;
    private final double amount;

    // E30425
    private int hash;
    // -----------------------

    public Transaction(String who, Date when, double amount) {
        this.who = who;
        this.when = when;
        this.amount = amount;

        // E30425
        this.hash = hashCache();
        // -----------------------
    }

    public Transaction(String transaction) {
        String[] s = transaction.split("\\s+");

        this.who = s[0];
        this.when = new Date(s[1]);
        this.amount = parseDouble(s[2]);

        // E30425
        this.hash = hashCache();
        // -----------------------
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
        return "name: " + who + "\t" + "date: " + when + "\t" + "amount: " + amount + "\n";
    }

    public boolean equalsAmount(Transaction that) {
        return this.amount == that.amount;
    }

    public boolean equals(Object x) {
        if (this == x) return true;
        if (x == null) return false;
        // Return the class of object.
        if (this.getClass() != x.getClass()) return false;
        // Force type transfer.
        Transaction that = (Transaction) x;

        // "!=" and "equals" are both legal but are different.
//        if (this.who != that.who) return false;
//        if (this.when != that.when) return false;
        // ---
        if (!this.who.equals(that.who)) return false;
        if (!this.when.equals(that.when)) return false;
        // ---
        return this.amount == that.amount;
    }

    // E20121
    public int compareTo(Transaction that) {
        return Double.compare(this.amount, that.amount);
    }
    // --------------------

    public int hashCode() {
        if (hash < 0) {
            hash = 17;
            hash = 31 * hash + who.hashCode();
            hash = 31 * hash + when.hashCode();
            hash = 31 * hash + ((Double) amount).hashCode();
        }
        return hash;
    }

    private int hashCache() {
        int hash = 17;
        hash = 31 * hash + who.hashCode();
        hash = 31 * hash + when.hashCode();
        hash = 31 * hash + ((Double) amount).hashCode();
        return hash;
    }


    public static void main(String[] args) {
        Date when = new Date(6, 4, 2014);
        Transaction a = new Transaction("rjk", when, 1009);
        StdOut.println(a);

        String s = "rjk-4/3/2015-10920";
        Transaction b = new Transaction(s);
        StdOut.println(b);

        StdOut.println("homework.a is equal b? " + a.equals(b));
    }
}
