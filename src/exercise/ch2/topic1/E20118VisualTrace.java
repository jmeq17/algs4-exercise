package exercise.ch2.topic1;

/*
Visual trace. Modify your solution to the previous exercise to make Insertion,
Selection and Shell produce visual traces such as those depicted in this section. Hint :
Judicious use of setYscale() makes this problem easy. Extra credit : Add the code necessary
to produce red and gray color accents such as those in our figures.
 */

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import exercise.ch2.classForExercise.ShellForVisual;

public class E20118VisualTrace {
    public static void main(String[] args) {
        int N = 20;
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();
        }

        StdDraw.setCanvasSize(800, 800);
        StdDraw.setYscale(0, 20 * 1.1);

//        SelectionForVisual.sort(homework.a);
//        InsertionForVisual.sort(homework.a);
        ShellForVisual.sort(a);
    }
}
