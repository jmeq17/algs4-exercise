package homework.partII.week2;

import edu.princeton.cs.algs4.Picture;

import java.io.File;

public class SCT {


    //  unit testing (optional)
    public static void main(String[] args) {
        File file = new File("HW_Project\\seam\\3x7.png");
        Picture a = new Picture(file);
        SeamCarver sc = new SeamCarver(a);

//        int[] seam = sc.findHorizontalSeam();
//        sc.removeHorizontalSeam(seam);

//        for (int i = 0; i < 9; i++) {
//            int[] seam2 = sc.findVerticalSeam();
//            sc.removeVerticalSeam(seam2);
//            sc.findVerticalSeam();
//        }
//        sc.findVerticalSeam();


        int[] bordseam = {2, 2, 1, 0, 1, 2, 2};

        sc.removeVerticalSeam(bordseam);
    }
}
