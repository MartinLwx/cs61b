package deque;


public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    /** Bidirectional LinkedListDeque's node*/
    public class Node {
        public T item;
        public Node prev;
        public Node next;
        public Node(T x, Node p, Node n) {
            item = x;
            prev = p;
            next = n;
        }
    }

    /** Constructor: initialize sentinel node */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node tmp = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = tmp;
        sentinel.next = tmp;
        size += 1;
    }

    public void addLast(T item) {
        Node tmp = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = tmp;
        sentinel.prev = tmp;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (!isEmpty()) {
            System.out.print(sentinel.next);
            Node cur = sentinel.next.next;
            while (cur != sentinel) {
                System.out.print(" " + cur.item);
                cur = cur.next;
            }
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            Node tmp = sentinel.next;
            tmp.next.prev = sentinel;
            sentinel.next = tmp.next;
            if (!isEmpty()) {
                size -= 1;
            }
            return tmp.item;
        }
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            Node tmp = sentinel.prev;
            tmp.prev.next = sentinel;
            sentinel.prev = tmp.prev;
            if (!isEmpty()) {
                size -= 1;
            }
            return tmp.item;
        }
    }

    public T get(int index) {
        if (isEmpty()) {
            return null;
        } else {
            Node cur = sentinel;
            while (index >= 0) {
                cur = cur.next;
                index -= 1;
            }
            return cur.item;
        }
    }

    private T getHelper(Node cur, int index) {
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
}
