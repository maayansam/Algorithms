package com.example.Zoo;

import java.util.concurrent.atomic.AtomicInteger;


public class Tiger extends Animal {

    private static final AtomicInteger count = new AtomicInteger(0);

    public Tiger(String _soundFileName) {
        super(_soundFileName);
        setInnerNumber(count.incrementAndGet());

    }


    @Override
    public void sayInnerNumber (){
        System.out.println(String.format("Tiger inner number is %s", getInnerNumber()));
    }

    @Override
    public void speak(){
        sayInnerNumber();
        playSound();
        warning();
    }

    public void warning(){
        System.out.println("Tiger Warning");
    }


}
