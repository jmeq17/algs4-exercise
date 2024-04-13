package homework.partI.week1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationWeishanjian {
    private final WeightedQuickUnionUF id;
    /*
        char[] includes five identifiers:
        - b: black
        - white includes four idetifiers:
            - n: connected to n (abstract)
            - o: both not connected to 0 and n
            - f: connedted to 0
     */
    private final char[] isOpen;
    // The length of the matrix.
    private final int n;
    // The number of sites open.
    private int m;
    // The size of id.
    private final int size;

    // create n-by-n grid, with all sites initially blocked
    public PercolationWeishanjian(int n) {
        if (n <= 0) throw new IllegalArgumentException("The n must greater than 0.");

        this.n = n;
        this.m = 0;
        // The size of id[].
        this.size = n * n + 2;
        id = new WeightedQuickUnionUF(size);
        isOpen = new char[size];

        for (int i = 1; i < size - 1; i++)
            isOpen[i] = 'b';
        isOpen[0] = 'f';
        isOpen[size - 1] = 'n';
    }

    private int toIndex(int row, int col) {
        return (row - 1) * n + col;
    }

    private void union(int row1, int col1, int row2, int col2) {
        id.union((row1 - 1) * n + col1, (row2 - 1) * n + col2);
    }

    private void full(int row, int col) {
        if (n == 1) {
            id.union(0, 1);
            id.union(1, 2);
            isOpen[id.find(0)] = 'f';
            return;
        }

        // first line
        if (row == 1) {
            id.union(0, col);
            if (isOpen(row + 1, col)) {
                char t = isOpen[id.find(toIndex(row + 1, col))];
                if (t != 'f') union(row, col, row + 1, col);
                if (t == 'n') id.union(0, size - 1);
            }
            isOpen[id.find(0)] = 'f';
        }

        // last line
        else if (row == n) {
            isOpen[toIndex(row, col)] = 'n';

            if (isOpen(row - 1, col)) {
                char t = isOpen[id.find(toIndex(row - 1, col))];
                union(row, col, row - 1, col);
                if (t == 'f') {
                    id.union(0, size - 1);
                    isOpen[id.find(toIndex(row, col))] = 'f';
                } else isOpen[id.find(toIndex(row, col))] = 'n';

            }
            // left-below
            if (col == 1) {
                if (isOpen(row, col + 1)) {
                    char t = isOpen[id.find(toIndex(row, col + 1))];
                    char r = isOpen[id.find(toIndex(row, col))];

                    union(row, col, row, col + 1);
                    if (t == 'n' && r == 'n') isOpen[id.find(toIndex(row, col))] = 'n';
                    else {
                        id.union(0, size - 1);
                        isOpen[id.find(toIndex(row, col))] = 'f';
                    }
                }
            }
            // right-below
            else if (col == n) {
                if (isOpen(row, col - 1)) {
                    char t = isOpen[id.find(toIndex(row, col - 1))];
                    char r = isOpen[id.find(toIndex(row, col))];

                    union(row, col, row, col - 1);
                    if (t == 'n' && r == 'n') isOpen[id.find(toIndex(row, col))] = 'n';
                    else {
                        id.union(0, size - 1);
                        isOpen[id.find(toIndex(row, col))] = 'f';
                    }
                }
            }
            // middle-below
            else {
                if (isOpen(row, col + 1)) {
                    char t = isOpen[id.find(toIndex(row, col + 1))];
                    char r = isOpen[id.find(toIndex(row, col))];

                    union(row, col, row, col + 1);
                    if (t == 'n' && r == 'n') isOpen[id.find(toIndex(row, col))] = 'n';
                    else {
                        id.union(0, size - 1);
                        isOpen[id.find(toIndex(row, col))] = 'f';
                    }
                }
                if (isOpen(row, col - 1)) {
                    char t = isOpen[id.find(toIndex(row, col - 1))];
                    char r = isOpen[id.find(toIndex(row, col))];

                    union(row, col, row, col - 1);
                    if (t == 'n' && r == 'n') isOpen[id.find(toIndex(row, col))] = 'n';
                    else {
                        id.union(0, size - 1);
                        isOpen[id.find(toIndex(row, col))] = 'f';
                    }
                }
            }
        }
        // left line
        else if (col == 1 || col == n) {
            if (col == 1 && isOpen(row, col + 1)) {
                char t = isOpen[id.find(toIndex(row, col + 1))];
                union(row, col, row, col + 1);
                if (t == 'n') isOpen[id.find(toIndex(row, col))] = 'n';
                else if (t == 'f') isOpen[id.find(toIndex(row, col))] = 'f';
            } else if (col == n && isOpen(row, col - 1)) {
                char t = isOpen[id.find(toIndex(row, col - 1))];
                union(row, col, row, col - 1);
                if (t == 'n') isOpen[id.find(toIndex(row, col))] = 'n';
                else if (t == 'f') isOpen[id.find(toIndex(row, col))] = 'f';
            }

            if (isOpen(row - 1, col)) {
                char t = isOpen[id.find(toIndex(row - 1, col))];
                char r = isOpen[id.find(toIndex(row, col))];

                union(row, col, row - 1, col);
                if (t != 'f' && r != 'f') {
                    if (t == 'n' || r == 'n') isOpen[id.find(toIndex(row, col))] = 'n';
                } else {
                    if (t == 'n' || r == 'n') id.union(0, size - 1);
                    isOpen[id.find(toIndex(row, col))] = 'f';
                }
            }
            if (isOpen(row + 1, col)) {
                char t = isOpen[id.find(toIndex(row + 1, col))];
                char r = isOpen[id.find(toIndex(row, col))];

                union(row, col, row + 1, col);
                if (t != 'f' && r != 'f') {
                    if (t == 'n' || r == 'n') isOpen[id.find(toIndex(row, col))] = 'n';
                } else {
                    if (t == 'n' || r == 'n') id.union(0, size - 1);
                    isOpen[id.find(toIndex(row, col))] = 'f';
                }
            }
        }
        // middle-middle
        else {
            // up
            if (isOpen(row - 1, col)) {
                char t = isOpen[id.find(toIndex(row - 1, col))];
                union(row, col, row - 1, col);

                if (t == 'n') isOpen[id.find(toIndex(row, col))] = 'n';
                else if (t == 'f') isOpen[id.find(toIndex(row, col))] = 'f';
            }
            // down
            if (isOpen(row + 1, col)) {
                char t = isOpen[id.find(toIndex(row + 1, col))];
                char r = isOpen[id.find(toIndex(row, col))];

                union(row, col, row + 1, col);

                if (t != 'f' && r != 'f') {
                    if (t == 'n' || r == 'n') isOpen[id.find(toIndex(row, col))] = 'n';
                } else {
                    if (t == 'n' || r == 'n') id.union(0, size - 1);
                    isOpen[id.find(toIndex(row, col))] = 'f';
                }
            }
            // right
            if (isOpen(row, col + 1)) {
                char t = isOpen[id.find(toIndex(row, col + 1))];
                char r = isOpen[id.find(toIndex(row, col))];

                union(row, col, row, col + 1);
                if (t != 'f' && r != 'f') {
                    if (t == 'n' || r == 'n') isOpen[id.find(toIndex(row, col))] = 'n';
                } else {
                    if (t == 'n' || r == 'n') id.union(0, size - 1);
                    isOpen[id.find(toIndex(row, col))] = 'f';
                }
            }
            // left
            if (isOpen(row, col - 1)) {
                char t = isOpen[id.find(toIndex(row, col - 1))];
                char r = isOpen[id.find(toIndex(row, col))];

                union(row, col, row, col - 1);
                if (t != 'f' && r != 'f') {
                    if (t == 'n' || r == 'n') isOpen[id.find(toIndex(row, col))] = 'n';
                } else {
                    if (t == 'n' || r == 'n') id.union(0, size - 1);
                    isOpen[id.find(toIndex(row, col))] = 'f';
                }
            }
        }
    }

    // opens the site (row, col) if it is blocked
    public void open(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException("The row and col must greater than 0 and less than n + 1.");

        if (isOpen(row, col)) return;
        isOpen[toIndex(row, col)] = 'o';
        full(row, col);
        m++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException("The row and col must greater than 0 and less than n + 1.");

        return isOpen[toIndex(row, col)] != 'b';
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException("The row and col must greater than 0 and less than n + 1.");
        return id.find(0) == id.find((row - 1) * n + col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return m;
    }

    // does the system percolate?
    public boolean percolates() {
        return id.find(0) == id.find(size - 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 100;
        PercolationWeishanjian a = new PercolationWeishanjian(n);

        while (!a.percolates()) {
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);
            if (!a.isOpen(row, col)) a.open(row, col);
        }
        StdOut.println(a.numberOfOpenSites());
        StdOut.println("p = " + (double) a.numberOfOpenSites() / (n * n));
    }
}
