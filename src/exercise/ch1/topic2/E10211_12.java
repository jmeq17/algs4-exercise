package exercise.ch1.topic2;

/*
21
Develop an implementation SmartDate of our Date API that raises an exception
if the date is not legal.

22
Add homework.a method dayOfTheWeek() to SmartDate that returns homework.a String value
Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, or Sunday, giving the appropriate
day of the week for the date. You may assume that the date is in the 21st
century.
 */

import edu.princeton.cs.algs4.StdOut;

public class E10211_12 {
    private final int month;
    private final int year;
    private final int day;

    public E10211_12(int month, int day, int year) {
        isDateValid(month, day, year);

        this.month = month;
        this.year = year;
        this.day = day;
    }

    public int month() {
        return month;
    }

    public int year() {
        return year;
    }

    public int day() {
        return day;
    }

    // 2000-1-1 is Saturday
    public String dayOfTheWeek() {
        String[] weeks = {"Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};
        int[] dayOfmonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int days = 365 * (year - 2000) + ((year - 2000) / 4) + 1;
        for (int i = 1; i < month; i++) {
            days += dayOfmonth[i];
        }
        days += day;
        int indexOfWeek = days % 7;
        return weeks[indexOfWeek];
    }

    public String toString() {
        return "year: " + year + ", month: " + month + ", day: " + day;
    }

    private void isDateValid(int month, int day, int year) {
        if (month > 12 || month < 1) throw new IllegalArgumentException("Month must be in [1, 12].");
        if (day < 1 || day > 31) throw new IllegalArgumentException("Day must be in [1, 31].");
        if (year < 1) throw new IllegalArgumentException("Year must be mare than 1.");
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31)
            throw new IllegalArgumentException("Day must be in [1, 30].");
        if (month == 2 && day > 29 && year / 4 == 0)
            throw new IllegalArgumentException("Day must be in [1, 29].");
        if (month == 2 && day > 28 && year / 4 != 0)
            throw new IllegalArgumentException("Day must be in [1, 28].");
    }

    private boolean isDateValid2(int month, int day, int year) {
        boolean valid = true;
        int[] maxNumberOfDaysPerMonth = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (year < 1 || month < 1 || month > 12 || day < 1 || day > maxNumberOfDaysPerMonth[month - 1]) {
            valid = false;
        }
        if (year / 4 == 0 && day == 29) {
            valid = false;
        }
        return valid;
    }


    public static void main(String[] args) {
        E10211_12 a = new E10211_12(4, 29, 2013);
        StdOut.println(a.dayOfTheWeek());

        E10211_12 b = new E10211_12(3, 5, 2021);
        StdOut.println(b.dayOfTheWeek());
    }
}
