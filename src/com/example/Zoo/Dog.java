package com.example.Zoo;

import java.util.concurrent.atomic.AtomicInteger;


public class Dog extends Animal {

    private static final AtomicInteger count = new AtomicInteger(0);

    public Dog(String _soundFileName) {

        super(_soundFileName);
        setInnerNumber(count.incrementAndGet());
    }

    @Override
    public void sayInnerNumber (){
        System.out.println(String.format("Dog inner number is %s", getInnerNumber()));
    }
    @Override
    public void speak(){
        sayInnerNumber();
        playSound();
    }

}
