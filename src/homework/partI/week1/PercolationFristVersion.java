package homework.partI.week1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationFristVersion {
    private final Node[][] id;
    // The size of array.
    private final int n;
    // number of sites open
    private int m;

    private static class Node {
        int val;
        int sz = 1;
        boolean isOpen = false;

        public Node(int val) {
            this.val = val;
        }
    }

    // create n-by-n grid, with all sites initially blocked
    public PercolationFristVersion(int n) {
        if (n < 0) throw new IllegalArgumentException("The n must greater than 0.");
        this.n = n;
        this.m = 0;
        id = new Node[n + 1][n + 1];

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                id[i][j] = new Node(j + ((i - 1) * n));
            }
        }
    }

    public void union(int row1, int col1, int row2, int col2) {
        int rootP = find(row1, col1);
        int rootQ = find(row2, col2);

        if (rootP == rootQ) return;

        col1 = rootP % n;
        if (col1 == 0) {
            col1 = n;
            row1 = rootP / n;
        } else row1 = rootP / n + 1;

        col2 = rootQ % n;
        if (col2 == 0) {
            col2 = n;
            row2 = rootQ / n;
        } else row2 = rootQ / n + 1;

        if (id[row1][col1].sz < id[row2][col2].sz) {
            id[row1][col1].val = rootQ;
            id[row2][col2].sz += id[row1][col1].sz;
        } else {
            id[row2][col2].val = rootP;
            id[row1][col1].sz += id[row2][col2].sz;
        }
    }

    public int find(int row, int col) {
        int t = (row - 1) * n + col;
        while (t != id[row][col].val) {
            t = id[row][col].val;
            col = t % n;
            if (col == 0) {
                col = n;
                row = t / n;
            } else row = t / n + 1;
        }
        return t;
    }

    // opens the site (row, col) if it is blocked
    public void open(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException("The row and col must greater than 0 and less than n + 1.");
        id[row][col].isOpen = true;

        if (row == 1 || row == n) {
            if (col == 1) {
                if (isOpen(row, col + 1))
                    union(row, col, row, col + 1);
            } else if (col == n) {
                if (isOpen(row, col - 1))
                    union(row, col, row, col - 1);
            } else {
                if (isOpen(row, col + 1))
                    union(row, col, row, col + 1);
                if (isOpen(row, col - 1))
                    union(row, col, row, col - 1);
            }
            if (row == n) {
                if (isOpen(row - 1, col))
                    union(row, col, row - 1, col);
            } else {
                if (isOpen(row + 1, col))
                    union(row, col, row + 1, col);
            }
        } else if (col == 1) {
            if (isOpen(row + 1, col))
                union(row, col, row + 1, col);
            if (isOpen(row - 1, col))
                union(row, col, row - 1, col);
            if (isOpen(row, col + 1))
                union(row, col, row, col + 1);
        } else if (col == n) {
            if (isOpen(row + 1, col))
                union(row, col, row + 1, col);
            if (isOpen(row - 1, col))
                union(row, col, row - 1, col);
            if (isOpen(row, col - 1))
                union(row, col, row, col - 1);
        } else {
            if (isOpen(row, col - 1))
                union(row, col, row, col - 1);
            if (isOpen(row, col + 1))
                union(row, col, row, col + 1);
            if (isOpen(row + 1, col))
                union(row, col, row + 1, col);
            if (isOpen(row - 1, col))
                union(row, col, row - 1, col);
        }

        m++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException("The row and col must greater than 0 and less than n + 1.");

        return id[row][col].isOpen;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException("The row and col must greater than 0 and less than n + 1.");
        return true;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return m;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++)
                if (find(1, i) == find(n, j)) return true;
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 20;
        PercolationFristVersion a = new PercolationFristVersion(n);

        while (!a.percolates()) {
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);
            if (!a.isOpen(row, col)) a.open(row, col);
        }
        StdOut.println(a.numberOfOpenSites());
        StdOut.println("p = " + (double) a.numberOfOpenSites() / (n * n));
    }
}
