package com.example.LinkedList;


public class LinkedAnimalsProgram {

    private final static int LIST_HEAD_TAIL_ADDITION = 5;
    private static LinkedList linkedLst = new LinkedList();

    public static void main(String[] args) {

        createRandomAnimalsList(LIST_HEAD_TAIL_ADDITION);
        printList();
        removeAllAndExecute();

        System.exit(0);
    }

    public static void createRandomAnimalsList(int num) {

            // add num to head
            for(int i = 0; i<num; i++) {
                Linkable lk = getRandomLinkableAnimal();
                System.out.println(String.format("add %s to head", lk.getName() ));
                linkedLst.insertAtHead(lk);
            }
            // add num to tail
        for(int i = 0; i<num; i++) {
            Linkable lk = getRandomLinkableAnimal();
            System.out.println(String.format("add %s to tail", lk.getName() ));
            linkedLst.insertAtTail(lk);
        }
    }

    private static Linkable getRandomLinkableAnimal()
    {
        AnimalType randomType = RandomAnimalType.getRandomType();
        LinkableFactory factory = randomType.getFactory();
        Linkable lk = (Linkable)factory.getLinkable();
        return lk;

    }

    private static void printList()
    {
        Linkable curr = linkedLst.getHead();
        if(curr==null) {
            System.out.println("empty list can't printAll");
            return;
        }

        for(Linkable lk : linkedLst) {
            System.out.println(String.format("Item in list is: %s ", lk.getName()));
        }
    }

    private static void removeAllAndExecute()
    {
        if(linkedLst.getSize()==0) {
            System.out.println("empty list can't execute removeAll");
            return;
        }

        Linkable removed = null;
        while(linkedLst.getSize() != 0){

            removed = linkedLst.removeFromHead();
            System.out.println(String.format("removed %s", removed.getName()));
        }


    }


}
