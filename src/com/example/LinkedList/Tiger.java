package com.example.LinkedList;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by maayan.s on 12/20/16.
 */

// TODO: Animal should be Linkable
public class Tiger extends Animal  {

    private static final AtomicInteger count = new AtomicInteger(0);

    private Linkable next;

    // returns the next element in the list
    public Linkable getNext(){
        return next;
    }

    // sets the next element in the list
    public void setNext(Linkable node){
        next =  node;
    }

    public Tiger(String _soundFileName) {
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
        return String.format("Tiger inner number is %s", getInnerNumber());
    }



}