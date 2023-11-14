package org.example.hospital.util.my_linked_list;

import java.util.*;

public class MyLinkedList1<E> implements Iterable<E> {
    private final List<E> innerList = new LinkedList<>();

    public MyLinkedList1() { }

    public void add(E element) {
        innerList.add(element);
    }

    public void add(int index, E element) {
        innerList.add(index, element);
    }

    public void addAll(Collection<E> collection) {
        innerList.addAll(collection);
    }

    public void addAll(int index, Collection<E> collection) {
        innerList.addAll(index, collection);
    }

    public E remove(E element) {
        innerList.remove(element);
        return element;
    }

    public E remove(int index) {
        innerList.remove(index);
        return innerList.get(index);
    }

    public E get(int index) {
        return innerList.get(index);
    }

    public void set(int index, E element) {
        innerList.set(index, element);
    }

    public int size() {
        return innerList.size();
    }

    public boolean contains(E element) {
        return innerList.contains(element);
    }

    public boolean containsAll(Collection<E> collection) {
        return new HashSet<>(innerList).containsAll(collection);
    }

    public boolean isEmpty() {
        return innerList.isEmpty();
    }

    public int indexOf(E element) {
        return innerList.indexOf(element);
    }

    @Override
    public Iterator<E> iterator() {
        return innerList.iterator();
    }
}
