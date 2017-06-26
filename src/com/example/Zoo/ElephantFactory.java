package com.example.Zoo;

/**
 * Created by maayan.s on 12/22/16.
 */
public class ElephantFactory extends AnimalFactory {

    private final static String ELEPHANT_SOUND_FILE = "/animals129.wav";

    @Override
    public Animal getAnimal(){
        return new Elephant(ELEPHANT_SOUND_FILE);
    }
}
