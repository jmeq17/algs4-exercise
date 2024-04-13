package utils;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import static java.lang.Double.parseDouble;

public class Evaluate {

    public static Stack<Double> evaluate(String Infix){
        Stack<Double> operands = new Stack<>();
        Stack<String> operators = new Stack<>();

        String[] s = Infix.split("\\s+");

        for (String i : s) {
            if (i.equals("(")) ;
            else if (i.equals("+")) operators.push(i);
            else if (i.equals("-")) operators.push(i);
            else if (i.equals("*")) operators.push(i);
            else if (i.equals("/")) operators.push(i);
            else if (i.equals("sqrt")) operators.push(i);
            else if (i.equals(")")) {
                String j = operators.pop();
                if (j.equals("sqrt")) operands.push(Math.sqrt(operands.pop()));
                else if (j.equals("+")) operands.push(operands.pop() + operands.pop());
                else if (j.equals("-")) operands.push(operands.pop() - operands.pop());
                else if (j.equals("*")) operands.push(operands.pop() * operands.pop());
                else if (j.equals("/")) operands.push(operands.pop() / operands.pop());
            } else operands.push(parseDouble(i));
        }
        return operands;
    }


    public static void main(String[] args) {
        String Infix = "( 2 + ( ( 3 + 4 ) * ( 5 * 6 ) ) )";
        Stack<Double> operands = evaluate(Infix);
        StdOut.println(operands.pop());
    }
}
