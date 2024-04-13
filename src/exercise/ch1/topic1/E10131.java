package exercise.ch1.topic1;

/*
Random connections. Write homework.a program that takes as command-line arguments
an integer N and homework.a double value p (between 0 and 1), plots N equally spaced dots of size
.05 on the circumference of homework.a circle, and then, with probability p for each pair of points,
draws homework.a gray line connecting them.
 */

/*
Thanks to Rene Argento (https://github.com/reneargento)
 */

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class E10131 {

    public static void draw(int N, double p) {
        StdDraw.setScale(-10, 10);  // 20
        StdDraw.setPenRadius(0.05); // 0.6

        double[][] a = new double[N][2];

        for (int i = 0; i < N; i++) {
            a[i][0] = Math.cos(2 * Math.PI * i / N) * 8;
            a[i][1] = Math.sin(2 * Math.PI * i / N) * 8;
            StdDraw.point(a[i][0], a[i][1]);
        }

        StdDraw.setPenRadius();

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (StdRandom.bernoulli(p)) StdDraw.line(a[i][0], a[i][1], a[j][0], a[j][1]);
            }
        }
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        double p = StdIn.readDouble();
        draw(N, p);
    }
}
