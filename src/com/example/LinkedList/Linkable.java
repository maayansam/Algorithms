package com.example.LinkedList;

/**
 * Created by maayan.s on 12/22/16.
 */
public interface Linkable {

    // returns the next element in the list
    public Linkable getNext();
    // sets the next element in the list
    public void setNext(Linkable node);

    public String getName();
}
