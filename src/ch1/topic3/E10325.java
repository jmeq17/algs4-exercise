package ch1.topic3;

/*
Write homework.a method insertAfter() that takes two linked-list Node arguments and
inserts the second after the first on its list (and does nothing if either argument is null).
 */

/*
实例方法不能以 Node 作为参数
 */

import utils.LinkedList;
import edu.princeton.cs.algs4.StdOut;

public class E10325 {

    public static void main(String[] args) {
        LinkedList<String> linked = new LinkedList<>();

        String[] input = "abcdefgh".split("");

        for (String s : input) linked.add(s);

        String s = "INSERT";

        StdOut.print("before INSERT: ");
        for (String item : linked) {
            StdOut.print(item + " ");
        }

        StdOut.println();
        linked.insertAfter("d", s);

        StdOut.print("after INSERT: ");
        for (String item : linked) {
            StdOut.print(item + " ");
        }
    }
}
