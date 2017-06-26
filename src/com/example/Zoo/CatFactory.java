package com.example.Zoo;

/**
 * Created by maayan.s on 12/22/16.
 */
public class CatFactory extends AnimalFactory {

    private final static String CAT_SOUND_FILE = "/animals020.wav";

    @Override
    public Animal getAnimal(){
        return new Cat(CAT_SOUND_FILE);
    }
}
