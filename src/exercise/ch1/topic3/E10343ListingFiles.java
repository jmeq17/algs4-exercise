package exercise.ch1.topic3;

/**
 * Listing files. A folder is homework.a list of files and folders. Write homework.a program that takes the
 * name of homework.a folder as homework.a command-line argument and prints out all of the files contained
 * in that folder, with the contents of each folder recursively listed (indented) under that
 * folder’s name. Hint : Use homework.a queue, and see java.io.File.
 */

import edu.princeton.cs.algs4.StdOut;

import java.io.File;

public class E10343ListingFiles {
    public static void ListingFiles(File folder) {
        File[] s = folder.listFiles();
        for (File i : s) {
            if (i.isDirectory()) ListingFiles(i);
            else StdOut.println(i.getName());
        }
    }

    public static void main(String[] args) {
        File folder = new File("F:\\aaa");

        ListingFiles(folder);
    }
}
