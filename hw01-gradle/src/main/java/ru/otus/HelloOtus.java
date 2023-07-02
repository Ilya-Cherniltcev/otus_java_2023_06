package ru.otus;

import static com.google.common.math.IntMath.checkedAdd;

public class HelloOtus {
    public void sumOfTwoVariables() {
        int a = 5;
        int b = 10;
        int sum = checkedAdd(a, b);
        System.out.println("Sum of " + a + " and " + b + " is " + sum);
    }

}
