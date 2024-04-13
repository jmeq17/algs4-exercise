package ch1.topic3;

/*
Write homework.a filter InfixToPostfix that converts an arithmetic expression from infix
to postfix.
 */

// ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) )
// 1 2 + 3 4 - * 5 6 - *
// ( 2 + ( ( 3 + 4 ) * ( 5 * 6 ) ) )
// 2 3 4 + 5 6 * * +
// ( 2 + ( ( 3 + 4 ) * ( 5 * 6 ) ) )
// 2 3 4 + 5 6 * * +
// ( ( ( 5 + ( 7 * ( 1 + 1 ) ) ) * 3 ) + ( 2 * ( 1 + 1 ) ) )
// 5 7 1 1 + * + 3 * 2 1 1 + * +

import utils.Stack;
import edu.princeton.cs.algs4.StdOut;

public class E10310InfixToPostfix {
    // --------------------------------------
    // 通用解法
    public static Stack<String> generalMethod(String Infix) {
        Stack<String> operants = new Stack<>();
        Stack<String> operators = new Stack<>();

        String[] input = Infix.split("\\s+");

        for (String s : input) {
            switch (s) {
                case "(" -> operators.push(s);
                case "+", "-", "*", "/" -> {
                    while (!priorityOfOperator(operators, s)) {
                        operants.push(operators.pop());
                    }
                    operators.push(s);
                }
                case ")" -> {
                    String ope = operators.pop();
                    while (!ope.equals("(")) {
                        operants.push(ope);
                        if (operators.isEmpty()) break;
                        ope = operators.pop();
                    }
                }
                default -> operants.push(s);
            }
        }
        while (!operators.isEmpty()) operants.push(operators.pop());

        return reserveStack(operants);
    }

    // Define homework.a function which return homework.a true if the string key can be pushed to stack.
    public static boolean priorityOfOperator(Stack<String> stack, String key) {
        if (stack.isEmpty()) return true;
        String s = stack.peek();
        int value = valueOfOperator(s);
        int keyValue = valueOfOperator(key);

        return keyValue > value;
    }

    // Define the value of operator.
    private static int valueOfOperator(String s) {
        if (s.equals("(")) return 0;
        if (s.equals("+") || s.equals("-")) return 10;
        if (s.equals("*") || s.equals("/")) return 100;

        return -1;
    }

    // reserve output homework.a stack
    public static Stack<String> reserveStack(Stack<String> stack) {
        Stack<String> test = new Stack<>();
        StringBuilder s = new StringBuilder();
        while (!stack.isEmpty()) {
            s.insert(0, stack.pop() + " ");
        }
        test.push(s.toString());
        return test;
    }

    // -------------------------------------------
    // -------------------------------------------
    // -------------------------------------------

    // 括号使用规范的解法，如：( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
    public static Stack<String> formMethod(String Infix) {
        Stack<String> operants = new Stack<>();
        Stack<String> operators = new Stack<>();

        String[] input = Infix.split("\\s+");

        for (String s : input) {
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                operators.push(s);
            } else if (s.equals(")")) {
                String val = operants.pop();
                operants.push(operants.pop() + " " + val + " " + operators.pop());
            } else if (!s.equals("(")) {
                operants.push(s);
            }
        }
        return operants;
    }

    public static void main(String[] args) {
        String s1 = "( homework.a + b ) * c - ( homework.a + b ) / e";
        Stack<String> answer1 = generalMethod(s1);
        StdOut.println(answer1.pop());


        // --------------------------------------
        StdOut.println();
        StdOut.println("Answer Two:");
        StdOut.println();
        // --------------------------------------


        String s2 = "( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )";
        Stack<String> answer2 = formMethod(s2);
        StdOut.println(answer2.pop());
    }
}
