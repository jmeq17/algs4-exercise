package homework.partI.week1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Percolation1 {
    private final Node[] id;
    // The size of array.
    private final int n;
    // number of sites open
    private int m;
    // The size of id[].
    private final int size;

    private static class Node {
        int val;
        int sz = 1;
        boolean isOpen = false;

        public Node(int val) {
            this.val = val;
        }
    }

    // create n-by-n grid, with all sites initially blocked
    public Percolation1(int n) {
        if (n <= 0) throw new IllegalArgumentException("The n must greater than 0.");

        this.n = n;
        this.m = 0;
        // The size of id[].
        this.size = n * n + 2;
        id = new Node[size];

        for (int i = 0; i < size; i++) id[i] = new Node(i);

        id[0].isOpen = true;
        id[size - 1].isOpen = true;
    }

    private void union(int row1, int col1, int row2, int col2) {
        union((row1 - 1) * n + col1, (row2 - 1) * n + col2);
    }

    private void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        if (id[pRoot].sz < id[qRoot].sz) {
            id[pRoot].val = qRoot;
            id[qRoot].sz += id[pRoot].sz;
        } else {
            id[qRoot].val = pRoot;
            id[pRoot].sz += id[qRoot].sz;
        }
    }

    private int find(int p) {
        while (p != id[p].val) {
            p = id[p].val;
        }
        return p;
    }

    private void full(int row, int col) {
        if (n == 1) {
            union(0, 1);
            union(1, 2);
            return;
        }

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

            if (row == 1) {
                union(0, col);
                if (isOpen(2, col))
                    union(row, col, 2, col);

            } else {
                if (isOpen(n - 1, col))
                    union(row, col, n - 1, col);
                if (isFull(row, col)) union((n - 1) * n + col, size - 1);
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
    }

    // opens the site (row, col) if it is blocked
    public void open(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException("The row and col must greater than 0 and less than n + 1.");

        if (isOpen(row, col)) return;
        id[(row - 1) * n + col].isOpen = true;
        full(row, col);
        m++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException("The row and col must greater than 0 and less than n + 1.");

        return id[(row - 1) * n + col].isOpen;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException("The row and col must greater than 0 and less than n + 1.");
        return find(0) == find((row - 1) * n + col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return m;
    }

    // does the system percolate?
    public boolean percolates() {
        return find(0) == find(size - 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 100;
        Percolation1 a = new Percolation1(n);

        while (!a.percolates()) {
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);
            if (!a.isOpen(row, col)) a.open(row, col);
        }
        StdOut.println(a.numberOfOpenSites());
        StdOut.println("p = " + (double) a.numberOfOpenSites() / (n * n));
    }
}
