package com.example.HomeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by maayan.s on 1/19/17.
 */
public class CalculatorTest {

    String num1;
    String num2;
    String num_modulo;

    @Before
    public void setUp() throws Exception {
        num1 = "9";
        num2 = "3";
        num_modulo = "2";
    }

    @Test
    public void runTest_plus() throws Exception {

        int result = Calculator.run(num1, num2, "+");
        assertEquals(result, 12);
    }
    @Test
    public void runTest_minus() throws Exception {

        int result = Calculator.run(num1, num2, "-");
        assertEquals(result,  6);
    }
    @Test
    public void runTest_multiply() throws Exception {

        int result = Calculator.run(num1, num2, "*");
        assertEquals(result, 27);
    }
    @Test
    public void runTest_division() throws Exception {

        int result = Calculator.run(num1, num2, "/");
        assertEquals(result, 3);
    }
    @Test
    public void runTest_modulo() throws Exception {

        int result = Calculator.run(num1, num_modulo, "%");
        assertEquals(result, 1);
    }
    @Test
    public void runTest_exponent() throws Exception {

        int result = Calculator.run(num_modulo, num2, "^");
        assertEquals(result, 8);
    }
    @Test(expected=WrongCalculatorInputStreamException.class)
    public void runTest_exception() throws Exception {
        Calculator.run(num1, num2, "&");
     }

    @Test
    public void unaryTest_decrement() throws Exception {

        int w = 5;
        int result = Calculator.unary(Calculator.UNARY_DECREMENT, true, w);
        assertEquals(--w, result);
    }
    @Test
    public void unaryTest_increment() throws Exception {
        int w = 5;
        int result = Calculator.unary(Calculator.UNARY_INCREMENT, true, w);
        assertEquals(++w, result);
    }
    @Test
    public void unaryTest_decrementPost() throws Exception {

        int w = 5;
        int result = Calculator.unary(Calculator.UNARY_DECREMENT, false, w);
        assertEquals(w--, result);
    }
    @Test
    public void unaryTest_incrementPost() throws Exception {
        int w = 5;
        int result = Calculator.unary(Calculator.UNARY_INCREMENT, false, w);
        assertEquals(w++, result);
    }
    @Test(expected=WrongCalculatorInputStreamException.class)
    public void unaryTest_exception() throws Exception {
        int w = 5;
        int result = Calculator.unary("**", false, w);

    }

}