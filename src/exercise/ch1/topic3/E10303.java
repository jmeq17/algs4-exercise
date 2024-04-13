package exercise.ch1.topic3;

/*
Suppose that homework.a client performs an intermixed sequence of (stack) push and pop
operations. The push operations put the integers 0 through 9 in order onto the stack;
the pop operations print out the return values. Which of the following sequence(s)
could not occur?
    homework.a. 4 3 2 1 0 9 8 7 6 5
    b. 4 6 8 7 5 3 2 9 0 1
    c. 2 5 6 7 4 8 9 3 1 0
    d. 4 3 2 1 0 5 6 7 8 9
    e. 1 2 3 4 5 6 9 8 7 0
    f. 0 4 6 5 3 8 1 7 2 9
    g. 1 4 7 9 8 6 5 3 0 2
    h. 2 1 4 3 6 5 8 7 9 0
 */

import utils.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class E10303 {
    public static boolean isValid(int[] seq) {
        Stack<Integer> stack = new Stack<>();
        int currentNum = 9;
        int index = 9;
        while (currentNum >= 0) {
            if (index >= 0 && seq[index] == currentNum) {
                index--;
                currentNum--;
            } else if (!stack.isEmpty() && stack.peek() == currentNum) {
                stack.pop();
                currentNum--;
            } else {
                if (index < 0)
                    break;
                stack.push(seq[index]);
                index--;
            }
        }
        return stack.isEmpty();
    }


    // 按输出顺序的做法
    // 失败
//    public static boolean isTRUE(int[] homework.a) {
//        int N = homework.a.length;
//        for (int i = 0; i < N; i++) {
//            int k = homework.a[i];
//            if (k == 0) continue;
//            int[] b = new int[k];
//            for (int j = 0; j < k; j++) {
//                b[j] = getIndex(homework.a, j);
//            }
//            if (!compareValue(b)) return false;
//        }
//        return true;
//    }

//    public static int getIndex(int[] arr, int value) {
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] == value) {
//                return i;
//            }
//        }
//        return -1;
//    }

//    public static boolean compareValue(int[] homework.a) {
//        int N = homework.a.length;
//        for (int i = 0; i < N; i++) {
//            for (int j = i + 1; j < N; j++) {
//                if (homework.a[i] < homework.a[j]) return false;
//            }
//        }
//        return true;
//    }


    public static void main(String[] args) {
//        In in = new In(args[0]);  // 这个做法怎么弄啊，看不懂啊
        int i = 97;
        while (!StdIn.isEmpty()) {
            String s = StdIn.readLine();
            String[] lines = s.split(" ");
            int[] n = new int[10];
            for (int j = 0; j < lines.length; j++) {
                n[j] = Integer.parseInt(lines[j]);
            }
            if (isValid(n)) {
                StdOut.println((char) i + ": TRUE");
            } else {
                StdOut.println((char) i + ": FALSE");
            }
            i++;
        }
    }
}
