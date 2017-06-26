package com.example.Algo.Premutation;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by maayan.s on 4/15/17.
 */
public class premuteFromNumberToNumberConstraint {
// Assume input is correct
    public static void printWithConstraint(int startNum, int endNum) {
        String start = String.valueOf(startNum);
        String end = String.valueOf(endNum);
        boolean[] used = new boolean[10];
        StringBuffer buffer = new StringBuffer();
        int length = start.length();
        premute(start, end, length, 0, used, buffer);
    }

    public static void premute(String start, String end, int length, int position, boolean[] used, StringBuffer buffer) {

        if(length == position) {
            System.out.println(buffer);
        }

        Set<String> allowedChars = allowedNums(start, end, position);

        for (String s : allowedChars) {
            if(used[Integer.parseInt(s)])
                continue;
            buffer.append(s);
            used[Integer.parseInt(s)] = true;
            premute(start, end, length, position+1, used, buffer);
            buffer.setLength(buffer.length() -1);
            used[Integer.parseInt(s)] = false;
        }
    }

    public static Set<String> allowedNums(String start, String end, int position) {
        //assume that input is correct, same length, and position is less then length
        String s = (position < start.length()) ? start.substring(position, position+1) : start.substring(start.length()-1);
        String e = (position < end.length()) ? end.substring(position, position+1) : end.substring(end.length()-1);
        int num = Integer.parseInt(s);
        int num2 = Integer.parseInt(e);
        Set<String> numbers = new HashSet<String>();

        for (int i = num; i <= num2; i++) {
            numbers.add(String.valueOf(i));
        }
        return numbers;

    }

    public static void main(String[] args){
        printWithConstraint(1000000000,1299999999 );
    }
}

//1000000000 to 1598670980 options 1,2,3,4,5,6,7,8,9,0 for each char.
//first char can start only with 1 and later each one we used we need not to use anymorefor the second one only from 0-5,
//get possible numbers
// and also check within boolean used[] if it is already used or not if yes continue.
