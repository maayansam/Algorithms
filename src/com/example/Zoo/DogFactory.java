package com.example.Zoo;

public class DogFactory extends AnimalFactory {

    private final static String DOG_SOUND_FILE = "/animals110.wav";

    @Override
    public Animal getAnimal(){
        return new Dog(DOG_SOUND_FILE);
    }
}
