package com.example.LinkedList;

/**
 * Created by maayan.s on 12/21/16.
 */
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