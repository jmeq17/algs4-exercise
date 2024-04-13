package homework.partII.week4;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.TST;

import java.util.HashSet;

/**
 * This class implements boggle by graph.
 */

public class BoggleSolverV_2 {
    private final TST<Integer> tst;

    private int rows, cols;

    public BoggleSolverV_2(String[] dictionary) {
        this.tst = new TST<>();
        StdRandom.shuffle(dictionary);
        for (String s : dictionary) tst.put(s, s.length());
    }

    private boolean isValidDice(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public Iterable<String> getAllValidWords(BoggleBoard board) {
        rows = board.rows();
        cols = board.cols();
        int rc = rows * cols;
        Bag<Integer>[] adj = (Bag<Integer>[]) new Bag[rc];
        char[] chs = new char[rc];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int v = row * cols + col;
                adj[v] = new Bag<>();
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

        HashSet<String> set = new HashSet<>();

        for (int v = 0; v < rc; v++) {
            boolean[] marked = new boolean[rc];
            String s;
            if (chs[v] != 'Q') s = chs[v] + "";
            else s = chs[v] + "U";
            dfs(adj, v, s, chs, marked, set);
        }

        return set;
    }

    private String dfs(Bag<Integer>[] adj, int v, String s, char[] ch, boolean[] marked, HashSet<String> set) {
        marked[v] = true;

        if (tst.contains(s) && tst.get(s) > 2) {
            set.add(s);
        }

        for (int w : adj[v]) {
            if (!marked[w]) {
                if (ch[w] != 'Q') s += ch[w];
                else s += ch[w] + "U";
                s = dfs(adj, w, s, ch, marked, set);
            }
        }

        marked[v] = false;
        if (s.length() > 1 && s.charAt(s.length() - 2) == 'Q') return s.substring(0, s.length() - 2);
        return s.substring(0, s.length() - 1);
    }

    public int scoreOf(String word) {
        if (tst.contains(word)) {
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
        In in = new In("HW_Project\\boggle\\dictionary-yawl.txt");
        String[] dictionary = in.readAllStrings();
        BoggleSolverV_2 solver = new BoggleSolverV_2(dictionary);
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
//        BoggleSolverAll solver = new BoggleSolverAll(dictionary);
//        solver.scoreOf("ANSWERS");
//        StdOut.println(solver.scoreOf("BA"));
//    }
}
