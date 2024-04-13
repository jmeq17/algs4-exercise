package ch3.topic5;

import utils.SparseVector;

public class SparseMatrix {
    private final SparseVector[] st;
    // The row must be specified, but not to col.
    private final int row;
    private int col;

    private final boolean isDynamic;

    public SparseMatrix(int row) {
        this.isDynamic = true;
        this.row = row;
        st = new SparseVector[row];
        for (int i = 0; i < row; i++) st[i] = new SparseVector();
    }

    public SparseMatrix(int row, int col) {
        this.isDynamic = false;
        this.row = row;
        this.col = col;
        st = new SparseVector[row];
        for (int i = 0; i < row; i++) st[i] = new SparseVector(col);
    }

    public int sizeRow() {
        return row;
    }

    public int sizeCol() {
        return col;
    }

    public int size() {
        return row * col;
    }

    public int nnz() {
        int sum = 0;
        for (int i = 0; i < row; i++) sum += st[i].nnz();
        return sum;
    }

    public void put(int x, int y, double val) {
        if (isDynamic) {
            if (x < 0 || x >= row) throw new IllegalArgumentException();
            st[x].put(y, val);
            if (y > col) col = y;
        } else {
            if (x < 0 || x >= row || y < 0 || y >= col) throw new IllegalArgumentException();
            st[x].put(y, val);
        }
    }

    public double get(int x, int y) {
        if (x < 0 || x >= row || y < 0 || y >= col) throw new IllegalArgumentException();
        return st[x].get(y);
    }

    public SparseMatrix plus(SparseMatrix that) {
        if (this.row != that.row || this.col != that.col)
            throw new IllegalArgumentException("The size of that is different from this.");

        SparseMatrix third = new SparseMatrix(row, col);
        double key;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                key = this.get(i, j) + that.get(i, j);
                if (key != 0) third.put(i, j, key);
            }
        }
        return third;
    }

    public SparseVector times(SparseVector that) {
        if (this.col != that.dim())
            throw new IllegalArgumentException("The size of that is different from this.");

        SparseVector third = new SparseVector(row);
        for (int i = 0; i < row; i++) {
            double sum = 0;
            for (int j = 0; j < col; j++)
                sum += this.get(i, j) * that.get(j);
            if (sum != 0) third.put(i, sum);
        }
        return third;
    }

    public SparseMatrix times(SparseMatrix that) {
        if (this.row != that.col || this.col != that.row)
            throw new IllegalArgumentException("The size of that is different from this.");

        SparseMatrix third = new SparseMatrix(row);
        for (int i = 0; i < row; i++) {
            double sum = 0;
            for (int j = 0; j < col; j++)
                sum += this.get(i, j) * that.get(j, i);
            if (sum != 0) third.put(i, i, sum);
        }
        return third;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("row = " + row + ", " + "col = " + col + ", nonzeros = " + nnz() + "\n");
        for (int i = 0; i < row; i++) {
            s.append(i).append(": ").append(st[i]).append("\n");
        }
        return s.toString();
    }
}
