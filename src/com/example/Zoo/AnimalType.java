package com.example.Zoo;

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