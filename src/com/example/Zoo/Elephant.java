package com.example.Zoo;

import java.util.concurrent.atomic.AtomicInteger;

public class Elephant extends Animal {

    private static final AtomicInteger count = new AtomicInteger(0);

    public Elephant(String _soundFileName) {
        super(_soundFileName);
        setInnerNumber(count.incrementAndGet());

    }

    @Override
    public void sayInnerNumber (){
        System.out.println(String.format("Elephant inner number is %s", getInnerNumber()));
    }

    @Override
    public void speak(){
        sayInnerNumber();
        playSound();
    }

}
