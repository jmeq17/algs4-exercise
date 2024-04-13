package exercise.ch1.topic2;

/*
Rational numbers. Implement an immutable data type Rational for rational
numbers that supports addition, subtraction, multiplication, and division.

public class Rational
    Rational(int numerator. int denominator)
    Rational plus(Rational b)                   sum of this number and b
    Rational minus(Rational b)                  difference of this number and b
    Rational times(Rational b)                  product of this number and b
    Rational divides(Rational b)                quotient of this number and b
    boolean equals(Rational that)               is this number equal to that ?
    String toString()                           string representation

You do not have to worry about testing for overflow (see Exercise 1.2.17), but use as
instance variables two long values that represent the numerator and denominator to
limit the possibility of overflow. Use Euclid’s algorithm (see page 4) to ensure that the
numerator and denominator never have any common factors. Include homework.a test client that
ch1.topic4.exercises all of your methods.
 */

import utils.EuclidAlgorithms;
import edu.princeton.cs.algs4.StdOut;

public class E10216 {

    private final long numerator;
    private final long denominator;
    private final double rational;

    public E10216(int numerator, int denominator) {
        if (interPrime(numerator, denominator)) throw new IllegalArgumentException("Argumens are error.");

        this.numerator = numerator;
        this.denominator = denominator;
        this.rational = (double) numerator / (double) denominator;
    }

    public E10216 plus(E10216 b) {
        return new E10216((int) this.numerator * (int) b.denominator + (int) this.denominator * (int) b.numerator, (int) this.denominator * (int) b.denominator);
    }

    public E10216 minus(E10216 b) {
        return new E10216((int) this.numerator * (int) b.denominator - (int) this.denominator * (int) b.numerator, (int) this.denominator * (int) b.denominator);
    }

    public E10216 times(E10216 b) {
        return new E10216((int) this.numerator * (int) b.numerator, (int) this.denominator * (int) b.denominator);
    }

    public E10216 divides(E10216 b) {
        return new E10216((int) this.numerator * (int) b.denominator, (int) this.denominator * (int) b.numerator);
    }

    public boolean equals(E10216 that) {
        return this.rational == that.rational;
    }

    public String toString() {
        return "rational is: " + this.rational;
    }

    private boolean interPrime(int numerator, int denominator) {
        return EuclidAlgorithms.gcd(numerator, denominator) > 1;
    }


    public static void main(String[] args) {
        E10216 a = new E10216(13, 17);
        E10216 b = new E10216(21, 29);

        StdOut.println(a.plus(b));
        StdOut.println(a.minus(b));
        StdOut.println(a.times(b));
        StdOut.println(a.divides(b));

        StdOut.println(a.equals(b));
        StdOut.println(a);
    }
}
