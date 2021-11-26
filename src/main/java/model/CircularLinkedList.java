package model;

import java.util.LinkedList;

/**
 * Circular linked list represents list where the first element is the next
 * element of the last element and the last element is the previous element of
 * the first element.
 *
 * @author Jakub Nowak gr 5
 * @version 2.0
 */
public class CircularLinkedList<E> extends LinkedList<E> {

    /**
     * Method returns element of a circular linked list at the given index. When
     * index is lower than 0 method returns element from the end of the list.
     * When index is higher or equal size method returns element from the
     * beggining of the list.
     *
     * @param index index of an element
     * @return the element at the given index
     */
    @Override
    public E get(int index) {
        if (index >= 0) {
            return super.get(index % size());
        } else {
            while (index < 0) {
                index += size();
            }
            return super.get(index);
        }
    }

    /**
     * Method returns previous element in the list. Becouse this is circular
     * linked list returning previous element of first element returns last
     * element from the list.
     *
     * @param object object which previous element will be returned.
     * @return previous element
     */
    public E getPrev(E object) {
        return get(super.indexOf(object) - 1);
    }

    /**
     * Method returns next element in the list. Becouse this is circular linked
     * list returning next element of last element returns first element from
     * the list.
     *
     * @param object object which next element will be returned.
     * @return next element
     */
    public E getNext(E object) throws NullPointerException {
        return get(super.indexOf(object) + 1);
    }
}
