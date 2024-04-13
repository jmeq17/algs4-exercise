package ch3.topic2;

/*
Draw the BST that results when you insert the keys E A S Y Q U E S T I O N,
in that order (associating the value i with the ith key, as per the convention in the text)
into an initially empty tree. How many compares are needed to build the tree?
 */

public class E30201 {
    public static void main(String[] args) {
        BST st = new BST<>();
        String[] s = "E A S Y Q U E S T I O N".split("\\s+");

        int j = 1;
        for (String i : s) st.put(i, j++);
    }
}
