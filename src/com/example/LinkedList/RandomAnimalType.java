package com.example.LinkedList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomAnimalType {

    private static final int MINIMUM_INDEX = 0;
    private static Random random = new Random();

    private static final List types = new ArrayList(AnimalType.values().length);
    static {
        for (AnimalType type : AnimalType.values()) {
            types.add(type);
        }
    }

    public static AnimalType at (int index){
        return (AnimalType)types.get(index);
    }

    public static AnimalType getRandomType(){

        int max = AnimalType.values().length - 1;
        int min = MINIMUM_INDEX;
        int randomNum = random.nextInt((max-min) +1 ) + min;
        return at(randomNum);

    }
}
