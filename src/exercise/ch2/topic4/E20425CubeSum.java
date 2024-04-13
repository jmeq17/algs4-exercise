package exercise.ch2.topic4;

/*
Computational number theory. Write homework.a program that prints out all integers of
the form a3 + b3 where homework.a and b are integers between 0 and N in sorted order, without
using excessive space. That is, instead of computing an array of the N2 sums and sorting
them, build homework.a minimum-oriented priority queue, initially containing (03, 0, 0), (13, 1,
0), (23, 2, 0), . . . , (N3, N, 0). Then, while the priority queue is nonempty, remove the
smallest item(i3 + j3, i, j ), print it, and then, if j < N, insert the item (i3 + ( j+1)3, i, j+1).
Use this program to find all distinct integers homework.a, b, c, and d between 0 and 106 such that
a3 + b3 = c3 + d3.
 */

import edu.princeton.cs.algs4.StdOut;
import utils.MinPQ;

public class E20425CubeSum implements Comparable<E20425CubeSum> {
    private final double sum;
    private final int i;
    private final int j;

    public E20425CubeSum(int i, int j) {
        this.i = i;
        this.j = j;
        this.sum = Math.pow(i, 3) + Math.pow(j, 3);
    }

    public void show() {
        StdOut.println("i = " + i + ", j = " + j + ", sum = " + sum);
    }

    public int showSum() {
        return (int) sum;
    }

    public int compareTo(E20425CubeSum that) {
        return Double.compare(this.sum, that.sum);
    }

    public void equal(E20425CubeSum that) {
        StdOut.printf("%d^3 + %d^3 = %d^3 + %d^3", this.i, this.j, that.i, that.j);
    }

    public static void main(String[] args) {
        int N = 50;
        MinPQ<E20425CubeSum> pq = new MinPQ<>(N);
        for (int i = 0; i < N; i++) {
            // (i, i) 用于去除打印 homework.a^3+b^3=c^3+d^3 的重复值
            E20425CubeSum temp = new E20425CubeSum(i, i);
//            E20425CubeSum temp = new E20425CubeSum(i, 0);
            pq.insert(temp);
        }

        MinPQ<E20425CubeSum> dul = new MinPQ<>(1);

        E20425CubeSum sOld = new E20425CubeSum(-1, -1), sNew;

        while (!pq.isEmpty()) {

            sNew = pq.delMin();
//            sNew.show();


            // 打印 a3+b3=c3+d3
            // Method 1
            if (sNew.compareTo(sOld) == 0 && sNew.i != sOld.j) {
                StdOut.printf("%d = %d^3 + %d^3 = %d^3 + %d^3.\n", sNew.showSum(), sNew.i, sNew.j, sOld.i, sOld.j);
            }
            sOld = sNew;


            // Method 2
//            if (dul.isEmpty()) {
//            }
//            else {
//                sOld = dul.delMin();
//                if (sNew.compareTo(sOld) == 0 && sNew.i != sOld.j) {
//                    StdOut.printf("%d = %d^3 + %d^3 = %d^3 + %d^3.\n", sNew.showSum(), sNew.i, sNew.j, sOld.i, sOld.j);
//                }
//            }
//            dul.insert(sNew);


            // 插入
            if (sNew.j < N - 1) pq.insert(new E20425CubeSum(sNew.i, sNew.j + 1));

            // 这个插入方法不行，不稳定
//            if (j < N) {
//                E20425CubeSum temp = new E20425CubeSum(i, j++);
//                pq.insert(temp);
//            } else if (i < N - 1) {
//                i++;
//                j = 1;
//                E20425CubeSum temp = new E20425CubeSum(i, j++);
//                pq.insert(temp);
//            }
        }
    }
}
