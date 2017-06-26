package com.example.LinkedList;

public class TigerFactory extends LinkableFactory<Tiger> {

    private final static String TIGER_SOUND_FILE = "/animals027.wav";
    @Override
    public Tiger getLinkable() { return new Tiger(TIGER_SOUND_FILE); }
}
