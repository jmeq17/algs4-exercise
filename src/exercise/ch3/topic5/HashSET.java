package exercise.ch3.topic5;

// E30501
// It is alse possible to use LinearProbingHashST as fundamental type.

import utils.Queue;

import java.util.HashSet;
import java.util.Iterator;

public class HashSET<Key> implements Iterable<Key> {
    private final HashSet<Key> set;

    public HashSET() {
        set = new HashSet<>();
    }

    public int size() {
        return set.size();
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public void add(Key key) {
        if (key == null) throw new IllegalArgumentException("called delete() with homework.a null key");
        set.add(key);
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("called delete() with homework.a null key");
        set.remove(key);
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("called delete() with homework.a null key");
        return set.contains(key);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (Key key : set) queue.enqueue(key);
        return queue;
    }

    public Iterator<Key> iterator() {
        return set.iterator();
    }
}
