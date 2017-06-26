package com.example.helloworld;

import com.google.common.base.Preconditions;
import java.util.Random;
import java.security.*;


public class HelloWorld {

    public static void main (String[] args){

        final String myName = args[0];

        int randomPrint = RandomRangeNumber.GetRand(1,10);

        for(int i=0; i<randomPrint; i++) {
            System.out.println(String.format("Message %d hello world, good morning my name is: %s", i, myName));
        }
    }

    public static class RandomRangeNumber {
        public static int GetRand(int min, int max){
            Preconditions.checkState(max > min, "min is bigger than max");
            Random ran =  new Random(); //need int
            int range = max - min +1;
            int retRan = ran.nextInt(range) + min;
            return retRan;
        }
    }
}
