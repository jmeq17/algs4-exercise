package exercise.ch1.topic3;

/*
Write homework.a method delete() that takes an int argument k and deletes the kth element in homework.a linked list, if it exists.
 */

import utils.LinkedList;
import edu.princeton.cs.algs4.StdOut;

public class E10320 {

    // 静态方法似乎不行，foreach 遍历只能访问不能修改，或者说没有定义迭代器的 remove() 方法
//    public static Object delete(LinkedList linked, int k) {
//        Iterator iterator = linked.iterator();
//
//        for (int i = 1; i <= k; i++) {
//            if (!iterator.hasNext()) throw new IllegalArgumentException("k is too large then N.");
//        }
//
//        Object s = iterator.next();
//        while (iterator.hasNext()) {
//            s = iterator.next();
//        }
//
//        return s;
//    }

    public static void main(String[] args) {
        LinkedList<String> linked = new LinkedList<>();

        String[] input = "abcdefgh".split("");

        for (String s : input) linked.add(s);

        StdOut.println("The last node is: " + linked.remove(4) + ", Expection: e");
//        StdOut.println("Static method. The last node is: " + delete(linked, 4) + ", Expection: 3");
    }
}
