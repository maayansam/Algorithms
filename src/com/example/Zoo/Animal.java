package com.example.Zoo;

import java.util.concurrent.atomic.AtomicInteger;
import java.io.*;
import sun.audio.*;

public abstract class Animal {

    private final static int DEAFUALT_INNER = 0;
    private int innerNumber;
    private final String soundFileName;

    public Animal(String _soundFileName) {
        innerNumber = DEAFUALT_INNER;
        soundFileName = _soundFileName;
    }

    // accessors
    public String getSoundFileName() {
        return soundFileName;
    }
    public int getInnerNumber() {
        return innerNumber;
    }
    public void setInnerNumber(int innerNumber) {
        this.innerNumber = innerNumber;
    }

    // abstracts
    public abstract void speak(); //this is just for the practice since we use same functions in it this is not necessary
    public abstract void sayInnerNumber ();

    //common
    public void playSound (){
        try{
            InputStream inputStream = getClass().getResourceAsStream(getSoundFileName());
            AudioStream audioStream = new AudioStream(inputStream);
            AudioPlayer.player.start(audioStream);
        }
        catch(Exception e) {

            System.out.println(String.format("Can't play sound for %s", getSoundFileName()));
        }
    }



}
