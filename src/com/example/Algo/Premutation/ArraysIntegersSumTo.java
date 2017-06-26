package com.example.Algo.Premutation;

import java.util.ArrayList;


// aimed to solve the question: Determine if any 3 integers in an array sum to 0.
// can use an integer more than once
public class ArraysIntegersSumTo {

    public static boolean isAnySumTo(int numOfIntegers, int sumToNum, int[] options) {

        int[] resultSum = new int[numOfIntegers];//buffer need to restart all with 0 assume it is
        int position = 0;
        return premute (options,sumToNum, resultSum, position);
    }

    public static boolean premute(int[] options, int sumToNum, int[] resultSum, int position) {

        if(position == resultSum.length) {
            if(sum(resultSum) == sumToNum) {
                return true;
            }
            else
                return false;
        }
        for(int i=0; i<options.length; i++){
            int curr = options[i];
            resultSum[position] = curr;
            if(premute(options, sumToNum, resultSum, position+1)) {
                return true;
            }
        }
        return false;
    }

    private static int sum(int[] array) {
        if(array == null)
            return 0;
        int sum = 0;
        for(int i=0; i< array.length; ++i) {
            sum+=array[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] options = {1, -5,10};
        int sumToNum = 0;
        if(isAnySumTo(3,0, options)){
            System.out.println("yes!");
        }
        else
            System.out.println("no!");
    }

}
