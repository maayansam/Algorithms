package com.example.HomeTest;

import java.io.Serializable;
import java.lang.String;
import java.util.Arrays;
import java.util.stream.Stream;


public class ExpressionSet implements Serializable {

        private static final int VARIABLE_INDEX = 0;
        private static final int EQUALITY_ASSIGNMENT_OPERATORS_INDEX = 1;
        private static final int EXPRESSION_START_INDEX = 2;

        private String parameter;
        private String[] expression;

        public ExpressionSet(String combinedExpression ) throws WrongCalculatorInputStreamException, UnhandledOperationInputException {
            splitToParameterAndExpression(combinedExpression);
        }

        public String getParameter() { return parameter; }

        public String[] getExpression() { return expression; }

        @Override
        public String toString() {

            return parameter + "=" + expression;
        }

        @Override
        public int hashCode() {

            return parameter.hashCode() * 17 /*diffrentiate key from value*/+ (expression == null ? 0 : expression.hashCode());
        }


        private void splitToParameterAndExpression(String s) throws WrongCalculatorInputStreamException, UnhandledOperationInputException {

            String[] tokens = Parser.splitFullExpression(s);
            String key = tokens[VARIABLE_INDEX];

            if(!Parser.isVariable(key)) {
                throw new WrongCalculatorInputStreamException(String.format("Non variable token %s in expression %s", key, s)) ;
            }

            String operation = tokens[EQUALITY_ASSIGNMENT_OPERATORS_INDEX]; // expect = or += -= /= etc.
            String[] exp  = Arrays.copyOfRange(tokens, EXPRESSION_START_INDEX, tokens.length);


            Parser.AssignmentEnum assignmt = Parser.isAssignment(operation);
            if (assignmt == Parser.AssignmentEnum.Arithmetic) {
              String[]  reEval =  ExpressionEvaluator.assignmentOperation(key, operation);
              exp = Stream.concat(Arrays.stream(reEval), Arrays.stream(exp)).toArray(String[]::new);
            }
            else if(assignmt != Parser.AssignmentEnum.Simple) {
                throw new WrongCalculatorInputStreamException(String.format("Invalid token in expression %s, where assignment operator is expected.", key, s)) ;
            }

            parameter = key;
            expression =  exp;
        }

}
