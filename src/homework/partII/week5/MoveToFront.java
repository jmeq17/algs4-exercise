package homework.partII.week5;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.HashMap;

public class MoveToFront {
    public static class Stack {
        private Node first;

        private static class Node {
            char c;
            Node next;

            Node(char c) {
                this.c = c;
            }
        }

        public Stack() {
            for (int i = 255; i >= 0; i--) {
                Node oldfirst = first;
                first = new Node((char) i);
                first.next = oldfirst;
            }
        }

        public int push(char that) {
            if (first.c == that) return 0;

            Node x = first;
            int count = 1;

            while (true) {
                if (x.next.c == that) {
                    Node temp = x.next;
                    x.next = temp.next;
                    temp.next = first;
                    first = temp;
                    return count;
                }
                x = x.next;
                count++;
            }
        }

        public char push(int that) {
            if (that == 0) return first.c;

            Node x = first;

            for (int count = 0; count < that - 1; count++) {
                x = x.next;
            }

            Node temp = x.next;
            x.next = temp.next;
            temp.next = first;
            first = temp;

            return first.c;
        }
    }


    private static final int R = 256;

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode2() {
        String input = BinaryStdIn.readString();
        char[] ch = input.toCharArray();

        char[] mtf = new char[R];
        int[] reverse = new int[R];
        for (int i = 0; i < R; i++) {
            mtf[i] = (char) i;
            reverse[mtf[i]] = i;
        }

        for (char current : ch) {
            int d = reverse[current];

            BinaryStdOut.write(d, 8);

            for (int j = d; j > 0; j--) {
                mtf[j] = mtf[j - 1];
                reverse[mtf[j - 1]] = j;
            }

            mtf[0] = current;
            reverse[current] = 0;
        }
        BinaryStdOut.close();
    }

    // Performance optimization
    public static void encode3() {
        int[] mtf = new int[R];
        HashMap<Character, Integer> st = new HashMap<>();
        for (int i = 0; i < R; i++) {
            mtf[i] = i;
            st.put((char) i, i);
        }

        int root;

        while (!BinaryStdIn.isEmpty()) {
            // Test of timeing
//        for (int t = 0; t < 32000; t++) {
            char current = BinaryStdIn.readChar();
            root = st.get(current);

            BinaryStdOut.write(root, 8);

            for (int i = 1; i < root; i++) {
                mtf[i] = mtf[i - 1];
                st.put((char) i, mtf[i - 1]);   // This is false.
            }

            mtf[0] = current;
            st.put(current, 0);
        }
        BinaryStdOut.close();
    }

    // Performance optimization
    public static void encode4() {
        int[] mtf = new int[R];
        for (int i = 0; i < R; i++) mtf[i] = i;

        while (!BinaryStdIn.isEmpty()) {
            // Test of timeing
//        for (int t = 0; t < 32000; t++) {
            char current = BinaryStdIn.readChar();

            for (int i = R - 1; i >= 0; i--) {
                if (mtf[i] == current) {
                    BinaryStdOut.write(i, 8);

                    System.arraycopy(mtf, 0, mtf, 1, i);
                    break;
                }
            }

            mtf[0] = current;
        }
        BinaryStdOut.close();
    }

    // Performance optimization
    public static void encode() {
        Stack stack = new Stack();

        while (!BinaryStdIn.isEmpty()) {
            // Test of timeing
//        for (int t = 0; t < 32000; t++) {
            char current = BinaryStdIn.readChar();

            int order = stack.push(current);

            BinaryStdOut.write(order, 8);
        }
        BinaryStdOut.close();
    }

    // Performance optimization
    public static void encode5() {
        int[] mtf = new int[R];
        for (int i = 0; i < R; i++) mtf[i] = i;

        int count = 0, i;

        while (!BinaryStdIn.isEmpty()) {
            char current = BinaryStdIn.readChar();
            int index = mtf[current];
            BinaryStdOut.write(index, 8);
            mtf[current] = 0;

            for (i = 0; count < index; i++) {
                if (mtf[i] < index) {
                    mtf[i]++;
                    count++;
                }
            }
        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode2() {
        String input = BinaryStdIn.readString();
        char[] ch = input.toCharArray();

        char[] mtf = new char[R];
        for (int i = 0; i < R; i++) mtf[i] = (char) i;

        for (char current : ch) {
            BinaryStdOut.write(mtf[current], 8);
            char tem = mtf[current];

            System.arraycopy(mtf, 0, mtf, 1, current);

            mtf[0] = tem;
        }
        BinaryStdOut.close();
    }

    // Performance optimization
    public static void decode3() {
        char[] mtf = new char[R];
        for (int i = 0; i < R; i++) mtf[i] = (char) i;

        while (!BinaryStdIn.isEmpty()) {
            // Test of timeing
//        for (int t = 0; t < 256000; t++) {
            char current = BinaryStdIn.readChar();
            BinaryStdOut.write(mtf[current], 8);

            char tem = mtf[current];

            System.arraycopy(mtf, 0, mtf, 1, current);

            mtf[0] = tem;
        }
        BinaryStdOut.close();
    }

    // Performance optimization
    public static void decode() {
        Stack stack = new Stack();

        while (!BinaryStdIn.isEmpty()) {
            int current = BinaryStdIn.readChar();

            char tem = stack.push(current);

            BinaryStdOut.write(tem, 8);
        }
        BinaryStdOut.close();
    }

    // Performance optimization
    public static void decode5() {
        char[] mtf = new char[R];
        for (int i = 0; i < R; i++) mtf[i] = (char) i;

        while (!BinaryStdIn.isEmpty()) {
            int i = BinaryStdIn.readChar();
            BinaryStdOut.write(mtf[i], 8);

            char tem = mtf[i];

            System.arraycopy(mtf, 0, mtf, 1, i);

            mtf[0] = tem;
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) encode();
        if (args[0].equals("+")) decode();
    }
}
