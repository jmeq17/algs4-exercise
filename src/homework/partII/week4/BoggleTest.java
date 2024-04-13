package homework.partII.week4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class BoggleTest {
    public static class BoggleSolver1 {
        private Node root;
        private String[] dict;
        private int[] markedDict;

        private static class Node {
            private int val;
            private final Node[] next = new Node[26];
        }

        public BoggleSolver1(String[] dictionary) {
            this.dict = dictionary.clone();
            int len = dict.length;
            markedDict = new int[len];

            root = new Node();

            for (int i = 0; i < len; i++) {
                Node x = root;
                String key = dict[i];
                int l = key.length();

                for (int j = 0; j < l; j++) {
                    int c = key.charAt(j) - 'A';
                    if (x.next[c] == null) {
                        x.next[c] = new Node();
                    }
                    x = x.next[c];
                }
                x.val = i + 1;
            }
        }
    }

    public static class BoggleSolver2 {
        private Node root;

        private static class Node {
            private int val = 0;
            private final Node[] next = new Node[26];
        }

        public BoggleSolver2(String[] dictionary) {
            root = new Node();
            for (String s : dictionary) put(s);
        }

        public void put(String key) {
            root = put(root, key, 0);
        }

        private Node put(Node x, String key, int d) {
            if (x == null) x = new Node();
            if (d == key.length()) {
                x.val = 1;
                return x;
            }
            int c = key.charAt(d) - 'A';
            x.next[c] = put(x.next[c], key, d + 1);
            return x;
        }
    }

    // ---------------------------

    public static void getAllValidWords(String[] args) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);

        int score = 0;
        int count = 0;

        Iterable<String> q = solver.getAllValidWords(board);

        for (String word : q) {
            StdOut.println(word);
            score += solver.scoreOf(word);
            count++;
        }
        StdOut.println("Score = " + score);
        StdOut.println("Count = " + count);
    }

    public static void scoreOf(String[] args, String pat) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);

        solver.scoreOf(pat);
        StdOut.println(solver.scoreOf(pat));
    }


    public static void ratio(String[] args) {
        Stopwatch timer = new Stopwatch();
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();

        BoggleSolver solver = new BoggleSolver(dictionary);
        int count = 0;
        while (count < 30000) {
            BoggleBoard board = new BoggleBoard();
            solver.getAllValidWords(board);
            count++;
        }
        double time = timer.elapsedTime();
        double solPerSec = Math.floor(30000 / time * 100) / 100;
        double ratio = Math.floor(8667.29 / solPerSec * 100) / 100;
        StdOut.println("Total Time for 30000 random board is " + timer.elapsedTime());
        StdOut.println("reference solution per second is 8667.29");
        StdOut.println("student solution per second is " + solPerSec);
        StdOut.println("reference/student ratio is " + ratio);
    }

    public static void ratio2(String[] args) {
        int count;
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();

        count = 0;

        long time1Old = System.currentTimeMillis();
        while (count < 100) {
            BoggleSolver1 solver = new BoggleSolver1(dictionary);
            count++;
        }
        long time1New = System.currentTimeMillis();
        double time1 = time1New - time1Old;

        count = 0;

        long time2Old = System.currentTimeMillis();
        while (count < 100) {
            BoggleSolver2 solver2 = new BoggleSolver2(dictionary);
            count++;
        }
        long time2New = System.currentTimeMillis();
        double time2 = time2New - time2Old;

        double solPerSec1 = 100 / time1;
        double solPerSec2 = 100 / time2;
        double ratio = solPerSec1 / solPerSec2;
        double timeRatio = time1 / time2;
        StdOut.println("Total Time for 100 contructor of solver1 is " + time1 + "ms");
        StdOut.println("Total Time for 100 contructor of solver2 is " + time2 + "ms");
        StdOut.println("BoggleSolver1 solution per second is " + solPerSec1);
        StdOut.println("BoggleSolver2 solution per second is " + solPerSec2);
        StdOut.println("reference/student ratio is " + ratio);
        StdOut.println();
        StdOut.println("reference/student time ratio is " + timeRatio);
    }

    public static void main(String[] args) {
        String[] file = {"HW_Project\\boggle\\dictionary-algs4.txt",
                "HW_Project\\boggle\\dictionary-yawl.txt"};

//        ratio(file);
//        scoreOf(file, "");
//        getAllValidWords(file);
        ratio2(file);
    }
}
