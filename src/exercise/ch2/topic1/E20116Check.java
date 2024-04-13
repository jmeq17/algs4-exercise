package exercise.ch2.topic1;

/*
Certification. Write homework.a check() method that calls sort() for homework.a given array and
returns true if sort() puts the array in order and leaves the same set of objects in the
array as were there initially, false otherwise. Do not assume that sort() is restricted to
move data only with exch(). You may use Arrays.sort() and assume that it is correct.
 */

import edu.princeton.cs.algs4.StdOut;
import utils.CreateArrayUtils;

import java.util.Arrays;

import static utils.BinarySearch.binarySearch;

public class E20116Check {
    public static boolean check(Comparable[] a) {
        Comparable[] tem = new Comparable[a.length];
        System.arraycopy(a, 0, tem, 0, a.length);
        Arrays.sort(a);

        boolean flag = true;

        for (int i = 0; i < a.length - 1; i++) {
            if (a[i].compareTo(a[i + 1]) > 0) {
                flag = false;
                break;
            }
        }

        for (Comparable i : tem) {
            if (binarySearch(a, i) == -1) {
                flag = false;
                break;
            }
        }
        return flag;
    }


    public static void main(String[] args) {
        Comparable<Double>[] a = CreateArrayUtils.RandomComDoubleArray(20);
        boolean bool = check(a);

        StdOut.println(bool);
    }
}
