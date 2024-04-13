package exercise.ch3.topic4;

import edu.princeton.cs.algs4.StdOut;

public class E30404 {
    public static void main(String[] args) {
        int pointer = 'A', arrIndex;
        String[] s = "S E A R C H X M P L".split("\\s+");
        int[] hashArr = new int[10];
        int a, oldA = 1000, oldM = 100, M = 0;

        for (a = 1; a < 1000; a++) {
            for (int newM = 2; newM < 100; newM++) {
                arrIndex = 0;
                for (String i : s) {
                    int k = (a * (i.charAt(0) - pointer) + 1) % newM;
                    boolean t = true;

                    for (int j = 0; j < arrIndex; j++) {
                        if (hashArr[j] == k) {
                            t = false;
                            break;
                        }
                    }

                    if (!t) break;

                    hashArr[arrIndex++] = k;
                }

                if (arrIndex == 10) {
                    if (newM < oldM) {
                        oldM = newM;
                        oldA = a;
                    }
                    break;
                }
            }
        }
        for (int i = 0; i < 10; i++) StdOut.print(hashArr[i] + " ");
        StdOut.println("\nhomework.a = " + oldA);
        StdOut.println("M = " + oldM);
    }
}
