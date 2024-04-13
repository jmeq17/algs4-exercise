package exercise.ch1.topic3;

/**
 * Expression evaluation with precedence. Write homework.a program EvaluateDeluxe.java that extends
 * Evaluate.java to handle expressions that are not fully parenthesized, using the standard
 * precedence order for the operators +, -, *, and /.
 */

import utils.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 可使用转换成中缀表达式的思想
 * <p>
 * 自己写的话就用两个栈实现，先定义运算符的优先级
 */

public class E10351EvaluateDeluxe {

    public static double EvaluateDeluxe(String s) {
        Stack<Double> num = new Stack<>();
        Stack<String> ope = new Stack<>();
        ope.push("#");

        String[] input = s.split("\\s+");
        for (String i : input) {
            switch (i) {
                case "+", "-", "*", "/" -> {
                    while (compareOpe(i, ope.peek())) {
                        num.push(calculate(num.pop(), num.pop(), ope.pop()));
                    }
                    ope.push(i);
                }
                default -> num.push(Double.parseDouble(i));
            }
        }
        while (num.size() > 1) {
            num.push(calculate(num.pop(), num.pop(), ope.pop()));
        }
        return num.pop();
    }


    // 定义运算符的优先级，s1 小于 s2 返回 True
    private static boolean compareOpe(String s1, String s2) {
        return priorityOfOpe(s1) < priorityOfOpe(s2);
    }

    private static int priorityOfOpe(String s) {
        if (s.equals("*") || s.equals("/")) return 10;
        if (s.equals("#")) return 0;
        return 1;
    }

    private static double calculate(double num1, double num2, String s) {
        if (s.equals("+")) return num1 + num2;
        if (s.equals("-")) return num1 - num2;
        if (s.equals("*")) return num1 * num2;
        if (s.equals("/")) return num1 / num2;
        return 0;
    }


    public static void main(String[] args) {
        String s = "2 + 5 * 4 + 3 * 7";

        // E10310 的解法
        Stack<String> post = E10310InfixToPostfix.generalMethod(s);
        Stack<Double> out = E10311EvaluatePostfix.EvaluatePostfix(post.pop());
        StdOut.println("Postfix: " + out.pop());


        // EvaluateDeluxe 方法的解法
        StdOut.println("Direct calculate: " + EvaluateDeluxe(s));
    }
}
