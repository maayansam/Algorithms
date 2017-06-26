package com.example.LinkedList;

public interface Linkable {

    // returns the next element in the list
    public Linkable getNext();
    // sets the next element in the list
    public void setNext(Linkable node);

    public String getName();
}
