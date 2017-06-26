package com.example.Zoo;

import java.util.concurrent.atomic.AtomicInteger;


public class Cat extends Animal  {

    private static final AtomicInteger count = new AtomicInteger(0);
    public Cat(String _soundFileName) {
        super(_soundFileName);
        setInnerNumber(count.incrementAndGet());
    }

    @Override
    public void sayInnerNumber (){
        System.out.println(String.format("Cat inner number is %s", getInnerNumber()));
    }

    @Override
    public void speak(){
        sayInnerNumber();
        playSound();
    }

}
