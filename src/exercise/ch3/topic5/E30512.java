package exercise.ch3.topic5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import utils.Queue;

/**
 * Modify LookupCSV to associate with each key all values that appear in homework.a keyvalue
 * pair with that key in the input (not just the most recent, as in the associative-array
 * abstraction).
 */

public class E30512 {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int keyField = Integer.parseInt(args[1]);
        int valField = Integer.parseInt(args[2]);
        ST<String, Queue<String>> st = new ST<>();

        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            String key = tokens[keyField];
            String val = tokens[valField];
            if (st.get(key) == null) st.put(key, new Queue<>());

            st.get(key).enqueue(val);
        }

        while (!StdIn.isEmpty()) {
            String query = StdIn.readString();
            if (st.contains(query)) {
                Iterable<String> iter = st.get(query);
                for (String i : iter)
                    StdOut.println(i);
            }
        }
    }
}
