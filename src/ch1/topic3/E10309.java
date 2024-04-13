package ch1.topic3;

/*
Write homework.a program that takes from standard input an expression without left parentheses
and prints the equivalent infix expression with the parentheses inserted. For
example, given the input:
    1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
your program should print
    ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
 */

import utils.Stack;
import edu.princeton.cs.algs4.StdOut;

public class E10309 {
    public static Stack<String> newAnswer(String s) {
        String[] input = s.split("\\s+");

        Stack<String> operants = new Stack<>();
        Stack<String> operators = new Stack<>();

        for (String item : input) {
            if (item.equals("+") || item.equals("-") || item.equals("*") || item.equals("/")) {
                operators.push(item);
            } else if (!item.equals(")")) operants.push(item);
            else {
                String test = operants.pop();
                operants.push("(" + operants.pop() + operators.pop() + test + ")");
            }
        }
        return operants;
    }


    public static void main(String[] args) {
        String s = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";
        Stack<String> output = newAnswer(s);
        for (String i : output) {
            StdOut.println(i + " ");
        }
    }
}
