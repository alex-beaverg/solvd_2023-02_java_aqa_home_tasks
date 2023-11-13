package org.example.hospital.util.my_linked_list;

import java.util.*;

public class MyLinkedList1<E> implements Iterable<E> {
    private final List<E> innerList = new LinkedList<>();

    public MyLinkedList1() { }

    public void add(E element) {
        innerList.add(element);
    }

    public void remove(E element) {
        innerList.remove(element);
    }

    public void add(int index, E element) {
        innerList.add(index, element);
    }

    public E get(int index) {
        return innerList.get(index);
    }

    public void remove(int index) {
        innerList.remove(index);
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

    public void addAll(Collection<E> collection) {
        innerList.addAll(collection);
    }

    public void set(int index, E element) {
        innerList.set(index, element);
    }

    public void addAll(int index, Collection<E> collection) {
        innerList.addAll(index, collection);
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
