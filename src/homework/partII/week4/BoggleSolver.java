package homework.partII.week4;

import edu.princeton.cs.algs4.Queue;

public class BoggleSolver {
    private static final int A = 65;
    private static final int U_MINUX_A = 20;

    private final Node root;
    private final String[] dict;
    private BoggleBoard board;
    private int rows, cols;
    private int markedCur = 0;
    private final int[] markedDict;
    private boolean[][] marked;

    private static class Node {
        private int val;
        private final Node[] next = new Node[26];
    }

    public BoggleSolver(String[] dictionary) {
        this.dict = dictionary.clone();
        int len = dict.length;
        markedDict = new int[len];

        root = new Node();

        for (int i = 0; i < len; i++) {
            Node x = root;
            String key = dict[i];
            int l = key.length();

            for (int j = 0; j < l; j++) {
                int c = key.charAt(j) - A;
                if (x.next[c] == null) {
                    x.next[c] = new Node();
                }
                x = x.next[c];
            }
            x.val = i + 1;
        }
    }

    private void initMarkedD() {
        for (int i = 0; i < markedDict.length; i++)
            markedDict[i] = markedDict[i] >>> 31;
        markedCur = 0;
    }

    public Iterable<String> getAllValidWords(BoggleBoard board) {
        rows = board.rows();
        cols = board.cols();
        this.board = board;
        marked = new boolean[rows][cols];

        Queue<String> queue = new Queue<>();

        markedCur++;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dfs(root.next[board.getLetter(r, c) - A], r, c, 0, queue);
            }
        }

        if (markedCur == Integer.MAX_VALUE) initMarkedD();

        return queue;
    }

    private void dfs(Node x, int r, int c, int d, Queue<String> queue) {
        if (x == null || marked[r][c]) return;
        if (board.getLetter(r, c) == 'Q') {
            if (x.next[U_MINUX_A] == null) return;
            x = x.next[U_MINUX_A];
            d++;
        }

        marked[r][c] = true;

        if (x.val != 0 && markedDict[x.val - 1] < markedCur && d > 1) {
            queue.enqueue(dict[x.val - 1]);
            markedDict[x.val - 1] = markedCur;
        }

        if (r - 1 >= 0 && c - 1 >= 0) dfs(x.next[board.getLetter(r - 1, c - 1) - A], r - 1, c - 1, d + 1, queue);
        if (r - 1 >= 0) dfs(x.next[board.getLetter(r - 1, c) - A], r - 1, c, d + 1, queue);
        if (r - 1 >= 0 && c + 1 < cols) dfs(x.next[board.getLetter(r - 1, c + 1) - A], r - 1, c + 1, d + 1, queue);
        if (c - 1 >= 0) dfs(x.next[board.getLetter(r, c - 1) - A], r, c - 1, d + 1, queue);
        if (c + 1 < cols) dfs(x.next[board.getLetter(r, c + 1) - A], r, c + 1, d + 1, queue);
        if (r + 1 < rows && c - 1 >= 0) dfs(x.next[board.getLetter(r + 1, c - 1) - A], r + 1, c - 1, d + 1, queue);
        if (r + 1 < rows) dfs(x.next[board.getLetter(r + 1, c) - A], r + 1, c, d + 1, queue);
        if (r + 1 < rows && c + 1 < cols) dfs(x.next[board.getLetter(r + 1, c + 1) - A], r + 1, c + 1, d + 1, queue);

        marked[r][c] = false;
    }

    private boolean contains(String word) {
        int len = word.length();
        Node x = root;

        for (int i = 0; i < len; i++) {
            if (x == null) return false;
            x = x.next[word.charAt(i) - A];
        }

        if (x == null) return false;

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
}
