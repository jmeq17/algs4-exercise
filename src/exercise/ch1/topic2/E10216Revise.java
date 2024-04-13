package exercise.ch1.topic2;

/*
Thanks to Rene Argento, I reference his answer.
 */

import utils.EuclidAlgorithms;
import edu.princeton.cs.algs4.StdOut;

public class E10216Revise {
    private final long numerator;
    private final long denominator;
    private final double rational;

    public E10216Revise(int numerator, int denominator) {

        // RuntimeException derives from Rene Argento.
        if (denominator == 0) throw new RuntimeException("Denominator cannot be 0");

        int gcd = EuclidAlgorithms.gcd(numerator, denominator);

        if (gcd > 1) {
            this.numerator = numerator / gcd;
            this.denominator = denominator / gcd;
        } else {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        this.rational = (double) numerator / (double) denominator;
    }

    public E10216Revise plus(E10216Revise b) {
        return new E10216Revise((int) (this.numerator * b.denominator + this.denominator * b.numerator), (int) (this.denominator * b.denominator));
    }

    public E10216Revise minus(E10216Revise b) {
        return new E10216Revise((int) (this.numerator * b.denominator - this.denominator * b.numerator), (int) (this.denominator * b.denominator));
    }

    public E10216Revise times(E10216Revise b) {
        return new E10216Revise((int) (this.numerator * b.numerator), (int) (this.denominator * b.denominator));
    }

    public E10216Revise divides(E10216Revise b) {
        return new E10216Revise((int) (this.numerator * b.denominator), (int) (this.denominator * b.numerator));
    }

    public boolean equals(E10216Revise that) {
        return this.rational == that.rational;
    }

    public String toString() {
        return "rational is: " + this.rational;
    }

    public static void main(String[] args) {
        E10216Revise a = new E10216Revise(12, 14);
        E10216Revise b = new E10216Revise(21, 29);

        StdOut.println("homework.a: " + a);
        StdOut.println("b: " + b);

        StdOut.println("homework.a + b: " + a.plus(b));
        StdOut.println("homework.a - b: " + a.minus(b));
        StdOut.println("homework.a * b: " + a.times(b));
        StdOut.println("homework.a ÷ b: " + a.divides(b));

        StdOut.println("homework.a = b? " + a.equals(b));
    }
}
