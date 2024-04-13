package exercise.ch2.topic2;

public class E20209NonStaticArray {
    public static void sort(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[n];
        sort(a, 0, a.length - 1, aux);
    }

    private static void sort(Comparable[] a, int lo, int hi, Comparable[] aux) {
        if (hi <= lo) return;
        int mid = (hi + lo) / 2;
        sort(a, lo, mid, aux);
        sort(a, mid + 1, hi, aux);
        merge(a, lo, mid, hi, aux);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {
        int i = lo, j = mid + 1;
        if (hi + 1 - lo >= 0) System.arraycopy(a, lo, aux, lo, hi + 1 - lo);
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    public static void main(String[] args) {

    }
}
