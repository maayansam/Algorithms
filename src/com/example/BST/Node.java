package com.example.BST;


public class Node {

    private int value;
    private Node left;
    private Node right;

    public Node(int data) {
        this.value = data;
        left = null;
        right = null;
    }

    public int getValue() {
        return value;
    }
    public Node getLeft() {
        return left;
    }
    public Node getRight() {
        return right;
    }
    public void setValue(int data){
        this.value = data;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
    public void setRight(Node right){
        this.right = right;
    }
}
