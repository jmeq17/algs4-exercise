package ch1.topic3;

/*
Write homework.a method removeAfter() that takes homework.a linked-list Node as argument and
removes the node following the given one (and does nothing if the argument or the next
field in the argument node is null).
 */

/*
实例方法的话不能以 Node 作为参数，以 Item 未参数编写实例方法。
 */

import utils.LinkedList;
import edu.princeton.cs.algs4.StdOut;

public class E10324 {

    // 修改链表，无法通过静态方法实现（除非补全迭代器的 remove() 代码
//    public void removeAfter(Node node) {
//        if (node != null && node.next != null) {
//            node.next = null;
//        }
//    }


    public static void main(String[] args) {
        LinkedList<String> linked = new LinkedList<>();

        String[] input = "abcdefgh".split("");

        for (String s : input) linked.add(s);
        String s = "e";

        StdOut.print("before delete: ");
        for (String item : linked) {
            StdOut.print(item + " ");
        }

        StdOut.println();
        linked.removeAfter(s);

        StdOut.print("after delete: ");
        for (String item : linked) {
            StdOut.print(item + " ");
        }
    }
}
