package com.example.Zoo;

/**
 * Created by maayan.s on 12/21/16.
 */
public enum AnimalType {

    CAT(new CatFactory()), DOG(new DogFactory()), TIGER(new TigerFactory()), ELEPHANT(new ElephantFactory());

    private final AnimalFactory factory;

    AnimalType(AnimalFactory factory) {
        this.factory = factory;
    }

    public AnimalFactory getFactory() {
        return factory;
    }
}