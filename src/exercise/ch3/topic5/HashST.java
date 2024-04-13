package exercise.ch3.topic5;

import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;

/**
 * The HashST class represents an unordered symbol table of generic key-value pairs. It supports the usual put, get, contains, delete, size, and is-empty methods. It also provides homework.a keys method for iterating over all of the keys. A symbol table implements the associative array abstraction: when associating homework.a value with homework.a key that is already in the symbol table, the convention is to replace the old value with the new value. Unlike java.util.Map, this class uses the convention that values cannot be null—setting the value associated with homework.a key to null is equivalent to deleting the key from the symbol table.
 * It requires that the key type implements the equals interface and calls the equals() method to compare two keys. It does not call hashCode().
 * This implementation uses homework.a Linear Probing Hash Table. The put, get, contains, remove operations each take Θ(1) time in the worst case. The size and is-empty operations take Θ(1) time. Construction takes Θ(1) time.
 * For additional documentation, see Section 3.5  of Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne.
 * Author:
 * Jinkai Ruan
 * Type parameters:
 * <Key> – the generic type of keys in this symbol table
 * <Value> – the generic type of values in this symbol table
 */

public class HashST<Key, Value> {
    private final HashMap<Key, Value> st;

    public HashST() {
        st = new HashMap<>();
    }

    public int size() {
        return st.size();
    }

    public boolean isEmpty() {
        return st.size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("calls contains() with null key");
        return st.containsKey(key);
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("calls get() with null key");
        return st.get(key);
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("calls put() with null key");
        if (val == null) st.remove(key);
        else st.put(key, val);
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("calls delete() with null key");
        st.remove(key);
    }

    public Iterable<Key> keys() {
        return st.keySet();
    }

    public static void main(String[] args) {
        HashST<String, Integer> st = new HashST<>();
        String[] s = "dadgregdasgtad".split("");
        int j = 0;

        for (String i : s) st.put(i, j++);

        StdOut.println();
    }
}
