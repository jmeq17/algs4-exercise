package homework.partII.week5;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    private static final int R = 256;

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
//        String s = "ABRACADABRA!";
        String s = BinaryStdIn.readString();
        CircularSuffixArray c = new CircularSuffixArray(s);

        int n = c.length();
        char[] t = new char[n];
        int first = 0, i;

        for (i = 0; i < n; i++) {
            int tem = c.index(i);
            if (tem == 0) {
                t[i] = s.charAt(n - 1);
                first = i;
            } else t[i] = s.charAt(tem - 1);
        }

        BinaryStdOut.write(first);
        for (i = 0; i < n; i++)
            BinaryStdOut.write(t[i]);
        BinaryStdOut.close();

//        System.out.println();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        char[] head = BinaryStdIn.readString().toCharArray();
//        int first = 3;
//        char[] head = "ARD!RCAAAABB".toCharArray();
        int n = head.length, i, count = 0, tem = first;

        int[] next = new int[n];
        char[] tail = head.clone();

        /**
         * 性能杀手
         */
//        Arrays.sort(head);

        // HW.PartII.Week5.MSD sort for head[]
        int[] msd = new int[R + 1];
        char[] aux = new char[n];
        for (i = 0; i < n; i++) msd[head[i] + 1]++;
        for (i = 0; i < R; i++) msd[i + 1] += msd[i];
        for (i = 0; i < n; i++) aux[msd[head[i]]++] = head[i];
        System.arraycopy(aux, 0, head, 0, n);

//        Queue<Integer>[] queue = (Queue<Integer>[]) new Queue[r];
//
//        for (int i = 0; i < n; i++) {
//            if (queue[tail[i]] == null) queue[tail[i]] = new Queue<>();
//            queue[tail[i]].enqueue(i);
//        }
//
//        for (int i = 0; i < n; i++)
//            next[i] = queue[head[i]].dequeue();

        int[] index = new int[R];

        for (i = 0; i < n; i++) {
            index[head[i]] = count;
            count++;
        }

        for (i = n - 1; i >= 0; i--) {
            next[index[tail[i]]--] = i;
        }

//        StdOut.println("head" + " origin");

        for (i = 0; i < n; i++) {
//            StdOut.println(head[i] + "    " + head[tem]);
            BinaryStdOut.write(head[tem]);
            tem = next[tem];
        }

        BinaryStdOut.close();

//        System.out.println();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-")) transform();
        if (args[0].equals("+")) inverseTransform();
    }
}
