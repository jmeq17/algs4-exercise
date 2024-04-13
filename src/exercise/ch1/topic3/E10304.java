package exercise.ch1.topic3;

/*
Write homework.a stack client Parentheses that reads in homework.a text stream from standard input
and uses homework.a stack to determine whether its parentheses are properly balanced. For example,
your program should print true for [()]{}{[()()]()} and false for [(]).
 */

import utils.Stack;
import edu.princeton.cs.algs4.StdOut;

public class E10304 {
    // 法1，错了
//    public static void main(String[] args) {
//        // 创建三个 stack 储存三种括号
//        Stack<String> parenthese = new Stack<>();
//        Stack<String> bracket = new Stack<>();
//        Stack<String> curlyBracee = new Stack<>();
//
//        // 2
//        String str = args[0];
////        StdOut.println(str);
////        StdOut.println(str.length());
//
//        // 2
//        for (int i = 0; i < str.length(); i++) {
//
//            //1
////        while (!StdIn.isEmpty()) {
////            String s = StdIn.readString();
//
//            // 2
//            String s = String.valueOf(str.charAt(i));
//
//            if (s.equals("(")) {
//                parenthese.push(s);
//            } else if (s.equals("[")) {
//                bracket.push(s);
//            } else if (s.equals("{")) {
//                curlyBracee.push(s);
//
//            } else if (s.equals(")")) {
//                if (parenthese.isEmpty()) {
//                    StdOut.println("Error");
//                    return;
//                }
//                parenthese.pop();
//            } else if (s.equals("]")) {
//                if (bracket.isEmpty()) {
//                    StdOut.println("Error");
//                    return;
//                }
//                bracket.pop();
//            } else if (s.equals("}")) {
//                if (curlyBracee.isEmpty()) {
//                    StdOut.println("Error");
//                    return;
//                }
//                curlyBracee.pop();
//            } else {
//                StdOut.println("Error");
//                return;
//            }
//        }
//        if (parenthese.isEmpty() && bracket.isEmpty() && curlyBracee.isEmpty()) {
//            StdOut.println("Good Job!");
//        } else {
//            StdOut.println("Error");
//        }
//    }

    // ------------------------------------------------------------

    // 法2 还可以
//    public static void main(String[] args) {
//        // 创建 char 的栈
//        Stack<Character> stack = new Stack<>();
//        String input = args[0];
//        boolean flag = false;
//        for (int i = 0; i < input.length(); i++) {
//            char s = input.charAt(i);
//            if (s == '(' || s == '[' || s == '{') {
//                stack.push(s);
//            } else if (s == ')' || s == ']' || s == '}') {
//                if (stack.isEmpty()) {
//                    break;
//                } else if (s == ')') {
//                    if (stack.pop() != '(') {
//                        break;
//                    }
//                } else if (s == ']') {
//                    if (stack.pop() != '[') {
//                        break;
//                    }
//                } else if (s == '}') {
//                    if (stack.pop() != '{') {
//                        break;
//                    }
//                }
//            } else {
//                flag = true;
//            }
//        }
//        if (!stack.isEmpty() || flag) {
//            StdOut.println("Error");
//        } else {
//            StdOut.println("Good Job!");
//        }
//    }

    // ------------------------------------------------------------

    // 法3 最好
//    public static boolean isValid(String input) {
//        Stack<Character> s = new Stack<Character>();
//        int i;
//        for (i = 0; i < input.length(); i++) {
//            char ch = input.charAt(i);
//            if (ch == '{' || ch == '(' || ch == '[') {
//                s.push(ch);
//            } else if (s.isEmpty()) {
//                break;
//            } else if (ch == '}') {
//                if ('{' != s.pop())
//                    break;
//            } else if (ch == ')') {
//                if ('(' != s.pop())
//                    break;
//            } else if (ch == ']') {
//                if ('[' != s.pop())
//                    break;
//            }
//        }
//        return (i == input.length() && s.isEmpty());
//    }
//
//    public static void main(String[] args) {
//        while (!StdIn.isEmpty()) {
//            String input = StdIn.readString();
//            if (isValid(input)) {
//                System.out.println("OK");
//            } else {
//                System.out.println("Invalid");
//            }
//        }
//    }

    // ------------------------------------------------------------

    // 最新
    public static boolean Parentheses(String s) {
        Stack<Character> left = new Stack<>();

        int N = s.length();

        for (int i = 0; i < N; i++) {
            char k = s.charAt(i);

            if (k == '[' || k == '{' || k == '(') {
                left.push(k);
            } else {
                if (left.isEmpty()) return false;
                char test = left.pop();
                if (test == '[' && k != ']') return false;
                if (test == ')' && k != ')') return false;
                if (test == '}' && k != '}') return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s1 = "[()]{}{[()()]()}";
        String s2 = "[(])";
        String s3 = "}])";

        StdOut.println("[()]{}{[()()]()}: " + Parentheses(s1));
        StdOut.println("[(]): " + Parentheses(s2));
        StdOut.println("}]): " + Parentheses(s3));
    }
}
