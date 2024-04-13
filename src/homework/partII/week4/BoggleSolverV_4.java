package homework.partII.week4;

import edu.princeton.cs.algs4.*;

/**
 * This class implements boggle by TrieST in-site implemented.
 */

public class BoggleSolverV_4 {
    private static final int A = 65;
    private static final int Q_MINUX_A = 16;
    private static final int U_MINUX_A = 20;

    private final String[] dict;
    private final Node root;

    //    private HW.PartII.Week4.Bag[] adj;
    private int[][] adj;
    //    private int[] diff;
    private char[] chs;
    private int rows, cols;
    //    private boolean[] markedD;
    private final int[] markedD;
    private int markedCur = 0;
    private boolean[] marked;

    private static class Node {
        private int val;
        //        private int marked = 0;
        private final Node[] next = new Node[26];
    }

    public BoggleSolverV_4(String[] dictionary) {
        this.dict = dictionary.clone();
        int len = dict.length;
        markedD = new int[len];

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

    private boolean isValidDice(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    private void initMarkedD() {
        for (int i = 0; i < markedD.length; i++)
            markedD[i] = markedD[i] >>> 31;

        markedCur = 0;
    }

    public Iterable<String> getAllValidWords(BoggleBoard board) {
        int a = board.rows();
        int b = board.cols();
        int rc = b * a;

        if (rows != a || rows != b) {
            rows = a;
            cols = b;
            adj = new int[rc][8];
            marked = new boolean[rc];

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    int v = row * cols + col;
                    int count = 0;

                    if (isValidDice(row - 1, col)) adj[v][count++] = ((row - 1) * cols + col) + 1;
                    if (isValidDice(row + 1, col)) adj[v][count++] = ((row + 1) * cols + col) + 1;
                    if (isValidDice(row, col - 1)) adj[v][count++] = (row * cols + col - 1) + 1;
                    if (isValidDice(row, col + 1)) adj[v][count++] = (row * cols + col + 1) + 1;
                    if (isValidDice(row - 1, col - 1)) adj[v][count++] = ((row - 1) * cols + col - 1) + 1;
                    if (isValidDice(row - 1, col + 1)) adj[v][count++] = ((row - 1) * cols + col + 1) + 1;
                    if (isValidDice(row + 1, col - 1)) adj[v][count++] = ((row + 1) * cols + col - 1) + 1;
                    if (isValidDice(row + 1, col + 1)) adj[v][count] = ((row + 1) * cols + col + 1) + 1;
                }
            }
        }

        chs = new char[rc];
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                chs[row * cols + col] = board.getLetter(row, col);

//        diff = new int[]{-cols - 1, -cols, -cols + 1, -1, 1, cols - 1, cols, cols + 1};

//        for (int row = 0; row < rows; row++) {
//            for (int col = 0; col < cols; col++) {
//                int v = row * cols + col;
//                adj[v] = new HW.PartII.Week4.Bag();
//                chs[v] = board.getLetter(row, col);
//                if (isValidDice(row - 1, col)) adj[v].add((row - 1) * cols + col);
//                if (isValidDice(row + 1, col)) adj[v].add((row + 1) * cols + col);
//                if (isValidDice(row, col - 1)) adj[v].add(row * cols + col - 1);
//                if (isValidDice(row, col + 1)) adj[v].add(row * cols + col + 1);
//                if (isValidDice(row - 1, col - 1)) adj[v].add((row - 1) * cols + col - 1);
//                if (isValidDice(row - 1, col + 1)) adj[v].add((row - 1) * cols + col + 1);
//                if (isValidDice(row + 1, col - 1)) adj[v].add((row + 1) * cols + col - 1);
//                if (isValidDice(row + 1, col + 1)) adj[v].add((row + 1) * cols + col + 1);
//            }
//        }

        Queue<String> queue = new Queue<>();

//        markedD = new boolean[dict.length];

//        markedD++;

        markedCur++;

        for (int v = 0; v < rc; v++) {
            dfs(root, v, 0, queue);
//            dfs(v, queue);
        }

        if (markedCur == Integer.MAX_VALUE) initMarkedD();

        return queue;
    }

    private void dfs(Node x, int v, int d, Queue<String> queue) {
        if (x == null) return;
        int c = chs[v] - A;
        if (chs[v] == 'Q') {
            if (x.next[Q_MINUX_A] == null || x.next[Q_MINUX_A].next[U_MINUX_A] == null) return;
            x = x.next[Q_MINUX_A];
            c = U_MINUX_A;
            d++;
        }

        marked[v] = true;

        Node tem = x.next[c];
//        if (tem != null && tem.val != 0 && !markedD[tem.val - 1] && d > 1) {
//            queue.enqueue(dict[tem.val - 1]);
//            markedD[tem.val - 1] = true;
//        }
        if (tem != null && tem.val != 0 && markedD[tem.val - 1] < markedCur && d > 1) {
            queue.enqueue(dict[tem.val - 1]);
            markedD[tem.val - 1] = markedCur;
        }
//        if (tem != null && tem.val != 0 && !tem.marked && d > 1) {
//            queue.enqueue(dict[tem.val - 1]);
//            tem.marked = true;
//        }

//        for (Object t : adj[v]) {
//            int w = (int) t;
//            if (!marked[w]) dfs(x.next[c], w, d + 1, queue);
//        }
        for (int w : adj[v]) {
            if (w != 0 && !marked[w - 1]) {
                dfs(x.next[c], w - 1, d + 1, queue);
            }
        }

        marked[v] = false;
    }

    private void dfs(int i, Queue<String> queue) {
        Stack<Integer> stack = new Stack<>();
        Stack<Node> node = new Stack<>();
        stack.push(i);
        node.push(root);

        int d = 0;

        while (!stack.isEmpty()) {
            int v = stack.pop();
            Node x = node.pop();


            int c = chs[v] - A;
            if (chs[v] == 'Q') {
                if (x.next[Q_MINUX_A] == null || x.next[Q_MINUX_A].next[U_MINUX_A] == null) return;
                x = x.next[Q_MINUX_A];
                c = U_MINUX_A;
                d++;
            }

            if (x.next[c] == null) {
                continue;
            }

            marked[v] = true;

//            Node tem = x.next[c];
//            if (tem != null && tem.val != 0 && !markedD[tem.val - 1] && d > 1) {
//                queue.enqueue(dict[tem.val - 1]);
//                markedD[tem.val - 1] = true;
//            }

            Node tem = x.next[c];
//            if (tem.val != 0 && !markedD[tem.val - 1] && d > 1) {
//                queue.enqueue(dict[tem.val - 1]);
//                markedD[tem.val - 1] = true;
//            }
//            if (tem != null && tem.val != 0 && tem.marked < markedD && d > 1) {
//                queue.enqueue(dict[tem.val - 1]);
//                tem.marked = markedD;
//            }
//            if (tem != null && tem.val != 0 && tem.marked < markedD && d > 1) {
//                queue.enqueue(dict[tem.val - 1]);
//                tem.marked = markedD;
//            }
            if (tem != null && tem.val != 0 && markedD[tem.val - 1] < markedCur && d > 1) {
                queue.enqueue(dict[tem.val - 1]);
                markedD[tem.val - 1] = markedCur;
            }

            for (int w : adj[v]) {
                if (w != 0 && !marked[w - 1]) {
                    stack.push(w - 1);
                    node.push(x.next[c]);
                }
            }

            d++;
        }
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

    public static void main(String[] args) {
        In in = new In("HW_Project\\boggle\\dictionary-yawl.txt");
        String[] dictionary = in.readAllStrings();
        BoggleSolverV_4 solver = new BoggleSolverV_4(dictionary);
        BoggleBoard board = new BoggleBoard("HW_Project\\boggle\\test.txt");

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
//        HW.PartII.Week4.BoggleSolverV_4 solver = new HW.PartII.Week4.BoggleSolverV_4(dictionary);
//        solver.scoreOf("XI");
//        StdOut.println(solver.scoreOf("VALDES"));
//    }
}
