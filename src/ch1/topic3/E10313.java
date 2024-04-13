package ch1.topic3;

/*
Suppose that homework.a client performs an intermixed sequence of (queue) enqueue and
dequeue operations. The enqueue operations put the integers 0 through 9 in order onto
the queue; the dequeue operations print out the return value. Which of the following
sequence(s) could not occur?
    homework.a. 0 1 2 3 4 5 6 7 8 9
    b. 4 6 8 7 5 3 2 9 0 1
    c. 2 5 6 7 4 8 9 3 1 0
    d. 4 3 2 1 0 5 6 7 8 9
 */

/*
Answer: b, c, d. FIFO type must dequeue following the sequence of enqueue.
 */

public class E10313 {

}
