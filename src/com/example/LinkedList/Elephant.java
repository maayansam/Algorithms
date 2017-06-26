package com.example.LinkedList;

/**
 * Created by maayan.s on 12/22/16.
 */

import java.util.concurrent.atomic.AtomicInteger;

public class Elephant extends Animal  {

    private Linkable next;

    private static final AtomicInteger count = new AtomicInteger(0);

    // returns the next element in the list
    public Linkable getNext(){
        return next;
    }

    // sets the next element in the list
    public void setNext(Linkable node){
        next =  node;
    }

    public Elephant(String _soundFileName) {
        super(_soundFileName);
        next = null;
        setInnerNumber(count.incrementAndGet());
    }

    @Override
    public void sayInnerNumber (){
        System.out.println(getName());
    }

    @Override
    public String getName(){
        return String.format("Elephant inner number is %s", getInnerNumber());
    }


}
