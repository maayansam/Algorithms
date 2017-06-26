package com.example.HomeTest;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ExpressionSetTest {
    @Test
    public void getKey() throws Exception {
        String combinedExpression = "y= 5*3 +(10* (2 + 4 / 2))";
        ExpressionSet es = new ExpressionSet(combinedExpression);
        String variable = es.getParameter();
        assertEquals(variable, "y");

    }

    @Test
    public void getValue() throws Exception {
        String combinedExpression = "y= 5*3 +(10* (2 + 4 / 2))";
        String[] splittedExp = {"5", "*", "3", "+","(","10","*", "(", "2", "+","4", "/", "2", ")", ")"};
        ExpressionSet es = new ExpressionSet(combinedExpression);
        String[] expression = es.getExpression();
        assertEquals("actual result length is different then expected",splittedExp.length, expression.length);

        boolean equal = Arrays.equals(expression,splittedExp);
        assertTrue(equal);


    }

}