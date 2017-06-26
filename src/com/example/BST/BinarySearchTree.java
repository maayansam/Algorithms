package com.example.BST;

/**
 * Created by maayan.s on 3/25/17.
 */
/*public class BinarySearchTree {

    Node root;

    public BinarySearchTree() {
        root = null;
    }

    // running time the number of times we repeatedly halve,
    // starting at n nn, until we get the value 1: the base-2 logarithm of n nn. We write it as \lg n lgn.
    public void add(int value) {

        if (root == null) {
            root = new Node(value);
            return;
        }

        Node newNode = new Node(value);
        Node curr = root;
        while(curr != null) {
            if(curr.getValue() == value){
                return;
            }
            else if(curr.getValue() > value) {
                if(curr.getLeft()!= null) {
                    curr = curr.getLeft();
                }
                else {
                    curr.setLeft(newNode);
                    return;
                }
            }
            else if(curr.getValue() < value) {
                if(curr.getRight() != null) {
                    curr = curr.getRight();
                }
                else {
                    curr.setRight(newNode);
                    return;
                }
            }
        }

    }

        // first find it then delete
        // when you find need to know if have no kids 1 kid 2 kids, need to know to found successor, remmeber if you are on the left side or right side
        public boolean delete(int id){
            Node parent = root;
            Node current = root;
            boolean isLeftChild = false;
            while(current.getValue()!=id){
                parent = current;
                if(current.getValue()>id){
                    isLeftChild = true;
                    current = current.getLeft();
                }else{
                    isLeftChild = false;
                    current = current.getRight();
                }
                if(current ==null){
                    return false;
                }
            }
            //if i am here that means we have found the node
            //Case 1: if node to be deleted has no children
            if(current.left==null && current.right==null){
                if(current==root){
                    root = null;
                }
                if(isLeftChild ==true){
                    parent.left = null;
                }else{
                    parent.right = null;
                }
            }
            //Case 2 : if node to be deleted has only one child
            else if(current.right==null){
                if(current==root){
                    root = current.left;
                }else if(isLeftChild){
                    parent.left = current.left;
                }else{
                    parent.right = current.left;
                }
            }
            else if(current.left==null){
                if(current==root){
                    root = current.right;
                }else if(isLeftChild){
                    parent.left = current.right;
                }else{
                    parent.right = current.right;
                }
            }else if(current.left!=null && current.right!=null){

                //now we have found the minimum element in the right sub tree
                Node successor	 = getSuccessor(current);
                if(current==root){
                    root = successor;
                }else if(isLeftChild){
                    parent.left = successor;
                }else{
                    parent.right = successor;
                }
                successor.left = current.left;
            }
            return true;
        }

        public Node getSuccessor(Node deleleNode){
            Node successsor =null;
            Node successsorParent =null;
            Node current = deleleNode.right;
            while(current!=null){
                successsorParent = successsor;
                successsor = current;
                current = current.left;
            }
            //check if successor has the right child, it cannot have left child for sure
            // if it does have the right child, add it to the left of successorParent.
//		successsorParent
            if(successsor!=deleleNode.right){
                successsorParent.left = successsor.right;
                successsor.right = deleleNode.right;
            }
            return successsor;
        }



    //is bst valid or not
}
*/