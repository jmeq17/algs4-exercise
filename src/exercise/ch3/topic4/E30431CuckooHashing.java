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

public class E30431CuckooHashing {

}
