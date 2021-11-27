package model;

import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * CircularLinkedListTest tests the methods of CircularLinkedList class.
 *
 * @author Jakub Nowak gr 5
 * @version 2.0
 */
public class CircularLinkedListTest {

    /**
     * Circualar linked list to test.
     */
    private static CircularLinkedList<Integer> list;

    /**
     * Method creates new CirculalLinkedList and add three elements to it.
     */
    @BeforeAll
    public static void setUp() {
        list = new CircularLinkedList<>();
        list.add(5);
        list.add(7);
        list.add(10);
    }

    /**
     * Test checks if the previous element of the list is returned correctly.
     */
    @Test
    public void testGetPrev() {
        assertSame(list.getPrev(list.getFirst()), list.getLast(), "Previous elemenent of the first element of the list isn't equal last");
        assertSame(list.getPrev(list.get(1)), list.getFirst(), "Previous element of first element isn't equal first element");
    }

    /**
     * Test checks if the next element of the list is returned correctly.
     */
    @Test
    public void testGetNext() {
        assertSame(list.getNext(list.getLast()), list.getFirst(), "Next elemenent of the lat element of the list isn't equal first");
        assertSame(list.getNext(list.getFirst()), list.get(1), "Next element of first element isn't equal second element");
    }
}
