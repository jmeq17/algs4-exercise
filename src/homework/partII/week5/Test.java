package homework.partII.week5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Test {
    public static void testOfCircularSuffixArray(String file, int n, int t) {
        In in = new In(file);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++)
            sb.append(in.readChar());

        String s = sb.toString();
        long count = 0;

        for (int i = 0; i < t; i++) {
            long time1 = System.currentTimeMillis();
            new CircularSuffixArray(s);
            long time2 = System.currentTimeMillis();
            count += (time2 - time1);
        }

        double allTime = (count + 0.0) / 1000 / t;
        System.out.println(allTime);
    }

    public static void testOfMoveToFrontOf_encode() {
        long time = System.currentTimeMillis();
        MoveToFront.encode();
        long time2 = System.currentTimeMillis();

        double allTime = (time2 - time + 0.0) / 1000;
        System.out.println();
        System.out.println(allTime);
    }

    public static void testOfMoveToFrontOf_decode() {
        long time = System.currentTimeMillis();
        MoveToFront.decode();
        long time2 = System.currentTimeMillis();

        double allTime = (time2 - time + 0.0) / 1000;
        System.out.println();
        System.out.println(allTime);
    }

    // produce homework.a 100 random charcter
    public static void randomChar(int n) {
        for (int i = 0; i < n; i++) {
            int c = StdRandom.uniformInt(0, 26);
            StdOut.print((char) (c + 65));
        }
        StdOut.println();
    }


    public static void main(String[] args) {
        String file = "HW_Project\\burrows\\dickens.txt";
        int n = 32000;
        int t = 100;

        // Test of CircularSuffixArray
        testOfCircularSuffixArray(file, n, t);

        // Test of MoveToFront, it requires homework.a standard input.
//        testOfMoveToFrontOf_encode();
//        testOfMoveToFrontOf_decode();


//        randomChar(100);

//        String[] homework.a = {"ABR!A", "BR!AA", "R!AAB", "!AABR", "AABR!"};
//
//        LSD.sort(homework.a, 5);
//        System.out.println();
    }
}
