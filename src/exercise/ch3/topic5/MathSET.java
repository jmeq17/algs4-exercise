package exercise.ch3.topic5;

import edu.princeton.cs.algs4.StdOut;

/**
 * E30517
 * Finite mathematical sets. Your goal is to develop an implementation of the following
 * API for processing finite mathematical sets:
 */
/*
public class MathSET<Key>
          MathSET(Key[] universe)             create the empty set(using given universe)
          void add(Key key)                   put key into the set
          MathSET<Key> complement()           set of keys in the universe that are not in this set
          void union(MathSET<Key> homework.a)          put any keys from homework.a into the set that are not already there
          void intersection(MathSET<Key> homework.a)   remove any keys from this set that are not in homework.a
          void delete(Key key)                remove key from the set
          boolean contains(Key key)           is key in the set?
          boolean isEmpty()                   is the set empty?
          int size()                          number of keys in the set
 */

public class MathSET<Key> {
    private final HashSET<Key> set;
    private final Key[] universe;

    public MathSET(Key[] universe) {
        this.universe = universe;
        set = new HashSET<>();
        for (Key key : universe)
            set.add(key);
    }

    public void add(Key key) {
        if (key == null) throw new IllegalArgumentException("called delete() with homework.a null key");
        set.add(key);
    }

    public MathSET<Key> complement() {
        MathSET<Key> that = new MathSET<>(universe);
        for (Key value : universe) {
            if (set.contains(value)) {
                that.delete(value);
            }
        }
        return that;
    }

    public void union(MathSET<Key> a) {
        if (a == null) throw new IllegalArgumentException("called delete() with homework.a null homework.a");
        for (Key key : a.keys()) {
            if (!this.contains(key)) set.add(key);
        }
    }

    public void intersection(MathSET<Key> a) {
        if (a == null) throw new IllegalArgumentException("called delete() with homework.a null homework.a");
        // 这里不能用 (Key key : set)，因为 set 是实时变化的，这引发一个线程异常，
        // 而 java 的机制使得 keys() 保存为一个临时变量，不会改变
        for (Key key : keys()) {
            if (!a.contains(key)) delete(key);
        }
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("called delete() with homework.a null key");
        set.delete(key);
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("called delete() with homework.a null key");
        return set.contains(key);
    }

    public int size() {
        return set.size();
    }

    public boolean isEmpty() {
        return set.size() == 0;
    }

    public Iterable<Key> keys() {
        return set.keys();
    }

    public void show() {
        for (Key key : set) StdOut.print(key + " ");
        StdOut.println();
    }
}
