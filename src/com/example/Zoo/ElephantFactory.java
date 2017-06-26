package com.example.Zoo;


public class ElephantFactory extends AnimalFactory {

    private final static String ELEPHANT_SOUND_FILE = "/animals129.wav";

    @Override
    public Animal getAnimal(){
        return new Elephant(ELEPHANT_SOUND_FILE);
    }
}
