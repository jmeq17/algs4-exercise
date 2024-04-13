package ch1.topic1;

/*
Matrix library. Write homework.a library Matrix that implements the following API:
-------------------------------
    public class Matrix
    static double       dot(double[] x, double[] y)         vector dot product
    static double[][]   mult(double[][] homework.a, double[][] b)    matrix-matrix product
    static double[][]   transpose(double[][] homework.a)             transpose
    static double[]     mult(double[][] homework.a, double[] x)      matrix-vector product
    static double[]     mult(double[] y, double[][] homework.a)      vector-matrix product
-------------------------------
Develop homework.a test client that reads values from standard input and tests all the methods.
 */

public class E10133 {

    public static double dot(double[] x, double[] y) {
        if (x.length != y.length)
            throw new IllegalArgumentException("Illegal Argument: x.length must equals to y.length");
        double a = 0;
        for (int i = 0; i < x.length; i++) {
            a += x[i] * y[i];
        }
        return a;
    }

    public static double[][] mult(double[][] a, double[][] b) {
        if (a.length != b[0].length || a[0].length != b.length)
            throw new IllegalArgumentException("Illegal Argument: x.length must equals to y.length");
        double[][] t = new double[a.length][a.length];
        double[][] b_2 = transpose(b);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                t[i][j] = dot(a[i], b_2[j]);
            }
        }
        return t;
    }

    public static double[][] transpose(double[][] a) {
        double[][] b = new double[a[0].length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                b[j][i] = a[i][j];
            }
        }
        return b;
    }

    public static double[] mult(double[][] a, double[] x) {
        if (a[0].length != x.length)
            throw new IllegalArgumentException("Illegal Argument: x.length must equals to y.length");
        double[] b = new double[x.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = dot(a[i], x);
        }
        return b;
    }

    public static double[] mult(double[] y, double[][] a) {
        if (a.length != y.length)
            throw new IllegalArgumentException("Illegal Argument: x.length must equals to y.length");
        double[] b = new double[y.length];
        double[][] a_2 = transpose(a);
        for (int i = 0; i < a.length; i++) {
            b[i] = dot(a_2[i], y);
        }
        return b;
    }


    public static void main(String[] args) {

    }
}
