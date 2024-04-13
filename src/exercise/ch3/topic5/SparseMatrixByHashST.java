package exercise.ch3.topic5;

// 不如另一个版本好：SparseMatrix

public class SparseMatrixByHashST {
    private final HashST<Integer, Double>[] st;
    private final int row;
    private int col;

    public SparseMatrixByHashST(int row) {
        this.row = row;
        st = new HashST[row];
        for (int i = 0; i < row; i++) {
            st[i] = new HashST<>();
        }
    }

    public SparseMatrixByHashST(HashST<Integer, Double>[] rowVector) {
        row = rowVector.length;
        st = new HashST[row];
        for (int i = 0; i < rowVector.length; i++) {
            for (Integer integer : rowVector[i].keys()) {
                put(i, integer, rowVector[i].get(integer));
            }
        }
    }

    public void put(int x, int y, double val) {
        st[x].put(y, val);
        if (y > col) col = y;
    }

    public int sizeRow() {
        return row;
    }

    public int sizeCol() {
        return col;
    }

    public double get(int x, int y) {
        return st[x].get(y);
    }

    public SparseMatrixByHashST add(SparseMatrixByHashST that) {
        if (this.sizeRow() != that.sizeRow() || this.sizeCol() != that.sizeCol())
            throw new IllegalArgumentException("The size of that is different from this.");

        SparseMatrixByHashST third = new SparseMatrixByHashST(row);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                double key = this.get(i, j) + that.get(i, j);
                if (key != 0) third.put(i, j, key);
            }
        }
        return third;
    }

    public SparseMatrixByHashST multiply(SparseMatrixByHashST that) {
        if (this.sizeRow() != that.sizeCol() || this.sizeCol() != that.sizeRow())
            throw new IllegalArgumentException("The size of that is different from this.");

        SparseMatrixByHashST third = new SparseMatrixByHashST(row);
        double sum = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++)
                sum += this.get(i, j) * that.get(j, i);
            if (sum != 0) third.put(i, i, sum);
        }
        return third;
    }
}
