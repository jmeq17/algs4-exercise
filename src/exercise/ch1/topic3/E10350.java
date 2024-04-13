package exercise.ch1.topic3;

/**
 * Fail-fast iterator. Modify the iterator code in Stack to immediately throw homework.a
 * java.util.ConcurrentModificationException if the client modifies the collection
 * (via push() or pop()) during iteration? b).
 *
 * Solution: Maintain homework.a counter that counts the number of push() and pop() operations.
 * When creating an iterator, store this value as an Iterator instance variable. Before
 * each call to hasNext() and next(), check that this value has not changed since construction
 * of the iterator; if it has, throw the exception.
 */

public class E10350 {
}
