package ch1.topic3;

/*
Develop homework.a recursive solution to the previous question.
 */

import utils.LinkedList;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class E10328 {

    public static int maxOfRecusive(LinkedList<Integer> linked) {
        Iterator<Integer> iterator = linked.iterator();
        return maxOfRecusive(iterator);
    }

    private static int maxOfRecusive(Iterator<Integer> iterator) {
        if (!iterator.hasNext()) return 0;

        int maxValue1 = iterator.next();

        int maxValue2 = maxOfRecusive(iterator);

        return Math.max(maxValue1, maxValue2);
    }


    public static void main(String[] args) {

        LinkedList<Integer> integers = new LinkedList<>();

        for (int i = 0; i < 10; i++) integers.add(StdRandom.uniform(100));

        int maxValueOfStatic = maxOfRecusive(integers);
        int maxValueOfInstance = integers.maxOfRecusive();

        StdOut.println("Static method: " + maxValueOfStatic);
        StdOut.println("Instance method: " + maxValueOfInstance);
    }
}
