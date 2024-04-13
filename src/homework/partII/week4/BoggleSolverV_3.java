package homework.partII.week4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * This class implements boggle by TST in-site implemented.
 */

public class BoggleSolverV_3 {
    private final String[] dict;
    private boolean[] markedD;
    private Node root;
    private int rows, cols;

    private boolean[] marked;
    private Bag[] adj;
    private char[] chs;

    private static class Node {
        private final char c;
        private int val;
        //        private boolean marked;
        private Node left, mid, right;

        Node(char c) {
            this.c = c;
        }
    }

    public BoggleSolverV_3(String[] dictionary) {
        this.dict = dictionary.clone();
        // Shuffling homework.a array will request more time than the worst situation, because there are only 26 letters,
        // which requests at most 26 time compares.
        StdRandom.shuffle(dict);
        int len = dict.length;

        for (int i = 0; i < len; i++) root = put(root, dict[i], i + 1, 0);
    }

    private Node put(Node x, String key, int val, int d) {
        char c = key.charAt(d);
        if (x == null) x = new Node(c);

        int cmp = c - x.c;

        if (cmp < 0) x.left = put(x.left, key, val, d);
        else if (cmp > 0) x.right = put(x.right, key, val, d);
        else if (d < key.length() - 1) x.mid = put(x.mid, key, val, d + 1);
        else x.val = val;

        return x;
    }

    private boolean isValidDice(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public Iterable<String> getAllValidWords(BoggleBoard board) {
        rows = board.rows();
        cols = board.cols();
        int rc = rows * cols;
        adj = new Bag[rc];
        chs = new char[rc];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int v = row * cols + col;
                adj[v] = new Bag();
                chs[v] = board.getLetter(row, col);
                if (isValidDice(row - 1, col)) adj[v].add((row - 1) * cols + col);
                if (isValidDice(row + 1, col)) adj[v].add((row + 1) * cols + col);
                if (isValidDice(row, col - 1)) adj[v].add(row * cols + col - 1);
                if (isValidDice(row, col + 1)) adj[v].add(row * cols + col + 1);
                if (isValidDice(row - 1, col - 1)) adj[v].add((row - 1) * cols + col - 1);
                if (isValidDice(row - 1, col + 1)) adj[v].add((row - 1) * cols + col + 1);
                if (isValidDice(row + 1, col - 1)) adj[v].add((row + 1) * cols + col - 1);
                if (isValidDice(row + 1, col + 1)) adj[v].add((row + 1) * cols + col + 1);
            }
        }

        Queue<String> queue = new Queue<>();
        marked = new boolean[rc];
        markedD = new boolean[dict.length];

        for (int v = 0; v < rc; v++) {
            dfs(root, v, 0, queue);
        }

        return queue;
    }

    private Node findU(Node x) {
        if (x == null) return null;

        int cmp = x.c - 'U';

        if (cmp < 0) return findU(x.right);
        else if (cmp > 0) return findU(x.left);
        else return x;
    }

    private void dfs(Node x, int v, int d, Queue<String> queue) {
        if (x == null) return;

        marked[v] = true;

        int cmp = chs[v] - x.c;

        if (cmp > 0) dfs(x.right, v, d, queue);
        else if (cmp < 0) dfs(x.left, v, d, queue);
        else {
//            if (x.c == 'Q') {
//                if (x.mid != null && x.mid.c == 'U') {
//                    x = x.mid;
//                    d++;
//                } else {
//                    marked[v] = false;
//                    return;
//                }
//            }
//
//            if (x.val != 0 && !markedD[x.val - 1] && d > 1) {
//                queue.enqueue(dict[x.val - 1]);
//                markedD[x.val - 1] = true;
//            }
//
//            for (int w : adj[v])
//                if (!marked[w])
//                    dfs(x.mid, w, d + 1, queue);

            if (x.c == 'Q') {
                x = findU(x.mid);
                if (x == null) return;
                d++;
            }

            if (x.val != 0 && !markedD[x.val - 1] && d > 1) {
                queue.enqueue(dict[x.val - 1]);
                markedD[x.val - 1] = true;
            }

            for (Object t : adj[v]) {
                int w = (int) t;
                if (!marked[w])
                    dfs(x.mid, w, d + 1, queue);
            }
        }
        marked[v] = false;
    }

    private boolean contains(String word) {
        return contains(root, word, 0);
    }

    private boolean contains(Node x, String word, int d) {
        if (x == null) return false;

        int cmp = word.charAt(d) - x.c;

        if (cmp > 0) return contains(x.right, word, d);
        else if (cmp < 0) return contains(x.left, word, d);
        else if (d < word.length() - 1) return contains(x.mid, word, d + 1);

        return x.val != 0;
    }

    public int scoreOf(String word) {
        if (contains(word)) {
            int n = word.length();

            if (n == 3 || n == 4) return 1;
            if (n == 5) return 2;
            if (n == 6) return 3;
            if (n == 7) return 5;
            if (n > 7) return 11;
        }

        return 0;
    }

    public static void main(String[] args) {
        In in = new In("HW_Project\\boggle\\dictionary-algs4.txt");
        String[] dictionary = in.readAllStrings();
        BoggleSolverV_3 solver = new BoggleSolverV_3(dictionary);
        BoggleBoard board = new BoggleBoard("HW_Project\\boggle\\board-q.txt");

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

//    public static void main(String[] args) {
//        In in = new In("HW_Project\\boggle\\dictionary-yawl.txt");
//        String[] dictionary = in.readAllStrings();
//        HW.PartII.Week4.BoggleSolverV_3 solver = new HW.PartII.Week4.BoggleSolverV_3(dictionary);
//        solver.scoreOf("DOLLY");
//        StdOut.println(solver.scoreOf("UNHACK"));
//    }
}
