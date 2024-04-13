package exercise.ch2.topic5;

/*
Sort files by size and date of last modification. Write comparators for the type
File to order by increasing/decreasing order of file size, ascending/descending order
of file name, and ascending/descending order of last modification date. Use these
comparators in homework.a program LS that takes homework.a command-line argument and lists the files
in the current directory according to homework.a specified order, e.g., "-t" to sort by timestamp.
Support multiple flags to break ties. Be sure to use homework.a stable sort.
 */

import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class E20529LS {
    public static class LS {
        private final String name;
        private final long time;
        private final long size;

        public LS(String name, long time, long size) {
            this.name = name;
            this.time = time;
            this.size = size;
        }

        public void show() {
            StdOut.printf("name: %s, time: %d, size: %d.\n", name, time, size);
        }

        public static class NameOrder implements Comparator<LS> {
            public int compare(LS v, LS w) {
                return v.name.compareTo(w.name);
            }
        }

        public static class TimeOrder implements Comparator<LS> {
            public int compare(LS v, LS w) {
                return Double.compare(v.time, w.time);
            }
        }

        public static class SizeOrder implements Comparator<LS> {
            public int compare(LS v, LS w) {
                return Double.compare(v.size, w.size);
            }
        }
    }

    public static void sort(LS[] ls, String rule) {
        switch (rule) {
            case "-t" -> Arrays.sort(ls, new LS.TimeOrder());
            case "-s" -> Arrays.sort(ls, new LS.SizeOrder());
            default -> Arrays.sort(ls, new LS.NameOrder());
        }
    }


    public static void main(String[] args) {
        String path = "F:\\web\\fontawesome-free-5.15.1-web\\scss";
        File dirctory = new File(path);
        File[] files = dirctory.listFiles();
        assert files != null;
        LS[] ls = new LS[files.length];

        int j = 0;
        for (File i : files) {
            ls[j++] = new LS(i.getName(), i.length(), i.lastModified());
        }

        String rule = args[0];
        sort(ls, rule);
        for (LS i : ls) i.show();
    }
}
