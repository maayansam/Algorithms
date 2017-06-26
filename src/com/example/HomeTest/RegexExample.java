package com.example.HomeTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    private static final String VAR_REGEX = "[a-zA-Z]\\w{0,100}";
    private static final String BIN_OPERATOR = "\\+|-|\\*|/|%|^";
    private static final String NUM_REGEX = "\\d*";

    //private static final String UNARY_REGEX =  "(\\w+(\\+{2}|-{2}))|((\\+{2}|-{2})\\w+)";
    //private static final Pattern UNARY_PATTERN = Pattern.compile(UNARY_REGEX);

    //private static final String PLUS_MINUS_REGEX = "((?<!\\+{1})\\+{1}(?!\\+{1}))|(?<!-{1})(-{1}(?!-{1}))";

    private static final String PLUS_SEPERATOR_REGEX = "(?<!\\+{1})\\+{1}(?!\\+{1}))";
    private static final String MINUS_SEPERATOR_REGEX = "(?<!-{1})(-{1}(?!-{1})";
    private static final String PLUS_MINUS_REGEX = "(" + PLUS_SEPERATOR_REGEX + "|" + MINUS_SEPERATOR_REGEX +")";


    private static final String NUMBERS_REGEX  = "\\d+";


    private static final String UNARY_PREFIX_OPERATOR = "(^(-{1})" + "((" + VAR_REGEX + ")" + "|(" + NUMBERS_REGEX + "))$)";
    private static final Pattern UNARY_PREFIX_PATTERN = Pattern.compile(UNARY_PREFIX_OPERATOR);

    private static final String UNARY_REGEX = "(" + VAR_REGEX + "(\\+{2}|-{2}))|((\\+{2}|-{2})" + VAR_REGEX +" )";
    private static final Pattern UNARY_PATTERN = Pattern.compile(UNARY_REGEX);
    private static final String DELIMITERS_REGEX = "[()\\/*%^=]";
    private static final String ASSIGNMENT_OPERATORS_REGEX = "(\\+{1}|-{1}|\\*{1}|\\/{1})(\\={1})";
    private static final String ASSIGNMENT_SPLIT_OPERATORS_REGEX = "((?<=" + ASSIGNMENT_OPERATORS_REGEX +")|(?=" + ASSIGNMENT_OPERATORS_REGEX +"))";

    private static final Pattern ASSIGNMENT_OPERATORS_PATTERN = Pattern.compile(ASSIGNMENT_OPERATORS_REGEX);
    private static final String FULL_SPLIT_REGEX = "(" + ASSIGNMENT_SPLIT_OPERATORS_REGEX + ")" + "|(" + DELIMITERS_REGEX + ")" +"|(" + UNARY_REGEX + ")" + "|(" + PLUS_MINUS_REGEX + ")";

    private static final String ILLEGAL_UNARY_REGEX = "(^\\d+\\w*(\\+{2}|-{2}))|(^(\\+{2}|-{2})\\d+\\w*)";
    private static final Pattern ILLEGAL_UNARY_PATTERN = Pattern.compile(ILLEGAL_UNARY_REGEX);

    private static final String ASSIGNMENT_REGEX = "(=)";
    private static final String ASSIGNMENTS_REGEX = "(" + ASSIGNMENT_REGEX + ")|(" + ASSIGNMENT_OPERATORS_REGEX + ")" ;


    private static final String SPACES_REGEX= "\\s*";
    private static final String ANYTHING_REGEX = ".*";
    private static final Pattern ASSIGNMENT_PATTERN = Pattern.compile(ASSIGNMENT_REGEX);

    //(?<assignment>(" + ASSIGNMENT_REGEX + ")|(" + ASSIGNMENT_OPERATORS_REGEX + "))";

    //(?<expression>(.))

    private static final String EXPRESSION_REGEX = "(" + SPACES_REGEX + ")(?<var>" + VAR_REGEX + ")(" + SPACES_REGEX + ")(?<assign>" + ASSIGNMENTS_REGEX + ")(" + SPACES_REGEX + ")(?<expression>" + ANYTHING_REGEX + ")";

    private static final Pattern VAR_PATTERN = Pattern.compile(VAR_REGEX);

    private static final String REGEX = "#\\w+";
    private static final String EXP_TEST = "(?<="+ REGEX+ ")|(?=" + REGEX + ")";

   /* public static void main(String[] args) {


       // Matcher matcher = ASSIGNMENT_OPERATORS_PATTERN.matches("y += 5 +2");
        //Matcher matcher =r ILLEGAL_UNARY_PATTERN.matcher("67676--");
        //Matcher matcher = ASSIGNMENT_PATTERN.matcher("=");
        //Matcher matcher = ASSIGNMENT_OPERATORS_PATTERN.matcher("+=");
        //System.out.println(matcher.matches());

        //todo validate 2 foolowing operators or operands are not valid
        //todo if someone put illegal char it is not valid look for illegal chars in the string like \..

        String a = "y += 5 +2";
        String[] str = splitExpression(a);

        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }


    }*/

    //check validation of +-

    public static String[] splitExpression(String str)  {

        String[] s1 = str.split(" ");
        List<String> list = new ArrayList<>();
        for (String s : s1) {
            String[] splitted = null;
            /*if(!ASSIGNMENT_OPERATORS_PATTERN.matcher(s).matches()) {
                splitted = s.split("(?<=" + FULL_SPLIT_REGEX + ")|(?=" + FULL_SPLIT_REGEX + ")");
                list.addAll(Arrays.asList(splitted));
             }
            else{
                list.add(s);

            }*/
            splitted = s.split("(?<="+ FULL_SPLIT_REGEX+ ")|(?=" + FULL_SPLIT_REGEX + ")");
            list.addAll(Arrays.asList(splitted));


        }

        //..String[] splitted = str.replaceAll("\\s+","").split("(?<=[()\\/*%^=+-])|(?=[()\\/*%^=+-])");

        String[] retArray = new String[list.size()];
        retArray = list.toArray(retArray);

        return retArray;



    }

    public static void main(String[] args) {

       // Matcher matcher = Pattern.compile(VAR_REGEX).matcher("a123&");
        //MatchResult matchResult = matcher.toMatchResult();


        //if(matcher.matches()) {

          // System.out.println("yes");
        //}

        String s = "#Séjour  Hôtel Sol Jandia Mar Appartements *** #Fuerteventura 19.avr.15 8j/7n(889€/p) ✈ #Paris #AllInClusive  http://t.co/YfEkoRPy6B";
        String[] splitted = s.split(EXP_TEST);
        List<String> ls = Arrays.asList(splitted);
        System.out.println(ls);
        System.exit(0);
    }

    //split by + if ++ exist attach it to either left or right

   // private static final String PLUSMINUS_REGEX = "([^+])(\\+{2})([^+])";//"(\\w+(\\+{2}|-{2}))|((\\+{2}|-{2})\\w+)";
   // private static final Pattern PLUSMINUS_PATTERN = Pattern.compile(PLUSMINUS_REGEX);

   // private static String[] splitPlusMinus(String s){

    //}



}
