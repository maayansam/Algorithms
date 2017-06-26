package com.example.HomeTest;

import java.util.Map;
import java.util.HashMap;


public class ExpressionEvaluator {

    private static final int NEW_SIZE = 2;
    private static final int BEGINNING_INDEX = 0;
    private static final int UNARY_SIZE = 2;

    private static Map<String, Integer> çalculatedExpressionsMap = new HashMap<String, Integer>();
    public static Map<String, Integer> expressionsMap() {
        return çalculatedExpressionsMap;
    }
    public static void printMap () {

        if (çalculatedExpressionsMap.size()  ==   0) {
            System.out.println("Map is Empty");
            return;
        }

        boolean firstTime = true;
        System.out.print("(");

        for(Map.Entry<String, Integer> entry : çalculatedExpressionsMap.entrySet()) {

            System.out.print(firstTime ? "" : ", ");
            System.out.print(String.format("%s = %d", entry.getKey(), entry.getValue()));
            firstTime = false;

        }
        System.out.println(")");

    }

    public ExpressionEvaluator() {}

    public static int unary (String expression) throws UnhandledOperationInputException, WrongCalculatorInputStreamException {

        // exit point if expression is not unary
        int incIdx = expression.indexOf(Calculator.UNARY_INCREMENT);
        int decIdx = expression.indexOf(Calculator.UNARY_DECREMENT);
        if(incIdx < 0  && decIdx < 0 ) {
            throw new UnhandledOperationInputException(String.format("ExpressionEvaluator.unary: %s is not an unary expression", expression));
        }

        String variable = getVariable(incIdx, decIdx, expression);
        int varIdx = expression.indexOf(variable);

        int result = 0;
        String unaryOperator = incIdx >= 0 ? Calculator.UNARY_INCREMENT : Calculator.UNARY_DECREMENT; //TODO
        boolean pre = varIdx > 0; // is the unary expression pre or post operand?

        result = fromUnary(variable, unaryOperator, pre, expression);
        //set var to its new value according to the unary operation
        çalculatedExpressionsMap.put(variable, fromUnary(variable, unaryOperator, true, expression));

        return result;
    }
    private static int fromUnary (String var, String unary, boolean pre, String expression) throws WrongCalculatorInputStreamException, UnhandledOperationInputException {

        if(!çalculatedExpressionsMap.containsKey(var)) {
            throw new WrongCalculatorInputStreamException(String.format("Wrong Input. The expression: %s has a variable=%s that haven't been calculated yet.",expression, var));
        }

        int value = çalculatedExpressionsMap.get(var);

        return Calculator.unary(unary, pre, value);
    }
    private static String getVariable(int incIdx, int decIdx, String expression) {
        // TODO: can use matcher with groups
        String var = null;
        int startIndex = 0;
        int endIndex = expression.length() -1;

        if(incIdx == BEGINNING_INDEX || decIdx == BEGINNING_INDEX) {
                startIndex = UNARY_SIZE;
        }
        else if(incIdx > BEGINNING_INDEX || decIdx > BEGINNING_INDEX) {
            startIndex = BEGINNING_INDEX;
            endIndex = endIndex - UNARY_SIZE;
        }

        var = expression.substring(startIndex, endIndex+1 /*add one since it is exclusive*/);
        return var;
    }

    public static String[] assignmentOperation(String key, String operation /*assignment operator*/ ) throws UnhandledOperationInputException {

        String[] reEvaluated = new String[NEW_SIZE];

        reEvaluated[0] = key;
        switch(operation) {
            case "+=":
                reEvaluated[1] = "+";
                break;

            case "-=":
                reEvaluated[1] = "-";
                break;

            case "*=":
                reEvaluated[1] = "*";
                break;

            case "/=":
                reEvaluated[1] = "/";
                break;

            default: //exception expected
                throw new UnhandledOperationInputException(String.format("Unexpected assignment operation %s", operation));

        }

        return reEvaluated;

    }
}
