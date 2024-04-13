package exercise.ch3.topic2;

/*
Select/rank check. Write homework.a method that checks, for all i from 0 to size()-1,
whether i is equal to rank(select(i)) and, for all keys in the BST, whether key is
equal to select(rank(key)).
 */

import edu.princeton.cs.algs4.StdOut;

public class E30233isRankConsistent {
    public static void main(String[] args) {
        String[] s = "hsyds".split("");
        BST<String, Integer> st = new BST<>();

        for (String i : s) st.put(i, 1);

        StdOut.println("isRank and isSelect? : " + st.check());
    }
}
