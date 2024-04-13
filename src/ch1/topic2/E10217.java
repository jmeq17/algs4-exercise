package ch1.topic2;

/*
Robust implementation of rational numbers. Use assertions to develop an implementation
of Rational (see Exercise 1.2.16) that is immune to overflow.
 */

import utils.EuclidAlgorithms;
import edu.princeton.cs.algs4.StdOut;

public class E10217 {
    private final long numerator;
    private final long denominator;
    private final double rational;

    public E10217(int numerator, int denominator) {

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

    public E10217 plus(E10217 b) {

        assert (this.numerator * b.denominator + this.denominator * b.numerator <= Integer.MAX_VALUE && this.denominator * b.denominator <= Integer.MAX_VALUE) : "ASSERT_AVOIDING_OVERFLOW_MESSAGE";

        int newNumerator = (int) (this.numerator * b.denominator + this.denominator * b.numerator);
        int newDenominator = (int) (this.denominator * b.denominator);

        return new E10217(newNumerator, newDenominator);
    }

    public E10217 minus(E10217 b) {

        assert (this.numerator * b.denominator - this.denominator * b.numerator <= Integer.MAX_VALUE && this.denominator * b.denominator <= Integer.MAX_VALUE) : "ASSERT_AVOIDING_OVERFLOW_MESSAGE";

        int newNumerator = (int) (this.numerator * b.denominator - this.denominator * b.numerator);
        int newDenominator = (int) (this.denominator * b.denominator);

        return new E10217(newNumerator, newDenominator);
    }

    public E10217 times(E10217 b) {

        assert (this.numerator * b.numerator <= Integer.MAX_VALUE && this.denominator * b.denominator <= Integer.MAX_VALUE) : "ASSERT_AVOIDING_OVERFLOW_MESSAGE";

        int newNumerator = (int) (this.numerator * b.numerator);
        int newDenominator = (int) (this.denominator * b.denominator);

        return new E10217(newNumerator, newDenominator);
    }

    public E10217 divides(E10217 b) {

        assert (this.numerator * b.denominator <= Integer.MAX_VALUE && this.denominator * b.numerator <= Integer.MAX_VALUE) : "ASSERT_AVOIDING_OVERFLOW_MESSAGE";

        int newNumerator = (int) (this.numerator * b.denominator);
        int newDenominator = (int) (this.denominator * b.numerator);

        return new E10217(newNumerator, newDenominator);
    }

    public boolean equals(E10217 that) {
        return this.rational == that.rational;
    }

    public String toString() {
        return "rational is: " + this.rational;
    }

    public static void main(String[] args) {
        int numeratorA = (int) Math.pow(2, 20);
        int denominatorA = (int) Math.pow(2, 20) + 1;

        int numeratorB = (int) Math.pow(2, 19);
        int denominatorB = (int) Math.pow(2, 19) + 1;

        E10217 a = new E10217(numeratorA, denominatorA);
        E10217 b = new E10217(numeratorB, denominatorB);

        StdOut.println("homework.a: " + a);
        StdOut.println("b: " + b);

        StdOut.println("homework.a + b: " + a.plus(b));
        StdOut.println("homework.a - b: " + a.minus(b));
        StdOut.println("homework.a * b: " + a.times(b));
        StdOut.println("homework.a ÷ b: " + a.divides(b));

        StdOut.println("homework.a = b? " + a.equals(b));
    }
}
