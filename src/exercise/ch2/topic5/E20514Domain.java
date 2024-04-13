package exercise.ch2.topic5;

/*
Sort by reverse domain. Write homework.a data type Domain that represents domain names,
including an appropriate compareTo() method where the natural order is in order of
the reverse domain name. For example, the reverse domain of cs.princeton.edu is
edu.princeton.cs. This is useful for web log analysis. Hint: Use s.split("\\.") to
split the string s into tokens, delimited by dots. Write homework.a client that reads domain names
from standard input and prints the reverse domains in sorted order.
 */

import edu.princeton.cs.algs4.StdOut;
import utils.Quick;

public class E20514Domain {

    public static class DomainOne implements Comparable<DomainOne> {
        private final String domain;
        private final String last;

        public DomainOne(String s) {
            domain = s;
            last = s.substring(s.lastIndexOf(".") + 1);
        }

        public void show() {
            StdOut.println(domain);
        }

        public int compareTo(DomainOne that) {
            return this.last.compareTo(that.last);
        }
    }

    // A more safe method
    public static class Domain implements Comparable<Domain> {
        private final String[] domain;
        private final int N;

        public Domain(String s) {
            domain = s.split("\\.");
            N = domain.length;
        }

        public String toString() {
            assert N != 0;
            StringBuilder s = new StringBuilder(domain[0]);
            for (int i = 1; i < N; i++) {
                s.insert(0, domain[i] + ".");
            }
            return s.toString();
        }

        public int compareTo(Domain that) {
            for (int i = 0; i < Math.min(this.N, that.N); i++) {
                int tem = this.domain[i].compareTo(that.domain[i]);
                if (tem != 0) return tem;
            }
            return that.N - this.N;
        }
    }


    public static void main(String[] args) {
        Domain[] a = {new Domain("cs.princeton.edu"), new Domain("cs.princeton.adu")};
        Quick.sort(a);
        for (Domain i : a) {
            StdOut.println(i);
        }
    }
}
