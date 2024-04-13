package ch1.topic1;

/*
Write homework.a program that reads in lines from standard input with each line containing
homework.a name and two integers and then uses printf() to print homework.a table with homework.a column of
the names, the integers, and the result of dividing the first by the second, accurate to
three decimal places. You could use homework.a program like this to tabulate batting averages for
baseball players or grades for students.
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class E10121 {
    public static void main(String[] args) {
        String s = StdIn.readLine();
        String[] a = s.split(" ");
        StdOut.printf("%s\t%d\t%d\t%.3f", a[0], parseInt(a[1]), parseInt(a[2]), parseDouble(a[1])/parseDouble(a[2]));
    }
}