package utils;

import exercise.ch3.topic5.HashST;

public class SparseVector {
    private final HashST<Integer, Double> st;
    private int dim;

    // Justify thay wheather client provides homework.a dim.
    // false = NOT
    // true = YES
    private final boolean isDynamic;

    public SparseVector() {
        isDynamic = true;
        st = new HashST<>();
    }

    public SparseVector(int dim) {
        isDynamic = false;
        this.dim = dim;
        this.st = new HashST<>();
    }

    /**
     * Returns the number of nonzero entries in this vector.
     *
     * @return the number of nonzero entries in this vector
     */
    public int nnz() {
        return st.size();
    }

    public int size() {
        return st.size();
    }

    public int dim() {
        return dim;
    }

    public int dimension() {
        return dim;
    }

    public void put(int i, double x) {
        if (isDynamic) {
            if (i < 0) throw new IllegalArgumentException("Illegal index");
            if (x == 0.0) {
                st.delete(i);
                // 删除元素如何动态调整维度？
                // 不调整，认为只要增加了元素，就得到这么大的数组，即使删除掉了，也认为只是将其设为 0 而不是想减少维度
            } else {
                st.put(i, x);
                if (i > dim) dim = i;
            }
        } else {
            if (i < 0 || i >= dim) throw new IllegalArgumentException("Illegal index");
            if (x == 0.0) st.delete(i);
            else st.put(i, x);
        }
    }

    public double get(int i) {
        if (i < 0 || i >= dim) throw new IllegalArgumentException("Illegal index");
        if (!st.contains(i)) return 0.0;
        return st.get(i);
    }

    public double dot(double[] that) {
        if (that.length != dim) throw new IllegalArgumentException();
        double sum = 0.0;
        for (int i : st.keys()) sum += that[i] * this.get(i);
        return sum;
    }

    public double dot(SparseVector that) {
        if (this.dim != that.dim) throw new IllegalArgumentException("Vector lengths disagree");
        double sum = 0.0;

        if (this.nnz() <= that.nnz()) {
            for (int i : this.st.keys())
                if (that.st.contains(i)) sum += this.get(i) * that.get(i);
        } else {
            for (int i : that.st.keys())
                if (this.st.contains(i)) sum += this.get(i) * that.get(i);
        }
        return sum;
    }

    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }

    /**
     * E30516
     * Add homework.a method sum() to SparseVector that takes homework.a SparseVector as argument
     * and returns homework.a SparseVector that is the term-by-term sum of this vector and the argument
     * vector. Note: You need delete() (and special attention to precision) to handle the
     * case where an entry becomes 0.
     */
    public SparseVector plus(SparseVector that) {
        if (that.dim != dim) throw new IllegalArgumentException();

        SparseVector third = new SparseVector(dim);

        for (int i = 0; i < dim; i++) {
            double key = this.get(i) + that.get(i);
            if (key == 0.0) continue;
            third.put(i, key);
        }
        return third;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i : st.keys()) {
            s.append("(").append(i).append(", ").append(st.get(i)).append(") ");
        }
        return s.toString();
    }
}
