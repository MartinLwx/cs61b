package deque;


import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
    private Node<T> sentinel;
    private int size;

    /** Bidirectional LinkedListDeque's node */
    public static class Node<T> {
        private final T item;
        private Node<T> prev;
        private Node<T> next;
        public Node(T x, Node<T> p, Node<T> n) {
            item = x;
            prev = p;
            next = n;
        }
    }

    /** Constructor: initialize the sentinel node */
    public LinkedListDeque() {
        sentinel = new Node<>(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node<T> tmp = new Node<>(item, sentinel, sentinel.next);
        sentinel.next.prev = tmp;
        sentinel.next = tmp;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node<T> tmp = new Node<>(item, sentinel.prev, sentinel);
        sentinel.prev.next = tmp;
        sentinel.prev = tmp;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (!isEmpty()) {
            System.out.print(sentinel.next);
            Node<T> cur = sentinel.next.next;
            while (cur != sentinel) {
                System.out.print(" " + cur.item);
                cur = cur.next;
            }
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            Node<T> tmp = sentinel.next;
            tmp.next.prev = sentinel;
            sentinel.next = tmp.next;
            if (!isEmpty()) {
                size -= 1;
            }
            return tmp.item;
        }
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            Node<T> tmp = sentinel.prev;
            tmp.prev.next = sentinel;
            sentinel.prev = tmp.prev;
            if (!isEmpty()) {
                size -= 1;
            }
            return tmp.item;
        }
    }

    /** get() uses iteration */
    @Override
    public T get(int index) {
        if (isEmpty()) {
            return null;
        } else {
            Node<T> cur = sentinel;
            while (index >= 0) {
                cur = cur.next;
                index -= 1;
            }
            return cur.item;
        }
    }

    private T getHelper(Node<T> cur, int index) {
        if (isEmpty()) {
            return null;
        }
        /* Base case */
        if (index == 0) {
            return cur.item;
        }
        return getHelper(cur.next, index - 1);
    }

    public T getRecursive(int index) {
        return getHelper(sentinel.next, index);
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }
        if (!(otherObject instanceof LinkedListDeque)) {
            return false;
        }
        LinkedListDeque<?> other = (LinkedListDeque<?>) otherObject;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (this.get(i) != other.get(i)) {
                return false;
            }
        }
        return true;
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> pos;

        LinkedListIterator() {
            pos = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return pos == sentinel;
        }

        @Override
        public T next() {
            T item = pos.item;
            pos = pos.next;
            return item;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }
}
