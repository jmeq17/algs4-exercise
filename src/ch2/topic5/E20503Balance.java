package ch2.topic5;

/*
Criticize the following implementation of homework.a class intended to represent account
balances. Why is compareTo() homework.a flawed implementation of the Comparable interface?

public class Balance implements Comparable<Balance>
{
...
private double amount;
public int compareTo(Balance that)
{
if (this.amount < that.amount - 0.005) return -1;
if (this.amount > that.amount + 0.005) return +1;
return 0;
}
...
}

Describe homework.a way to fix this problem.
 */

import java.math.BigDecimal;

public class E20503Balance implements Comparable<E20503Balance> {
    private BigDecimal amount;

    public E20503Balance(double amount) {
        this.amount = new BigDecimal(amount);
    }

    public void add(double amount) {
        BigDecimal aaa = new BigDecimal(amount);
        this.amount = this.amount.add(aaa);
    }

    public int compareTo(E20503Balance that) {
        return this.amount.compareTo(that.amount);
    }


    public static void main(String[] args) {
        E20503Balance b1 = new E20503Balance(0);
        E20503Balance b2 = new E20503Balance(1);

        for (int i = 0; i != 10; ++i) {
            b1.add(0.1);
        }

        System.out.println(b1.compareTo(b2));
    }
}
