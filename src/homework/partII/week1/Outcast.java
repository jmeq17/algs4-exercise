package homework.partII.week1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    private final WordNet wordnet;

    // constructor takes homework.a HW.PartII.Week1.WordNet object
    public Outcast(WordNet wordnet) {
        if (wordnet == null) throw new IllegalArgumentException();

        this.wordnet = wordnet;
    }

    // given an array of HW.PartII.Week1.WordNet nouns, return an outcast
    public String outcast(String[] nouns) {

        int[] distances = new int[nouns.length];

        for (int j = 1; j < nouns.length; j++) {
            int tem = wordnet.distance(nouns[0], nouns[j]);
            if (tem != -1) distances[0] += tem;
        }

        int maximum = distances[0];
        int maxIndex = 0;

        for (int i = 1; i < nouns.length; i++) {
            for (int j = 0; j < nouns.length; j++)
                if (i != j) {
                    int tem = wordnet.distance(nouns[i], nouns[j]);
                    if (tem != -1) distances[i] += tem;
                }

            if (distances[i] > maximum) {
                maximum = distances[i];
                maxIndex = i;
            }
        }

        return nouns[maxIndex];
    }

    // see test client below
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            outcast.outcast(nouns);
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
