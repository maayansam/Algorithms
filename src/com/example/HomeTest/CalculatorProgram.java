package com.example.HomeTest;

import java.lang.String;

/**
 * Created by maayan.s on 1/9/17.
 */
public class CalculatorProgram {


    public static void main(String[] args) throws MismatchedParenthesesException {

        String[] expressions = {"w = 1",
                "b = ++w + w++",
                "a = -w",
                "j= -2 + ++w  ",
                "x= j++ +5",
                "y= 5*((3)) +(10* (2 + 4 / 2))",
                "i = 2",
                "i +=x +y",
                "m = (((1))", //TODO: returns null
                "m =  2 / 3"};

        try {
            execute(expressions);
        } catch (Exception e) {
            printErrorMsg(e);
        }

        System.exit(0);
    }

    private static void printErrorMsg(Exception e) {

        if (e != null) {
            System.out.println(e.getMessage());
        } else {
            System.out.println("Wrong Input");
        }
    }

    private static void execute(String[] expressions) throws WrongCalculatorInputStreamException, UnhandledOperationInputException, MismatchedParenthesesException {
        // for each parameter to be evaluated would hold the expression to be calculated
        ShuntingYard shuntingYard = new ShuntingYard();
        for (String s : expressions) {

            ExpressionSet set = new ExpressionSet(s);
            // evaluate expression with Shunting Yard algorithm
            Integer result = shuntingYard.calculate(set.getExpression());

            if (result == null) {
                System.out.println(String.format("Invalid expression: %s", s));
                continue;
            }
            // keep results for multi use of variable's value
            ExpressionEvaluator.expressionsMap().put(set.getParameter(), result);
        }
        // see results
        ExpressionEvaluator.printMap();
    }


}
