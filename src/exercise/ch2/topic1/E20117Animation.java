package exercise.ch2.topic1;

/*
Animation. Add code to Insertion, Selection and Shell to make them
draw the array contents as vertical bars like the visual traces in this section, redrawing
the bars after each pass, to produce an animated effect, ending in homework.a “sorted” picture
where the bars appear in order of their height. Hint : Use homework.a client like the one in the text
that generates random Double values, insert calls to show() as appropriate in the sort
code, and implement homework.a show() method that clears the canvas and draws the bars.
 */

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import exercise.ch2.classForExercise.ShellForAnimation;

public class E20117Animation {
    public static void main(String[] args) {
        int N = 20;
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();
        }

        // 高度越矮，越看不出画图的过程
        StdDraw.setCanvasSize(30 * (N + 3), 90);
        StdDraw.setYscale(0, 1.1);

//        SelectionForAnimation.sort(homework.a);
//        InsertionForAnimation.sort(homework.a);
        ShellForAnimation.sort(a);
    }
}
