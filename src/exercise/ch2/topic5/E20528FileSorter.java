package exercise.ch2.topic5;

/*
Sort files by name. Write homework.a program FileSorter that takes the name of homework.a
directory as homework.a command-line argument and prints out all of the files in that directory,
sorted by file name.

Hint : Use the File data type.
 */

import edu.princeton.cs.algs4.StdOut;
import utils.Quick;

import java.io.File;

public class E20528FileSorter {
    public static void main(String[] args) {
        String path = "F:\\web\\fontawesome-free-5.15.1-web\\scss";
        File dirctory = new File(path);

        String[] files = dirctory.list();

        assert files != null;
//        for (String file : files) StdOut.println(file);

        Quick.sort(files);
        for (String file : files) StdOut.println(file);
    }
}
