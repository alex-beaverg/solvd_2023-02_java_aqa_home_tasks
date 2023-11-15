package org.example.hospital.util.my_linked_list;

import java.util.Iterator;

public class MyLinkedList<E> implements Iterable<E> {
    int size = 0;
    Node<E> first;
    Node<E> last;

    public MyLinkedList() { }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public void add(E element) {
        final Node<E> last = this.last;
        final Node<E> newNode = new Node<>(last, element, null);
        this.last = newNode;
        if (last == null)
            first = newNode;
        else
            last.next = newNode;
        size++;
    }

    public void add(int index, E element) {
        checkPositionIndex(index);
        if (index == size)
            linkLast(element);
        else
            linkBefore(element, node(index));
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> current = first; current != null; current = current.next) {
                if (current.item == null) {
                    unlink(current);
                    return true;
                }
            }
        } else {
            for (Node<E> current = first; current != null; current = current.next) {
                if (o.equals(current.item)) {
                    unlink(current);
                    return true;
                }
            }
        }
        return false;
    }

    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> current = node(index);
        E oldVal = current.item;
        current.item = element;
        return oldVal;
    }

    public void clear() {
        for (Node<E> current = first; current != null; ) {
            Node<E> next = current.next;
            current.item = null;
            current.next = null;
            current.prev = null;
            current = next;
        }
        first = last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(Object object) {
        int index = 0;
        if (object == null) {
            for (Node<E> current = first; current != null; current = current.next) {
                if (current.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> current = first; current != null; current = current.next) {
                if (object.equals(current.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException("[IndexOutOfBoundsException]: Index " + index + " is out of range from 0 to " + size + "!");
        }
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("[IndexOutOfBoundsException]: Index " + index + " is out of range from 0 to " + size + "!");
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    private void linkBefore(E e, Node<E> succ) {
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
    }

    private E unlink(Node<E> current) {
        final E element = current.item;
        final Node<E> next = current.next;
        final Node<E> prev = current.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            current.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            current.next = null;
        }

        current.item = null;
        size--;
        return element;
    }

    private Node<E> node(int index) {
        Node<E> current;
        if (index < (size >> 1)) {
            current = first;
            for (int i = 0; i < index; i++)
                current = current.next;
        } else {
            current = last;
            for (int i = size - 1; i > index; i--)
                current = current.prev;
        }
        return current;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E item = current.item;
                    current = current.next;
                    return item;
                }
                return null;
            }
        };
    }
}
