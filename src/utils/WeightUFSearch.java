package utils;

import exercise.ch1.topic5.UFWeightQuickUnion;

public class WeightUFSearch {
    private final UFWeightQuickUnion uf;
    private final int s;

    public WeightUFSearch(Graph G, int s) {
        this.s = s;
        uf = new UFWeightQuickUnion(G.V());
        for (int i = 0; i < G.V(); i++) {
            for (Integer integer : G.adj(i)) {
                uf.union(i, integer);
            }
        }
    }

    public boolean marked(int v) {
        return uf.connected(s, v);
    }

    public int count() {
        return uf.count(s);
    }
}
