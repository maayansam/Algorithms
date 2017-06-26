package com.example.HomeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ExpressionEvaluatorTest {
    @Before
    public void setUp() throws Exception {

        ExpressionEvaluator.expressionsMap().put("w", 5);
    }

    @Test
    public void expressionsMap() throws Exception {

        //check that map is updated after unary
        String unaryW = "++w";
        int unaryValue = ExpressionEvaluator.unary(unaryW);
        //w should be 6 in map
        int value = ExpressionEvaluator.expressionsMap().get("w");
        assertEquals(value, 6);
    }
    @Test(expected=NullPointerException.class)
    public void expressionsMap_expressionNotExist() throws Exception {

        //check that map is updated after unary
        String unaryW = "++w";
        int unaryValue = ExpressionEvaluator.unary(unaryW);
        //w should be 6 in map
        int value = ExpressionEvaluator.expressionsMap().get("t");

    }

    @Test
    public void unaryTest_incPre() throws Exception {
        String unaryW = "++w";
        int unaryValue = ExpressionEvaluator.unary(unaryW);
        assertEquals(unaryValue, 6);
    }

    @Test
    public void unaryTest_incPost() throws Exception {
        String unaryW = "w++";
        int unaryValue = ExpressionEvaluator.unary(unaryW);
        assertEquals(unaryValue, 5);
    }

    @Test
    public void unaryTest_decPost() throws Exception {
        String unaryW = "w--";
        int unaryValue = ExpressionEvaluator.unary(unaryW);
        assertEquals(unaryValue, 5);
    }

    @Test
    public void unaryTest_decPre() throws Exception {
        String unaryW = "--w";
        int unaryValue = ExpressionEvaluator.unary(unaryW);
        assertEquals(unaryValue, 4);
    }

    @Test(expected=UnhandledOperationInputException.class)
    public void unaryTest_unhandledException() throws Exception {
        String unaryW = "-+w";
        int unaryValue = ExpressionEvaluator.unary(unaryW);
    }

    @Test(expected=WrongCalculatorInputStreamException.class)
    public void unaryTest_wrongInputException() throws Exception {
        String unaryW = "--t"; //this parameter does not exist in map, exception expected
        int unaryValue = ExpressionEvaluator.unary(unaryW);
    }

    @Test
    public void assignmentOperationTest_plus() throws Exception {
        String[] reEvalAssgnmnt = ExpressionEvaluator.assignmentOperation("i53", "+=");
        assertEquals("i53", reEvalAssgnmnt[0]);
        assertEquals("+", reEvalAssgnmnt[1]);
    }
    @Test
    public void assignmentOperationTest_minus() throws Exception {
        String[] reEvalAssgnmnt = ExpressionEvaluator.assignmentOperation("i53", "-=");
        assertEquals("i53", reEvalAssgnmnt[0]);
        assertEquals("-", reEvalAssgnmnt[1]);
    }
    @Test
    public void assignmentOperationTest_multiple() throws Exception {
        String[] reEvalAssgnmnt = ExpressionEvaluator.assignmentOperation("i53", "*=");
        assertEquals("i53", reEvalAssgnmnt[0]);
        assertEquals("*", reEvalAssgnmnt[1]);
    }
    @Test
    public void assignmentOperationTest_split() throws Exception {
         String[] reEvalAssgnmnt = ExpressionEvaluator.assignmentOperation("i53", "/=");
        assertEquals("i53", reEvalAssgnmnt[0]);
        assertEquals("/", reEvalAssgnmnt[1]);
    }
    @Test(expected=UnhandledOperationInputException.class)
    public void assignmentOperationTestException() throws Exception {
        String[] reEvalAssgnmnt = ExpressionEvaluator.assignmentOperation("i53", "=%");

    }
}