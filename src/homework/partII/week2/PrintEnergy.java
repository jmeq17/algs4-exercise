package homework.partII.week2;

/******************************************************************************
 *  Compilation:  javac HW.PartII.Week2.PrintEnergy.java
 *  Execution:    java HW.PartII.Week2.PrintEnergy input.png
 *  Dependencies: HW.PartII.Week2.SeamCarver.java
 * <p>
 *
 *  Read image from file specified as command line argument. Print energy
 *  of each pixel as calculated by HW.PartII.Week2.SeamCarver object.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

public class PrintEnergy {

    public static void main(String[] args) {
        Picture picture = new Picture("HW_Project\\seam\\6x5.png");
        StdOut.printf("image is %d pixels wide by %d pixels high.\n", picture.width(), picture.height());

        SeamCarver sc = new SeamCarver(picture);

        StdOut.printf("Printing energy calculated for each pixel.\n");

        for (int row = 0; row < sc.height(); row++) {
            for (int col = 0; col < sc.width(); col++)
                StdOut.printf("%9.0f ", sc.energy(col, row));
            StdOut.println();
        }
    }
}
