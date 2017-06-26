package com.example.HomeTest;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by maayan.s on 1/22/17.
 */
public class ParserTest {
    @Test
    public void isNumberTest() throws Exception {
        boolean isNum = Parser.isNumber("987643");
        assertTrue(isNum);
    }

    @Test
    public void isNumberTest_notANum() throws Exception {
        boolean isNum = Parser.isNumber("987643r");
        assertTrue(!isNum);
    }

    @Test
    public void isVariableTest() throws Exception {
        boolean isVar = Parser.isVariable("Z89t");
        assertTrue(isVar);
    }

    @Test
    public void isVariableTest_notAVar() throws Exception {
        boolean isVar = Parser.isVariable("96R");
        assertTrue(!isVar);
    }

    @Test
    public void isUnaryTest_prePlus() throws Exception {
        boolean isUnary = Parser.isUnary("++r1");
        assertTrue(isUnary);
    }
    @Test
    public void isUnaryTest_postPlus() throws Exception {
        boolean isUnary = Parser.isUnary("r1++");
        assertTrue(isUnary);
    }
    @Test
    public void isUnaryTest_notUnaryPlus() throws Exception {
        boolean isUnary = Parser.isUnary("++1r");
        assertTrue(!isUnary);
    }

    @Test
    public void isUnaryTest_preMinus() throws Exception {
        boolean isUnary = Parser.isUnary("--r1");
        assertTrue(isUnary);
    }
    @Test
    public void isUnaryTest_postMinus() throws Exception {
        boolean isUnary = Parser.isUnary("r1--");
        assertTrue(isUnary);
    }

    @Test
    public void isUnaryTest_notUnaryMinus() throws Exception {
        boolean isUnary = Parser.isUnary("--1r");
        assertTrue(!isUnary);
    }

    @Test
    public void isAssignment_simple() throws Exception {
        //=, +=, -=, /=, *=, ==
        Parser.AssignmentEnum type = Parser.isAssignment("=");
        assertEquals(type, Parser.AssignmentEnum.Simple);
    }
    @Test
    public void isAssignmentTest() throws Exception {
        Parser.AssignmentEnum type = Parser.isAssignment("+=");
        assertEquals(type, Parser.AssignmentEnum.Arithmetic);
    }
    @Test
    public void isAssignmentTest_minus() throws Exception {
        Parser.AssignmentEnum type = Parser.isAssignment("-=");
        assertEquals(type, Parser.AssignmentEnum.Arithmetic);
    }
    @Test
    public void isAssignmentTest_division() throws Exception {
        Parser.AssignmentEnum type = Parser.isAssignment("/=");
        assertEquals(type, Parser.AssignmentEnum.Arithmetic);
    }
    @Test
    public void isAssignmentTest_multiply() throws Exception {
        Parser.AssignmentEnum type = Parser.isAssignment("*=");
        assertEquals(type, Parser.AssignmentEnum.Arithmetic);
    }
    @Test
    public void isAssignment_notAsgnmnt() throws Exception {
        Parser.AssignmentEnum type = Parser.isAssignment("==");
        assertEquals(type, Parser.AssignmentEnum.None);
    }
    @Test
    public void isAssignment_notAsgnmnt_() throws Exception {
        Parser.AssignmentEnum type = Parser.isAssignment("=+");
        assertEquals(type, Parser.AssignmentEnum.None);
    }

    @Test
    public void splitFullExpression() throws Exception {
        String[] expected = {"y","=","4", "+", "5", "*","(","7","+", "5",")","-","++w"};
        String[] splittedActual = Parser.splitFullExpression("y=4+ 5 *(7+5)\2 - ++w");
        boolean equal = Arrays.equals(expected, splittedActual);
        assertTrue(equal);
    }
    @Test(expected=WrongCalculatorInputStreamException.class)
    public void splitFullExpression_wrongExpression() throws Exception {
        //giving wrong variable in expression or wrong assignment should cause an exception
        String[] expected = {"y1","=", "%","4", "+", "5", "*","(","7","+", "5",")","-","++w"};
        String[] splittedActual = Parser.splitFullExpression("y %= 4+ 5 *(7+5)\2 - ++w");
    }



}