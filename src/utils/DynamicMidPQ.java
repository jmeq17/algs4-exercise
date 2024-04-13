package utils;

import edu.princeton.cs.algs4.StdOut;

public class DynamicMidPQ<Key extends Comparable<Key>> {
    private int nMax = 0;
    private int nMin = 0;
    private int n;
    private final Key[] pqMax;
    private final Key[] pqMin;
    private Key mid;

    public DynamicMidPQ(int maxN) {
        pqMax = (Key[]) new Comparable[maxN / 2 + 2];
        pqMin = (Key[]) new Comparable[maxN / 2 + 2];
    }

    public void insert(Key k) {
        if (isEmpty()) {
            mid = k;
        } else {
            if (mid.compareTo(k) > 0) {
                pqMax[++nMax] = k;
                swimMax(nMax);
            } else {
                pqMin[++nMin] = k;
                swimMin(nMin);
            }
            adjust();
        }
        n++;
    }

    public Key delMid() {
        Key currentMid = mid;
        if (nMin != 0 || nMax != 0) {
            if (nMax < nMin) {
                mid = pqMin[1];

                exch(pqMin, 1, nMin);
                pqMin[nMin--] = null;
                sinkMin(1);
            } else {
                mid = pqMax[1];

                exch(pqMax, 1, nMax);
                pqMax[nMax--] = null;
                sinkMax(1);
            }
            adjust();
        }
        n--;
        return currentMid;
    }

    public void adjust() {
        if (Math.abs(nMin - nMax) <= 1) return;

        if (nMax > nMin) {
            pqMin[++nMin] = mid;
            swimMin(nMin);

            mid = pqMax[1];
            exch(pqMax, 1, nMax);
            pqMax[nMax--] = null;
            sinkMax(1);
        } else {
            pqMax[++nMax] = mid;
            swimMax(nMax);

            mid = pqMin[1];
            exch(pqMin, 1, nMin);
            pqMin[nMin--] = null;
            sinkMin(1);
        }
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private boolean less(Key[] pq, int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(Key[] pq, int i, int j) {
        Key k = pq[i];
        pq[i] = pq[j];
        pq[j] = k;
    }

    private void swimMax(int i) {
        while (i > 1 && less(pqMax, i / 2, i)) {
            exch(pqMax, i, i / 2);
            i = i / 2;
        }
    }

    private void swimMin(int i) {
        while (i > 1 && less(pqMin, i, i / 2)) {
            exch(pqMin, i, i / 2);
            i = i / 2;
        }
    }

    private void sinkMax(int i) {
        while (2 * i <= nMax) {
            int j = 2 * i;
            if (j < nMax && less(pqMax, j, j + 1)) j++;
            if (!less(pqMax, i, j)) break;
            exch(pqMax, i, j);
            i = j;
        }
    }

    private void sinkMin(int i) {
        while (2 * i <= nMin) {
            int j = 2 * i;
            if (j < nMin && less(pqMin, j + 1, j)) j++;
            if (!less(pqMin, j, i)) break;
            exch(pqMin, i, j);
            i = j;
        }
    }

    public void show() {
        while (!isEmpty()) {
            StdOut.print(delMid() + " ");
        }
    }


    public static void main(String[] args) {
        String[] a = "abcdefghijk".split("");

        DynamicMidPQ<String> pq = new DynamicMidPQ<>(a.length);

        for (String s : a) {
            pq.insert(s);
        }

        pq.show();
    }
}
