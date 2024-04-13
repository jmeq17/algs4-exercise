package exercise.ch3.topic3;

/*
E30339
Delete the minimum. Implement the deleteMin() operation for red-black
BSTs by maintaining the correspondence with the transformations given in the text for
moving down the left spine of the tree while maintaining the invariant that the current
node is not homework.a 2-node.

E30341
Delete the maximum. Implement the deleteMax() operation for red-black
BSTs. Note that the transformations involved differ slightly from those in the previous
exercise because red links are left-leaning.

E30342
Delete. Implement the delete() operation for red-black BSTs, combining the
methods of the previous two ch1.topic4.exercises with the delete() operation for BSTs.
 */

import edu.princeton.cs.algs4.StdOut;
import utils.RedBlackBST;

public class E30339_41 {
    public static void main(String[] args) {
        String[] s = "Dmoving down the left spine of thet".split("");
        RedBlackBST<String, Integer> st = new RedBlackBST<>();

        for (String i : s) st.put(i, 1);

        StdOut.print("Before:   ");
        st.print();
        StdOut.println("\nExpected:   D d e f g h i l m n o p s t v w ");

        st.deleteMin();
        st.deleteMin();
        st.deleteMax();
        st.deleteMax();
        st.delete("i");

        StdOut.print("After:    ");
        st.print();
        StdOut.println("\nExpected: d e f g h l m n o p s t ");
    }
}
