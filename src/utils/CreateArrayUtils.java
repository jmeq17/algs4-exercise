/**
 * 用于创建数组的工具集
 */

package utils;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class CreateArrayUtils {
    /**
     * 创建一个随机整型数组
     *
     * @param size  数组尺寸
     * @param scale 元素值范围
     * @return 返回整型数组
     */
    public static int[] RandomIntArray(int size, int scale) {
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = StdRandom.uniformInt(-scale, scale);
        }
        return a;
    }

    /**
     * 创建一个随机可比较 Integer 类型数组
     *
     * @param size  数组尺寸
     * @param scale 元素值范围
     * @return 返回 Comparable 类型数组
     */
    public static Comparable<Integer>[] RandomComIntArray(int size, int scale) {
        Comparable<Integer>[] a = new Comparable[size];
        for (int i = 0; i < size; i++) {
            a[i] = StdRandom.uniformInt(-scale, scale);
        }

        return a;
    }

    public static Comparable<Double>[] RandomComDoubleArray(int size) {
        Comparable<Double>[] a = new Comparable[size];
        for (int i = 0; i < size; i++) {
            a[i] = StdRandom.uniformDouble();
        }
        return a;
    }

    /**
     * 创建一个全部相同的可比较元素的数组
     *
     * @param size  数组尺寸
     * @param value 元素值
     * @return 返回 Comparable 型数组
     */
    public static Comparable[] EqualComArray(int size, int value) {
        Comparable[] a = new Comparable[size];
        for (int i = 0; i < size; i++) {
            a[i] = value;
        }
        return a;
    }

    /**
     * 创建连续整型数组序列
     *
     * @param N 数组尺寸
     * @return 返回连续整型数组序列
     */
    public static int[] IntSeqArray(int N) {
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = i;
        }
        return a;
    }

    /**
     * 创建不重复整数数组
     *
     * @param N  数组尺寸
     * @param lo 元素最小值
     * @param hi 元素最大值+1
     * @return 返回元素不同的整型数组
     */
    public static int[] CreatArray(int N, int lo, int hi) {
        // 需要大量时间用于排除重复值
        int[] a = new int[N];
        int[] demo = new int[N];
        for (int i = 0; i < N; ) {
            int val = StdRandom.uniform(lo, hi);
            if (BinarySearch.indexOf(demo, val) == -1) {
                demo[N - i - 1] = val;
                a[i++] = val;
            }
        }
        return a;
    }


    /**
     * 展示字符串数组
     *
     * @param s 字符串
     */
    public static void showStringArray(String[] s) {
        for (String i : s) {
            StdOut.println(i);
        }
    }

    /**
     * 测试用例
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        Comparable<Double>[] comparables = RandomComDoubleArray(10);

        System.out.println(Arrays.toString(comparables));
    }
}
