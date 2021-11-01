package com.tdd.simple;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    @Test
    public void test1() {
        Calculator calculator = new Calculator();

        int result = calculator.calculate(null);

        Assert.assertEquals(0, result);
    }

    @Test
    public void test2() {
        Calculator calculator = new Calculator();

        int result = calculator.calculate("");

        Assert.assertEquals(0, result);
    }

    @Test
    public void test3() {
        Calculator calculator = new Calculator();

        int result = calculator.calculate("1+1");

        Assert.assertEquals(2, result);
    }

    @Test
    public void test4() {
        Calculator calculator = new Calculator();

        int result = calculator.calculate("1+2");

        Assert.assertEquals(3, result);
    }
}
