package org.example.hospital.util.my_linked_list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyLinkedList1<E> implements Iterable<E> {
    private final List<E> innerList = new LinkedList<>();

    public MyLinkedList1() { }

    public void add(E element) {
        innerList.add(element);
    }

    public E get(int index) {
        return innerList.get(index);
    }

    public void remove(E element) {
        innerList.remove(element);
    }

    public int size() {
        return innerList.size();
    }

    public boolean contains(E element) {
        return innerList.contains(element);
    }

    @Override
    public Iterator<E> iterator() {
        return innerList.iterator();
    }
}
