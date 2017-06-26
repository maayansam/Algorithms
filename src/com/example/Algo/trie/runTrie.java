package com.example.Algo.trie;

import java.util.List;

/**
 * Created by maayan.s on 4/14/17.
 */
public class runTrie {

    //We will be maintaining a trie. Each node in the trie will contain the following information:
    //1. : Number of times this node has been visited during insertion of the strings.
    //2. : An array of pointers that contains the pointer to the next nodes.  will contain the next node that is reachable using 'a',
    //will contain the next node that is reachable using 'b' and so on. Initially, the pointers will be null.
    //Handling operations:
    //1. Add : We will simply insert the string  in the trie. We will increase the value of the variable
    //         of the nodes that are visited in the process by
    //2. Find : We need to find the number of strings which have been inserted into the trie and have as a prefix. We will start
    // from the root of the trie and traverse it using the string .
    // The value of the variable  stored in the final node ( the node where the traversal ends) is the answer to this operation.
    // This is because all the strings that have  as prefix must have visited this node and no other string can visit this node.


    public static class Node {
        private  int count = 0;
        private Node[] nextChildren;// = new Node[26];

        public Node[] getNextChildren(){
            return nextChildren;
        }

        public int getCount() {
            return count;
        }

        public void addToCount() {
            count++;
        }

        // set my next char
        public Node setAtNextChar(int index) {

            if (nextChildren == null) {
                nextChildren = new Node[26];
            }

            if(nextChildren[index] == null) {
                nextChildren[index] = new Node();
            }
            nextChildren[index].addToCount();
            return nextChildren[index];
        }
    }

    public static class Trie {

        public Node dummy = null;// = new Node[26];

        public Trie(){}
        public void add(String contact) {
            char s;
            int index;

            if(dummy == null){
                dummy = new Node();
            }
            //Node current = dummy;
            if(contact != null && !contact.trim().isEmpty()) {
                dummy.addToCount(); // you will know how many in the dictionary for dummy
                Node current = dummy;
                for(int i=0; i<contact.length(); ++i) {
                    s = contact.charAt(i);
                    index = s - 'a';
                    current = current.setAtNextChar(index);

                    }

                }
            }

        public int find(String prefix) {

                if(dummy.getNextChildren() == null){
                    return 0;
                }
                Node current = dummy;
                for(int i=0; i<prefix.length();++i){
                    char s = prefix.charAt(i);
                    if(current.getNextChildren() == null) {
                        return 0;
                    }

                    int index = s - 'a';
                    current = current.getNextChildren()[index];
                    if(current == null) {
                        return 0;
                    }
                }
                return current.getCount();
            }

    }

    public static void  main(String[] args) {


        Trie trie = new Trie();
        trie.add("abdc");
        trie.add("abtyr");
        trie.add("ghjkl");
        trie.add("glkm");
        System.out.println(trie.find("abt")); //1
        System.out.println(trie.find("g")); //2
        System.out.println(trie.find("lkj"));//0
        System.out.println(trie.find("gjkl"));//0
        System.out.println(trie.find("abd")); //1_

        System.exit(0);
    }
}



