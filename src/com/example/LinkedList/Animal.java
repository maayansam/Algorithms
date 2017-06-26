package com.example.LinkedList;


/**
 * Created by maayan.s on 12/20/16.
 */
public abstract class Animal implements Linkable {

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


    //common
    public abstract void sayInnerNumber ();



}
