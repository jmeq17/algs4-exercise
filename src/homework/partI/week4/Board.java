package homework.partI.week4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    private final int[][] tiles;
    private final int n;

    // create homework.a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.n = tiles.length;
        this.tiles = tiles.clone();
        for (int i = 0; i < n; i++) this.tiles[i] = tiles[i].clone();
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int k = 1;
        int num = 0;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (tiles[i][j] != k++) num++;

        if (tiles[n - 1][n - 1] != n * n) num--;

        return num;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == n * i + j + 1 || tiles[i][j] == 0) continue;

                int col = tiles[i][j] % n;
                int row = tiles[i][j] / n + 1;
                if (col == 0) {
                    col = n;
                    row--;
                }
                sum += Math.abs(row - 1 - i);
                sum += Math.abs(col - 1 - j);
            }
        }

        return sum;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < n; i++)
            if (i != n - 1) {
                for (int j = 0; j < n; j++)
                    if (tiles[i][j] != n * i + j + 1) return false;
            } else
                for (int j = 0; j < n - 1; j++)
                    if (tiles[i][j] != n * i + j + 1) return false;

        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;
        if (this == y) return true;
        if (this.n != that.n) return false;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (this.tiles[i][j] != that.tiles[i][j]) return false;

        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int[] zero = findZero();
        assert zero != null;
        int row = zero[0];
        int col = zero[1];

        Queue<Board> queue = new Queue<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == row) {
                    if (j == col - 1)
                        queue.enqueue(exch(i, j, row, col));
                    else if (j == col + 1)
                        queue.enqueue(exch(i, j, row, col));
                } else if (j == col) {
                    if (i == row - 1)
                        queue.enqueue(exch(i, j, row, col));
                    else if (i == row + 1)
                        queue.enqueue(exch(i, j, row, col));
                }
            }
        }

        return queue;
    }

    private int[] findZero() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (tiles[i][j] == 0)
                    return new int[]{i, j};

        return null;
    }

    private Board exch(int i1, int j1, int i2, int j2) {
        // Only clone tiles is unavaiable, because the values of tiles just are reference to actual value.
        int[][] tiles = this.tiles.clone();
        for (int i = 0; i < n; i++) {
            tiles[i] = this.tiles[i].clone();
        }

        int tem = tiles[i1][j1];
        tiles[i1][j1] = tiles[i2][j2];
        tiles[i2][j2] = tem;
        return new Board(tiles);
    }

    // homework.a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        if (tiles[0][0] == 0) return exch(0, 1, 1, 1);
        if (tiles[0][1] == 0) return exch(0, 0, 1, 0);
        return exch(0, 0, 0, 1);
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board board = new Board(tiles);

        StdOut.println(board);
        StdOut.println(board.dimension() + ", Expected: " + n);
        StdOut.println(board.hamming());
        StdOut.println(board.manhattan());
        StdOut.println(board.isGoal());
        StdOut.println(board.equals(board.exch(1, 1, n - 1, n - 1)));
        Iterable<Board> iter = board.neighbors();
        for (Board b : iter) StdOut.println(b);
        StdOut.println(board.twin());
    }
}
