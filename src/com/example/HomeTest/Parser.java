package com.example.HomeTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by maayan.s on 1/16/17.
 */
public class Parser {

    private static final String VAR_REGEX = "[a-zA-Z]\\w{0,100}";
    private static final Pattern VAR_PATTERN = Pattern.compile(VAR_REGEX);

    private static final String ASSIGNMENT_OPERATORS_REGEX = "(\\+{1}|-{1}|\\*{1}|\\/{1})(\\={1})";
    private static final Pattern ASSIGNMENT_OPERATORS_PATTERN = Pattern.compile(ASSIGNMENT_OPERATORS_REGEX);

    private static final String UNARY_REGEX = "(^" + VAR_REGEX + "(\\+{2}|-{2})$)|(^(\\+{2}|-{2})" + VAR_REGEX + "$)";
    private static final Pattern UNARY_PATTERN = Pattern.compile(UNARY_REGEX);

    private static final String NUMBER_REGEX  = "(-{0,1})\\d+";
    private static final Pattern NUMBER_PATTERN = Pattern.compile(NUMBER_REGEX);

    // find only single plus, without adjacent + signs
    private static final String PLUS_SEPERATOR_REGEX = "(?<!\\+{1})\\+{1}(?!\\+{1}))";
    private static final String MINUS_SEPERATOR_REGEX = "(?<!-{1})(-{1}(?!-{1})";
    private static final String PLUS_MINUS_REGEX = "(" + PLUS_SEPERATOR_REGEX + "|" + MINUS_SEPERATOR_REGEX +")";

    private static final String DELIMITERS_REGEX = "[()\\/*%^]";
    private static final String FULL_SPLIT_REGEX = "(" + DELIMITERS_REGEX + ")" +"|(" + UNARY_REGEX + ")" + "|(" + PLUS_MINUS_REGEX + ")";

    private static final String ILLEGAL_UNARY_REGEX = "(^\\d+\\w*(\\+{2}|-{2}))|(^(\\+{2}|-{2})\\d+\\w*)";
    private static final Pattern ILLEGAL_UNARY_PATTERN = Pattern.compile(ILLEGAL_UNARY_REGEX);

    private static final String ASSIGNMENT_REGEX = "(=)";
    private static final Pattern ASSIGNMENT_PATTERN = Pattern.compile(ASSIGNMENT_REGEX);
    private static final String ASSIGNMENTS_REGEX = "(" + ASSIGNMENT_REGEX + ")|(" + ASSIGNMENT_OPERATORS_REGEX + ")" ;


    private static final String SPACES_REGEX= "\\s*";
    private static final String ANYTHING_REGEX = ".*";
    private static final String EXPRESSION_REGEX = "(" + SPACES_REGEX + ")(?<var>" + VAR_REGEX + ")(" + SPACES_REGEX + ")(?<assign>" + ASSIGNMENTS_REGEX + ")(" + SPACES_REGEX + ")(?<expression>" + ANYTHING_REGEX + ")";
    private static final Pattern EXPRESSION_PATTERN = Pattern.compile(EXPRESSION_REGEX);
    
    public enum AssignmentEnum {
        Simple, Arithmetic, None
    }

    public static boolean isNumber(String token) {
        Matcher matcher = NUMBER_PATTERN.matcher(token);
        return matcher.matches();
    }

    public static boolean isVariable(String token) {
        Matcher matcher = VAR_PATTERN.matcher(token);
        return matcher.matches();
    }

    public static boolean isUnary(String token) {
        Matcher matcher = UNARY_PATTERN.matcher(token);
        return matcher.matches();
    }

    public static AssignmentEnum isAssignment(String token) {
        Matcher matcher = ASSIGNMENT_PATTERN.matcher(token);
        Matcher operatorAssignmentMatcher = ASSIGNMENT_OPERATORS_PATTERN.matcher(token);
        return matcher.matches() ? AssignmentEnum.Simple : (operatorAssignmentMatcher.matches() ? AssignmentEnum.Arithmetic :AssignmentEnum.None);
    }

    public static String[] splitFullExpression(String str)  throws WrongCalculatorInputStreamException {

        Matcher matcher = EXPRESSION_PATTERN.matcher(str);

        List<String> list = new ArrayList<>();
        if(matcher.matches()) {

            list.add(matcher.group("var").trim());
            list.add(matcher.group("assign").trim());

            String expression = matcher.group("expression");

            List<String> splitted = splitExpression(expression);
            list.addAll(splitted);
            String[] retArray = new String[list.size()];
            retArray = list.toArray(retArray);
            return retArray;
        }
        else {
            throw new WrongCalculatorInputStreamException(String.format("This expression is not supprted: %s", str));
        }
    }

    private static List<String> splitExpression(String expression){

        String[] s1 = expression.split(" ");
        List<String> list = new ArrayList<>();
        for (String s : s1) {
            String[] splitted = s.split("(?<="+ FULL_SPLIT_REGEX+ ")|(?=" + FULL_SPLIT_REGEX + ")");
            list.addAll(Arrays.asList(splitted));
        }

        return list;
    }


}
