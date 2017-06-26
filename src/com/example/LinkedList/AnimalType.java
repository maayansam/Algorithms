package com.example.LinkedList;


public enum AnimalType {

    TIGER(new TigerFactory()), ELEPHANT(new ElephantFactory());

    private final LinkableFactory factory;

    AnimalType(LinkableFactory factory) {
        this.factory = factory;
    }

    public LinkableFactory getFactory() {
        return factory;
    }
}