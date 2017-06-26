package com.example.Zoo;


public class CatFactory extends AnimalFactory {

    private final static String CAT_SOUND_FILE = "/animals020.wav";

    @Override
    public Animal getAnimal(){
        return new Cat(CAT_SOUND_FILE);
    }
}
