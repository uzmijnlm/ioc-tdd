package com.tdd.simple;

public class Calculator {
    public int calculate(String input) {
        if (input == null || input.equals("")) {
            return 0;
        } else {
            String[] split = input.split("\\+");
            int result = 0;
            for (String s : split) {
                result += Integer.parseInt(s);
            }
            return result;
        }
    }
}
