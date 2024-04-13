package exercise.ch2.topic5;

/*
Write homework.a program Frequency that reads strings from standard input and prints
the number of times each string occurs, in descending order of frequency.
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import utils.Quick;

public class E20508Frequency {
    public static void frequency(String[] a) {
        Quick.sort(a);
//        int N = homework.a.length;
//        int[] auxStr = new int[N], auxNum = new int[N];
//
//        auxNum[0] = 1;
//
//        for (int i = 1, j = 0; i < N; i++) {
//            if (!homework.a[i].equals(homework.a[i - 1])) {
//                j = i;
//            }
//            auxNum[j]++;
//        }

        // 通过逆数组映射，不太行，因为可能某两个字符串出现的次数一样多，
        // 这样反向映射会出现覆盖现象
//        for (int i = 0; i < N; i++) {
//            if (auxNum[i] != 0) auxStr[auxNum[i]] = i;
//        }
//
//        MergeForInt.sort(auxNum);
//
//        for (int i = 0; i < N; i++) {
//            if (auxNum[i] != 0) {
//                StdOut.printf("%s: %d.\n", homework.a[auxStr[auxNum[i]]], auxNum[i]);
//            }
//        }

        // 一种解法是扫描 N 遍 auxNum，找出最小值，然后打印，太慢了

        // 再定义一个字符串数组，将 auxSum 的值与对应的字符串拼接，然后排序，但当某字符串出现次数超过 10 次时会出错
        // 因为字符串排序是从第一个字符一次排序的


        // 通过嵌套类 Record 实现
        int n = a.length;
        Record[] aux = new Record[n];
        int count = 0, j = 1;

        for (int i = 0; i < n - 1; i++) {
            if (!a[i].equals(a[i + 1])) {
                aux[count++] = new Record(a[i], j);
                j = 1;
            } else {
                j++;
            }
        }

        aux[count++] = new Record(a[n - 1], j);

        Record[] aux2 = new Record[count];
        System.arraycopy(aux, 0, aux2, 0, count);

        Quick.sort(aux2);
        for (int i = 0; i < count; i++) {
            aux2[i].show();
        }
    }

    // 通过嵌套类实现
    public static class Record implements Comparable<Record> {
        private final String str;
        private final int num;

        public Record(String str, int num) {
            this.str = str;
            this.num = num;
        }

        public void show() {
            StdOut.printf("%s: %d.\n", str, num);
        }

        public int compareTo(Record that) {
            return Integer.compare(this.num, that.num);
        }
    }

    public static void main(String[] args) {
        String[] a = "Create homework.a data type Version that data type Create homework.a data type Version that data type Version represents homework.a".split("\\s+");

        In in = new In(args[0]);    // files/tale2
        String[] s = in.readAllStrings();

        frequency(s);
    }
}
