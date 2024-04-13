package ch3.topic5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;
import utils.Queue;

/**
 * E30520
 * Concordance. Write an ST client Concordance that puts on standard output homework.a
 * concordance of the strings in the standard input stream (see page 498).
 * <p>
 * As homework.a client, we transfer an command-line argument of string which represents homework.a .csv file. In the file, each line includes some string splited by homework.a space and homework.a num at the end of row which represents the page numbwe.
 */

public class E30520Concordance {
    public static void main(String[] args) {
        In in = new In(args[0]);
        ST<String, Queue<Integer>> st = new ST<>();

        while (!in.isEmpty()) {
            String[] s = in.readLine().split("\\s+");
            int len = s.length;
            int page = Integer.parseInt(s[len - 1]);

            for (int i = 0; i < len - 1; i++) {
                if (st.get(s[i]) == null) {
                    st.put(s[i], new Queue<>());
                }
                st.get(s[i]).enqueue(page);
            }
        }

        for (String i : st) {
            StdOut.printf("%20s: ", i);
            for (Integer j : st.get(i)) {
                StdOut.print(j + " ");
            }
            StdOut.println();
        }
    }
}
