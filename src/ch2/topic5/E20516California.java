package ch2.topic5;

/*
Unbiased election. In order to thwart bias against candidates whose names appear
toward the end of the alphabet, California sorted the candidates appearing on its
2003 gubernatorial ballot by using the following order of characters:
R W Q O J M V A H B S G Z X N T C I E K U P D Y F L
Create homework.a data type where this is the natural order and write homework.a client California with homework.a
single static method main() that sorts strings according to this ordering. Assume that
each string is composed solely of uppercase letters.
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import utils.Quick;

import java.util.Arrays;
import java.util.Comparator;

public class E20516California {
    public static class California implements Comparable<California> {
        private final String name;
        private final int N;

        public California(String s) {
            name = s;
            N = s.length();
        }

        public String toString() {
            return name;
        }

        public int compareTo(California that) {
            for (int i = 0; i < Math.min(this.N, that.N); i++) {
                String a = this.name.substring(i, i + 1).toUpperCase();
                String b = that.name.substring(i, i + 1).toUpperCase();
                String order = "RWQOJMVAHBSGZXNTCIEKUPDYFL";
                int tem = order.indexOf(a) - order.indexOf(b);
                if (tem != 0) return tem;
            }
            return that.N - this.N;
        }
    }

    // Implemention of comparator.
    public static final Comparator<String> CANDIDATE_ORDER = new CandidateComparator();

    private static class CandidateComparator implements Comparator<String> {
        public int compare(String a, String b) {
            int n = Math.min(a.length(), b.length());
            String order = "RWQOJMVAHBSGZXNTCIEKUPDYFL";
            for (int i = 0; i < n; i++) {
                int aindex = order.indexOf(a.charAt(i));
                int bindex = order.indexOf(b.charAt(i));
                if (aindex < bindex) return -1;
                else if (aindex > bindex) return +1;
            }
            return a.length() - b.length();
        }
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] candidates = in.readAll().toUpperCase().split("\\n+");
        California[] a = new California[candidates.length];

        // Inner class
        for (int i = 0; i < candidates.length; i++) a[i] = new California(candidates[i]);
        Quick.sort(a);
        for (California i : a) StdOut.println(i);

        StdOut.println("\nComparator implement:");

        // Comparator
        Arrays.sort(candidates, E20516California.CANDIDATE_ORDER);
        for (String candidate : candidates) StdOut.println(candidate);
    }
}
