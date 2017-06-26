package com.example.Zoo;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by maayan.s on 12/20/16.
 */
public class ZooDriver {

    private final static int ZOO_SIZE = 10;
    private static Animal[] animals = new Animal[ZOO_SIZE];

    public static void main(String[] args) {

        createRandomAnimalsArray();
        speakAll();
        System.exit(0);

    }

    public static void createRandomAnimalsArray() {

        for(int i=0; i<ZOO_SIZE; i++){

            AnimalType randomType = RandomAnimalType.getRandomType();
            AnimalFactory factory  = randomType.getFactory();
            animals[i] = factory.getAnimal();
        }
    }

     public static void speakAll(){
        for(Animal a : animals){
            a.speak();
            sleep(5000);

         }
    }

    private static void sleep(long millis)
    {
        try{

            Thread.sleep(millis);
        }
        catch (InterruptedException e){
            System.out.println("sleep failure");
        }
    }



}



