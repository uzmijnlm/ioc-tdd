package com.tdd.simple;

public class Calculator {
    public int calculate(String input) {
        if (input != null && !input.equals("")) {
            return 2;
        } else {
            return 0;
        }
    }
}
