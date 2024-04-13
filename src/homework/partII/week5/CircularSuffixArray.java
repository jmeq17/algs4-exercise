package homework.partII.week5;

import edu.princeton.cs.algs4.StdRandom;

public class CircularSuffixArray {
    private final int[] index;
    private final int n;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null) throw new IllegalArgumentException();

        n = s.length();
        index = new int[n];
        for (int i = 0; i < n; i++) index[i] = i;

//        String[] suffix = new String[n];
//        for (int i = 0; i < n; i++) {
//            suffix[i] = s.substring(i, n) + s.substring(0, i);
//            index[i] = i;
//        }

//        MSD_Old.sort(suffix, index);
        MSD.sort(s, index);
//        HW.PartII.Week5.MSD.sort3(s, index);
    }

    // length of s
    public int length() {
        return n;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= n) throw new IllegalArgumentException();
        return index[i];
    }

    // unit testing (required)
//    public static void main(String[] args) {
//        String s = "ABRACADABRA!";
//
////        for (int i = 0; i < s.length; i++) {
////            String[] tem = Arrays.copyOfRange(s, i, s.length);
////            for (String value : tem) System.out.print(value + " ");
////            System.out.println();
////        }
//
//        CircularSuffixArray c = new CircularSuffixArray(s);
//
//        System.out.println();
//    }

//    public static void main(String[] args) {
//        String s = "ABRACADABRA!";
//        CircularSuffixArray c = new CircularSuffixArray(s);
//
//        int n = c.length();
//
//        for (int i = 0; i < n; i++) {
//            int d = c.index(i);
//
//            for (int j = d, k = 0; k < n; k++) {
//                System.out.print(s.charAt(j));
//                j = (j + 1) % n;
//            }
//            System.out.println();
//        }
//
//        System.out.println();
//    }

    // All input test.
//    public static void main(String[] args) {
//        String s = "ABRACADABRA!";
//        HW.PartII.Week5.CircularSuffixArray_RAW c = new HW.PartII.Week5.CircularSuffixArray_RAW(s);
//        int n = c.length();
//
//        System.out.println("i   Original Suffixes    Sorted Suffixes   index[i]");
//        System.out.println("---------------------------------------------------");
//
//        for (int i = 0; i < n; i++) {
//            int d = c.index(i);
//
//            System.out.printf("%-5d", i);
//
//            for (int j = i, k = 0; k < n; k++) {
//                System.out.print(s.charAt(j));
//                j = (j + 1) % n;
//            }
//
//            System.out.print("         ");
//
//            for (int j = d, k = 0; k < n; k++) {
//                System.out.print(s.charAt(j));
//                j = (j + 1) % n;
//            }
//
//            System.out.print("       " + d + "\n");
//        }
//    }

    // index() and length() test
//    public static void main(String[] args) {
//        String s = "CTNRHNJKFGXLVLVVSVTLLKAQWMEYHRQDSVXYBTDXSKWVVBHGCRWUPYOGXJJFSRYTGAIJEJTSHPLTMHDIIMZFQANSZNBFBLQDZXLR";
////        String s = BinaryStdIn.readString();
//        CircularSuffixArray c = new CircularSuffixArray(s);
//        int n = c.length();
//
//        System.out.println("n = " + n);
//
//        System.out.println("i   Original Suffixes    Sorted Suffixes   index[i]");
//        System.out.println("---------------------------------------------------");
//
//        for (int i = 0; i < n; i++) {
//            int d = c.index(i);
//
//            System.out.printf("%-5d", i);
//
//            for (int j = i, k = 0; k < n; k++) {
//                System.out.print(s.charAt(j));
//                j = (j + 1) % n;
//            }
//
//            System.out.print("         ");
//
//            for (int j = d, k = 0; k < n; k++) {
//                System.out.print(s.charAt(j));
//                j = (j + 1) % n;
//            }
//
//            System.out.print("       " + d + "\n");
//        }
//
//        System.out.println("\n" + c.index(0) + ", excepted: 206");
//    }

    // check index() and length() with random extended ASCII strings
    public static void main(String[] args) {
        int num = 20;
        StringBuilder sb = new StringBuilder();

        sb.append((char) StdRandom.uniform(0x00, 0x01));
        sb.append((char) StdRandom.uniform(0xaf, 0xb0));
        sb.append((char) StdRandom.uniform(0x00, 0x01));
        sb.append((char) StdRandom.uniform(0x4e, 0x4f));
        sb.append((char) StdRandom.uniform(0xcb, 0xcc));
        sb.append((char) StdRandom.uniform(0x4d, 0x4e));
        sb.append((char) StdRandom.uniform(0x49, 0x4a));
        sb.append((char) StdRandom.uniform(0xf7, 0xf8));
        sb.append((char) StdRandom.uniform(0x44, 0x45));
        sb.append((char) StdRandom.uniform(0xcd, 0xce));
//        sb.append((char) StdRandom.uniform(0x72, 0x73));
//        sb.append((char) StdRandom.uniform(0x4e, 0x4f));
//        sb.append((char) StdRandom.uniform(0x77, 0x78));
//        sb.append((char) StdRandom.uniform(0x43, 0x44));
//        sb.append((char) StdRandom.uniform(0x72, 0x73));
//        sb.append((char) StdRandom.uniform(0x75, 0x76));

//            for (int i = 0; i < num; i++) sb.append((char) StdRandom.uniform(0, 128));

        String s = sb.toString();
        CircularSuffixArray c = new CircularSuffixArray(s);
        int n = c.length();

        System.out.println("n = " + n);

        System.out.println("i   Original Suffixes    Sorted Suffixes   index[i]");
        System.out.println("---------------------------------------------------");

        for (int i = 0; i < n; i++) {
            int d = c.index(i);

                System.out.printf("%-5d", i);

                for (int j = i, k = 0; k < n; k++) {
                    System.out.printf("%x ", (int) s.charAt(j));
                    j = (j + 1) % n;
                }

                System.out.print("         ");

            for (int j = d, k = 0; k < n; k++) {
                System.out.printf("%x ", (int) s.charAt(j));
                j = (j + 1) % n;
            }

            System.out.print("       " + d + "\n");
        }

        System.out.println("\n" + c.index(0) + ", excepted: 206");
    }

//    public static void main(String[] args) {
//        int num = 100;
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < num; i++) sb.append((char) StdRandom.uniform(0, 128));
//
//        String s = sb.toString();
//        CircularSuffixArray c = new CircularSuffixArray(s);
//        int n = c.length();
//
//        System.out.println("n = " + n);
//
//        for (int i = 0; i < n; i++) {
//            int d = c.index(i);
//            int tem = d;
//
//            for (int j = d, k = 0; k < n; k++) {
//                if (s.charAt(j) != s.charAt(tem))
//                    for (int t = 0; t < n; t++) StdOut.print(s.charAt(t));
//
//                j = (j + 1) % n;
//                tem = (tem + 1) % n;
//            }
//        }
//    }
}
