package com.example.LinkedList;

/**
 * Created by maayan.s on 12/25/16.
 */

public class ElephantFactory extends LinkableFactory<Elephant> {

    private final static String ELEPHANT_SOUND_FILE = "/animals129.wav";

    @Override
    public Elephant getLinkable(){
        return new Elephant(ELEPHANT_SOUND_FILE);
    }
}
