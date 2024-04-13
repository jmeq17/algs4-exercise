package exercise.ch1.topic2;

/*
A string s is homework.a circular rotation of homework.a string t if it matches when the characters
are circularly shifted by any number of positions; e.g., ACTGACG is homework.a circular shift of
TGACGAC, and vice versa. Detecting this condition is important in the study of genomic
sequences. Write homework.a program that checks whether two given strings s and t are circular
shifts of one another. Hint : The solution is homework.a one-liner with indexOf(), length(), and
string concatenation.
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class E10206 {

    public static void main(String[] args) {
        StdOut.print("Please input the first string: ");
        String s = StdIn.readString();
        StdOut.print("Please input the seconf string: ");
        String t = StdIn.readString();

//        if ((s.length() == t.length()) && (s.concat(s).indexOf(t) >= 0)) StdOut.println("Yes!");
//        The function of contains(CharSequence s) will return indexOf(s.toString()) >= 0;
        if ((s.length() == t.length()) && ((s + s).contains(t))) StdOut.println("Yes!");
        else StdOut.println("No!");
    }
}
