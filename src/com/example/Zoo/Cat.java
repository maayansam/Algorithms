package com.example.Zoo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by maayan.s on 12/20/16.
 */
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
