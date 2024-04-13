package ch3.topic5;

import edu.princeton.cs.algs4.StdOut;

public class E30517 {
    public static void main(String[] args) {
        String[] s = "putkeyintotheset".split("");
        MathSET<String> set = new MathSET<>(s);

        StdOut.printf("%16s", "Initial: ");
        set.show();

        // add()
        set.add("a");
        StdOut.printf("%16s", "add(): ");
        set.show();

        // delete()
        set.delete("s");
        StdOut.printf("%16s", "delete(): ");
        set.show();

        // complement()
        MathSET<String> set2 = set.complement();
        StdOut.printf("%16s", "complement(): ");
        set2.show();

        // union()
        MathSET<String> set3 = new MathSET<>("uvw".split(""));
        set.union(set3);
        StdOut.printf("%16s", "union(): ");
        set.show();

        // intersection()
        MathSET<String> set4 = new MathSET<>("test".split(""));
        StdOut.printf("%16s", "intersection(): ");
        set.intersection(set4);
        set.show();

        StdOut.println();

        // contains()
        StdOut.println(set.contains("t") + ", Excepted: true.");

        // isEmpty()
        StdOut.println(set.isEmpty() + ", Excepted: false.");

        // size()
        StdOut.println(set.size() + ", Excepted: 2.");
    }
}
