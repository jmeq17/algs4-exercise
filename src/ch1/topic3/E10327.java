package ch1.topic3;

/*
Write homework.a method max() that takes homework.a reference to the first node in homework.a linked list as
argument and returns the value of the maximum key in the list. Assume that all keys are
positive integers, and return 0 if the list is empty.
 */

/*
通过静态方法和实例方法都实现了
 */

import utils.LinkedList;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class E10327 {

    public static int max(LinkedList<Integer> linked) {
        Iterator<Integer> iterator = linked.iterator();
        if (!iterator.hasNext()) return 0;
        int maxValue = iterator.next();
        while (iterator.hasNext()) {
            int temp = iterator.next();
            if (maxValue < temp) maxValue = temp;
        }
        return maxValue;
    }

    public static void main(String[] args) {
        LinkedList<Integer> integers = new LinkedList<>();

        for (int i = 0; i < 10; i++) integers.add(StdRandom.uniform(100));

        int maxValueOfStatic = max(integers);
        int maxValueOfInstance = integers.max();

        StdOut.println("Static method: " + maxValueOfStatic);
        StdOut.println("Instance method: " + maxValueOfInstance);
    }
}
