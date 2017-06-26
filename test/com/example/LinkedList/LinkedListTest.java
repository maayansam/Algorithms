package com.example.LinkedList;

import java.util.Iterator;
import com.example.LinkedList.LinkedList.LinkableIterator;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class LinkedListTest {

    //@Before every test method import org.junit.before; set up to run before a test
    //@After every test method teardown after every test
    //@BeforeClass before just one time should be static method
    //(expected=nullexception...)
    //performance @Test(timeout=1000) //miliseconds
    //parametrized tests @RunWith(Parameterized.class) //above the class
    //parameterized for method: @Parameters public static Collection testConditions return the paraemters collections return arraya.asList(expectedOutputs
    // // the function that uses the parameters: ) String input; String  expectedOutput; the test should use the variables, constructor local variables
    //each parametrized tests has its own class
    // test suite to run just a few tests

    private LinkedList myList;

    @Before
    public  void Init() {
        myList = new LinkedList();
    }

    @Test
    public void testIterator() throws Exception {

        /*Linkable a1 = getRandomLinkableAnimal();
        myList.insertAtHead(a1);
        Linkable a2 = getRandomLinkableAnimal();
        myList.insertAtHead(a2);
        Linkable a3 = getRandomLinkableAnimal();
        myList.insertAtHead(a3);
        Linkable a4 = getRandomLinkableAnimal();
        myList.insertAtHead(a4);
        Linkable a5 = getRandomLinkableAnimal();
        myList.insertAtHead(a5);
        Linkable[] list = {a5, a4, a3, a2, a1};

        // go over the linked list to test if the iterator iterates right
        LinkableIterator iterator = (LinkableIterator)myList.iterator();
        int i=1;
        Linkable actual = iterator.current();
        while(iterator.hasNext()){
            assertAnimal((Animal)actual, (Animal)list[i]);
            actual = iterator.next();
            i++;
        }*/

        //ask Lior as for how to test this item got confused when can't pproach the first item just the rest of the list

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetHead_insertNull_ThrowsValidException() throws Exception {

        Animal expected = null;
        testGetHeadInsertAtMyListHead(expected);
    }

    @Test
    public void testGetHead_addFirst_addsecond_addthird() throws Exception {

        Elephant expected  = new Elephant("e1");
        testGetHeadInsertAtMyListHead(expected);

        Tiger expect2 = new Tiger("e2");
        testGetHeadInsertAtMyListHead(expect2);

        Elephant exp3 = new Elephant("e3");
        testGetHeadInsertAtMyListHead(exp3);

    }

    private void testGetHeadInsertAtMyListHead(Linkable expected){
        myList.insertAtHead((Linkable)expected);
        Linkable actual = myList.getHead();
        assertAnimal((Animal)actual, (Animal)expected);

    }


    private void assertAnimal(Animal actual, Animal expected){

        if(actual == expected && actual == null) {// expected values
            return;
        }

        assertEquals(actual, expected);
//        assertTrue(actual != null && expected != null);
//        assertEquals(expected.getClass(), actual.getClass() );
//        assertEquals(expected.getInnerNumber(), actual.getInnerNumber());

    }

    private void assertNotEqualAnimal(Animal actual, Animal expected){

        // make sure items are not equal take care of null option
        if((actual == null || expected == null) && actual != expected )
            return;

        // if both null you want to assert
        assertFalse(actual == null && expected == null);

        // one of them should not be equal
        boolean condition = (expected.getClass() != actual.getClass()) || (expected.getInnerNumber()!= actual.getInnerNumber());
        assertTrue(condition);
    }

    @Test
    public void testGetSizeNoItems() throws Exception {

        assertEquals(0, myList.getSize());
    }

    @Test
    public void testGetSizefewAddedItems() throws Exception {

        int size = 3;
        createRandomAnimalsList(size);
        assertEquals(size, myList.getSize());
    }

    private void createRandomAnimalsList(int num) {

        for (int i = 0; i < num; i++) {

            myList.insertAtHead(getRandomLinkableAnimal());
        }
    }
    private Linkable getRandomLinkableAnimal()
    {
        AnimalType randomType = RandomAnimalType.getRandomType();
        LinkableFactory factory = randomType.getFactory();
        Linkable lk = (Linkable)factory.getLinkable();
        return lk;

    }

    @Test
    public void testGetSizeRemovedItems() throws Exception {

        int size = 3;
        createRandomAnimalsList(size);
        int itemsToRemove = 2;
        removeNumberOfItems(itemsToRemove);
        size = size - itemsToRemove;
        assertEquals(size, myList.getSize());

    }

    private void removeNumberOfItems(int num) {

        for (int i = 0; i < num; i++) {
            myList.removeFromHead();
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void insertAtHead_tryInsertNullItem() throws Exception {
        // try to insert null and recieve a valid exception
        Animal expected = null;
        myList.insertAtHead((Linkable)expected);
    }

    @Test
    public void insertAtHead_oneItem() throws Exception {

        // insert one item
        Animal expected = (Animal)getRandomLinkableAnimal();
        myList.insertAtHead((Linkable)expected);

        // check actual head is the expected one:
        Animal head = (Animal)myList.getHead();
        assertAnimal(head, expected);

        //check head and tail are equal (therfore equal to expected)
        Animal tail = (Animal)myList.getTail();
        assertAnimal(tail, expected);

        //make sure size is 1
        int size = myList.getSize();
        assertTrue(size == 1);
    }

    @Test
    public void insertAtHead_2Items() throws Exception {

        // insert one item
        Animal expectedLast = (Animal)getRandomLinkableAnimal();
        myList.insertAtHead((Linkable)expectedLast);

        //insert second item
        Animal expectedFirst = (Animal)getRandomLinkableAnimal();
        myList.insertAtHead((Linkable)expectedFirst);

        // check actual head (last added to the list) is the expected one:
        Animal head = (Animal)myList.getHead();
        assertAnimal(head, expectedFirst);

        //check head and tail are equal (therfore equal to expected)
        Animal tail = (Animal)myList.getTail();
        assertNotEqualAnimal(tail, head);

        // check tail is the first added
        assertAnimal(tail, expectedLast);

        //make sure size is 1
        int size = myList.getSize();
        assertTrue(size == 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void insertAtTail_tryInsertNullItem() throws Exception {
        // try to insert null and recieve a valid exception
        Animal expected = null;
        myList.insertAtTail((Linkable)expected);
    }

    @Test
    public void insertAtTail_oneItem() throws Exception {

        // insert one item
        Animal expected = (Animal)getRandomLinkableAnimal();
        myList.insertAtTail((Linkable)expected);

        // check actual tail is the expected one:
        Animal tail = (Animal)myList.getTail();
        assertAnimal(tail, expected);

        //check head and tail are equal (therfore equal to expected)
        Animal head = (Animal)myList.getHead();
        assertAnimal(head, expected);

        //make sure size is 1
        int size = myList.getSize();
        assertTrue(size == 1);
    }

    @Test
    public void insertAtTail_2Items() throws Exception {

        // insert one item
        Animal expectedFirst = (Animal)getRandomLinkableAnimal();
        myList.insertAtTail((Linkable)expectedFirst);

        //insert second item
        Animal expectedLast = (Animal)getRandomLinkableAnimal();
        myList.insertAtTail((Linkable)expectedLast);

        // check actual tail (first added to the list) is the expected one:
        Animal tail = (Animal)myList.getTail();
        assertAnimal(tail, expectedLast);

        //check head and tail are not equal
        Animal head = (Animal)myList.getHead();
        assertNotEqualAnimal(head, tail);

        // check head is the first actually added
        assertAnimal(head, expectedFirst);

        //make sure size is 2
        int size = myList.getSize();
        assertTrue(size == 2);
    }












    @Test
    public void removeFromHead() throws Exception {


    }

}