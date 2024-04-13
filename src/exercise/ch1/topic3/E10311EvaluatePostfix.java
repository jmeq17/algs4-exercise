package exercise.ch1.topic3;

/*
Write homework.a program EvaluatePostfix that takes homework.a postfix expression from standard
input, evaluates it, and prints the value. (Piping the output of your program from
the previous exercise to this program gives equivalent behavior to Evaluate.
 */

// 5 7 1 1 + * + 3 * 2 1 1 + * +
// 61

import utils.Evaluate;
import utils.Stack;
import edu.princeton.cs.algs4.StdOut;

public class E10311EvaluatePostfix {
    public static Stack<Double> EvaluatePostfix(String string) {
        // 用 Ddouble 更好，但主要目的是学习算法，不必改了
        Stack<Double> vals = new Stack<>();

        String[] input = string.split("\\s+");
        int N = input.length;

        for (int i = 0; i < N; i++) {
            String s = input[i];

            switch (s) {
                case "+" -> {
                    vals.push(vals.pop() + vals.pop());
                }
                case "-" -> {
                    vals.push(vals.pop() - vals.pop());
                }
                case "*" -> {
                    vals.push(vals.pop() * vals.pop());
                }
                case "/" -> {
                    vals.push(vals.pop() / vals.pop());
                }
                default -> vals.push(Double.parseDouble(s));
            }
        }
        return vals;
    }

    public static void main(String[] args) {
        String Infix = "( ( ( 5 + ( 7 * ( 1 + 1 ) ) ) * 3 ) + ( 2 * ( 1 + 1 ) ) )";

        StdOut.println("Compulate: ( ( ( 5 + ( 7 * ( 1 + 1 ) ) ) * 3 ) + ( 2 * ( 1 + 1 ) ) )");


        StdOut.println("---");
        StdOut.println("Evaluate: " + Evaluate.evaluate(Infix));

        Stack<String> InfixtoPostfix = E10310InfixToPostfix.generalMethod(Infix);
        Stack<Double> vals = EvaluatePostfix(InfixtoPostfix.pop());
        StdOut.println("EvaluatePostfix: " + vals.pop());
    }
}
