package homework.partII.week4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Bag;

import java.util.HashSet;

/**
 * This version implements the assignment by two-dimension HW.PartII.Week4.Bag<Integer[]> array to maintain characters and by dfs to search homework.a pattern.
 * It can get all scores of implments and memory test but not timing test.
 */

public class BoggleSolverV_1 {
    private final String[] dict;
    private final int n;
//    private HashSet<String> set;

    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolverV_1(String[] dictionary) {
//        StringBuilder txt = new StringBuilder();
//        for (String s : dictionary) {
//            txt.append(s);
//        }
        this.dict = dictionary.clone();
        this.n = dict.length;

//        set = new HashSet<>();
//
//        set.addAll(Arrays.asList(dictionary).subList(0, n));
    }

    public Iterable<String> getAllValidWords(BoggleBoard board) {
        if (board.rows() == 1) return rowsIs1(board);
        if (board.cols() == 1) return colsIs1(board);

        int rows = board.rows();
        int cols = board.cols();
        Bag<Integer[]>[][] adj = (Bag<Integer[]>[][]) new Bag[rows][cols];

        adj[0][0] = new Bag<>();
        adj[0][0].add(new Integer[]{0, 1});
        adj[0][0].add(new Integer[]{1, 0});
        adj[0][0].add(new Integer[]{1, 1});

        adj[0][cols - 1] = new Bag<>();
        adj[0][cols - 1].add(new Integer[]{0, cols - 2});
        adj[0][cols - 1].add(new Integer[]{1, cols - 1});
        adj[0][cols - 1].add(new Integer[]{1, cols - 2});

        adj[rows - 1][0] = new Bag<>();
        adj[rows - 1][0].add(new Integer[]{rows - 1, 1});
        adj[rows - 1][0].add(new Integer[]{rows - 2, 0});
        adj[rows - 1][0].add(new Integer[]{rows - 2, 1});

        adj[rows - 1][cols - 1] = new Bag<>();
        adj[rows - 1][cols - 1].add(new Integer[]{rows - 1, cols - 2});
        adj[rows - 1][cols - 1].add(new Integer[]{rows - 2, cols - 1});
        adj[rows - 1][cols - 1].add(new Integer[]{rows - 2, cols - 2});

        for (int col = 1; col < cols - 1; col++) {
            adj[0][col] = new Bag<>();
            adj[0][col].add(new Integer[]{0, col - 1});
            adj[0][col].add(new Integer[]{0, col + 1});
            adj[0][col].add(new Integer[]{1, col});
            adj[0][col].add(new Integer[]{1, col - 1});
            adj[0][col].add(new Integer[]{1, col + 1});


            adj[rows - 1][col] = new Bag<>();
            adj[rows - 1][col].add(new Integer[]{rows - 1, col - 1});
            adj[rows - 1][col].add(new Integer[]{rows - 1, col + 1});
            adj[rows - 1][col].add(new Integer[]{rows - 2, col});
            adj[rows - 1][col].add(new Integer[]{rows - 2, col - 1});
            adj[rows - 1][col].add(new Integer[]{rows - 2, col + 1});
        }

        for (int row = 1; row < rows - 1; row++) {
            adj[row][0] = new Bag<>();
            adj[row][0].add(new Integer[]{row - 1, 0});
            adj[row][0].add(new Integer[]{row + 1, 0});
            adj[row][0].add(new Integer[]{row, 1});
            adj[row][0].add(new Integer[]{row - 1, 1});
            adj[row][0].add(new Integer[]{row + 1, 1});

            adj[row][cols - 1] = new Bag<>();
            adj[row][cols - 1].add(new Integer[]{row - 1, cols - 1});
            adj[row][cols - 1].add(new Integer[]{row + 1, cols - 1});
            adj[row][cols - 1].add(new Integer[]{row, cols - 2});
            adj[row][cols - 1].add(new Integer[]{row - 1, cols - 2});
            adj[row][cols - 1].add(new Integer[]{row + 1, cols - 2});
        }

        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < cols - 1; col++) {
                adj[row][col] = new Bag<>();
                adj[row][col].add(new Integer[]{row - 1, col});
                adj[row][col].add(new Integer[]{row + 1, col});
                adj[row][col].add(new Integer[]{row, col - 1});
                adj[row][col].add(new Integer[]{row, col + 1});
                adj[row][col].add(new Integer[]{row + 1, col + 1});
                adj[row][col].add(new Integer[]{row - 1, col + 1});
                adj[row][col].add(new Integer[]{row + 1, col - 1});
                adj[row][col].add(new Integer[]{row - 1, col - 1});
            }
        }

        Queue<String> q = new Queue<>();

        for (int i = 0; i < n; i++) {
            String pat = dict[i];
            int len = pat.length();
            if (len < 3 || len > rows * cols * 2) continue;
            char c = pat.charAt(0);

            for (int row = 0; row < rows; row++) {
                boolean mark = false;

                for (int col = 0; col < cols; col++) {

                    if (c == board.getLetter(row, col)) {
                        int d;

                        if (c == 'Q') {
                            if (pat.charAt(1) != 'U') continue;
                            else d = 2;
                        } else d = 1;

                        boolean[][] marked = new boolean[rows][cols];
                        d = dfs(board, row, col, adj, marked, pat, d);
                        if (d == len) {
                            q.enqueue(pat);
                            mark = true;
                            break;
                        }
                    }
                }
                if (mark) break;
            }
        }

        return q;
    }

    private int dfs(BoggleBoard board, int row, int col, Bag<Integer[]>[][] adj, boolean[][] marked, String pat, int d) {
        if (d == pat.length()) return d;

        marked[row][col] = true;
        int t = d;

        for (Integer[] a : adj[row][col]) {
            int r = a[0];
            int c = a[1];

            char ch = board.getLetter(r, c);

            if (!marked[r][c] && pat.charAt(d) == ch) {
                if (ch == 'Q') {
                    if (pat.length() - 1 == d) break;
                    if (pat.charAt(d + 1) == 'U') t = dfs(board, r, c, adj, marked, pat, d + 2);
                    else break;
                } else
                    t = dfs(board, r, c, adj, marked, pat, d + 1);

                if (t == pat.length()) return t;
            }
        }
        marked[row][col] = false;
        return t;
    }

    private Iterable<String> rowsIs1(BoggleBoard board) {
        StringBuilder txt1 = new StringBuilder();
        StringBuilder txt2 = new StringBuilder();
        int cols = board.cols();
        for (int col = 0; col < cols; col++) {
            char c = board.getLetter(0, col);
            txt1.append(c);
            txt2.insert(0, c);

            if (c == 'Q') {
                txt1.append('U');
                txt2.append('U');
            }
        }

//        Queue<String> q = new Queue<>();

        HashSet<String> q = new HashSet<>();

        boyerMoore(txt1.toString(), q);
        boyerMoore(txt2.toString(), q);

        return q;
    }

    private Iterable<String> colsIs1(BoggleBoard board) {
        StringBuilder txt1 = new StringBuilder();
        StringBuilder txt2 = new StringBuilder();

        int rows = board.rows();
        for (int row = 0; row < rows; row++) {
            char c = board.getLetter(row, 0);
            txt1.append(c);
            txt2.insert(0, c);

            if (c == 'Q') {
                txt1.append('U');
                txt2.append('U');
            }
        }

//        Queue<String> q = new Queue<>();

        HashSet<String> q = new HashSet<>();

        boyerMoore(txt1.toString(), q);
        boyerMoore(txt2.toString(), q);

        return q;
    }

    private void boyerMoore(String txt, HashSet<String> q) {
        int N = txt.length();

        for (int x = 0; x < n; x++) {
            String pat = dict[x];
            if (pat.length() < 3) continue;

            int M = pat.length();
            int[] right = new int[256];
            for (int c = 0; c < 256; c++) right[c] = -1;
            for (int i = 0; i < M; i++) right[pat.charAt(i)] = i;

            int skip;
            for (int i = 0; i <= N - M; i += skip) {
                skip = 0;
                for (int j = M - 1; j >= 0; j--) {
                    if (pat.charAt(j) != txt.charAt(i + j)) {
                        skip = Math.max(1, j - right[txt.charAt(i + j)]);
                        break;
                    }
                }
                if (skip == 0) {
//                    if (i < n) q.enqueue(pat);
                    q.add(pat);
                    break;
                }
            }
        }
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (binarySearch(word)) {
            int n = word.length();

            if (n == 3 || n == 4) return 1;
            if (n == 5) return 2;
            if (n == 6) return 3;
            if (n == 7) return 5;
            if (n > 7) return 11;
        }

        return 0;
    }

    private boolean binarySearch(String word) {
        int lo = 0, hi = n - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            int cmp = minus(word, dict[mid]);

            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return true;
        }

        return false;
    }

    private int minus(String a, String b) {
        int min = Math.min(a.length(), b.length());

        for (int i = 0; i < min; i++) {
            int cmp = a.charAt(i) - b.charAt(i);
            if (cmp > 0) return 1;
            if (cmp < 0) return -1;
        }

        if (b.length() > a.length()) return -1;
        else if (b.length() < a.length()) return 1;
        return 0;
    }

    public static void main(String[] args) {
        In in = new In("HW_Project\\boggle\\dictionary-yawl.txt");
        String[] dictionary = in.readAllStrings();
        BoggleSolverV_1 solver = new BoggleSolverV_1(dictionary);
        BoggleBoard board = new BoggleBoard("HW_Project\\boggle\\board-pneumonoultramicroscopicsilicovolcanoconiosis.txt");

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
