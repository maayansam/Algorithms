package com.example.HomeTest;

import java.util.*;

public class ShuntingYard {

    private Queue<String> output = null;
    private Stack<String> operatorsStack = null;
    private ExpectedTokenEnum expectedToken = ExpectedTokenEnum.Operand;
    private String formerToken = null;
    private boolean negativePrefix = false;

    private static final String RIGHT_PARENTHESIS = ")";
    private static final String LEFT_PARENTHESIS = "(";
    private static final String NEGATIVE_PREFIX = "-";

    // Associativity constants for operators
    public static final int LEFT_ASSOC = 0;
    public static final int RIGHT_ASSOC = 1;

    private static final String ILLEGAL_EXP_MSG = "Illegal combination in your input: %s";

    public  static HashMap<String, Operator> OPERATORS = new HashMap<String, Operator>();
    static {
        // Map<"token", []{precendence, associativity}>
        OPERATORS.put("+", new Operator("+", 0, LEFT_ASSOC  ));
        OPERATORS.put("-", new Operator("-", 0, LEFT_ASSOC  ));
        OPERATORS.put("*", new Operator("*", 5, LEFT_ASSOC  ));
        OPERATORS.put("/", new Operator("/", 5, LEFT_ASSOC  ));
        OPERATORS.put("%", new Operator("%", 5, LEFT_ASSOC  ));
        OPERATORS.put("^", new Operator("^", 10, RIGHT_ASSOC));
    }

    private enum ExpectedTokenEnum {
        Operand, Operator
    }

    private enum TokenType {
        Number, Variable, Unary, Binary, OpenParenthesis, CloseParenthesis
    }

    public ShuntingYard() {
        reset();
    }

    private void reset() {
        output = new LinkedList();
        operatorsStack = new Stack<String>();
        expectedToken = ExpectedTokenEnum.Operand;
        formerToken = null;
        negativePrefix = false;
    }

    private Queue<String> infixToPostfix(String[] inputTokens) throws UnhandledOperationInputException, MismatchedParenthesesException, WrongCalculatorInputStreamException {

        for(int i=0; i < inputTokens.length; i++){

            // read a token
            String s = inputTokens[i];
            String token = s.trim();

            // if the token represent an unary expression, evaluate it.
            if(Parser.isUnary(token)) {
                if(negativePrefix){
                    throw new WrongCalculatorInputStreamException(String.format(ILLEGAL_EXP_MSG + " token is after '-' negative prefix where only a varibale or a number is expected", token));
                }
                token = evaluateUnaryExpression(token); // now continue with this value to be added to the prefix
            }
            // if the token is a number than push it to the output queue
            if (numberHandling(token)) {
                expectedToken = ExpectedTokenEnum.Operator;

            }
            else if(variableHandling(token)) {
                expectedToken = ExpectedTokenEnum.Operator;

            }
            else if (operatorHandling(token)) {
                expectedToken = ExpectedTokenEnum.Operand;
                if(negativePrefixHandling(token)){
                    negativePrefix = true;
                }
            }
            // If the token is a left parenthesis (i.e. "("), then push it onto the stack.
            else if(leftParenthesisHandling(token)) {
                expectedToken = ExpectedTokenEnum.Operand;

            }
            // If the token is a right parenthesis (i.e. ")")
            else if(rightParenthesisHandling(token)) {
                expectedToken = ExpectedTokenEnum.Operator;
            }
            else {
                throw new WrongCalculatorInputStreamException(String.format(ILLEGAL_EXP_MSG, token));
            }

            formerToken = token;

        } // no more tokens to read
        popOperatorsToOutputQueue();

       return output;
    }

    private boolean validateExpression(TokenType currType) {

        switch(currType){
            case Number:
                if(!expectedToken.equals(ExpectedTokenEnum.Operand))
                    return false;
                break;
            case Variable:
                if(!expectedToken.equals(ExpectedTokenEnum.Operand))
                    return false;
                break;
            //TODO: this is probably redundant
            case Unary:
                if(!expectedToken.equals(ExpectedTokenEnum.Operand))
                    return false;
                break;
            case Binary:
                if(!expectedToken.equals(ExpectedTokenEnum.Operator))
                    return false;
                break;
            case OpenParenthesis:
                if(!expectedToken.equals(ExpectedTokenEnum.Operand))
                    return false;
                break;
            case CloseParenthesis:
                if(!expectedToken.equals(ExpectedTokenEnum.Operator))
                    return false;
                break;
            default:
                return false;
        }
        return true;
    }

    private void popOperatorsToOutputQueue() throws MismatchedParenthesesException {
        // While there are still operator tokens in the stack:
        while(operatorsStack.size() > 0) {

            String tk = operatorsStack.peek();
            if(tk.equals(LEFT_PARENTHESIS) || tk.equals(RIGHT_PARENTHESIS)) {
                // If the operator token on the top of the stack is a parenthesis, then there are mismatched parentheses.
                throw new MismatchedParenthesesException();
            }
            else {
                // Pop the operator onto the output queue.
                output.add(operatorsStack.pop());
            }
        }
    }

    private boolean negativePrefixHandling(String token) {
        if(token.equals(NEGATIVE_PREFIX)){
            if(formerToken == null || isOperator(formerToken) ||  formerToken.equals(LEFT_PARENTHESIS)) {
                return true;
            }
        }
        return false; //binary operator
    }

    private boolean operatorHandling(String token) throws WrongCalculatorInputStreamException {

        boolean isO1Operator = isOperator(token);
        if(negativePrefixHandling(token)){
            return true;
        }

        if(isO1Operator) {
            if(negativePrefix){
                negativePrefix = false;
                throw new WrongCalculatorInputStreamException(String.format(ILLEGAL_EXP_MSG + "operator token is after '-' negative prefix", token));
            }

            if(!validateExpression(TokenType.Binary)) {
                throw new WrongCalculatorInputStreamException(String.format("Binary operator: %s exist where %s is expected", token, expectedToken.toString()));
            }

            if(operatorsStack.size() > 0) {

                Operator o1 = OPERATORS.get(token);

                boolean isO2Operator = isOperator(operatorsStack.peek());
                if(isO2Operator) {
                    Operator o2 = OPERATORS.get(operatorsStack.peek());
                    boolean isO1leftAssociative = o1.getAssociativity() == LEFT_ASSOC;
                    int o1Precedence = o1.getPrecedence();
                    int o2Precedence = o2.getPrecedence();
                    // while there is an operator token o2, at the top of the operator stack
                    // and either o1 is left-associative and its precedence is less than or equal to that of o2,
                    // or o1 is right associative, and has precedence less than that of o2,
                    while (operatorsStack.size() > 0 && isO2Operator && ((isO1leftAssociative && o1Precedence <= o2Precedence) || (!isO1leftAssociative && o1Precedence < o2Precedence))) {
                        //pop o2 off the operator stack, onto the output queue;
                        output.add(operatorsStack.pop());
                        if(operatorsStack.size() > 0) {
                            o2 = OPERATORS.get(operatorsStack.peek());
                            isO2Operator = isOperator(operatorsStack.peek());
                            o2Precedence = o2.getPrecedence();
                        }
                    }
                }
            }

            // at the end of iteration push o1 onto the operator stack.
            operatorsStack.push(token);
            return true;
        }
        return false;
    }

    private boolean numberHandling(String token) throws WrongCalculatorInputStreamException {
        if(Parser.isNumber(token)) {
            if(!validateExpression(TokenType.Number)) {
                throw new WrongCalculatorInputStreamException(String.format("Number: %s exist where %s is expected", token, expectedToken.toString()));
            }
            if(negativePrefix){
                negativePrefix = false;
                output.add(String.valueOf(negativeCalc(token)));
            }
            else{
                output.add(token);
            }
            return true;
        }
        return false;
    }

    private String negativeCalc(String token) {
        int curr = Integer.valueOf(token);
        return String.valueOf(curr*(-1));
    }

    private boolean variableHandling(String token) throws WrongCalculatorInputStreamException {

        if(Parser.isVariable(token)) { // pattern matching

            if(!validateExpression(TokenType.Variable)) {
                throw new WrongCalculatorInputStreamException(String.format("Variable: %s exists where %s is expected", token, expectedToken.toString()));
            }

            try {
                int value = ExpressionEvaluator.expressionsMap().get(token);
                if(negativePrefix){
                    negativePrefix = false;
                    String numStr = String.valueOf(value);
                    output.add(String.valueOf(negativeCalc(numStr)));

                }
                else {
                    output.add(String.valueOf(value));
                }
                return true;
            }
            catch(Exception e) {
                //operand value doesn't exist in the map, something went wrong..
                throw new WrongCalculatorInputStreamException(String.format("Invalid expression. Token %s cannot be evaluated.", token));
            }
        }
        return false;
    }

    private boolean isOperator(String token) {
        if(token == null)
            return false;
        return OPERATORS.containsKey(token);
    }

    private String evaluateUnaryExpression (String token) throws WrongCalculatorInputStreamException, UnhandledOperationInputException {
        //TODO: what's valid used for?
        boolean valid = true;

        int value = ExpressionEvaluator.unary(token);

        if(valid) {
            return String.valueOf(value);
        }
        else
            return token;
    }

    private boolean rightParenthesisHandling(String token) throws UnhandledOperationInputException, MismatchedParenthesesException {

        boolean isRightParenthesis = token.equals(RIGHT_PARENTHESIS);

        if(isRightParenthesis) {

            if(negativePrefix){
                negativePrefix = false;
                throw new MismatchedParenthesesException(String.format(ILLEGAL_EXP_MSG + "close parenthesis token is after '-' negative prefix", token));
            }

            if(!validateExpression(TokenType.CloseParenthesis)) {
                throw new MismatchedParenthesesException(String.format("Close parenthesis persists where %s expected", expectedToken.toString()));
            }

            // Until the token at the top of the stack is a left parenthesis, pop operators off the stack onto the output queue.
            while(operatorsStack.size() > 0 && !operatorsStack.peek().equals(LEFT_PARENTHESIS)) {
                output.add(operatorsStack.pop());
            }
            // Pop the left parenthesis from the stack, but not onto the output queue.
            if( operatorsStack.size() > 0 && operatorsStack.peek().equals(LEFT_PARENTHESIS)) {
                String lp = operatorsStack.pop();
            }
            else {            //TODO: can't handle input containing %
                // If the stack runs out without finding a left parenthesis, then there are mismatched parentheses.
                throw new UnhandledOperationInputException(String.format("Mismatch number of parenthesis in your expression. Right parenthesis exist with no matching left parenthesis"));
            }

            // If the token at the top of the stack is a function token, pop it onto the output queue.
        }
        return isRightParenthesis;
    }

    private boolean leftParenthesisHandling(String token) throws MismatchedParenthesesException{
        if(token.equals(LEFT_PARENTHESIS)) {
            if(negativePrefix){
                negativePrefix = false;
                throw new MismatchedParenthesesException(String.format(ILLEGAL_EXP_MSG + "open parenthesis token is after '-' negative prefix", token));
            }
            if(!validateExpression(TokenType.OpenParenthesis)) {
                throw new MismatchedParenthesesException(String.format("Open parenthesis persists where %s expected", expectedToken.toString()));
            }
            operatorsStack.push(token);
            return true;
        }
        return false;
    }

    public Integer calculate (String[] inputTokens) throws UnhandledOperationInputException, WrongCalculatorInputStreamException, MismatchedParenthesesException {

        reset();
        Postfix postfix = new Postfix(infixToPostfix(inputTokens));
        if(postfix.size() > 0) {
            return postfix.evaluate();
        }

        return null;
    }

    public class Postfix {

        private Queue<String> expression;

        public Postfix(Queue<String> postfixExpression) {
            this.expression = postfixExpression;
        }

        public int evaluate() throws UnhandledOperationInputException, WrongCalculatorInputStreamException {

            if (!valid()) {
                throw new UnhandledOperationInputException(String.format("Postfix.evaluate: something went wrong expression is not valid s", this.expression));
            }

            // here can also be i, j etc.
            if (size() == 1) {
                return Integer.valueOf(expression.poll());
            }

            Stack<String> stack = new Stack<String>();
            while (size() > 0) {
                // read token
                String token = expression.poll();
                boolean isOperator = isOperator(token);

                if (isOperator) {
                    if(stack.size() < 2) {
                        throw new UnhandledOperationInputException(String.format("Invalid Expression. The token: %s doesn't have matching operands.",  token));
                    }
                    String operand1 = stack.pop();
                    String operand2 = stack.pop();
                    int res = Calculator.run(operand2, operand1, token);
                    stack.push(String.valueOf(res));

                } else {//meaning we have an operand/number in hand
                    stack.push(token);
                }

            }

            return Integer.valueOf(stack.pop());
        }

        public int size() {
            if(!valid())
                return 0;
            return expression.size();
        }
        private boolean valid() {
            if(expression == null || expression.size() <= 0 ) {
                return false;
            }
            return true;
        }

    }

}
