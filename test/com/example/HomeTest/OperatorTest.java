package com.example.HomeTest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by maayan.s on 1/22/17.
 */
public class OperatorTest {
    @Test
    public void getToken() throws Exception {
        Operator op = new Operator("+", 0,  ShuntingYard.LEFT_ASSOC);
        assertEquals("+", op.getToken());
    }

    @Test
    public void getPrecedence() throws Exception {
        Operator op = new Operator("*", 5,  ShuntingYard.LEFT_ASSOC);
        assertEquals(5, op.getPrecedence());
    }

    @Test
    public void getAssociativity() throws Exception {
        Operator op = new Operator("^", 10,  ShuntingYard.RIGHT_ASSOC);
        assertEquals(ShuntingYard.RIGHT_ASSOC, op.getAssociativity());
    }

    @Test
    public void equals_sameInstance() throws Exception {
        Operator op = new Operator("^", 10,  ShuntingYard.RIGHT_ASSOC);
        assertTrue(op.equals(op));
    }
    @Test
    public void equals_differentValues() throws Exception {

        Operator op = new Operator("^", 10,  ShuntingYard.RIGHT_ASSOC);
        Operator rf = new Operator("*", 5,  ShuntingYard.LEFT_ASSOC);
        assertTrue(!op.equals(rf));

    }
    @Test
    public void equals_differentInstances() throws Exception {

        Operator op = new Operator("^", 10,  ShuntingYard.RIGHT_ASSOC);
        String test = "NOT EQUAL";
        assertTrue(!op.equals(test));
    }

}