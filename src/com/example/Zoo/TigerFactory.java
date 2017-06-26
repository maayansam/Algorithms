package com.example.Zoo;

/**
 * Created by maayan.s on 12/22/16.
 */
public class TigerFactory extends AnimalFactory {

    private final static String TIGER_SOUND_FILE = "/animals027.wav";
    @Override
    public Animal getAnimal(){
        return new Tiger(TIGER_SOUND_FILE);

    }
}
