package com.example.LinkedList;

/**
 * Created by maayan.s on 12/22/16.
 */
public class TigerFactory extends LinkableFactory<Tiger> {

    private final static String TIGER_SOUND_FILE = "/animals027.wav";
    @Override
    public Tiger getLinkable() { return new Tiger(TIGER_SOUND_FILE); }
}
