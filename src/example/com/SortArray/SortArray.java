package example.com.SortArray;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

/**
 * Created by maayan.s on 12/14/16.
 */
public class SortArray {

    public static void main (String[] args){
        // Start with simple code
        int[] myArray = new int [20]; //put as const/final

        // Random initialization
        for (int i=0; i<20; i++)  {
            myArray[i]  = RandomRangeNumber.GetRand(1, 1000);
        }

        // try 2 ways of printing
        Display(myArray);
        // Sort

        Arrays.sort(myArray);
        //keep it imple. look also at display
        Arrays.toString(myArray);
        Display(myArray);

        System.exit(0);

    }

    private static void Display(int[] array){
        for (int t: array) {
            System.out.print(t + " ");

        }
        System.out.println("");

    }


    public static class RandomRangeNumber {
        public static int GetRand(int min, int max){
            //validation
            if(min>=max)
                throw new InvalidParameterException(" min is bigger than max");
            Random ran =  new Random(); //need int
            int range = max - min +1;
            int retRan = ran.nextInt(range) + min;
            return retRan;
        }
    }



}

