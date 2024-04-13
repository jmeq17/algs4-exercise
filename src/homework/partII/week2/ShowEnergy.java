package homework.partII.week2; /******************************************************************************
 *  Compilation:  javac HW.PartII.Week2.ShowEnergy.java
 *  Execution:    java HW.PartII.Week2.ShowEnergy input.png
 *  Dependencies: HW.PartII.Week2.SeamCarver.java HW.PartII.Week2.SCUtility.java
 *
 *
 *  Read image from file specified as command line argument. Show original
 *  image (only useful if image is large enough).
 *
 ******************************************************************************/


import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

public class ShowEnergy {

    public static void main(String[] args) {
        Picture picture = new Picture("HW_Project\\seam\\HJocean.png");
        StdOut.printf("image is %d columns by %d rows\n", picture.width(), picture.height());
        picture.show();
        SeamCarver sc = new SeamCarver(picture);

        StdOut.printf("Displaying energy calculated for each pixel.\n");
        SCUtility.showEnergy(sc);

    }

}
