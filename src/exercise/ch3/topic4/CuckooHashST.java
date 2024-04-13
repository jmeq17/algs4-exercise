package exercise.ch3.topic4;

/*
Cuckoo hashing. Develop homework.a symbol-table implementation that maintains two
hash tables and two hash functions. Any given key is in one of the tables, but not both.
When inserting homework.a new key, hash to one of the tables; if the table position is occupied,
replace that key with the new key and hash the old key into the other table (again kicking
out homework.a key that might reside there). If this process cycles, restart. Keep the tables less
than half full. This method uses homework.a constant number of equality tests in the worst case
for search (trivial) and amortized constant time for insert.
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class CuckooHashST<Key, Value> {
    private static final int INIT_CAPACITY = 3;

    private Key[] st1;
    private Key[] st2;
    private Value[] val1;
    private Value[] val2;
    private int M1;
    private int M2;
    private int N1;
    private int N2;

    public CuckooHashST() {
        this(INIT_CAPACITY);
    }

    public CuckooHashST(int M) {
        st1 = (Key[]) new Object[M];
        st2 = (Key[]) new Object[M];
        val1 = (Value[]) new Object[M];
        val2 = (Value[]) new Object[M];
        this.M1 = M;
        this.M2 = M;
    }

    private int hash1(Key key, int N) {
        return (key.hashCode() & 0x7fffffff) % N;
    }

    private int hash2(Key key, int N) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h & (N - 1);
    }

    private void resize1(int cap) {
        Key[] k = (Key[]) new Object[cap];
        Value[] v = (Value[]) new Object[cap];
        N1 = 0;

        for (int i = 0; i < M1; i++) {
            if (st1[i] == null) continue;

            int t = hash1(st1[i], cap);
            if (k[t] == null) {
                k[t] = st1[i];
                v[t] = val1[i];
                N1++;
            } else {
                if (N2 >= M2 / 2) resize2(2 * M2);
                int p = hash2(k[t], M2);
                if (st2[p] == null) {
                    st2[p] = k[t];
                    val2[p] = v[t];
                    N2++;
                } else {
                    // restart
                }
                k[t] = st1[i];
                v[t] = val1[i];
            }
        }
        st1 = k;
        val1 = v;
        M1 = cap;
    }

    private void resize2(int cap) {
        Key[] k = (Key[]) new Object[cap];
        Value[] v = (Value[]) new Object[cap];
        N2 = 0;

        for (int i = 0; i < M2; i++) {
            if (st2[i] == null) continue;

            int t = hash1(st2[i], cap);
            if (k[t] == null) {
                k[t] = st2[i];
                v[t] = val2[i];
                N2++;
            } else {
                if (N1 >= M1 / 2) resize1(2 * M1);
                int p = hash1(k[t], M1);
                if (st1[p] == null) {
                    st1[p] = k[t];
                    val1[p] = v[t];
                    N1++;
                } else {
                    throw new IllegalArgumentException("Cycle");
                }
                k[t] = st2[i];
                v[t] = val2[i];
            }
        }
        st2 = k;
        val2 = v;
        M2 = cap;
    }

    public void put(Key key, Value val) {
        if (get(key) != null) return;

        if (StdRandom.uniform(0, 2) == 1) {
            if (N1 >= M1 / 2) resize1(2 * M1);
            int k = hash1(key, M1);
            if (st1[k] == null) {
                st1[k] = key;
                val1[k] = val;
                N1++;
            } else {
                if (N2 >= M2 / 2) resize2(2 * M2);
                int t = hash2(st1[k], M2);
                if (st2[t] == null) {
                    st2[t] = st1[k];
                    val2[t] = val1[k];
                    N2++;
                } else {
                    throw new IllegalArgumentException("Cycle");
                }
                st1[k] = key;
                val1[k] = val;
            }
        } else {
            if (N2 >= M2 / 2) resize2(2 * M2);
            int k = hash2(key, M2);
            if (st2[k] == null) {
                st2[k] = key;
                val2[k] = val;
                N2++;
            } else {
                if (N1 >= M1 / 2) resize2(2 * M1);
                int t = hash1(st2[k], M1);
                if (st1[t] == null) {
                    st1[t] = st2[k];
                    val1[t] = val2[k];
                    N1++;
                } else {
                    throw new IllegalArgumentException("Cycle");
                }
                st2[k] = key;
                val2[k] = val;
            }
        }
    }

    public Value get(Key key) {
        if (st1[hash1(key, M1)] != null && st1[hash1(key, M1)].equals(key)) return val1[hash1(key, M1)];
        if (st2[hash2(key, M2)] != null && st2[hash2(key, M2)].equals(key)) return val2[hash2(key, M2)];
        return null;
    }

    public static void main(String[] args) {
        CuckooHashST<String, Integer> st = new CuckooHashST<>();
        int i = 0;

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            st.put(s, i);
            i++;
        }
        st.put("a", 2);
    }
}
