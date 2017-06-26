package com.example.RandomLines;

import java.util.List;
import java.util.Random;

/**
 * Created by maayan.s on 1/3/17.
 */
public class ListPrintService {

    private static String getRandomLine(List<String> list, int bound) {
        Random random = new Random();
        int index = random.nextInt(bound);
        return list.get(index);
    }

    public static void printRandomLine(List<String> list, int bound) {

        System.out.println(getRandomLine(list, bound));
    }


}
