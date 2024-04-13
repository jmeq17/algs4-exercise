package homework.partI.week4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private boolean isSolvable = true;
    private final Node goal;

    private static class Node implements Comparable<Node> {
        Board board;
        Node prev;
        int moves;
        int priority;

        private Node(Board board, Node prev, int moves) {
            this.board = board;
            this.prev = prev;
            this.moves = moves;
            this.priority = board.manhattan() + moves;
        }

        public int compareTo(Node that) {
            return Integer.compare(this.priority, that.priority);
        }
    }

    // find homework.a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();

        Board initSwap = initial.twin();

        MinPQ<Node> pq = new MinPQ<>();
        pq.insert(new Node(initial, null, 0));
        MinPQ<Node> qp = new MinPQ<>();
        qp.insert(new Node(initSwap, null, 0));

        while (!pq.min().board.isGoal()) {
            if (qp.min().board.isGoal()) {
                isSolvable = false;
                goal = null;
                return;
            }

            Node min = pq.delMin();
            for (Board b : min.board.neighbors())
                if (min.prev == null || !b.equals(min.prev.board))
                    pq.insert(new Node(b, min, min.moves + 1));

            min = qp.delMin();
            for (Board b : min.board.neighbors())
                if (min.prev == null || !b.equals(min.prev.board))
                    qp.insert(new Node(b, min, min.moves + 1));
        }
        goal = pq.min();
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable()) return -1;
        return goal.moves;
    }

    // sequence of boards in homework.a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;

        Stack<Board> solution = new Stack<>();
        Node current = goal;

        while (current != null) {
            solution.push(current.board);
            current = current.prev;
        }

        return solution;
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
