package com.example.LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedList implements Iterable<Linkable> {

    private Linkable head;
    private Linkable tail;
    private int size;

    public LinkedList()
    {
            head = null;
            tail = null;
            size = 0;
        }

    public Iterator<Linkable> iterator ()
    {
        return new LinkableIterator();
    }

    public Linkable getTail() {
        return tail;
    }

    private void setTail(Linkable tail) {
        this.tail = tail;
    }

    public Linkable getHead() {
        return head;
    }

    private void setHead(Linkable head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public void insertAtHead(Linkable item) {

        if (item == null) throw new IllegalArgumentException("Linked List Can't Get Null Value at head");

        //assume item->next is null otherwise this is not valid
        item.setNext(head);
        setHead(item);

        if (getTail() == null) {
            setTail(item);
        }

        // make sure not to add twice
        size++;
    }

    public void insertAtTail(Linkable item) {

        if (item == null) throw new IllegalArgumentException("Linked List Can't Get Null Value at tail");
        //check for null

        if (tail == null) {
            setTail(item);
            tail.setNext(null);
            if (head == null) {
                setHead(item);
            }
        } else {
            tail.setNext(item);
            setTail(tail.getNext());
        }

        size++;
    }

    // return a ref to the first item in the linked list and remove it from the list. if there are no items in the list would return null.
    public Linkable removeFromHead() {

        if (head == null) return null;

        Linkable removed = head;
        head = head.getNext();
        if (head == null) {
            setTail(null);
        }
        if (removed.getNext() != null) {
            removed.setNext(null);
        }

        size--;
        return removed;
    }

    public class LinkableIterator implements Iterator<Linkable> {

        private Linkable cursor;

        public LinkableIterator(){
            // buggy wat if iterator is calle dwhen nothing is in the list
            this.cursor = LinkedList.this.getHead();
        }

        public boolean hasNext() {

            return this.cursor != null && this.cursor.getNext() != null;

        }

        public Linkable next() {
            if(this.hasNext()){
                Linkable current = cursor;
                cursor = current.getNext();
                return  current;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}